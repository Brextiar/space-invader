package fr.app.ressources;

import fr.app.game.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (Main.scene.spaceship.isAlive()) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                Main.scene.spaceship.setxMove(Constantes.SPACESHIP_X_MOVE);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                Main.scene.spaceship.setxMove(-Constantes.SPACESHIP_X_MOVE);
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (!Main.scene.spaceshipFire.isFiring()) {
                    Sound.playSound("sounds/sonTirVaisseau.wav");
                    Main.scene.spaceshipFire.setyPos(Constantes.SPACESHIP_Y_POS - Constantes.SPACESHIP_FIRE_HEIGHT);
                    Main.scene.spaceshipFire.setxPos(Main.scene.spaceship.getxPos() + (Constantes.SPACESHIP_WIDTH / 2) - 1);
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
