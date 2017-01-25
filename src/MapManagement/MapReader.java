package MapManagement;

import java.io.*;

import Cell.*;

/**
 * Created by felix on 03/01/17.
 */
public class MapReader {
    final public static String MAPS_PATH = System.getProperty("user.dir") + File.separator + "Maps" + File.separator;

    public static Map createMap(String url) {
        url = MAPS_PATH + url;

        // Constantes Pour le Switch:Case plus bas
        final String hashtag = "#";
        final String zero = "o";
        final String X = "x";
        final String empty = " ";

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
                            Food food = new Food(FoodCell.FOOD_QUANTITY);
                            grid[c][x] = new FoodCell(new Coordinates(c, x), map, food);
                            map.addFood(food.getQuantity());
                            break;

                        case X:
                            grid[c][x] = new AnthillCell(new Coordinates(c, x), map);
                            map.addAnthill((AnthillCell) grid[c][x]);
                            break;

                        case empty:
                            grid[c][x] = new EmptyCell(new Coordinates(c, x), map);
                            break;
                    }

                    // Validation : vérifie si les cases en bordure de la carte sont bien des obstacles
                    if(c == 0 || c == length - 1 || x == 0 || x == width - 1) {
                        if(!(grid[c][x] instanceof ObstacleCell)) {
                            return null;
                        }
                    }
                }
                c++;
                // System.out.println("");
            }
            // Validation : vérifie si il existe bien au moins une fourmilière et une source de nourriture
            if (map.getAnthills().isEmpty() || map.getFoodLeft()==0)
                return null;

            fileReader.close();
            bufferedReader.close();

            // System.out.println("Lecture du fichier ok");

        } catch (IOException e) {
            System.out.println("Carte non valide : " + e.getMessage());
            System.exit(1);
        } catch(StringIndexOutOfBoundsException e) {
            System.out.println("Carte non valide : " + e.getMessage());
            System.exit(1);

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
