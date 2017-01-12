package MapManagement;

import Cell.*;

import java.util.ArrayList;

/**
 * Created by felix on 03/01/17.
 */
public class Map {
    private Cell[][] grid;
    // Renseigne la quantité totale de nourriture restante sur la carte
    private int foodLeft;
    private boolean useSensors;
    private ArrayList<AnthillCell> anthills;

    public Map(Cell[][] grid) {
        this.grid = grid;
        this.anthills = new ArrayList<AnthillCell>();
    }

    public int getFoodLeft() {
        return foodLeft;
    }

    public void setFoodLeft(int foodLeft) {
        this.foodLeft = foodLeft;
    }

    public boolean useSensors() {
        return useSensors;
    }

    public void setUseSensors(boolean useSensors) {
        this.useSensors = useSensors;
    }

    public ArrayList<AnthillCell> getAnthills() {
        return anthills;
    }

    public void setAnthills(ArrayList<AnthillCell> anthills) {
        this.anthills = anthills;
    }

    public Cell getCell(Coordinates coordinates) {
        if(coordinates.getX() < this.grid.length && coordinates.getY() < this.grid[0].length)
            return this.grid[coordinates.getX()][coordinates.getY()];
        else {
            System.out.println("Erreur : impossible d'accéder aux coordonnées x:" + coordinates.getX() + "y:" + coordinates.getY());
            System.exit(0);
            return null;
        }
    }

    // A compléter pour vérifier que la map soit juste, et qu'il n'y a pas eu d'erreur de lecture
    public void Read() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {

                String s = grid[x][y].getClass().getName();

                switch (s) {
                    case "Cell.AnthillCell":
                        System.out.print("x");
                        break;
                    case "Cell.EmptyCell":
                        System.out.print(" ");
                        break;
                    case "Cell.ObstacleCell":
                        System.out.print("#");
                        break;
                    case "Cell.FoodCell":
                        System.out.print("0");
                        break;
                    default:
                        break;
                }

            }
            // System.out.println("");
        }
    }

    public void printCell(int x, int y) {

        if (x <= grid.length && y <= grid[0].length) {
            // System.out.println(grid[x][y].getClass().getName().toString());
        } else {
            // System.out.println("Index out of bounds");
        }

    }

    /**
     * Permet d'ajouter la quantité donnée de nourriture à foodLeft
     * @param food
     */
    public void addFood(int food) {
        this.foodLeft += food;
    }

    /**
     * Permet de retirer la quantité donnée de nourriture à foodLeft
     * @param food
     */
    public void removeFood(int food) {
        this.foodLeft -= food;
    }

    /**
     * Permet d'ajouter une fourmilliere a la map
     * @param anthill
     */
    public void addAnthill(AnthillCell anthill) {
        this.anthills.add(anthill);
    }
}
