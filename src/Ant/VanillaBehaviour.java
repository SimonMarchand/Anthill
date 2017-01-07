package Ant;

import Cell.Cell;

/**
 * Created by felix on 03/01/17.
 */
public class VanillaBehaviour extends Behaviour {

    @Override
    public Cell nextCell(Ant ant) {
        if(ant.hasFood()) return backToAnthill(ant);

        this.setSurroundingCells(ant);

        return null;
    }
}
