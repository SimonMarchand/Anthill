package MapManagement;

import Ant.Ant;
import Ant.SensorBehaviour;
import Ant.VanillaBehaviour;
import Cell.AnthillCell;
import Cell.Cell;
import Cell.Coordinates;
import Cell.EmptyCell;

import java.util.ArrayList;


public class Map {


    private Cell[][] grid;
    private int foodLeft;  // Renseigne la quantité totale de nourriture restante sur la carte
    public float completion;
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

    public ArrayList<AnthillCell> getAnthills() {
        return anthills;
    }

    public void setAnthills(ArrayList<AnthillCell> anthills) {
        this.anthills = anthills;
    }

    public Cell getCell(Coordinates coordinates) {
        if (coordinates.getX() < this.grid.length && coordinates.getY() < this.grid[0].length)
            return this.grid[coordinates.getX()][coordinates.getY()];
        else {
            System.out.println("Erreur : impossible d'accéder aux coordonnées x:" + coordinates.getX() + "y:" + coordinates.getY());
            System.exit(0);
            return null;
        }
    }

    /**
     * Retourne le caractère correspondant au type de la cellule cible
     *
     * @param cell
     * @return String
     */
    public String printCellFrame(Cell cell) {

        String s = cell.getClass().getName();

        switch (s) {
            case "Cell.AnthillCell":
                return " X ";
            case "Cell.EmptyCell":
                return "   ";
            case "Cell.ObstacleCell":
                return " # ";
            case "Cell.FoodCell":
                return " o ";
            default:
                return "   ";
        }

    }

    /**
     * Permet d'ajouter la quantité donnée de nourriture à foodLeft
     *
     * @param food
     */
    public void addFood(int food) {
        this.foodLeft += food;
    }

    /**
     * Permet de retirer la quantité donnée de nourriture à foodLeft
     *
     * @param food
     */
    public void removeFood(int food) {
        this.foodLeft -= food;
    }

    /**
     * Permet d'ajouter une fourmilliere a la map
     *
     * @param anthill
     */
    public void addAnthill(AnthillCell anthill) {
        this.anthills.add(anthill);
    }

    /**
     * Permet de set le behaviour de toutes les fourmis avec senseurs ou non
     *
     * @param sensors
     */
    public void setBehaviours(boolean sensors) {
        for (AnthillCell anthill : this.anthills) {
            for (Ant ant : anthill.getAnts()) {
                if (sensors)
                    ant.setBehaviour(new SensorBehaviour(ant));
                else
                    ant.setBehaviour(new VanillaBehaviour(ant));
            }
        }
    }

    /**
     * Fait tourner une itération de déplacement de toutes les fourmis
     */
    public void runIteration() {
        // Donne la quantité de nourriture récoltée par les fourmis au total
        int foodStored = 0;

        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                cell.loosePheromones();
            }
        }

        for (AnthillCell anthill : anthills) {
            for (Ant ant : anthill.getAnts()) {
                ant.move();
            }
            foodStored += anthill.getFoodQuantity();
        }

        int totalFood = foodLeft + foodStored;
        this.completion = foodStored * 100;
        this.completion /= totalFood;
    }

    public int getWidth() {
        return grid.length;
    }

    public int getHeigt() {
        return grid[0].length;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int getMaxHistory() {
        int maxHistory = 0;
        for (Cell[] line : this.grid) {
            for (Cell cell : line) {
                if (cell.getPheromonesHistory() > maxHistory)
                    maxHistory = cell.getPheromonesHistory();
            }
        }

        return maxHistory;
    }
}
