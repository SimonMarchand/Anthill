package Ant;

import Cell.Cell;


public class CellEvaluation {
    private Cell cell;
    private float evaluation;

    public CellEvaluation(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }
}
