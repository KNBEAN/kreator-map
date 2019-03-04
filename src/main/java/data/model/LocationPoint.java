package data.model;

/**
 * This interface represents object of LocationPointEntity. It combines NodeEntity with LocationEntity
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
