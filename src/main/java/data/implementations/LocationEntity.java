package data.implementations;

import data.IdGenerator;
import data.model.Location;

import java.util.Objects;

public class LocationEntity implements Location {

    private int id;
    private String name;
    private String description;

    public LocationEntity(String name, String description) {
        this(IdGenerator.getId(),name,description);
    }

    public LocationEntity(String name) {
        this(IdGenerator.getId(),name,null);
    }

    public LocationEntity(int id, String name) {
        this(id,name,null);
    }

    public LocationEntity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return " ID: " + id + "\n" +
                " Name: " + name + "\n" +
                " Description: " + description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == LocationEntity.class){
            obj = (LocationEntity) obj;
            if (((LocationEntity) obj).getId()==id && ((LocationEntity) obj).getName().equals(name)) return true;
        }
        return false;
    }
}
