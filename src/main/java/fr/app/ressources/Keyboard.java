package fr.app.ressources;

import fr.app.game.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (Main.scene.spaceship.isAlive()) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                Main.scene.spaceship.setxMove(GameConfig.SPACESHIP_X_MOVE);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                Main.scene.spaceship.setxMove(-GameConfig.SPACESHIP_X_MOVE);
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (!Main.scene.spaceshipFire.isFiring()) {
                    Sound.playSound("sounds/sonTirVaisseau.wav");
                    Main.scene.spaceshipFire.setyPos(GameConfig.SPACESHIP_Y_POS - GameConfig.SPACESHIP_FIRE_HEIGHT);
                    Main.scene.spaceshipFire.setxPos(Main.scene.spaceship.getxPos() + (GameConfig.SPACESHIP_WIDTH / 2) - 1);
                    Main.scene.spaceshipFire.setFiring(true);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Main.scene.spaceship.setxMove(0);
    }
}
