package Ant;

import Cell.Cell;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by felix on 03/01/17.
 * Implémentation du comportement de la fourmi sans prise en compte des phéromones,
 * et la fourmi ne voit pas les cases autour d'elle. Elle peut choisir de se déplacer vers
 * une case obstacle, auquel cas elle s'y dirige mais reste sur place.
 */
public class VanillaBehaviour extends Behaviour {
    private static float EVAL_MAX = 20;
    private static float EVAL_2 = 15;
    private static float EVAL_3 = 10;
    private static float EVAL_4 = 5;
    private static float EVAL_MIN = 0;

    public VanillaBehaviour(Ant ant) {
        this.ant = ant;
    }

    @Override
    public Cell nextCell() {
        if (ant.hasFood()) return backToAnthill();

        this.setSurroundingCells();
        this.setSurroundingEvaluations();

        return getChosenCell();
    }

    @Override
    protected void setSurroundingEvaluations() {
        float evaluationsSum = 0;
        this.evaluations = new ArrayList<Float>();

        for (int i = 0; i < this.surroundings.size(); i++) {
            if (i == 0) {
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
            evaluationsSum += this.surroundings.get(i).getEvaluation();
        }

        for (CellEvaluation cell : this.surroundings) {
            cell.setEvaluation(cell.getEvaluation() / evaluationsSum);
            // Si on a déjà des évaluations dans le tableau, on ajoute l'évaluation précédente du tableau
            if(this.evaluations.size() != 0)
                this.evaluations.add(cell.getEvaluation() + this.evaluations.get(this.evaluations.size() - 1));
            else
                this.evaluations.add(cell.getEvaluation());
        }

    }
}
