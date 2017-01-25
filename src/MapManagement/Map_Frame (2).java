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
    private Map map;

    int x;
    int y;

    public Map_Frame(Map map) {
        x = map.getWidth();
        y = map.getHeigt();
        this.map = map;
        mainFrame = new JFrame("Anthill test");
        prepareFrame();
    }

    private void prepareFrame() {

        mainFrame.setSize(800, 800);
        update(map);
        mainFrame.setVisible(true);
    }


    private String cellCharacterAssignement(int x, int y) {
        return map.printCellFrame(map.getCell(new Coordinates(x, y)));
    }

    private int pheromoneOnCell(int x, int y) {
        return map.getCell(new Coordinates(x, y)).getPheromonesHistory();
    }


    public void update(Map map) {

        if(mainFrame != null){
            mainFrame.getContentPane().removeAll();
        }

        this.map = map;

        gridLayout = new GridLayout(x, y);

        mainFrame.setLayout(gridLayout);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //controlPanel = new JPanel(gridLayout);


        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                final JLabel label = new JLabel(cellCharacterAssignement(i, j));

                /*
                int pheromone = pheromoneOnCell(i,j);
                Color c = new Color(pheromone,0,0);
                label.setBackground(c);
                */

                label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                label.setHorizontalAlignment(JLabel.CENTER);
                mainFrame.add(label);
            }
        }
    }


}
