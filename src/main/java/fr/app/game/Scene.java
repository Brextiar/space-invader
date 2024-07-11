package fr.app.game;

import fr.app.entities.*;
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
    public Castle[] castles = new Castle[4];
    public EnemyFire enemyFire1, enemyFire2, enemyFire3;
    public Ovni ovni;

    /***** CONSTRUCTEUR *****/
    public Scene() {
        super();

        for (int col = 0; col < 4; col++) {
            this.castles[col] = new Castle(Constantes.SCREEN_MARGIN + Constantes.CASTLE_INIT_POS_X + col * (Constantes.CASTLE_WIDTH + Constantes.CASTLE_GAP));
        }

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

        //dessin des chateaux
        for (Castle castle : this.castles) {
            castle.drawCastle(graphics2D);
        }

        // Affichage des ennemis
        this.enemiesGroup.drawEnemies(graphics2D);

        //affichage du tir
        if (this.spaceshipFire.isFiring()) {
            this.spaceshipFire.drawSpaceshipFire(graphics2D);
        }

        // detection kill enemy
        this.enemiesGroup.fireTouchEnemy(this.spaceshipFire);

        // détection contact SpaceshipFire avec Castle
        this.spaceshipFire.spaceShipFireDestructCastle(this.castles);

        // draw EnemiFires
        if (Chrono.turnCounter % 500 == 0) {
            this.enemyFire1 = new EnemyFire(this.enemiesGroup.choiceFiringEnemy());
        }
        if (this.enemyFire1 != null) {
            this.enemyFire1.drawEnemyFire(graphics2D);
            this.enemyFire1.enemyFireDestructCastle(this.castles);
            if (this.enemyFire1.spaceshipTouching(this.spaceship)) {
                this.spaceship.setAlive(false);
            }
        }
        if (Chrono.turnCounter % 750 == 0) {
            this.enemyFire2 = new EnemyFire(this.enemiesGroup.choiceFiringEnemy());
        }
        if (this.enemyFire2 != null) {
            this.enemyFire2.drawEnemyFire(graphics2D);
            this.enemyFire2.enemyFireDestructCastle(this.castles);
            if (this.enemyFire2.spaceshipTouching(this.spaceship)) {
                this.spaceship.setAlive(false);
            }
        }
        if (Chrono.turnCounter % 900 == 0) {
            this.enemyFire3 = new EnemyFire(this.enemiesGroup.choiceFiringEnemy());
        }
        if (this.enemyFire3 != null) {
            this.enemyFire3.drawEnemyFire(graphics2D);
            this.enemyFire3.enemyFireDestructCastle(this.castles);
            if (this.enemyFire3.spaceshipTouching(this.spaceship)) {
                this.spaceship.setAlive(false);
            }
        }
        if (Chrono.turnCounter % 2500 == 0) {
            ovni = new Ovni();
        }
        if (this.ovni != null) {
            if (this.ovni.getxPos() > 0) {
                if (this.spaceshipFire.destroyOvni(this.ovni)) {
                    this.ovni.setxMove(0);
                    this.ovni.setAlive(false);
                    this.ovni.soundOvni.stop();
                    this.ovni.soundDestroyedOvni.play();
                }
                this.ovni.drawOvni(graphics2D);
            } else {
                this.ovni = null;
            }
        }
    }
}
