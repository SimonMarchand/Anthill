package Ant;

import Cell.Cell;

public class SensorBehaviour extends Behaviour implements IBehaviour {
    protected static final float PHEROMONEIMPACT = 0.5f;

    public SensorBehaviour(Ant ant) {
        this.ant = ant;
        ant.setBehaviour(this);
    }

    public Cell nextCell() {
        if (ant.hasFood()) return backToAnthill();

        this.setSurroundingCells();
        this.setSurroundingEvaluations();

        return getChosenCell();
    }

    /**
     * Set les évaluations des cases entourant la fourmi.
     */
    protected void setSurroundingEvaluations() {
        setBasicSurroundingEvaluations();
        setObstaclesEvaluations();
        for (CellEvaluation cellEvaluation : surroundings) {
            if (cellEvaluation.getEvaluation() != 0)
                cellEvaluation.setEvaluation(cellEvaluation.getEvaluation() + getPheromonesFromCell(cellEvaluation.getCell()));
        }

        fillEvaluations();
    }

    /**
     * Retourne le nombre de pheromones sur la case si elle en a, ou 0 si elle n'en a pas. Possibilite d'ajouter
     * une ponderation par le futur dans cette fonction.
     *
     * @param cell
     * @return float
     */
    private float getPheromonesFromCell(Cell cell) {
        if (cell.getPheromone() != null)
            return cell.getPheromone().getQuantitePheromone() * PHEROMONEIMPACT;
        else
            return 0;
    }

}
