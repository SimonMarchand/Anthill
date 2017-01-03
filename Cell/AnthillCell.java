package Cell;

/**
 * Created by felix on 03/01/17.
 */
public class AnthillCell {
	
	private int foodQuantity;
	
	public AnthillCell(){
		foodQuantity = 0;
	}
	
	public void setFood(int food){
		foodQuantity += food;
	}
	
	public int getQuantity(){
		return foodQuantity;
	}
	
}
