package Cell;

/**
 * Created by felix on 03/01/17.
 */
public class AnthillCell extends Cell {

	private int foodQuantity;
	

	public AnthillCell() {
		super(0);
		this.foodQuantity = 0;
	}
	
	
	public void placeFood(int foodQuantity) {
		this.foodQuantity += foodQuantity;
	}	
	
}
