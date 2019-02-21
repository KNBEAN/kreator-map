package data.model;

/**
 * This interface represents object of Location_Point. It combines Node with Location
 */
public interface Location_Point {

    /**
     * ID of unique point on the map
     * @return ID
     */
    int getMapPointID();

    /**
     * ID of place on the map
     * @return
     */
    int getLocationID();
}
