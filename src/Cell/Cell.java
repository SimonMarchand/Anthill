package Cell;

import MapManagement.Map;

/**
 * Created by felix on 03/01/17.
 */
public class Cell {

	protected int pheromonesHistory;
	protected Coordinates coord;
	protected Pheromone pheromone;
	protected Map map;
	
	
	public Cell(Coordinates coord, Map map) {
		this.pheromonesHistory = 0;
		this.coord = coord;
		this.pheromone = new Pheromone(0);
		this.map = map;
	}
	
	
	public int getPheromonesHistory() { return pheromonesHistory; }
	public void setPheromonesHistory(int pheromonesHistory) { this.pheromonesHistory = pheromonesHistory; }

	public Coordinates getCoord() { return coord; }
	public void setCoord(Coordinates coord) { this.coord = coord; }

	public Pheromone getPheromone() { return pheromone; }
	public void setPheromone(Pheromone pheromone) { this.pheromone = pheromone; }

	public Map getMap() { return map; }
	public void setMap(Map map) { this.map = map; }

	
	public void putPheromones(int nbPheromone){
		this.pheromonesHistory += nbPheromone;
		int totPheromones = this.pheromone.getQuantitePheromone() + nbPheromone;
		this.pheromone.setQuantitePheromone(totPheromones); 
	}

}
