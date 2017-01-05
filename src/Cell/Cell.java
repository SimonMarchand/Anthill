package Cell;

/**
 * Created by felix on 03/01/17.
 */
public class Cell {

	private int pheromoneHistory;
	private Coordinates coord;
	
	public Cell(int pheromoneHistory,int x, int y) {
		super();
		this.pheromoneHistory = pheromoneHistory;
		coord = new Coordinates(x,y);
	}

}
