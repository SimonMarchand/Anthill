package MapManagement;
import Cell.*;
/**
 * Created by felix on 03/01/17.
 */
public class Map {
    
    Cell[][] grid;
    
    public Map(Cell[][] grid){
        this.grid = grid;
    }
    
    public Cell getCell(Coordinates coordinates){
        return this.grid[coordinates.getX()][coordinates.getY()];
    }
    
    // A compléter pour vérifier que la map soit juste, et qu'il n'y a pas eu d'erreur de lecture
    public void Read(){
        for(int x=0 ; x<grid.length;x++){
            for(int y=0 ; y<grid[x].length;y++){
                String s = grid[x][y].getClass().getName();
                
                switch (s){
                    case "AnthillCell": System.out.print("x");break;
                    case "EmptyCell": System.out.print(" ");break;
                    case "ObstacleCell": System.out.print("#");break;
                    case "FoodCell": System.out.print("0");break;
                    default : break;
                }
                
            }
            System.out.println("");
        }
    }
    
}
