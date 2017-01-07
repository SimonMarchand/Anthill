package Ant;

import Cell.Cell;
import Cell.Coordinates;
import MapManagement.Map;

import java.util.ArrayList;

/**
 * Created by felix on 03/01/17.
 */
public abstract class Behaviour {
    // Contient toutes les cellules entourant la fourmi
    protected ArrayList<CellEvaluation> surroundings;
    protected Ant ant;

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
}
