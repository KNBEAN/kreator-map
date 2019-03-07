package data.implementations;

import data.model.Location;
import data.model.Node;
import data.model.LocationPoint;

import java.util.Objects;

public class LocationPointEntity implements LocationPoint {

    private int locationID;
    private int mapPointID;


    public LocationPointEntity(int locationID, int mapPointID) {
        this.locationID = locationID;
        this.mapPointID = mapPointID;
    }

    public LocationPointEntity(Node node, Location location) {
        this(location.getId(),node.getId());
    }

    @Override
    public int getMapPointID() {
        return mapPointID;
    }

    @Override
    public int getLocationID() {
        return locationID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == LocationPointEntity.class) {
            LocationPointEntity locationPoint = (LocationPointEntity) obj;
            if (locationPoint.getLocationID() == locationID && locationPoint.getMapPointID() == mapPointID) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationID, mapPointID);
    }

    @Override
    public String toString() {
        return "Map Point ID: " + mapPointID + "\n" +
                "LocationEntity ID: " + locationID;
    }
}
