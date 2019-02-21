package data.implementations;

import data.model.Location;
import data.model.Node;

public class Location_Point implements data.model.Location_Point {

    private int mapPointID;
    private int locationID;


    public Location_Point(int mapPointID, int locationID) {
        this.mapPointID = mapPointID;
        this.locationID = locationID;
    }

    public Location_Point(Node node, Location location){
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
}
