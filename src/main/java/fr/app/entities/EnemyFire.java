package fr.app.entities;

import fr.app.game.Main;
import fr.app.ressources.Chrono;
import fr.app.ressources.GameConfig;
import fr.app.ressources.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class EnemyFire extends Entity {

    Random random = new Random();

    /**
     * Constructor
     * @param enemyPositionArr the position of the enemy which fired
     */
    public EnemyFire(int[] enemyPositionArr) {
        super.xPos = enemyPositionArr[0] + GameConfig.ENEMY_WIDTH / 2 - 1;
        super.yPos = enemyPositionArr[1] + GameConfig.ENEMY_HEIGHT;
        super.width = GameConfig.ENEMY_FIRE_WIDTH;
        super.height = GameConfig.ENEMY_FIRE_HEIGHT;
        super.xMove = 0;
        super.yMove = GameConfig.ENEMY_Y_MOVE;
        super.strImg1 = "images/tirAlien1.png";
        super.strImg2 = "images/tirAlien2.png";
        super.strImg3 = "";
        if (random.nextInt(2) == 0) {
            super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg1)));
        } else {
            super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg2)));
        }

        super.image = this.icon.getImage();
    }

    /**
     * Move the enemy fire
     * @return the new position of the enemy fire
     */
    public int enemyFireMove() {
        if (Chrono.turnCounter % 4 == 0) {
            if (this.yPos < 600) {
                this.yPos += GameConfig.ENEMY_FIRE_Y_MOVE;
            }
        }
        return this.yPos;
    }

    /**
     *
     * @param graphics the graphics object
     */
    public void drawEnemyFire(Graphics graphics) {
        graphics.drawImage(this.image, this.xPos, this.enemyFireMove(), null);
    }

    /**
     *
     * @return true if the enemy fire is in the castle's height range
     */
    public boolean enemyFireIsInCastleHeigthRange() {
        return this.yPos < GameConfig.CASTLE_POS_Y + GameConfig.CASTLE_HEIGHT
                && this.yPos + this.height > GameConfig.CASTLE_POS_Y;
    }

    /**
     *
     * @return the number of the castle in which the enemy fire is
     */
    private int findFiredCastle() {
        int castleNbr = -1;
        int col = -1;
        while (castleNbr == -1 && col < 4) {
            col++;
            if (this.xPos + this.width - 1 > GameConfig.SCREEN_MARGIN + GameConfig.CASTLE_INIT_POS_X + col * (GameConfig.CASTLE_WIDTH + GameConfig.CASTLE_GAP)
                    && this.xPos + 1 < GameConfig.SCREEN_MARGIN + GameConfig.CASTLE_INIT_POS_X + GameConfig.CASTLE_WIDTH + col * (GameConfig.CASTLE_WIDTH + GameConfig.CASTLE_GAP)) {
                castleNbr = col;
            }
        }
        return castleNbr;
    }

    /**
     *
     * @param castle the castle in which the enemy fire is
     * @return the x position of the contact of the enemy fire with the castle
     */
    private int xFireContactCastle(Castle castle) {
        int xFireContactCastle = -1;
        if (this.xPos + this.width > castle.getxPos() && this.xPos < castle.getxPos() + GameConfig.CASTLE_WIDTH) {
            xFireContactCastle = this.xPos;
        }
        return xFireContactCastle;
    }

    /**
     *
     * @return an array contain the number of the castle in which the enemy fire is and the x position of the contact of the enemy fire with the castle
     */
    public int[] touchingFirePosition() {
        int[] touchingFirePosition = {-1, -1};
        if (this.enemyFireIsInCastleHeigthRange()) {
            touchingFirePosition[0] = this.findFiredCastle();
            if (touchingFirePosition[0] != -1) {
                touchingFirePosition[1] = this.xFireContactCastle(Main.scene.castles[touchingFirePosition[0]]);
            }
        }
        return touchingFirePosition;
    }

    /**
     *
     * @param castles Array of the castles
     */
    public void enemyFireDestructCastle(Castle[] castles) {
        int[] touchingFirePosition = this.touchingFirePosition();
        if (touchingFirePosition[0] != -1) {
            if (castles[touchingFirePosition[0]].findTopBrickFired(castles[touchingFirePosition[0]].findColFired(touchingFirePosition[1])) != -1
                    && castles[touchingFirePosition[0]].findTopBrickFired(castles[touchingFirePosition[0]].findColFired(touchingFirePosition[1])) != 27) {
                castles[touchingFirePosition[0]].topCastleDestruction(touchingFirePosition[1]);
                this.yPos = 700;
            }
        }
    }

    /**
     *
     * @param spaceship the spaceship
     * @return true if the enemy fire is touching the spaceship
     */
    public boolean spaceshipTouching(Spaceship spaceship) {
        if (this.yPos < spaceship.getyPos() + spaceship.getHeight()
                && this.yPos + this.height > spaceship.getyPos()
                && this.xPos + this.width > spaceship.getxPos()
                && this.xPos < spaceship.getxPos() + spaceship.getWidth()) {
            this.yPos = 700;
            Sound.playSound("sounds/sonDestructionVaisseau.wav");
            return true;
        }
        return false;
    }


}
