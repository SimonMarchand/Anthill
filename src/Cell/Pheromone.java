package Cell;

/**
 * Created by felix on 03/01/17.
 */
public class Pheromone {
	
	private static int quantitePheromone;
	
	public Pheromone(int quantite){
		this.quantitePheromone = quantite;
	}
	
	public int getQuantitePheromone(){
		return quantitePheromone;
	}
}
