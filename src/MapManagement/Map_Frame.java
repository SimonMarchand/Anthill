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


public class Map_Frame extends GridLayout {

    private GridLayout gridLayout;
    private JFrame mainFrame;
    private JLabel[][] labels;
    private JPanel mainPanel;
    private Font font;
    private Map map;

    int x;
    int y;

    /**
     * Crée la fenêtre de simulation
     * @param map
     */
    public Map_Frame(Map map) {
        x = map.getWidth();
        y = map.getHeigt();
        this.map = map;

        labels = new JLabel[x][y];
        mainFrame = new JFrame("Anthill test");

        prepareFrame();
    }

    /**
     * Initialise et définit la taille de la fenêtre
     */
    private void prepareFrame() {

        mainFrame.setSize(800, 800);
        update(map);
        mainFrame.setVisible(true);
    }


    /**
     * Retourne le caractère correspondant a la cellule cible
     * @param x
     * @param y
     * @return
     */
    private String cellCharacterAssignement(int x, int y) {
        return map.printCellFrame(map.getCell(new Coordinates(x, y)));
    }

    /**
     * Retourne la quantité de Phéromones sur la cellule cible
     * @param x
     * @param y
     * @return
     */
    private int pheromoneOnCell(int x, int y) {
        return map.getCell(new Coordinates(x, y)).getPheromonesHistory();
    }


    /**
     * Met à jour la map
     * @param map
     */
    public void update(Map map) {
        /*
        if(mainFrame != null){
            mainFrame.getContentPane().removeAll();
        }
        */

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

                labels[i][j] = new JLabel(cellCharacterAssignement(i, j));
                labels[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                mainFrame.add(labels[i][j]);

                /*
                final JLabel label = new JLabel(cellCharacterAssignement(i, j));
                int pheromone = pheromoneOnCell(i,j);
                Color c = new Color(pheromone,0,0);
                label.setBackground(c);

                label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                label.setHorizontalAlignment(JLabel.CENTER);
                mainFrame.add(label);
                */


            }
        }
    }

    /**
     * Permet d'afficher la map à chaque itération, et d'afficher l'historique des phéromones si le paramètre history est donné
     * true
     * @param map
     * @param history : afficher l'historique
     */
    public void Repaint(Map map, boolean history) {
        this.map = map;
        int maxHistory = map.getMaxHistory();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {

                int c;
                if (!history) {
                    c = map.getCell(new Coordinates(i, j)).getPheromone().getQuantitePheromone() * 4;
                } else {
                    c = map.getCell(new Coordinates(i, j)).getPheromonesHistory() * 255 / maxHistory;
                }

                if (c < 254) {
                    Color color = new Color(255, 254 - c, 254 - c);
                    labels[i][j].setBackground(color);
                } else {
                    Color color = new Color(255, 0, 0);
                    labels[i][j].setBackground(color);
                }
                labels[i][j].setOpaque(true);


                if ((map.getCell((new Coordinates(i, j))) instanceof FoodCell) && !((FoodCell) map.getCell(new Coordinates(i, j))).hasFood()) {
                    labels[i][j].setText("");
                } else {
                    labels[i][j].setText(cellCharacterAssignement(i, j));
                }
                labels[i][j].setBorder(BorderFactory.createLineBorder(Color.gray));
                labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                labels[i][j].repaint();

            }
        }
        mainFrame.getContentPane().validate();
        mainFrame.getContentPane().repaint();
    }


}
