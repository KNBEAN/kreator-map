package data.implementations;

import data.IdGenerator;
import data.model.QuickAccessLocation;

public class QuickAccessLocationEntity implements QuickAccessLocation {
    private int id;
    private int location_id;
    private int quick_access_type;

    public QuickAccessLocationEntity(int location_id, int quick_access_type) {
        this.id = IdGenerator.getId();
        this.location_id = location_id;
        this.quick_access_type = quick_access_type;
    }

    public QuickAccessLocationEntity(int id, int location_id, int quick_access_type) {
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
        if (obj.getClass() == QuickAccessLocationEntity.class) {
            if (((QuickAccessLocationEntity) obj).getID() == id && ((QuickAccessLocationEntity) obj).getLocationID() == (location_id)
                    && ((QuickAccessLocationEntity) obj).getQuickAccessType() == (quick_access_type)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "LocationEntity ID: " + location_id + "\n" +
                "Quick access type: " + quick_access_type;
    }
}
