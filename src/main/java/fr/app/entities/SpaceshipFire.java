package fr.app.entities;

import fr.app.game.Main;
import fr.app.ressources.GameConfig;
import fr.app.ressources.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SpaceshipFire extends Entity {

    private boolean isFiring = false;

    /**
     * Constructor
     */
    public SpaceshipFire() {
        super.xPos = 0;
        super.yPos = GameConfig.SPACESHIP_Y_POS - GameConfig.SPACESHIP_FIRE_HEIGHT;
        super.width = GameConfig.SPACESHIP_FIRE_WIDTH;
        super.height = GameConfig.SPACESHIP_FIRE_HEIGHT;
        super.xMove = 0;
        super.yMove = GameConfig.SPACESHIP_FIRE_Y_MOVE;
        super.strImg1 = "images/tirVaisseau.png";
        super.strImg2 = "";
        super.strImg3 = "";
        super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg1)));
        super.image = this.icon.getImage();
    }

    public boolean isFiring() {
        return isFiring;
    }

    public void setFiring(boolean firing) {
        isFiring = firing;
    }

    /**
     *
     * @return the new y position of the spaceship
     */
    public int fireMove() {
        if (isFiring) {
            if (this.yPos > 0) {
                this.yPos = this.yPos - GameConfig.SPACESHIP_FIRE_Y_MOVE;
            } else {
                isFiring = false;
            }
        }
        return yPos;
    }

    /**
     * Display the spaceship fire on the screen
     * @param graphics the graphics object
     */
    public void drawSpaceshipFire(Graphics graphics) {
        graphics.drawImage(this.image, this.xPos, this.fireMove(), null);
    }

    /**
     *
     * @param enemy the enemy
     * @return true if the spaceship fire hit the enemy
     */
    public boolean hasKillEnemy(Enemy enemy) {

        if (this.yPos < enemy.getyPos() + enemy.getHeight()
                && this.yPos + this.height > enemy.getyPos()
                && this.xPos + this.width > enemy.getxPos()
                && this.xPos < enemy.getxPos() + enemy.getWidth()){
            Sound.playSound("sounds/sonAlienMeurt.wav");
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return true if the spaceship fire is in the castle
     */
    private boolean spaceshipFireIsInCastleHeigthRange() {
        return this.yPos < GameConfig.CASTLE_POS_Y + GameConfig.CASTLE_HEIGHT
                && this.yPos + this.height > GameConfig.CASTLE_POS_Y;
    }

    /**
     *
     * @return the number of the castle in which the spaceship fire is
     */
    private int findCastleFired() {
        int castleNbr = -1;
        int col = -1;
        while (castleNbr == -1 && col < 4) {
            col++;
            if (this.xPos + this.width > GameConfig.SCREEN_MARGIN + GameConfig.CASTLE_INIT_POS_X + col * (GameConfig.CASTLE_WIDTH + GameConfig.CASTLE_GAP)
                    && this.xPos < GameConfig.SCREEN_MARGIN + GameConfig.CASTLE_INIT_POS_X + GameConfig.CASTLE_WIDTH + col * (GameConfig.CASTLE_WIDTH + GameConfig.CASTLE_GAP)) {
                castleNbr = col;
            }
        }
        return castleNbr;
    }

    /**
     *
     * @param castle the castle
     * @return the x position of the contact of the spaceship fire with the castle
     */
    public int xFireContactCastle(Castle castle) {
        int xFireContactCastle = -1;
        if (this.xPos + this.width > castle.getxPos() && this.xPos < castle.getxPos() + GameConfig.CASTLE_WIDTH) {
            xFireContactCastle = this.xPos;
        }
        return xFireContactCastle;
    }

    /**
     *
     * @return an array contain the number of the castle in which the spaceship fire is and the x position of the contact of the spaceship fire with the castle
     */
    public int[] touchingFirePosition() {
        int [] castleColFired = {-1, -1};
        if (this.spaceshipFireIsInCastleHeigthRange()) {
            castleColFired[0] = this.findCastleFired();
            if (castleColFired[0] != -1) {
                castleColFired[1] = this.xFireContactCastle(Main.scene.castles[castleColFired[0]]);
            }
        }
        return castleColFired;
    }

    /**
     *
     * @param castles Array of the castles
     */
    public void spaceShipFireDestructCastle (Castle[] castles) {
        int[] touchingFirePosition = this.touchingFirePosition();
        if (touchingFirePosition[0] != -1) {
            if (castles[touchingFirePosition[0]].findBottomBrickFired(castles[touchingFirePosition[0]].findColFired(touchingFirePosition[1])) != -1) {
                castles[touchingFirePosition[0]].bottomCastleDestruction(touchingFirePosition[1]);
                this.yPos = -100;
            }
        }
    }

    /**
     *
     * @param ovni the ovni
     * @return true if the spaceship fire hit the ovni
     */
    public boolean destroyOvni(Ovni ovni) {
        if (this.yPos < ovni.getyPos() + ovni.getHeight()
                && this.yPos + this.height > ovni.getyPos()
                && this.xPos + this.width > ovni.getxPos()
                && this.xPos < ovni.getxPos() + ovni.getWidth()) {
            Sound.playSound("sounds/sonDestructionSoucoupe.wav");
            this.isFiring = false;
            return true;
        } else {
            return false;
        }
    }
}
