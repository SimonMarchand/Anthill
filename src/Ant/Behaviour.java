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

    /**
     * Renvoie la cellule du prochain mouvement de la fourmi donnée en paramètre.
     * Décrite dans les classes filles.
     * @param ant
     * @return Cell cell
     */
    public Cell nextCell(Ant ant) {
        return null;
    }

    protected Cell backToAnthill(Ant ant) {
        return ant.getAntHill().getMap().getCell(ant.getBackTrack().pop());
    }

    protected void setSurroundingCells(Ant ant) {
        this.surroundings = new ArrayList<CellEvaluation>();

        // Ajoute les cases une par une, en commençant par la case en face de la fourmi et en tournant dans le sens horaire
        for(int i = 0; i < 8; i++) {
            this.surroundings.add(
                    new CellEvaluation(
                            getCellDirection(ant,Ant.ORIENTATIONS[
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
     * @param ant
     */
    protected void setSurroundingEvaluations(Ant ant) {}

    /**
     * Retourne la cellule autour de la fourmi à la direction donnée
     * @param ant
     * @param direction
     * @return
     */
    protected Cell getCellDirection(Ant ant, String direction) {
        Map map = ant.getAntHill().getMap();
        Cell cell;
        Coordinates coordinates;

        switch(direction) {
            case "N" :
                coordinates = new Coordinates(ant.getPosition().getX(), ant.getPosition().getY() + 1);
                cell = map.getCell(coordinates);
                break;

            case "NE" :
                coordinates = new Coordinates(ant.getPosition().getX() + 1, ant.getPosition().getY() + 1);
                cell = map.getCell(coordinates);
                break;

            case "E" :
                coordinates = new Coordinates(ant.getPosition().getX() + 1, ant.getPosition().getY());
                cell = map.getCell(coordinates);
                break;

            case "SE" :
                coordinates = new Coordinates(ant.getPosition().getX() - 1, ant.getPosition().getY() + 1);
                cell = map.getCell(coordinates);
                break;

            case "S" :
                coordinates = new Coordinates(ant.getPosition().getX() - 1, ant.getPosition().getY());
                cell = map.getCell(coordinates);
                break;

            case "SW" :
                coordinates = new Coordinates(ant.getPosition().getX() - 1, ant.getPosition().getY() - 1);
                cell = map.getCell(coordinates);
                break;

            case "W" :
                coordinates = new Coordinates(ant.getPosition().getX() - 1, ant.getPosition().getY());
                cell = map.getCell(coordinates);
                break;

            case "NW" :
                coordinates = new Coordinates(ant.getPosition().getX() - 1, ant.getPosition().getY() + 1);
                cell = map.getCell(coordinates);
                break;

            default:
                cell = ant.getCurrentCell();
        }

        return cell;
    }
}
