package Cell;

import MapManagement.Map;
/**
 * Created by felix on 03/01/17.
 */
public class FoodCell extends Cell {
	
	private Food food;
	

	public FoodCell(Coordinates coord, Map map, Food food) {
		super(coord, map);
		this.food = food;
	}
	
	public Food getFood(){
		return food;
	}
	
	public void setFood(Food food){
		this.food = food;
	}
}
