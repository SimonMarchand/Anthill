package MapManagement;

import Ant.*;
import Cell.*;

import java.util.ArrayList;

/**
 * Created by felix on 03/01/17.
 */
public class Map {
    private Cell[][] grid;
    // Renseigne la quantité totale de nourriture restante sur la carte
    private int foodLeft;
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

    // A compléter pour vérifier que la map soit juste, et qu'il n'y a pas eu d'erreur de lecture
    public void printMap() {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {

                printCell(cell);

            }
            System.out.println();
        }
    }

    public void printCell(Cell cell) {

        String s = cell.getClass().getName();

        switch (s) {
            case "Cell.AnthillCell":
                System.out.print("  X");
                break;
            case "Cell.EmptyCell":
                System.out.print("   ");
                break;
            case "Cell.ObstacleCell":
                System.out.print("  #");
                break;
            case "Cell.FoodCell":
                System.out.print("  o");
//                System.out.printf("%3d", ((FoodCell) cell).getFood().getQuantity());
                break;
            default:
                break;
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
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                cell.loosePheromones();
            }
        }

        for (AnthillCell anthill : anthills) {
            for (Ant ant : anthill.getAnts()) {
                ant.move();
            }
        }
    }

    /**
     * Affiche l'historique de tous les historiques de phéromones sur la carte
     */
    public void printPheromonesHistory() {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell instanceof EmptyCell)
                    System.out.printf("%3d", cell.getPheromonesHistory());
                else
                    printCell(cell);
            }
            System.out.println();
        }
    }

}
