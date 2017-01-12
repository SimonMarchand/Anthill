import MapManagement.Map;
import MapManagement.MapReader;

/**
 * Created by felix on 12/01/2017.
 */
public class CompareBehaviours {
    private final static int NB_ITERATIONS = 1000;

    /**
     * Permet de faire tourner un certain nombre de tests avec chaque
     * type de comportement pour comparer le nombre d'itérations
     *
     * @param args
     */
    public static void main(String args[]) {
        float vanilliaIterationsAverage = 0;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            Map map = MapReader.createMap("Map_test.txt");
            map.setBehaviours(false);
            vanilliaIterationsAverage += Main.runTest(map);
        }
        vanilliaIterationsAverage = vanilliaIterationsAverage / NB_ITERATIONS;

        float sensorsIterationsAverage = 0;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            Map map = MapReader.createMap("Map_test.txt");
            map.setBehaviours(true);
            sensorsIterationsAverage += Main.runTest(map);
        }
        sensorsIterationsAverage = sensorsIterationsAverage / NB_ITERATIONS;

        System.out.println("Nombre d'itérations moyen sans senseurs : " + vanilliaIterationsAverage);
        System.out.println("Nombre d'itérations moyen avec senseurs : " + sensorsIterationsAverage);

    }
}
