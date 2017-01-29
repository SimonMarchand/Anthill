package Main;

import Cell.AnthillCell;
import MapManagement.Map;
import MapManagement.MapReader;
import MapManagement.Map_Frame;

import java.util.Scanner;


public class SingleTest extends Main {

    public static void main(String args[]) {
        setVariables();

        Map map = initMap(getMapName());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Utiliser les senseurs ? o/n");
        boolean confirmation = scanner.nextLine().charAt(0) == 'o';

        map.setBehaviours(confirmation);

        Main.runTestWithFrame(map);
}

}
