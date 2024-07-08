package fr.app.game;

import fr.app.entities.Spaceship;
import fr.app.ressources.Constantes;

import java.awt.*;

import javax.swing.*;

public class Scene extends JPanel {
/***** VARIABLES *****/

    public Spaceship spaceship = new Spaceship();

/***** CONSTRUCTEUR *****/
    public Scene() {
        super();

    }

/***** METHODES *****/

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics graphics2D = (Graphics2D) graphics;

        // Dessin du fond
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, Constantes.SCREEN_WIDTH, Constantes.SCREEN_HEIGHT);

        // Dessin ligne verte bas d'écran
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(30, 530, 535, 5);

        // Affichage du vaisseau
        graphics2D.drawImage(this.spaceship.getImage(), this.spaceship.getxPos(), this.spaceship.getyPos(), null);

    }
}
