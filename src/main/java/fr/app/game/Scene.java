package fr.app.game;

import fr.app.entities.EnemiesGroup;
import fr.app.entities.Spaceship;
import fr.app.entities.SpaceshipFire;
import fr.app.ressources.Chrono;
import fr.app.ressources.Clavier;
import fr.app.ressources.Constantes;

import java.awt.*;

import javax.swing.*;

public class Scene extends JPanel {
/***** VARIABLES *****/

    public Spaceship spaceship = new Spaceship();
    public EnemiesGroup enemiesGroup = new EnemiesGroup();
    public SpaceshipFire spaceshipFire = new SpaceshipFire();

/***** CONSTRUCTEUR *****/
    public Scene() {
        super();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier());

        Thread screenChrono = new Thread(new Chrono());
        screenChrono.start();

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
        this.spaceship.drawSpaceship(graphics2D);

        // Affichage des ennemis
        this.enemiesGroup.drawEnemies(graphics2D);

        //affichage du tir
        if (this.spaceshipFire.isFiring()) {
            this.spaceshipFire.drawSpaceshipFire(graphics2D);
        }
    }
}
