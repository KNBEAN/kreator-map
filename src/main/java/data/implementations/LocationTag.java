package data.implementations;

public class LocationTag implements data.model.LocationTag {
    private int location_id;
    private String tag;

    public LocationTag(String tag, int locationId){
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
        if (obj.getClass() == LocationTag.class){
            obj = (LocationTag) obj;
            if (((LocationTag) obj).getTag().equals(tag) && ((LocationTag) obj).getLocation_id()==(location_id)) return true;
        }
        return false;
    }
}
