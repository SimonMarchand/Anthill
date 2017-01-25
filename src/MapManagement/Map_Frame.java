package MapManagement;

import Cell.AnthillCell;
import Cell.Cell;
import Cell.Coordinates;
import Cell.EmptyCell;
import Cell.FoodCell;
import Cell.ObstacleCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Brandon on 25/01/2017.
 */
public class Map_Frame extends GridLayout {

    private GridLayout gridLayout;
    private JFrame mainFrame;
    private JLabel title;
    private JPanel controlPanel;
    private Font font;

    int x;
    int y;

    Cell[][] cells;

    public Map_Frame(Map map){

        cells = map.getGrid();

        prepareFrame(map);


    }

    private void prepareFrame(Map map){

        x = map.getWidth();
        y = map.getHeigt();


        gridLayout = new GridLayout(x,y);


        mainFrame = new JFrame("Anthill test");
        mainFrame.setSize(800,800);
        mainFrame.setLayout(gridLayout);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel(gridLayout);

        for(int i=0 ; i < x ; i++){
            for(int j=0 ; j < y ; j++) {
                final JLabel label = new JLabel(cellCharacterAssignement(i,j,map));
                int pheromone = pheromoneOnCell(i,j,map);

                Color c = new Color(pheromone,0,0);
                label.setBackground(c);

                label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                label.setHorizontalAlignment(JLabel.CENTER);
                mainFrame.add(label);
            }
        }

        mainFrame.setVisible(true);

    }



    private String cellCharacterAssignement(int x, int y, Map map){
        return map.printCellFrame(cells[x][y]);
    }

    private int pheromoneOnCell(int x, int y, Map map){
        return map.getCell(new Coordinates(x,y)).getPheromonesHistory();
    }



}
