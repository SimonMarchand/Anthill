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
    final protected static int LOST_PHEROMONES = 1;


    public Cell(Coordinates coord, Map map) {
        this.pheromonesHistory = 0;
        this.coord = coord;
        this.pheromone = new Pheromone(0);
        this.map = map;
    }

    //Permet d'avoir l'historique des pheromones deposees sur les cellules
    public int getPheromonesHistory() {
        return pheromonesHistory;
    }

    public void setPheromonesHistory(int pheromonesHistory) {
        this.pheromonesHistory = pheromonesHistory;
    }

    //Retourne les cooordinees de la cellule
    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    //Retourne les pheromones présentes sur la cellule
    public Pheromone getPheromone() {
        return pheromone;
    }

    public void setPheromone(Pheromone pheromone) {
        this.pheromone = pheromone;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    //Met le nombre de pheromone mis en parametre dans la cellule
    public void putPheromones(int nbPheromone) {
        this.pheromonesHistory += nbPheromone;
        int totPheromones = this.pheromone.getQuantitePheromone() + nbPheromone;
        this.pheromone.setQuantitePheromone(totPheromones);
    }

    //Enleve un pheromone de la cellule par iteration
    public void loosePheromones() {
        if (this.pheromone.getQuantitePheromone() > LOST_PHEROMONES)
            this.pheromone.setQuantitePheromone(this.pheromone.getQuantitePheromone() - LOST_PHEROMONES);
        else
            this.pheromone.setQuantitePheromone(0);
    }

}
