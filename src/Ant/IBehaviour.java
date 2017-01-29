package Ant;

import Cell.Cell;

public interface IBehaviour {
    /**
     * Renvoie la cellule du prochain mouvement de la fourmi donnée en paramètre.
     * Décrite dans les classes filles.
     *
     * @return Cell cell
     */
    Cell nextCell();
}
