package fr.app.ressources;

import fr.app.game.Main;

public class Chrono implements Runnable {

    public static int turnCounter = 0;

    /**
     * Contains the game loop
     */
    public void run() {
        while (Main.game) {
            turnCounter++;
            Main.scene.repaint();
            try {
                Thread.sleep(GameConfig.PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
