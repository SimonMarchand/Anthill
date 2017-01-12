import MapManagement.Map;
import MapManagement.MapReader;

import java.nio.file.Paths;

/**
 * Created by felix on 03/01/17.
 */
public class Main {

    public static void main(String args[]) {
        Map map = MapReader.createMap("Map_test.txt");
        map.Read();

        map.printCell(0,5);
        map.printCell(2,3);
        map.printCell(4,7);
    }
}
