package data.implementations;

import data.Id_Generator;

public class QuickAccessLocation implements data.model.Quick_Access_Location {
    private int id;
    private int location_id;
    private int quick_access_type;

    public QuickAccessLocation(int location_id, int quick_access_type) {
        this.id = Id_Generator.getId();
        this.location_id = location_id;
        this.quick_access_type = quick_access_type;
    }

    public QuickAccessLocation(int id, int location_id, int quick_access_type) {
        this.id = id;
        this.location_id = location_id;
        this.quick_access_type = quick_access_type;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getLocationID() {
        return location_id;
    }

    @Override
    public int getQuickAccessType() {
        return quick_access_type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == QuickAccessLocation.class) {
            if (((QuickAccessLocation) obj).getID() == id && ((QuickAccessLocation) obj).getLocationID() == (location_id)
                    && ((QuickAccessLocation) obj).getQuickAccessType() == (quick_access_type)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Location ID: " + location_id + "\n" +
                "Quick access type: " + quick_access_type;
    }
}
