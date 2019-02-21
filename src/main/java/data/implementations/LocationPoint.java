package data.implementations;

import data.model.Location;
import data.model.Node;

public class LocationPoint implements data.model.LocationPoint {

    private int locationID;
    private int mapPointID;


    public LocationPoint(int locationID, int mapPointID) {
        this.locationID = locationID;
        this.mapPointID = mapPointID;
    }

    public LocationPoint(Node node, Location location) {
        this.mapPointID = node.getId();
        this.locationID = location.getId();
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
        if (obj.getClass() == LocationPoint.class) {
            LocationPoint locationPoint = (LocationPoint) obj;
            if (locationPoint.getLocationID() == locationID && locationPoint.getMapPointID() == mapPointID) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Map Point ID: " + mapPointID + "\n" +
                "Location ID: " + locationID;
    }
}
