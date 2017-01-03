package Ant;

import Cell.Cell;

/**
 * Created by felix on 03/01/17.
 */
public interface IBehaviour {

    /**
     * Renvoie la cellule du prochain mouvement de la fourmi donnée en paramètre
     * @param ant
     * @return Cell cell
     */
    public Cell nextCell(Ant ant);

}
