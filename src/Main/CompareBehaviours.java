package Main;

import MapManagement.Map;
import MapManagement.MapReader;

import java.util.Scanner;


public class CompareBehaviours extends Main {
    private static int NB_ITERATIONS = 20;

    /**
     * Permet de faire tourner un certain nombre de tests avec chaque
     * type de comportement pour comparer le nombre d'itérations
     *
     * @param args
     */
    public static void main(String args[]) {

        setVariables();

        String mapName = getMapName();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nombre d'itérations que vous souhaitez exécuter :");
        int nbIterations = scanner.nextInt();
        while (nbIterations <= 0) {
            System.out.println("Veuillez saisir une valeur supérieure à 0.");
            nbIterations = scanner.nextInt();
        }

        NB_ITERATIONS = nbIterations;

        float vanilliaIterationsAverage = 0;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            Map map = MapReader.createMap(mapName);
            map.setBehaviours(false);
            vanilliaIterationsAverage += Main.runTest(map);
            System.out.println("Simulations effectuées : " + (i + 1) + "/" + 2 * NB_ITERATIONS);
        }
        vanilliaIterationsAverage = vanilliaIterationsAverage / NB_ITERATIONS;

        float sensorsIterationsAverage = 0;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            Map map = MapReader.createMap(mapName);
            map.setBehaviours(true);
            sensorsIterationsAverage += Main.runTest(map);
            System.out.println("Simulations effectuées : " + (i + 1 + NB_ITERATIONS) + "/" + 2 * NB_ITERATIONS);
        }
        sensorsIterationsAverage = sensorsIterationsAverage / NB_ITERATIONS;

        System.out.println("\nNombre d'itérations moyen sans senseurs : " + vanilliaIterationsAverage);
        System.out.println("Nombre d'itérations moyen avec senseurs : " + sensorsIterationsAverage);

    }
}
