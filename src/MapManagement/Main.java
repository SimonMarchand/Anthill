import MapManagement.Map;
import MapManagement.MapReader;

/**
 * Created by felix on 03/01/17.
 */
public class Main {

    public static void main(String args[]) {


        Map map = MapReader.createMap("E:/Cours/APO/Projet_Anthill/Anthill_local/src/MapManagement/Map_test.txt");
        map.Read();

        map.printCell(0,5);
        map.printCell(2,3);
        map.printCell(4,7);

    }
}
