package Ant;

import Cell.Cell;

/**
 * Created by felix on 03/01/17.
 */
public class SensorBehaviour extends Behaviour {

    public SensorBehaviour(Ant ant) {
        this.ant = ant;
    }

    @Override
    public Cell nextCell() {
        if(ant.hasFood()) return backToAnthill();

        return null;
    }
}
