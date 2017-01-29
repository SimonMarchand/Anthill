package Ant;

import Cell.Cell;

/**
 * Created by felix on 03/01/17.
 * Implémentation du comportement de la fourmi sans prise en compte des phéromones,
 * et la fourmi ne voit pas les cases autour d'elle. Elle peut choisir de se déplacer vers
 * une case obstacle, auquel cas elle s'y dirige mais reste sur place.
 */
public class VanillaBehaviour extends Behaviour implements IBehaviour {

    public VanillaBehaviour(Ant ant) {
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

        fillEvaluations();
    }
}
