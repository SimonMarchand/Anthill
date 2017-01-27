package Ant;

import Cell.Cell;
/**
 * Created by felix on 25/01/17.
 */
public interface IBehaviour {
    /**
     * Renvoie la cellule du prochain mouvement de la fourmi donnée en paramètre.
     * Décrite dans les classes filles.
     *
     * @return Cell cell
     */
    public Cell nextCell();
}
