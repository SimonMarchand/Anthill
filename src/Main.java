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

        if (i == 1)
            map.setUseSensors(false);
        else
            map.setUseSensors(true);

        i = 0;
        while (map.getFoodLeft() > 0) {
            for (AnthillCell anthill : map.getAnthills()) {
                for (Ant ant : anthill.getAnts()) {
                    ant.move();
                    System.out.println("Position fourmi : x:" + ant.getPosition().getX() + " y:" + ant.getPosition().getY());
                }
            }
            i++;
//            System.out.println("Nourriture restante : " + map.getFoodLeft());
        }

        System.out.println("Nombre d'itérations : " + i);
    }
}
