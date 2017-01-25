package Main;

import Ant.Ant;
import Cell.AnthillCell;
import Cell.FoodCell;
import MapManagement.Map;
import MapManagement.MapReader;

import java.io.File;
import java.util.*;

/**
 * Created by felix on 03/01/17.
 */
public abstract class Main {

    /**
     * Permet de faire rentrer tous les paramètres par l'utilisateur
     */
    public static void setVariables() {

        System.out.println("Paramètres par défaut :");
        System.out.println("Nombre de fourmis par fourmillière : " + AnthillCell.ANTS_QUANTITY);
        System.out.println("Quantité de nourriture par case de nourriture : " + FoodCell.FOOD_QUANTITY);
        System.out.println("Nombre de phéromones déposées par les fourmis : " + Ant.PHEROMONES_CAPACITY);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Utiliser les paramètres par défaut ? o/n");
        boolean confirmation = scanner.nextLine().charAt(0) == 'o';
        // Si l'utilisateur veut utiliser les paramètres par défaut, on arrête la fonction
        if(confirmation) {
            return;
        }

        int i;

        System.out.println("Nombre de fourmis par fourmillière : ");
        i = scanner.nextInt();
        while (i < 0) {
            System.out.println("Veuillez saisir une valeur valide.");
            i = scanner.nextInt();
        }
        AnthillCell.setAntsQuantity(i);

        System.out.println("Quantité de nourriture par case de nourriture : ");
        i = scanner.nextInt();
        while (i < 0) {
            System.out.println("Veuillez saisir une valeur valide.");
            i = scanner.nextInt();
        }
        FoodCell.setFoodQuantity(i);

        System.out.println("Nombre de phéromones déposées par les fourmis : ");
        i = scanner.nextInt();
        while (i < 0) {
            System.out.println("Veuillez saisir une valeur valide.");
            i = scanner.nextInt();
        }
        Ant.setPheromonesCapacity(i);
    }

    public static int runTest(Map map) {
        int i = 0;
        while (map.getFoodLeft() > 0) {
            map.runIteration();
            i++;
        }

        return i;
    }

    /**
     * Permet à l'utilisateur de sélectionner une carte parmis la liste donnée
     * @return
     */
    public static String getMapName() {
        int mapIndex = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez la carte parmis la liste suivante : ");
        File mapsDir = new File(MapReader.MAPS_PATH);
        ArrayList<File> maps = new ArrayList<File>(Arrays.asList(mapsDir.listFiles()));
        for (int i = 0; i < maps.size(); i++) {
            File map = maps.get(i);
            if(map.isFile() && getFileExtension(map).equals("txt")){
                mapIndex++;
                System.out.println(mapIndex + " : " + map.getName());
            }
            else {
                maps.remove(i);
                i--;
            }
        }

        System.out.print("--> ");
        int userInput = scanner.nextInt();
        while (userInput < 1 || userInput > mapIndex) {
            System.out.println("Veuillez saisir une valeur valide.");
            userInput = scanner.nextInt();
        }

        return maps.get(userInput - 1).getName();
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    protected static Map initMap(String mapName) {
        Map map = MapReader.createMap(mapName);
        if(map!= null) return map;
        else {
            System.out.println("Carte non valide");
            System.exit(1);
            return null;
        }
    }
}
