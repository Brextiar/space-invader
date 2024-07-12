package fr.app.game;

import javax.swing.JFrame;

import fr.app.ressources.GameConfig;


public class Main {

    public static Scene scene;
    public static boolean game = true;

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame screen = new JFrame("Space Invaders");
        screen.setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        screen.setResizable(false);
        screen.setLocationRelativeTo(null);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setAlwaysOnTop(true);

        scene = new Scene();
        screen.setContentPane(scene);

        screen.setVisible(true);
    }
}