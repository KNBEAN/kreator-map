
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import data.JsonsParser;
import data.implementations.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    private static String edgeList;
    private static String floorList;
    private static String locationList;
    private static String locationPointsList;
    private static String nodeList;
    private static String quickAccessList;
    private static String tagList;
    private static String floorListDamaged;


    @BeforeAll
    static void initializePaths(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        edgeList = classLoader.getResource("test_jsons" +System.getProperty("file.separator")+"edgeList.json").getPath();
        floorList = classLoader.getResource("test_jsons" +System.getProperty("file.separator")+"floorList.json").getPath();
        locationList = classLoader.getResource("test_jsons" +System.getProperty("file.separator")+"locationList.json").getPath();
        locationPointsList = classLoader.getResource("test_jsons" +System.getProperty("file.separator")+"locationPointsList.json").getPath();
        nodeList = classLoader.getResource("test_jsons" +System.getProperty("file.separator")+"nodeList.json").getPath();
        quickAccessList = classLoader.getResource("test_jsons" +System.getProperty("file.separator")+"quickAccessList.json").getPath();
        tagList = classLoader.getResource("test_jsons" +System.getProperty("file.separator")+"tagList.json").getPath();
        floorListDamaged = classLoader.getResource("test_jsons" +System.getProperty("file.separator")+"floorListDamaged.json").getPath();
    }

    @Test
    void wrongFilePathPassed(){
        assertThrows(FileNotFoundException.class,()->{
            ArrayList<EdgeEntity> edges = JsonsParser.getEntityArrayList("edgeList.json",new TypeToken<List<EdgeEntity>>(){}.getType());
        });
    }

    @Test
    void shouldReturnAllEdges() throws FileNotFoundException {
        EdgeEntity edge1 = new EdgeEntity(1,1,2,100);
        EdgeEntity edge2 = new EdgeEntity(2,2,1,100);
        EdgeEntity edge3 = new EdgeEntity(3,3,1,50);
        EdgeEntity edge4 = new EdgeEntity(4,1,3,50);

        ArrayList<EdgeEntity> edges = new ArrayList<>(Arrays.asList(edge1,edge2,edge3,edge4));

        assertEquals(edges,JsonsParser.getEntityArrayList(edgeList,new TypeToken<List<EdgeEntity>>(){}.getType()));
    }

    @Test
    void shouldReturnAllFloors() throws FileNotFoundException {
        FloorEntity floor0 = new FloorEntity(0,"parter");
        FloorEntity floor1 = new FloorEntity(1,"1 piętro");

        ArrayList<FloorEntity> floors = new ArrayList<>(Arrays.asList(floor0,floor1));
        assertEquals(floors,JsonsParser.getEntityArrayList(floorList,new TypeToken<List<FloorEntity>>(){}.getType()));
    }

    @Test
    void shouldReturnAllLocations() throws FileNotFoundException {
        LocationEntity location1 = new LocationEntity(1,"SOR");
        LocationEntity location2 = new LocationEntity(2,"entry");

        ArrayList<LocationEntity> locations = new ArrayList<>(Arrays.asList(location1,location2));
        assertEquals(locations,JsonsParser.getEntityArrayList(locationList,new TypeToken<List<LocationEntity>>(){}.getType()));
    }

    @Test
    void shouldReturnAllNodes() throws FileNotFoundException {
        NodeEntity node1 = new NodeEntity(1,100,100,0,1,false);
        NodeEntity node2 = new NodeEntity(2,200,100,1,-1,false);
        NodeEntity node3 = new NodeEntity(3,100,200,1,2,false);

        ArrayList<NodeEntity> nodes = new ArrayList<>(Arrays.asList(node1,node2,node3));
        assertEquals(nodes,JsonsParser.getEntityArrayList(nodeList,new TypeToken<List<NodeEntity>>(){}.getType()));
    }

    @Test
    void shouldReturnALLLocationPointsList() throws FileNotFoundException {
        LocationPointEntity locationPoint1 = new LocationPointEntity(1,1);
        LocationPointEntity locationPoint2 = new LocationPointEntity(2,3);

        ArrayList<LocationPointEntity> locationPoints = new ArrayList<>(Arrays.asList(locationPoint1,locationPoint2));
        assertEquals(locationPoints,JsonsParser.getEntityArrayList(locationPointsList,new TypeToken<List<LocationPointEntity>>(){}.getType()));
    }

    @Test
    void shouldReturnAllQuickAccessList() throws FileNotFoundException {
        QuickAccessLocationEntity quickAccessLocation = new QuickAccessLocationEntity(1,2,1);
        QuickAccessLocationEntity quickAccessLocation1 = new QuickAccessLocationEntity(2,1,1);
        ArrayList<QuickAccessLocationEntity> quickAccessLocations = new ArrayList<>(Arrays.asList(quickAccessLocation,quickAccessLocation1));

        assertEquals(quickAccessLocations,JsonsParser.getEntityArrayList(quickAccessList,new TypeToken<List<QuickAccessLocationEntity>>(){}.getType()));
    }

    @Test
    void shouldReturnAllTagList() throws FileNotFoundException {
        LocationTagEntity locationTagEntity = new LocationTagEntity(1,"sor");
        LocationTagEntity locationTagEntity2 = new LocationTagEntity(2,"entry");
        ArrayList<LocationTagEntity> locationTagEntities = new ArrayList<>(Arrays.asList(locationTagEntity,locationTagEntity2));

        assertEquals(locationTagEntities,JsonsParser.getEntityArrayList(tagList,new TypeToken<List<LocationTagEntity>>(){}.getType()));
    }

    @Test
    void badFormattedJsonLoading(){
        assertThrows(JsonSyntaxException.class,()->{
            JsonsParser.getEntityArrayList(floorListDamaged,new TypeToken<List<FloorEntity>>(){}.getType());
        });
    }
}
