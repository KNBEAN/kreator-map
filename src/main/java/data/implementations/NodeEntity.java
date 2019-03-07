package data.implementations;

import data.IdGenerator;
import data.model.Node;

import java.util.Objects;

public class NodeEntity implements Node {

    private int id;
    private int floor;
    private int x;
    private int y;
    private int locationID;
    private boolean hardToReach;

    public NodeEntity(int x, int y, int floor, int locationId, boolean hardToReach) {
        this(IdGenerator.getId(), x, y, floor, locationId, hardToReach);
    }

    public NodeEntity(int x, int y, int floor) {
        this(IdGenerator.getId(), x, y, floor, -1, false);
    }

    public NodeEntity(int floor, int x, int y, boolean hardToReach) {
        this(IdGenerator.getId(), x, y, floor, -1, hardToReach);
    }

    public NodeEntity(int id, int x, int y, int floor, int locationID, boolean hardToReach) {
        this.id = id;
        this.floor = floor;
        this.x = x;
        this.y = y;
        this.locationID = locationID;
        this.hardToReach = hardToReach;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getFloor() {
        return floor;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getLocationID() {
        return locationID;
    }

    @Override
    public boolean getHardToReach() {
        return hardToReach;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != NodeEntity.class) return false;
        NodeEntity objectNode = (NodeEntity) obj;
        if (objectNode.getX() == x
                && objectNode.getFloor() == floor
                && objectNode.getY() == y
                && objectNode.getLocationID() == locationID
                && objectNode.getHardToReach() == hardToReach) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, floor, x, y, locationID, hardToReach);
    }

    @Override
    public String toString() {
        return " ID: " + id + "\n" +
                " FloorEntity: " + floor + "\n" +
                " X: " + x + "\n" +
                " Y: " + y + "\n" +
                " LocationEntity ID: " + locationID + "\n" +
                " Hard to reach: " + hardToReach;
    }
}
