package data.implementations;

import data.model.Floor;

import java.util.Objects;

public class FloorEntity implements Floor {
    private int floorNumber;
    private String floorName;
    private String imagePath;

    public FloorEntity(int floorNumber, String floorName) {
        this(floorNumber,floorName,null);
    }

    public FloorEntity(int floorNumber, String floorName, String imagePath) {
        this.floorNumber = floorNumber;
        this.floorName = floorName;
        this.imagePath = imagePath;
    }


    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public int getFloors() {
        return floorNumber;
    }

    @Override
    public String floorName(int floor) {
        return floorName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floorNumber, floorName, imagePath);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == FloorEntity.class){
            obj = (FloorEntity) obj;
            if (((FloorEntity) obj).getFloors()==floorNumber && ((FloorEntity) obj).getFloorName().equals(floorName)) return true;
        }
        return false;
    }

    public String getFloorName() {
        return floorName;
    }

    @Override
    public String toString() {
        return floorName;

    }

    public String getFloorInfo(){
        return "FloorEntity: " + getFloors() + "\n" +
                "FloorEntity name: " + getFloorName() + "\n" +
                "Image path: " + getImagePath();
    }
}
