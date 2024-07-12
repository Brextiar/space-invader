package fr.app.game;

import fr.app.entities.*;
import fr.app.ressources.Chrono;
import fr.app.ressources.Keyboard;
import fr.app.ressources.GameConfig;

import java.awt.*;

import javax.swing.*;

public class Scene extends JPanel {

    public Spaceship spaceship = new Spaceship();
    public EnemiesGroup enemiesGroup = new EnemiesGroup();
    public SpaceshipFire spaceshipFire = new SpaceshipFire();
    public Castle[] castles = new Castle[4];
    public EnemyFire enemyFire1, enemyFire2, enemyFire3;
    public Ovni ovni;

    private final Font scoreFont = new Font("JetBrains Mono", Font.PLAIN, 15);
    private final Font textFont = new Font("JetBrains Mono", Font.PLAIN, 60);

    public int score = 0;

    /**
     * Constructor
     */
    public Scene() {
        super();

        for (int col = 0; col < 4; col++) {
            this.castles[col] = new Castle(GameConfig.SCREEN_MARGIN + GameConfig.CASTLE_INIT_POS_X + col * (GameConfig.CASTLE_WIDTH + GameConfig.CASTLE_GAP));
        }

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Keyboard());

        Thread screenChrono = new Thread(new Chrono());
        screenChrono.start();

    }

    /**
     *
     * @param graphics the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics graphics2D = (Graphics2D) graphics;

        // Background
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        // draw the flore
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(30, 530, 535, 5);

        //display score
        graphics2D.setFont(this.scoreFont);
        graphics2D.drawString("SCORE : " + score, 400, 25);

        // draw spaceship
        this.spaceship.drawSpaceship(graphics2D);

        //draw castle
        for (Castle castle : this.castles) {
            castle.drawCastle(graphics2D);
        }

        // draw enemies
        this.enemiesGroup.drawEnemies(graphics2D);

        // start game's message
        if (Chrono.turnCounter < 500) {
            graphics2D.setFont(this.textFont);
            graphics2D.setColor(Color.RED);
            graphics2D.drawString("Good luck !", 105, 100);
        }

        // draw SpaceshipFire
        if (this.spaceshipFire.isFiring()) {
            this.spaceshipFire.drawSpaceshipFire(graphics2D);
        }

        // enemy kill detection
        this.enemiesGroup.fireTouchEnemy(this.spaceshipFire);

        // spaceship fire destruct castle
        this.spaceshipFire.spaceShipFireDestructCastle(this.castles);

        // draw EnemiesFires
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
                    if (this.ovni.getxMove() != 0){
                        this.score += GameConfig.OVNI_POINT;
                    }
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

        // end game's messages
        if (!this.spaceship.isAlive()) {
            graphics2D.setFont(this.textFont);
            graphics2D.setColor(Color.RED);
            graphics2D.drawString("GAME OVER", 35, 200);
            graphics2D.drawString("SCORE : " + score, 35, 280);
            graphics2D.setFont(this.scoreFont);
            graphics2D.drawString("PRESS ENTER TO RESTART", 150, 350);
        }

        //add new enemies when all enemies are destroyed
        if (this.enemiesGroup.getEnemiesNbr() == 0) {
            enemiesGroup = new EnemiesGroup();
        }

        // collisiton enemies with spaceship
        if (this.enemiesGroup.lowestEnemyPosition() > GameConfig.SPACESHIP_Y_POS) {
            this.spaceship.destructionOfSpaceship();
        }
    }
}
