package data.implementations;

import data.Id_Generator;

public class Node implements data.model.Node {

    private int id;
    private int floor;
    private int x;
    private int y;
    private int locationID;
    private boolean hardToReach;

    public Node(int x, int y, int floor, int locationId, boolean hardToReach){
        id = Id_Generator.getId();
        this.x = x;
        this.y = y;
        this.floor = floor;
        this.locationID = locationId;
        this.hardToReach = hardToReach;
    }
    public Node(int x, int y, int floor){
        id = Id_Generator.getId();
        this.x = x;
        this.y = y;
        this.floor = floor;
        this.locationID = -1;
        this.hardToReach = false;
    }

    public Node(int floor, int x, int y, boolean hardToReach) {
        this.floor = floor;
        this.x = x;
        this.y = y;
        this.locationID = -1;
        this.hardToReach = hardToReach;
    }

    public Node(int id, int x, int y, int floor, int locationID, boolean hardToReach) {
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
        if (obj.getClass() != Node.class) return false;
        Node objectNode = (Node) obj;
        if (objectNode.getX() == x
                && objectNode.getFloor() == floor
                && objectNode.getY() == y
                && objectNode.getLocationID() == locationID
                && objectNode.getHardToReach() == hardToReach) return true;
        return false;
    }

    @Override
    public String toString() {
        return " ID: " + id + "\n" +
                " Floor: "+ floor + "\n" +
                " X: " + x + "\n" +
                " Y: " + y + "\n" +
                " Location ID: "+ locationID + "\n" +
                " Hard to reach: " + hardToReach;
    }
}