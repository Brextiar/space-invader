package fr.app.entities;

import fr.app.game.Main;
import fr.app.ressources.Constantes;
import fr.app.ressources.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SpaceshipFire extends Entity {

    private boolean isFiring = false;

    public SpaceshipFire() {
        super.xPos = 0;
        super.yPos = Constantes.SPACESHIP_Y_POS - Constantes.SPACESHIP_FIRE_HEIGHT;
        super.width = Constantes.SPACESHIP_FIRE_WIDTH;
        super.height = Constantes.SPACESHIP_FIRE_HEIGHT;
        super.xMove = 0;
        super.yMove = Constantes.SPACESHIP_FIRE_Y_MOVE;
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

    public int fireMove() {
        if (isFiring) {
            if (this.yPos > 0) {
                this.yPos = this.yPos - Constantes.SPACESHIP_FIRE_Y_MOVE;
            } else {
                isFiring = false;
            }
        }
        return yPos;
    }

    public void drawSpaceshipFire(Graphics graphics) {
        graphics.drawImage(this.image, this.xPos, this.fireMove(), null);
    }

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

    private boolean spaceshipFireIsInCastleHeigthRange() {
        return this.yPos < Constantes.CASTLE_POS_Y + Constantes.CASTLE_HEIGHT
                && this.yPos + this.height > Constantes.CASTLE_POS_Y;
    }

    private int findCastleFired() {
        int castleNbr = -1;
        int col = -1;
        while (castleNbr == -1 && col < 4) {
            col++;
            if (this.xPos + this.width > Constantes.SCREEN_MARGIN + Constantes.CASTLE_INIT_POS_X + col * (Constantes.CASTLE_WIDTH + Constantes.CASTLE_GAP)
                    && this.xPos < Constantes.SCREEN_MARGIN + Constantes.CASTLE_INIT_POS_X + Constantes.CASTLE_WIDTH + col * (Constantes.CASTLE_WIDTH + Constantes.CASTLE_GAP)) {
                castleNbr = col;
            }
        }
        return castleNbr;
    }

    public int xFireContactCastle(Castle castle) {
        int xFireContactCastle = -1;
        if (this.xPos + this.width > castle.getxPos() && this.xPos < castle.getxPos() + Constantes.CASTLE_WIDTH) {
            xFireContactCastle = this.xPos;
        }
        return xFireContactCastle;
    }

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

    public void spaceShipFireDestructCastle (Castle[] castles) {
        int[] touchingFirePosition = this.touchingFirePosition();
        if (touchingFirePosition[0] != -1) {
            if (castles[touchingFirePosition[0]].findBottomBrickFired(castles[touchingFirePosition[0]].findColFired(touchingFirePosition[1])) != -1) {
                castles[touchingFirePosition[0]].bottomCastleDestruction(touchingFirePosition[1]);
                this.yPos = -100;
            }
        }
    }
}
