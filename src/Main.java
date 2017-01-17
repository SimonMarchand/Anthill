import Ant.Ant;
import Cell.AnthillCell;
import MapManagement.Map;
import MapManagement.MapReader;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by felix on 03/01/17.
 */
public class Main {

    public static void main(String args[]) {
        Map map = MapReader.createMap("Map_test.txt");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Pour choisir un comportement sans senseurs, tapez 1. " +
                "\nPour choisir un comportement avec senseurs taper 2.");
        int i = scanner.nextInt();
        while (i != 1 && i != 2) {
            System.out.println("Veuillez saisir une valeur valide.");
            i = scanner.nextInt();
        }

        System.out.println("Choisissez le nombre de fourmis par fourmillière : ");
        i = scanner.nextInt();
        while (i < 0) {
            System.out.println("Veuillez saisir une valeur valide.");
            i = scanner.nextInt();
        }

        AnthillCell.setAntsQuantity(i);

        map.setBehaviours(i == 2);

        int nbIterations = runTest(map);
        System.out.println("Nombre d'itérations : " + nbIterations);
        System.out.println("Historique des phéromones :");
        System.out.println();
        map.printPheromonesHistory();
    }

    public static int runTest(Map map) {
        int i = 0;
        while (map.getFoodLeft() > 0) {
            map.runIteration();
            i++;
        }

        return i;
    }
}
