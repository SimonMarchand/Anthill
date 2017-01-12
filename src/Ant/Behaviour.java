package Ant;

import Cell.Cell;
import Cell.Coordinates;
import MapManagement.Map;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by felix on 03/01/17.
 */
public abstract class Behaviour {
    // Contient toutes les cellules entourant la fourmi
    protected ArrayList<CellEvaluation> surroundings;
    protected Ant ant;
    // Contient la somme pondérée des évaluations des cases dans le sens horaire
    protected ArrayList<Float> evaluations;

    /**
     * Renvoie la cellule du prochain mouvement de la fourmi donnée en paramètre.
     * Décrite dans les classes filles.
     *
     * @return Cell cell
     */
    public Cell nextCell() {
        return null;
    }

    protected Cell backToAnthill() {
        return ant.getAntHill().getMap().getCell(ant.getBackTrack().pop());
    }

    /**
     * Set les cellules entourant la fourmi, en commançant par la case devant elle puis dans le sens horaire
     */
    protected void setSurroundingCells() {
        this.surroundings = new ArrayList<CellEvaluation>();

        // Ajoute les cases une par une, en commençant par la case en face de la fourmi et en tournant dans le sens horaire
        for (int i = 0; i < 8; i++) {
            this.surroundings.add(
                    new CellEvaluation(
                            getCellDirection(Ant.ORIENTATIONS[
                                    (Ant.getOrientationIndex(ant.getOrientation()) + i)
                                    ]
                            )
                    )
            );
        }
    }

    /**
     * Set les évaluations des cases entourant la fourmi.
     * Décrite dans les classes filles.
     */
    protected void setSurroundingEvaluations() {
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
                coordinates = new Coordinates(ant.getPosition().getX(), ant.getPosition().getY() + 1);
                cell = map.getCell(coordinates);
                break;

            case "NE":
                coordinates = new Coordinates(ant.getPosition().getX() + 1, ant.getPosition().getY() + 1);
                cell = map.getCell(coordinates);
                break;

            case "E":
                coordinates = new Coordinates(ant.getPosition().getX() + 1, ant.getPosition().getY());
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
                coordinates = new Coordinates(ant.getPosition().getX() - 1, ant.getPosition().getY());
                cell = map.getCell(coordinates);
                break;

            case "NW":
                coordinates = new Coordinates(ant.getPosition().getX() - 1, ant.getPosition().getY() + 1);
                cell = map.getCell(coordinates);
                break;

            default:
                cell = ant.getCurrentCell();
        }

        return cell;
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
        if(randomEvaluation == 0) {
            cell = ant.getCurrentCell();
        }
        else if(randomEvaluation <= this.evaluations.get(0)) {
            ant.setOrientation(Ant.ORIENTATIONS[ (Ant.getOrientationIndex(ant.getOrientation()) + 0) % 8 ]);
            cell = this.surroundings.get(0).getCell();
        }
        else {
            int i = 1;
            while (cell == null || i != this.evaluations.size()) {
                if(randomEvaluation >= this.evaluations.get(i - 1) && randomEvaluation <= this.evaluations.get(i)) {
                    ant.setOrientation(Ant.ORIENTATIONS[ (Ant.getOrientationIndex(ant.getOrientation()) + i) % 8 ]);
                    cell = this.surroundings.get(i).getCell();
                }
                i++;
            }
        }

        // Si jamais la cellule est nulle, on fait en sorte que la fourmi ne bouge pas.
        if(cell == null)
            cell = ant.getCurrentCell();

        if(cell.getClass().getName() == "ObstacleCell")
                cell = ant.getCurrentCell();

        return cell;
    }

}
