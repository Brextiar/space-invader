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
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Main.scene.spaceship.setxMove(Constantes.SPACESHIP_XMOVE);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Main.scene.spaceship.setxMove(-Constantes.SPACESHIP_XMOVE);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Main.scene.spaceship.setxMove(0);
    }
}
