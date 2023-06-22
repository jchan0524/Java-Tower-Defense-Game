import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/* 
 * Author: Justin Chan
 * Date: 05-10-2021
 * Main Class to run the program is in this file 
 */

// The GameFrame class is responsible for creating the main window of the game 
public class GameFrame extends JFrame {

    // The GamePanel instance where the game takes place
    public GamePanel game;

    // Constructor for the GameFrame 
    public GameFrame() {
        // Setting the size of the game window
        this.setSize(10000, 10000);
        // Setting the layout manager for the game window
        this.setLayout(new BorderLayout());

        // Creating a JPanel which acts as the content pane for the frame 
        JPanel windowPane = new JPanel();
        windowPane.setLayout(new BorderLayout());

        // Creating a menu bar with a "File" menu
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("File");

        // Creating a "New Game" menu item
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Starts a new game when the "New Game" menu item is clicked
                game.newGame();
            }
        });

        // Creating a "Quit" menu item
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Exits the program when the "Quit" menu item is clicked
                System.exit(0);
            }
        });

        // Adding menu items to the "File" menu
        menu.add(newGame);
        menu.add(quit);

        // Adding the "File" menu to the menu bar
        bar.add(menu);

        // Adding the menu bar to the north region of the windowPane
        windowPane.add(bar, BorderLayout.NORTH);

        // Creating a GamePanel instance and adding it to the center region of the windowPane
        game = new GamePanel(this);
        windowPane.add(game);

        // Adding windowPane to the center region of the frame
        this.add(windowPane, BorderLayout.CENTER);

        // Set the close operation to exit when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // The main method that launches the game window 
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Create a new GameFrame and make it visible
                new GameFrame().setVisible(true);
            }
        });
    }
}
