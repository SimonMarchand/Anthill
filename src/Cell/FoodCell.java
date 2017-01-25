package Cell;

import MapManagement.Map;

/**
 * Created by felix on 03/01/17.
 * Classe des cellules ayant contenu/contenant de la nourriture
 */
public class FoodCell extends Cell {

    private Food food;
    public static int FOOD_QUANTITY = 3;


    public FoodCell(Coordinates coord, Map map, Food food) {
        super(coord, map);
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    //Retourne vrai si la cellule contient de la nourriture
    public boolean hasFood() {
        if (food.getQuantity() == 0) return false;
        else return true;
    }

    public static void setFoodQuantity(int quantity) {
        FOOD_QUANTITY = quantity;
    }
}
