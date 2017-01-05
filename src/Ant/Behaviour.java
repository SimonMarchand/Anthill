package Ant;

import Cell.Cell;
import Cell.Coordinates;

/**
 * Created by felix on 03/01/17.
 */
public abstract class Behaviour {

    /**
     * Renvoie la cellule du prochain mouvement de la fourmi donnée en paramètre
     * @param ant
     * @return Cell cell
     */
    public Cell nextCell(Ant ant) {
        return null;
    }

    protected Cell backToAnthill(Ant ant) {
        return ant.getAntHill().getMap().getCell(ant.getBackTrack().pop());
    }

}
