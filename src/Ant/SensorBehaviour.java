package Ant;

import Cell.Cell;

/**
 * Created by felix on 03/01/17.
 */
public class SensorBehaviour extends Behaviour {

    @Override
    public Cell nextCell() {
        if(ant.hasFood()) return backToAnthill();

        return null;
    }
}
