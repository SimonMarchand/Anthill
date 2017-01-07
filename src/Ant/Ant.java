package Ant;

import Cell.AnthillCell;
import Cell.Cell;
import Cell.Coordinates;
import Cell.Food;
import Cell.FoodCell;

import java.util.Random;
import java.util.Stack;

/**
 * Created by felix on 03/01/17.
 */
public class Ant {
    public static int PHEROMONES_CAPACITY = 1;
    public static int FOOD_CAPACITY = 1;
    public static String[] ORIENTATIONS = {
            "N",
            "NE",
            "E",
            "SE",
            "S",
            "SW",
            "W",
            "NW",
    };

    private Coordinates position;
    private Stack backTrack;
    private AnthillCell antHill;
    private Food food;
    private Behaviour behaviour;
    // Correspond aux lettres du pôle vers lequel elle est orientée
    private String orientation;

    /**
     * Constructeur dans le cas où l'orientation n'est pas donnée. Elle est donc donnée aléatoirement
     *
     * @param position
     * @param antHill
     * @param behaviour
     */
    public Ant(Coordinates position, AnthillCell antHill, Behaviour behaviour) {
        this.position = position;
        this.antHill = antHill;
        this.behaviour = behaviour;
        this.backTrack = new Stack<Coordinates>();

        Random r = new Random();
        this.orientation = (ORIENTATIONS[r.nextInt(7)]);
    }

    /**
     * Constructeur avec orientation donnée en paramètre
     *
     * @param position
     * @param antHill
     * @param behaviour
     * @param orientation
     */
    public Ant(Coordinates position, AnthillCell antHill, Behaviour behaviour, String orientation) {
        this.position = position;
        this.antHill = antHill;
        this.behaviour = behaviour;
        this.backTrack = new Stack<Coordinates>();
        this.orientation = orientation;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public Stack<Coordinates> getBackTrack() {
        return backTrack;
    }

    public void setBackTrack(Stack backTrack) {
        this.backTrack = backTrack;
    }

    public AnthillCell getAntHill() {
        return antHill;
    }

    public void setAntHill(AnthillCell antHill) {
        this.antHill = antHill;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    /**
     * Retourne true si la fourmi transporte de la nourriture, false sinon
     *
     * @return
     */
    public boolean hasFood() {
        return this.getFood() != null;
    }

    /**
     * Retourne la cellule actuelle de la fourmi
     *
     * @return Cell cell
     */
    public Cell getCurrentCell() {
        return this.antHill.getMap().getCell(this.position);
    }

    /**
     * Exécute un mouvement de la fourmi :
     * Pose de phéromones
     * Déplacement
     * Sauvegarde backtrack
     * Récupération nourriture / Dépôt nourriture
     */
    public void move() {
        // Commence par déposer des phéromones sur la case actuelle si elle transporte de la nourriture
        if (this.hasFood()) {
            this.getCurrentCell().putPheromones(PHEROMONES_CAPACITY);
        }

        // Puis elle se déplace en demandant à son comportement vers quelle case se diriger
        Cell cell = this.behaviour.nextCell(this);
        this.position = cell.getCoord();

        // On enregistre le trajet si la fourmi n'a pas de nourriture sur elle
        if (!this.hasFood()) {
            this.backTrack.push(this.position);
        }

        // Enfin elle récupère la nourriture si elle est sur une case de nourriture, ou elle en dépose si elle est arrivée à la fourmillière
        // Ainsi on obtient une trace de phéromones sur tout le trajet (case nourriture comprise), sauf sur la fourmillière.
        if (cell.getClass().getName() == "FoodCell" && !this.hasFood() && ((FoodCell) cell).hasFood()) {
            this.takeFood((FoodCell) cell);
        } else if (cell.getClass().getName() == "AnthillCell" && this.hasFood()) {
            this.putFood((AnthillCell) cell);
            this.backTrack = new Stack<Coordinates>();
        }
    }

    private void takeFood(FoodCell cell) {
        Food food = cell.getFood();
        if (food.getQuantity() >= FOOD_CAPACITY) {
            this.food = new Food(FOOD_CAPACITY);
            food.setQuantity(food.getQuantity() - FOOD_CAPACITY);
        } else {
            this.food = new Food(food.getQuantity());
            food.setQuantity(0);
        }
    }

    private void putFood(AnthillCell cell) {
        cell.placeFood(this.getFood().getQuantity());
        this.food = null;
    }

    public static int getOrientationIndex(String orientation) {
        for(int i = 0; i < Ant.ORIENTATIONS.length; i++) {
            if(Ant.ORIENTATIONS[i] == orientation)
                return i;
        }

        // Si l'orientation n'a pas été trouvée on retourne un index négatif pour détecter l'erreur
        return -1;
    }
}
