package fr.app.entities;

import fr.app.ressources.Chrono;
import fr.app.ressources.Constantes;

import java.awt.*;

public class EnemiesGroup {

    private final Enemy[][] enemiesGroup = new Enemy[5][10];
    private boolean goRight, isPosition1;
    private int speed;

    public EnemiesGroup() {

        this.enemiesGroupInit();
        this.goRight = true;
        this.isPosition1 = true;
        this.speed = Constantes.ENEMY_SPEED;
    }

    private void enemiesGroupInit() {

        for (int column = 0; column < 10; column++) {
            this.enemiesGroup[0][column] = new Enemy(
                    Constantes.ENEMY_INIT_X_POS + (Constantes.ENEMY_WIDTH + Constantes.ENEMY_COL_GAP) * column,
                    Constantes.ENEMY_INIT_Y_POS,
                    "images/alienHaut1.png",
                    "images/alienHaut2.png");
            for (int row = 1; row < 3; row++) {
                this.enemiesGroup[row][column] = new Enemy(
                        Constantes.ENEMY_INIT_X_POS + (Constantes.ENEMY_WIDTH + Constantes.ENEMY_COL_GAP) * column,
                        Constantes.ENEMY_INIT_Y_POS + Constantes.ENEMY_RAW_GAP * row,
                        "images/alienMilieu1.png",
                        "images/alienMilieu2.png");
            }
            for (int row = 3; row < 5; row++) {
                this.enemiesGroup[row][column] = new Enemy(
                        Constantes.ENEMY_INIT_X_POS + (Constantes.ENEMY_WIDTH + Constantes.ENEMY_COL_GAP) * column,
                        Constantes.ENEMY_INIT_Y_POS + Constantes.ENEMY_RAW_GAP * row,
                        "images/alienBas1.png",
                        "images/alienBas2.png");
            }
        }
    }

    public void drawEnemies(Graphics graphics) {

        if (Chrono.turnCounter % (100 - 10 * this.speed) == 0) {
            this.enemiesMove();
        }
        for (int column = 0; column < 10; column++) {
            for (int row = 0; row < 5; row++) {
                if (this.enemiesGroup[row][column] != null) {
                    this.enemiesGroup[row][column].choiceImg(this.isPosition1);
                    graphics.drawImage(this.enemiesGroup[row][column].getImage(), this.enemiesGroup[row][column].getxPos(), this.enemiesGroup[row][column].getyPos(), null);
                }
            }
        }
    }

    public boolean isLeftBorderReached() {
        for (int column = 0; column < 10; column++) {
            for (int row = 0; row < 5; row++) {
                if (this.enemiesGroup[row][column].getxPos() < Constantes.SCREEN_MARGIN) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isRightBorderReached() {
        for (int column = 0; column < 10; column++) {
            for (int row = 0; row < 5; row++) {
                if (this.enemiesGroup[row][column].getxPos() + this.enemiesGroup[row][column].getWidth() > Constantes.SCREEN_WIDTH - Constantes.SCREEN_MARGIN) {
                    return true;
                }
            }
        }
        return false;
    }

    public void enemiesTurnDown() {
        if(isRightBorderReached()) {
            for (int column = 0; column < 10; column++) {
                for (int row = 0; row < 5; row++) {
                    this.enemiesGroup[row][column].setyPos(this.enemiesGroup[row][column].getyPos() + Constantes.ENEMY_Y_MOVE);
                }
            }
            this.goRight = false;
            if(this.speed < 9) {
                this.speed++;
            }
        } else if(isLeftBorderReached()) {
            for (int column = 0; column < 10; column++) {
                for (int row = 0; row < 5; row++) {
                    this.enemiesGroup[row][column].setyPos(this.enemiesGroup[row][column].getyPos() + Constantes.ENEMY_Y_MOVE);
                }
            }
            this.goRight = true;
            if(this.speed < 9) {
                this.speed++;
            }
        }
    }

    public void enemiesMove() {
        if(this.goRight) {
            for (int column = 0; column < 10; column++) {
                for (int row = 0; row < 5; row++) {
                    this.enemiesGroup[row][column].setxPos(this.enemiesGroup[row][column].getxPos() + Constantes.ENEMY_X_MOVE);
                }
            }
        } else {
            for (int column = 0; column < 10; column++) {
                for (int row = 0; row < 5; row++) {
                    this.enemiesGroup[row][column].setxPos(this.enemiesGroup[row][column].getxPos() - Constantes.ENEMY_X_MOVE);
                }
            }
        }

        this.isPosition1 = !this.isPosition1;
        this.enemiesTurnDown();
    }
}
