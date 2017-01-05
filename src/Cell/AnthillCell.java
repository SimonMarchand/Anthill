package Cell;

import MapManagement.Map;

/**
 * Created by felix on 03/01/17.
 */
public class AnthillCell extends Cell {
	private int foodQuantity;
	

	public AnthillCell(Coordinates coord, Map map) {
		super(coord, map);
		this.foodQuantity = 0;
	}

	public int getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}
	
	public void placeFood(int quantity) {
		this.foodQuantity += quantity;
	}	
	
}
