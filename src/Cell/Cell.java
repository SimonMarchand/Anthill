package Cell;

import MapManagement.Map;

/**
 * Created by felix on 03/01/17.
 */
public class Cell {

	private int pheromoneHistory;
	private Coordinates coord;
	private Pheromone pheromone;
	private Map map;
	
	
	public Cell(Coordinates coord, Map map) {
		this.pheromoneHistory = 0;
		this.coord = coord;
		this.pheromone = new Pheromone(0);
		this.map = map;
	}
	
	
	public int getPheromoneHistory() { return pheromoneHistory; }
	public void setPheromoneHistory(int pheromoneHistory) { this.pheromoneHistory = pheromoneHistory; }

	public Coordinates getCoord() { return coord; }
	public void setCoord(Coordinates coord) { this.coord = coord; }

	public Pheromone getPheromone() { return pheromone; }
	public void setPheromone(Pheromone pheromone) { this.pheromone = pheromone; }

	public Map getMap() { return map; }
	public void setMap(Map map) { this.map = map; }

	
	public void putPheromones(Pheromone nbPheromone){
		this.pheromoneHistory += 1;
		this.pheromone = nbPheromone;
	}

}
