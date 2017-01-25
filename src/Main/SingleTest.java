package Main;

import Cell.AnthillCell;
import MapManagement.Map;
import MapManagement.MapReader;

import java.util.Scanner;

/**
 * Created by felix on 25/01/17.
 */
public class SingleTest extends Main {

    public static void main(String args[]) {
        Map map = initMap(getMapName());

        setVariables();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Utiliser les senseurs ? o/n");
        boolean confirmation = scanner.nextLine().charAt(0) == 'o';

        map.setBehaviours(confirmation);

        int nbIterations = Main.runTest(map);
        System.out.println("Historique des phéromones :");
        System.out.println();
        map.printPheromonesHistory();
        System.out.println("Nombre d'itérations : " + nbIterations);
    }

}
