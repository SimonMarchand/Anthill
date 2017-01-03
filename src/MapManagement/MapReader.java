package MapManagement;
import java.io.*;
import Cell.*;

/**
 * Created by felix on 03/01/17.
 */
public class MapReader {
    
    public static Map createMap(String url){
       
        // Constantes Pour le Switch:Case plus bas
        final String hashtag = "#";
        final String zero = "0";
        final String X = "x";
        final String empty = " ";
      
        // Longueur et largeur de la map a charger
        int length = getTextFileLength(url);
        int width = getTextFileWidth(url);
        Cell[][] grid = new Cell[width][length];
        
        // Lecture du fichier .txt et transformation en une grille de cellules
        try {
            int c = 0;
            
            FileReader fileReader = new FileReader(url);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line ;
            
            while((line=bufferedReader.readLine())!=null){
                for ( int x = 0 ; x < width ; x++ ){
                    
                    // Distinction des différent type de cellules en fonction du charactère lu
                    switch (""+line.charAt(x)){
                        case hashtag :
                            grid[c][x] = new ObstacleCell();
                            break;
                            
                        case zero :
                            grid[c][x] = new FoodCell(0);
                            break;
                        case "1" :
                            grid[c][x] = new FoodCell(1);
                            break;    
                        case "2" :
                            grid[c][x] = new FoodCell(2);
                            break;
                        case "3" :
                            grid[c][x] = new FoodCell(3);
                            break; 
                        case "4" :
                            grid[c][x] = new FoodCell(4);
                            break;
                        case "5" :
                            grid[c][x] = new FoodCell(5);
                            break; 
                        case "6" :
                            grid[c][x] = new FoodCell(6);
                            break;
                        case "7" :
                            grid[c][x] = new FoodCell(7);
                            break;
                        case "8" :
                            grid[c][x] = new FoodCell(8);
                            break;
                        case "9" :
                            grid[c][x] = new FoodCell(9);
                            break;   
                            
                        case X :
                            grid[c][x] = new AnthillCell();
                            break;
                            
                        case empty :
                            grid[c][x] = new EmptyCell(0);
                            break;
                            
                    }
                    
                    c++;
                }
            }
            
        fileReader.close();
        bufferedReader.close();
            
        }catch(IOException e){
        }
        
        return new Map(grid);
    }
    
    private static int getTextFileLength(String url){
        int i = 0;
        try {
            
            FileReader fileReader = new FileReader(url);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line ;
            
            while((line=bufferedReader.readLine())!= null){
                i++;
            }
            
            fileReader.close();
            bufferedReader.close();
            
        }catch(IOException e){
        }
        
        return i;
    }
    
    private static int getTextFileWidth(String url){
        int i=0;
        try {
            
            FileReader fileReader = new FileReader(url);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            i = line.length();
            
            fileReader.close();
            bufferedReader.close();
            
        }catch(IOException e){
        }
        
        return i;
    }
    
}
