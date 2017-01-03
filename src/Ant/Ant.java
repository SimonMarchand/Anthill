package Ant;

import Cell.AnthillCell;
import Cell.Cell;
import Cell.Coordinates;
import Cell.Food;

import java.util.ArrayList;

/**
 * Created by felix on 03/01/17.
 */
public class Ant {
    private Coordinates position;
    private ArrayList backTrack;
    private AnthillCell antHill;
    private Food food;
    private IBehaviour behaviour;

    public Ant(Coordinates position, AnthillCell antHill, IBehaviour behaviour) {
        this.position = position;
        this.antHill = antHill;
        this.behaviour = behaviour;
        this.backTrack = new ArrayList<Coordinates>();
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public ArrayList getBackTrack() {
        return backTrack;
    }

    public void setBackTrack(ArrayList backTrack) {
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

    public IBehaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(IBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    /**
     * Retourne true si la fourmi transporte de la nourriture, false sinon
     * @return
     */
    public boolean hasFood() {
        return this.getFood()!=null;
    }

    /**
     * Retourne la cellule actuelle de la fourmi
     * @return Cell cell
     */
    public Cell getCurrentCell() {
        return null;
    }

    /**
     * Exécute un mouvement de la fourmi
     */
    public void move() {
        // Commence par déposer des phéromones sur la case actuelle si elle transporte de la nourriture
        // Puis elle se déplace en demandant à son comportement vers quelle case se diriger
        // Enfin elle récupère la nourriture si elle est sur une case de nourriture, ou elle en dépose si elle est arrivée à la fourmillière
        // Ainsi on obtient une trace de phéromones sur tout le trajet (case nourriture comprise), sauf sur la fourmillière.
    }
}
