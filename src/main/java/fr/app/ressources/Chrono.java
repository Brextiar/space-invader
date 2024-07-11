package fr.app.ressources;

import fr.app.game.Main;

public class Chrono implements Runnable {

    /***** VARIABLES *****/

    private final int PAUSE = 5; //temps d'attente entre 2 tours de boucles : 5 millisecondes
    public static int turnCounter = 0;

    /***** METHODES *****/
    public void run() {
        while (Main.game) {
            turnCounter++;
            Main.scene.repaint();
            try {
                Thread.sleep(PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
