package rs.elfak.mosis.markoilic.myplaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyPlacesList extends AppCompatActivity {

    //ArrayList<String> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent i = new Intent(MyPlacesList.this, EditMyPlaceActivity.class);
            startActivityForResult(i, NEW_PLACE);
        });

        ListView myPlacesList = (ListView)findViewById(R.id.my_places_list);
        myPlacesList.setAdapter(new ArrayAdapter<MyPlace>(this, android.R.layout.simple_list_item_1, MyPlacesData.getInstance().getMyPlaces()));
        myPlacesList.setOnItemClickListener(new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i , long l) {
                Bundle positionBundle = new Bundle();
                positionBundle.putInt("position", i);
                Intent intent = new Intent (MyPlacesList.this, ViewMyPlaceActivity.class);
                intent.putExtras(positionBundle);
                startActivity(intent);
            }
        });
        myPlacesList.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) contextMenuInfo;
                MyPlace place = MyPlacesData.getInstance().getPlace(info.position);
                contextMenu.setHeaderTitle(place.name);
                contextMenu.add(0, 1, 1, "View place");
                contextMenu.add(0, 2, 2, "Edit place");
                contextMenu.add(0, 3, 3, "Delete place");
                contextMenu.add(0, 4, 4, "Show on Map");
            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Bundle positionBundle = new Bundle();
        positionBundle.putInt("position", info.position);
        Intent i = null;
        if(item.getItemId() == 1)
        {
            i = new Intent(this, ViewMyPlaceActivity.class);
            i.putExtras(positionBundle);
            startActivity(i);
        }
        else if(item.getItemId() == 2)
        {
            i = new Intent(this, EditMyPlaceActivity.class);
            i.putExtras(positionBundle);
            startActivityForResult(i, 1);
        }
        else if(item.getItemId() == 3)
        {
            MyPlacesData.getInstance().deletePlace(info.position);
            setList();
        }
        else if(item.getItemId() == 4) {
            i = new Intent(this, MyPlacesMapsActivity.class);
            i.putExtra("state", MyPlacesMapsActivity.CENTER_PLACE_ON_MAP);
            MyPlace place = MyPlacesData.getInstance().getPlace(info.position);
            i.putExtra("lat", place.latitude);
            i.putExtra("lon", place.longitude);
            startActivityForResult(i, 2);
        }
        return super.onContextItemSelected(item);
    }

    private void setList(){
        ListView myPlacesList = (ListView)findViewById(R.id.my_places_list);
        myPlacesList.setAdapter(new ArrayAdapter<MyPlace>( this, android.R.layout.simple_list_item_1, MyPlacesData.getInstance().getMyPlaces()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_places_list, menu);
        return true;
    }

    static int NEW_PLACE = 1;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if( id == R.id.show_map_item ) {
            Toast.makeText(this, "Show Map!", Toast.LENGTH_SHORT).show();
        } else if( id == R.id.new_place_item )
        {
            Intent i = new Intent(this, EditMyPlaceActivity.class);
            startActivityForResult(i, NEW_PLACE);}
        else if( id == R.id.about_item ) {
            Intent i = new Intent(this, About.class);
            startActivity(i);
        } else if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "New place added", Toast.LENGTH_SHORT).show();
        }
    }
}