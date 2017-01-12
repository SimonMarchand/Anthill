package Ant;

import Cell.Cell;

import java.util.ArrayList;

/**
 * Created by felix on 03/01/17.
 */
public class SensorBehaviour extends Behaviour {

    public SensorBehaviour(Ant ant) {
        this.ant = ant;
    }

    @Override
    public Cell nextCell() {
        if (ant.hasFood()) return backToAnthill();

        return null;
    }

    @Override
    protected void setSurroundingEvaluations() {
        setBasicSurroundingEvaluations();

        for(CellEvaluation cellEvaluation : surroundings) {
            if(cellEvaluation.getCell().getClass().getName() == "ObstacleCell")
                cellEvaluation.setEvaluation(0);
            else
                cellEvaluation.setEvaluation(cellEvaluation.getEvaluation() + getPheromonesFromCell(cellEvaluation.getCell()));
        }

        fillEvaluations();
    }

    /**
     * Retourne le nombre de pheromones sur la case si elle en a, ou 0 si elle n'en a pas. Possibilite d'ajouter
     * une ponderation par le futur dans cette fonction.
     * @param cell
     * @return float
     */
    private float getPheromonesFromCell(Cell cell) {
        if (cell.getPheromone() != null)
            return cell.getPheromone().getQuantitePheromone();
        else
            return 0;
    }

}
