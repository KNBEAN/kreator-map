package data.implementations;

import data.model.LocationTag;

public class LocationTagEntity implements LocationTag {
    private int location_id;
    private String tag;

    public LocationTagEntity(String tag, int locationId){
        this.tag = tag;
        this.location_id = locationId;
    }

    @Override
    public String getTag() {
        return tag;
    }

    public int getLocation_id() {
        return location_id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == LocationTagEntity.class){
            obj = (LocationTagEntity) obj;
            if (((LocationTagEntity) obj).getTag().equals(tag) && ((LocationTagEntity) obj).getLocation_id()==(location_id)) return true;
        }
        return false;
    }
}
