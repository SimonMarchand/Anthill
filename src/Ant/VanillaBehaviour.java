package Ant;

import Cell.Cell;

/**
 * Created by felix on 03/01/17.
 */
public class VanillaBehaviour extends Behaviour {

    public VanillaBehaviour(Ant ant) {
        this.ant = ant;
    }

    @Override
    public Cell nextCell() {
        if (ant.hasFood()) return backToAnthill();

        this.setSurroundingCells();

        return null;
    }
}
