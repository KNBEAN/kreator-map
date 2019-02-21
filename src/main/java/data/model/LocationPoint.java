package data.model;

/**
 * This interface represents object of LocationPoint. It combines Node with Location
 */
public interface LocationPoint {

    /**
     * ID of place on the map
     * @return
     */
    int getLocationID();
    /**
     * ID of unique point on the map
     * @return ID
     */
    int getMapPointID();

}
