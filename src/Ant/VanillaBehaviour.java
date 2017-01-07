package Ant;

import Cell.Cell;

/**
 * Created by felix on 03/01/17.
 */
public class VanillaBehaviour extends Behaviour {
    private static int EVAL_MAX = 20;
    private static int EVAL_2 = 15;
    private static int EVAL_3 = 10;
    private static int EVAL_4 = 5;
    private static int EVAL_MIN = 0;

    public VanillaBehaviour(Ant ant) {
        this.ant = ant;
    }

    @Override
    public Cell nextCell() {
        if (ant.hasFood()) return backToAnthill();

        this.setSurroundingCells();

        return null;
    }

    @Override
    protected void setSurroundingEvaluations() {
        for (int i = 0; i < this.surroundings.size(); i++) {
            if (this.surroundings.get(i).getCell().getClass().getName() == "ObstacleCell") {
                this.surroundings.get(i).setEvaluation(-1);
            } else if (i == 0) {
                this.surroundings.get(i).setEvaluation(EVAL_MAX);
            } else if (i == 1 || i == this.surroundings.size() - 1) {
                this.surroundings.get(i).setEvaluation(EVAL_2);
            } else if (i == 2 || i == this.surroundings.size() - 2) {
                this.surroundings.get(i).setEvaluation(EVAL_3);
            } else if (i == 3 || i == this.surroundings.size() - 3) {
                this.surroundings.get(i).setEvaluation(EVAL_4);
            } else {
                this.surroundings.get(i).setEvaluation(EVAL_MIN);
            }
        }
    }
}
