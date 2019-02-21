
import com.google.gson.reflect.TypeToken;
import data.JsonsParser;
import data.implementations.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    private ClassLoader classLoader = getClass().getClassLoader();

    private String edgeList = classLoader.getResource("test_jsons"+System.getProperty("file.separator")+"edgeList.json").getPath();
    private String floorList = classLoader.getResource("test_jsons"+System.getProperty("file.separator")+"floorList.json").getPath();
    private String locationList = classLoader.getResource("test_jsons"+System.getProperty("file.separator")+"locationList.json").getPath();
    private String locationPointsList = classLoader.getResource("test_jsons"+System.getProperty("file.separator")+"locationPointsList.json").getPath();
    private String nodeList = classLoader.getResource("test_jsons"+System.getProperty("file.separator")+"nodeList.json").getPath();
    private String quickAccessList = classLoader.getResource("test_jsons"+System.getProperty("file.separator")+"quickAccessList.json").getPath();
    private String tagList = classLoader.getResource("test_jsons"+System.getProperty("file.separator")+"tagList.json").getPath();


    @Test
    void shouldThrowError(){
        assertThrows(FileNotFoundException.class,()->{
            ArrayList<Edge> edges = JsonsParser.getEntityArrayList("edgeList.json",new TypeToken<List<Edge>>(){}.getType());
        });
    }

    @Test
    void shouldNotThrowError(){
        assertDoesNotThrow(()->{
            JsonsParser.getEntityArrayList(edgeList,new TypeToken<List<Edge>>(){}.getType());
            JsonsParser.getEntityArrayList(floorList,new TypeToken<List<Floor>>(){}.getType());
            JsonsParser.getEntityArrayList(locationList,new TypeToken<List<Location>>(){}.getType());
            JsonsParser.getEntityArrayList(locationPointsList,new TypeToken<List<LocationPoint>>(){}.getType());
            JsonsParser.getEntityArrayList(nodeList,new TypeToken<List<Node>>(){}.getType());
            JsonsParser.getEntityArrayList(quickAccessList,new TypeToken<List<QuickAccessLocation>>(){}.getType());
            JsonsParser.getEntityArrayList(tagList,new TypeToken<List<LocationTag>>(){}.getType());
        });
    }

    @Test
    void shouldReturnAllEdges() throws FileNotFoundException {
        Edge edge1 = new Edge(1,1,2,100);
        Edge edge2 = new Edge(2,2,1,100);
        Edge edge3 = new Edge(3,3,1,50);
        Edge edge4 = new Edge(4,1,3,50);

        ArrayList<Edge> edges = new ArrayList<>(Arrays.asList(edge1,edge2,edge3,edge4));

        assertEquals(edges,JsonsParser.getEntityArrayList(edgeList,new TypeToken<List<Edge>>(){}.getType()));
    }

    @Test
    void shouldReturnAllFloors() throws FileNotFoundException {
        Floor floor0 = new Floor(0,"parter");
        Floor floor1 = new Floor(1,"1 piętro");

        ArrayList<Floor> floors = new ArrayList<>(Arrays.asList(floor0,floor1));
        assertEquals(floors,JsonsParser.getEntityArrayList(floorList,new TypeToken<List<Floor>>(){}.getType()));
    }

    @Test
    void shouldReturnAllLocations() throws FileNotFoundException {
        Location location1 = new Location(1,"SOR");
        Location location2 = new Location(2,"entry");

        ArrayList<Location> locations = new ArrayList<>(Arrays.asList(location1,location2));
        assertEquals(locations,JsonsParser.getEntityArrayList(locationList,new TypeToken<List<Location>>(){}.getType()));
    }

    @Test
    void shouldReturnAllNodes() throws FileNotFoundException {
        Node node1 = new Node(1,100,100,0,1,false);
        Node node2 = new Node(2,200,100,1,-1,false);
        Node node3 = new Node(3,100,200,1,2,false);

        ArrayList<Node> nodes = new ArrayList<>(Arrays.asList(node1,node2,node3));
        assertEquals(nodes,JsonsParser.getEntityArrayList(nodeList,new TypeToken<List<Node>>(){}.getType()));
    }

    @Test
    void shouldReturnALLLocationPointsList() throws FileNotFoundException {
        LocationPoint locationPoint1 = new LocationPoint(1,1);
        LocationPoint locationPoint2 = new LocationPoint(2,3);

        ArrayList<LocationPoint> locationPoints = new ArrayList<>(Arrays.asList(locationPoint1,locationPoint2));
        assertEquals(locationPoints,JsonsParser.getEntityArrayList(locationPointsList,new TypeToken<List<LocationPoint>>(){}.getType()));
    }

    @Test
    void shouldReturnAllQuickAccessList() throws FileNotFoundException {
        QuickAccessLocation quickAccessLocation = new QuickAccessLocation(1,2,1);
        QuickAccessLocation quickAccessLocation1 = new QuickAccessLocation(2,1,1);
        ArrayList<QuickAccessLocation> quickAccessLocations = new ArrayList<>(Arrays.asList(quickAccessLocation,quickAccessLocation1));

        assertEquals(quickAccessLocations,JsonsParser.getEntityArrayList(quickAccessList,new TypeToken<List<QuickAccessLocation>>(){}.getType()));
    }
}
