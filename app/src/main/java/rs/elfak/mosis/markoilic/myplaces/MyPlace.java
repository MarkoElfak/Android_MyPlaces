package rs.elfak.mosis.markoilic.myplaces;

import android.view.View;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MyPlace {
    public String name;
    public String description;
    public String longitude;
    public String latitude;
    @Exclude
    public String key;        // key dodat od strane Firebase pa ga Excludujemo/IgnoreExtra
    public MyPlace(){}



//    public String getLongitude(){
//        return longitude;
//    }
//
//    public void setLongitude(String longitude){
//        this.longitude = longitude;
//    }
//
//    public String getLatitude(){
//        return latitude;
//    }
//
//    public void setLatitude(String latitude){
//        this.latitude = latitude;
//    }


    public MyPlace(String nme, String desc) {
        this.name = nme;
        this.description = desc;
    }

    public MyPlace(String nme){
        this(nme,"");
    }

//    public String getName(){
//        return name;
//    }
//    public String getDesc(){
//        return description;
//    }
//    public void setName(String nme){
//        this.name = nme;
//    }
//    public void setDescription(String desc){
//        this.description = desc;
//    }

    @Override
    public String toString(){
        return this.name;
    }

}
