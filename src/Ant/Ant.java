package Ant;

import Cell.AnthillCell;
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

    public boolean hasFood() {
        return this.getFood()!=null;
    }
}
