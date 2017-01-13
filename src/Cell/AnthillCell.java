package Cell;

import MapManagement.Map;
import Ant.Ant;

import java.util.ArrayList;

/**
 * Created by felix on 03/01/17.
 */
public class AnthillCell extends Cell {
    private int foodQuantity;
    private ArrayList<Ant> ants;
    // Quantite de fourmis generees pour chaque fourmilliere
    private final static int ANTS_QUANTITY = 3;

    public AnthillCell(Coordinates coord, Map map) {
        super(coord, map);
        this.map.addAnthill(this);
        this.foodQuantity = 0;
        this.ants = new ArrayList<Ant>();

        for (int i = 0; i < ANTS_QUANTITY; i++) {
            this.ants.add(new Ant(this.getCoord(), this));
        }
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public ArrayList<Ant> getAnts() {
        return ants;
    }

    public void setAnts(ArrayList<Ant> ants) {
        this.ants = ants;
    }

    public void placeFood(int quantity) {
        this.foodQuantity += quantity;
    }

}
