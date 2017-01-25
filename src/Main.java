import MapManagement.Map;
import MapManagement.MapReader;
import MapManagement.Map_Frame;

/**
 * Created by felix on 03/01/17.
 */
public class Main {

    public static void main(String args[]) {


        Map map = MapReader.createMap("map1.txt");
        Map_Frame mapFrame = new Map_Frame(map);



    }
}
