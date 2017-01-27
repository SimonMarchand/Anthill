package Cell;

import MapManagement.Map;
import Ant.Ant;

import java.util.ArrayList;

/**
 * Created by felix on 03/01/17.
 */
public class AnthillCell extends Cell {
    //Quantite de nourriture dans la fourmilière
    private int foodQuantity;
    private ArrayList<Ant> ants;
    // Quantite de fourmis generees pour chaque fourmilliere
    public static int ANTS_QUANTITY = 10;

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

    //Permet de mettre la nourriture dans la fourmilliere
    public void placeFood(int quantity) {
        this.foodQuantity += quantity;
    }

    public static void setAntsQuantity(int ants) {
        ANTS_QUANTITY = ants;
    }

}
