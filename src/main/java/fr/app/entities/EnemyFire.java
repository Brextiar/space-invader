package fr.app.entities;

import fr.app.game.Main;
import fr.app.ressources.Chrono;
import fr.app.ressources.Constantes;
import fr.app.ressources.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class EnemyFire extends Entity {

    Random random = new Random();

    public EnemyFire(int[] enemyPositionArr) {
        super.xPos = enemyPositionArr[0] + Constantes.ENEMY_WIDTH / 2 - 1;
        super.yPos = enemyPositionArr[1] + Constantes.ENEMY_HEIGHT;
        super.width = Constantes.ENEMY_FIRE_WIDTH;
        super.height = Constantes.ENEMY_FIRE_HEIGHT;
        super.xMove = 0;
        super.yMove = Constantes.ENEMY_Y_MOVE;
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

    public int enemyFireMove() {
        if (Chrono.turnCounter % 4 == 0) {
            if (this.yPos < 600) {
                this.yPos += Constantes.ENEMY_FIRE_Y_MOVE;
            }
        }
        return this.yPos;
    }

    public void drawEnemyFire(Graphics graphics) {
        graphics.drawImage(this.image, this.xPos, this.enemyFireMove(), null);
    }

    public boolean enemyFireIsInCastleHeigthRange() {
        return this.yPos < Constantes.CASTLE_POS_Y + Constantes.CASTLE_HEIGHT
                && this.yPos + this.height > Constantes.CASTLE_POS_Y;
    }

    private int findFiredCastle() {
        int castleNbr = -1;
        int col = -1;
        while (castleNbr == -1 && col < 4) {
            col++;
            if (this.xPos + this.width - 1 > Constantes.SCREEN_MARGIN + Constantes.CASTLE_INIT_POS_X + col * (Constantes.CASTLE_WIDTH + Constantes.CASTLE_GAP)
                    && this.xPos + 1 < Constantes.SCREEN_MARGIN + Constantes.CASTLE_INIT_POS_X + Constantes.CASTLE_WIDTH + col * (Constantes.CASTLE_WIDTH + Constantes.CASTLE_GAP)) {
                castleNbr = col;
            }
        }
        return castleNbr;
    }

    private int xFireContactCastle(Castle castle) {
        int xFireContactCastle = -1;
        if (this.xPos + this.width > castle.getxPos() && this.xPos < castle.getxPos() + Constantes.CASTLE_WIDTH) {
            xFireContactCastle = this.xPos;
        }
        return xFireContactCastle;
    }

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
