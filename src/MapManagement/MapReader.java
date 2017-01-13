package MapManagement;

import java.io.*;

import Cell.*;

/**
 * Created by felix on 03/01/17.
 */
public class MapReader {
    final static String MAPS_PATH = System.getProperty("user.dir") + File.separator + "Maps" + File.separator;

    public static Map createMap(String url) {
        url = MAPS_PATH + url;

        // Constantes Pour le Switch:Case plus bas
        final String hashtag = "#";
        final String zero = "0";
        final String X = "x";
        final String empty = " ";

        final int FOOD_QUANTITY = 10;

        // Longueur et largeur de la map a charger
        int length = getTextFileLength(url);
        int width = getTextFileWidth(url);


        Cell[][] grid = new Cell[length][width];
        Map map = new Map(grid);

        // Lecture du fichier .txt et transformation en une grille de cellules
        try {
            int c = 0;

            FileReader fileReader = new FileReader(url);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;


            // System.out.println("width de la map : " + width);
            // System.out.println("Length de la map : " + length);

            // System.out.println("Lecture fichier en cours ");

            while ((line = bufferedReader.readLine()) != null) {
                for (int x = 0; x < width; x++) {

                    //System.out.print("" + line.charAt(x));
                    // Distinction des différent type de cellules en fonction du charactère lu
                    switch ("" + line.charAt(x)) {
                        case hashtag:
                            grid[c][x] = new ObstacleCell(new Coordinates(c, x), map);
                            break;

                        case zero:
                            Food food = new Food(FOOD_QUANTITY);
                            grid[c][x] = new FoodCell(new Coordinates(c, x), map, food);
                            map.addFood(food.getQuantity());
                            break;

                        case X:
                            grid[c][x] = new AnthillCell(new Coordinates(c, x), map);
                            break;

                        case empty:
                            grid[c][x] = new EmptyCell(new Coordinates(c, x), map);
                            break;
                    }
                }
                c++;
                // System.out.println("");
            }

            fileReader.close();
            bufferedReader.close();

            // System.out.println("Lecture du fichier ok");

        } catch (IOException e) {
            e.getMessage();
            // System.out.println("Input/Output Error, Impossible de lire le fichier");

        }

        return map;
    }

    private static int getTextFileLength(String url) {
        int i = 0;
        try {

            FileReader fileReader = new FileReader(url);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                i++;
            }

            fileReader.close();
            bufferedReader.close();

        } catch (IOException e) {
        }

        return i;
    }

    private static int getTextFileWidth(String url) {
        int i = 1;
        try {

            FileReader fileReader = new FileReader(url);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            i = line.length();

            fileReader.close();
            bufferedReader.close();

        } catch (IOException e) {
        }

        return i;
    }

}
