package rs.elfak.mosis.markoilic.myplaces;

import android.view.View;

public class MyPlace {
    String name;
    String description;
    String longitude;
    String latitude;
    int ID;

    public int getID() {
        return ID;
    }

    public void setId(int ID){
        this.ID = ID;
    }


    public String getLongitude(){
        return longitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    public String getLatitude(){
        return latitude;
    }

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }


    public MyPlace(String nme, String desc) {
        this.name = nme;
        this.description = desc;
    }

    public MyPlace(String nme){
        this(nme,"");
    }

    public String getName(){
        return name;
    }
    public String getDesc(){
        return description;
    }
    public void setName(String nme){
        this.name = nme;
    }
    public void setDescription(String desc){
        this.description = desc;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
