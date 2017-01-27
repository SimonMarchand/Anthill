package Ant;

import Cell.Cell;
import Cell.ObstacleCell;
import Cell.Coordinates;
import MapManagement.Map;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by felix on 03/01/17.
 */
public abstract class Behaviour {
    // Pondérations des évaluations
    protected static float EVAL_MAX = 250;
    protected static float EVAL_2 = 200;
    protected static float EVAL_3 = 50;
    protected static float EVAL_4 = 25;
    protected static float EVAL_MIN = 0;

    // Contient toutes les cellules entourant la fourmi
    protected ArrayList<CellEvaluation> surroundings;
    protected Ant ant;
    // Contient la somme pondérée des évaluations des cases dans le sens horaire
    protected ArrayList<Float> evaluations;

    protected Cell backToAnthill() {
        return ant.getAntHill().getMap().getCell(ant.getBackTrack().pop());
    }

    /**
     * Set les cellules entourant la fourmi, en commançant par la case devant elle puis dans le sens horaire
     */
    protected void setSurroundingCells() {
        this.surroundings = new ArrayList<CellEvaluation>();

        int antOrientationIndex = Ant.getOrientationIndex(ant.getOrientation());
        // Ajoute les cases une par une, en commençant par la case en face de la fourmi et en tournant dans le sens horaire
        for (int i = 0; i < 8; i++) {
            String direction = Ant.ORIENTATIONS[(antOrientationIndex + i) % Ant.ORIENTATIONS.length];
            this.surroundings.add(new CellEvaluation(getCellDirection(direction)));
        }
    }

    /**
     * Retourne la cellule autour de la fourmi à la direction donnée
     *
     * @param direction
     * @return
     */
    protected Cell getCellDirection(String direction) {
        Map map = ant.getAntHill().getMap();
        Cell cell;
        Coordinates coordinates;

        switch (direction) {
            case "N":
                coordinates = new Coordinates(ant.getPosition().getX() + 1, ant.getPosition().getY());
                cell = map.getCell(coordinates);
                break;

            case "NE":
                coordinates = new Coordinates(ant.getPosition().getX() + 1, ant.getPosition().getY() + 1);
                cell = map.getCell(coordinates);
                break;

            case "E":
                coordinates = new Coordinates(ant.getPosition().getX(), ant.getPosition().getY() + 1);
                cell = map.getCell(coordinates);
                break;

            case "SE":
                coordinates = new Coordinates(ant.getPosition().getX() - 1, ant.getPosition().getY() + 1);
                cell = map.getCell(coordinates);
                break;

            case "S":
                coordinates = new Coordinates(ant.getPosition().getX() - 1, ant.getPosition().getY());
                cell = map.getCell(coordinates);
                break;

            case "SW":
                coordinates = new Coordinates(ant.getPosition().getX() - 1, ant.getPosition().getY() - 1);
                cell = map.getCell(coordinates);
                break;

            case "W":
                coordinates = new Coordinates(ant.getPosition().getX(), ant.getPosition().getY() - 1);
                cell = map.getCell(coordinates);
                break;

            case "NW":
                coordinates = new Coordinates(ant.getPosition().getX() + 1, ant.getPosition().getY() - 1);
                cell = map.getCell(coordinates);
                break;

            default:
                cell = ant.getCurrentCell();
        }

        return cell;
    }

    /**
     * Permet de remplir le tableau d'évaluations, en utilisant la somme des pondérations des cellules environantes
     */
    protected void fillEvaluations() {
        this.evaluations = new ArrayList<Float>();
        float evaluationsSum = 0;

        for (CellEvaluation cell : this.surroundings) {
            evaluationsSum += cell.getEvaluation();
        }

        for (CellEvaluation cell : this.surroundings) {
            cell.setEvaluation(cell.getEvaluation() / evaluationsSum);
            // Si on a déjà des évaluations dans le tableau, on ajoute l'évaluation précédente du tableau
            if (this.evaluations.size() != 0)
                this.evaluations.add(cell.getEvaluation() + this.evaluations.get(this.evaluations.size() - 1));
            else
                this.evaluations.add(cell.getEvaluation());
        }
    }

    /**
     * Retourne la cellule choisie aléatoirement par la fourmi à partir
     * du tableau d'évaluation rempli par les classes filles.
     *
     * @return cell: Cell
     */
    protected Cell getChosenCell() {
        Random r = new Random();
        float randomEvaluation = r.nextFloat();
        Cell cell = null;

        /**
         * On veut éviter de donner une case ayant une évaluation de 0.
         * Une évaluation de 0 correspond dans le tableau d'évaluation à 2 valeurs identiques consécutives.
         * En testant sur une égalité non stricte, on est sûrs de choisir en premier une case possédant une évaluation
         * non nulle, sauf dans le cas où le float tiré est égal à 0. Dans ce cas, on dit que la fourmi ne bouge pas pour
         * éviter des erreurs.
         */
        if (randomEvaluation == 0) {
            cell = ant.getCurrentCell();
        } else if (randomEvaluation <= this.evaluations.get(0)) {
            ant.setOrientation(Ant.ORIENTATIONS[(Ant.getOrientationIndex(ant.getOrientation()) + 0) % Ant.ORIENTATIONS.length]);
            cell = this.surroundings.get(0).getCell();
        } else {
            int i = 1;
            while (cell == null && i < this.evaluations.size()) {
                if (randomEvaluation >= this.evaluations.get(i - 1) && randomEvaluation <= this.evaluations.get(i)) {
                    ant.setOrientation(Ant.ORIENTATIONS[(Ant.getOrientationIndex(ant.getOrientation()) + i) % Ant.ORIENTATIONS.length]);
                    cell = this.surroundings.get(i).getCell();
                }
                i++;
            }
        }

        // Si jamais la cellule est nulle, on fait en sorte que la fourmi ne bouge pas.
        if (cell == null)
            cell = ant.getCurrentCell();

        if (cell instanceof ObstacleCell)
            cell = ant.getCurrentCell();

        return cell;
    }

    /**
     * Set les evaluations de base pour les cases environnantes,
     * sans prise en compte des phéromones ni des obstacles
     */
    protected void setBasicSurroundingEvaluations() {
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
        }
    }

    protected void setObstaclesEvaluations() {
        for (CellEvaluation cellEvaluation : surroundings) {
            if (cellEvaluation.getCell() instanceof ObstacleCell)
                cellEvaluation.setEvaluation(0);
        }
    }

}
