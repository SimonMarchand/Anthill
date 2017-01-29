package MapManagement;

import java.io.*;

import Cell.*;


public class MapReader {

    // Url des maps
    final public static String MAPS_PATH = System.getProperty("user.dir") + File.separator + "Maps" + File.separator;


    /**
     * Methode lisant le fichier txt et créant la map de type Map correspondant
     * @param url
     * @return Map
     */
    public static Map createMap(String url) {
        url = MAPS_PATH + url;

        // Constantes Pour le Switch:Case plus bas
        final String hashtag = "#";
        final String zero = "o";
        final String X = "x";
        final String empty = " ";

        // Longueur et largeur de la map a charger  / V1 avant l'ajout des 4 première lignes dans le fichier map
        int length = getTextFileLength(url);
        int width = getTextFileWidth(url);


        Cell[][] grid = new Cell[length][width];
        Map map = new Map(grid);

        // Variable permettant la lecture du nombre de source et du nombre de nourriture
        int[] foodQuantity = null;
        int foodCelNb=0;

        // Lecture du fichier .txt et transformation en une grille de cellules
        try {
            int c = 0;

            FileReader fileReader = new FileReader(url);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {

                //Lecture des parametres des 4 premieres lignes
                if(c<4)
                {
                    String[] words = line.split(" ");
                    if (words.length!=0)
                    {
                        switch (c) {
                            case 0:
                                length=Integer.parseInt(words[0]);
                                break;

                            case 1:
                                width=Integer.parseInt(words[0]);
                                grid = new Cell[length][width];
                                map = new Map(grid);
                                break;

                            //Defini le nombre de sources
                            case 2:
                                foodQuantity = new int[Integer.parseInt(words[0])];
                                break;

                            //Defini la quantite de nourriture par source d'en haut/gauche a en bas/droite
                            case 3:
                                for (int i = 0; i < words.length; i++) {
                                    if (foodQuantity != null && foodQuantity.length != 0)
                                    {
                                        foodQuantity[i]= Integer.parseInt(words[i]);
                                    }
                                }
                                break;

                            default:
                                break;
                        }
                    }
                }

                else{
                    for (int x = 0; x < width; x++) {
                        // Distinction des différents types de cellules en fonction du charactère lu
                        switch ("" + line.charAt(x)) {
                            case hashtag:
                                grid[c-4][x] = new ObstacleCell(new Coordinates(c-4, x), map);
                                break;

                            case zero:
                                Food food;
                                if (foodQuantity != null && foodQuantity.length != 0){
                                    food = new Food(foodQuantity[foodCelNb]);
                                    foodCelNb++;
                                }
                                else
                                    food = new Food(FoodCell.FOOD_QUANTITY);

                                grid[c-4][x] = new FoodCell(new Coordinates(c-4, x), map, food);
                                map.addFood(food.getQuantity());
                                break;

                            case X:
                                grid[c-4][x] = new AnthillCell(new Coordinates(c-4, x), map);
                                map.addAnthill((AnthillCell) grid[c-4][x]);
                                break;

                            case empty:
                                grid[c-4][x] = new EmptyCell(new Coordinates(c-4, x), map);
                                break;
                        }

                        // Validation : vérifie si les cases en bordure de la carte sont bien des obstacles
                        if (c-4 == 0 || c-4 == length - 1 || x == 0 || x == width - 1) {
                            if (!(grid[c-4][x] instanceof ObstacleCell)) {
                                return null;
                            }
                        }
                    }
                }
                c++;
            }
            // Validation : vérifie si il existe bien au moins une fourmilière et une source de nourriture
            if (map.getAnthills().isEmpty() || map.getFoodLeft() == 0)
                return null;

            fileReader.close();
            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("Carte non valide : " + e.getMessage());
            System.exit(1);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Carte non valide : " + e.getMessage());
            System.exit(2);

        }
        return map;
    }


    /**
     * Retourne le nombre de ligne dans le fichier txt
     * @param url
     * @return
     */
    private static int getTextFileLength(String url) {
        int i = 0;
        try {

            FileReader fileReader = new FileReader(url);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                i++;
            }
            //On enleve les lignes de parametres
            i-=4;

            fileReader.close();
            bufferedReader.close();

        } catch (IOException e) {
        }

        return i;
    }


    /**
     * Retourne le nombre de colone dans le fichier txt
     * @param url
     * @return
     */
    private static int getTextFileWidth(String url) {
        int i = 0;
        try {

            FileReader fileReader = new FileReader(url);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Lire la ligne numero 5
            String line=null;
            for (int j=0; j<5; j++)
                line = bufferedReader.readLine();

            if (line != null) {
                i = line.length();
            }

            fileReader.close();
            bufferedReader.close();

        } catch (IOException e) {
        }

        return i;
    }
}
