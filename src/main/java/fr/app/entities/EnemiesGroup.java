package fr.app.entities;

import fr.app.ressources.Chrono;
import fr.app.ressources.Constantes;
import fr.app.ressources.Sound;

import java.awt.*;
import java.util.Random;

public class EnemiesGroup {

    private final Enemy[][] enemiesGroup = new Enemy[5][10];
    private boolean goRight, isPosition1;
    private int speed;
    private int enemySoundCounter = 0;

    private final int[] deadEnemies = {-1,-1};

    Random random = new Random();

    private int enemiesNbr = Constantes.TOTAL_ENEMIES;

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
                if (this.enemiesGroup[row][column] != null) {
                    if (this.enemiesGroup[row][column].getxPos() < Constantes.SCREEN_MARGIN) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isRightBorderReached() {
        for (int column = 0; column < 10; column++) {
            for (int row = 0; row < 5; row++) {
                if (this.enemiesGroup[row][column] != null) {
                    if (this.enemiesGroup[row][column].getxPos() + this.enemiesGroup[row][column].getWidth() > Constantes.SCREEN_WIDTH - Constantes.SCREEN_MARGIN) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void enemiesTurnDown() {
        if(isRightBorderReached()) {
            for (int column = 0; column < 10; column++) {
                for (int row = 0; row < 5; row++) {
                    if (this.enemiesGroup[row][column] != null) {
                        this.enemiesGroup[row][column].setyPos(this.enemiesGroup[row][column].getyPos() + Constantes.ENEMY_Y_MOVE);
                    }
                }
            }
            this.goRight = false;
            if(this.speed < 9) {
                this.speed++;
            }
        } else if(isLeftBorderReached()) {
            for (int column = 0; column < 10; column++) {
                for (int row = 0; row < 5; row++) {
                    if (this.enemiesGroup[row][column] != null) {
                        this.enemiesGroup[row][column].setyPos(this.enemiesGroup[row][column].getyPos() + Constantes.ENEMY_Y_MOVE);
                    }
                }
            }
            this.goRight = true;
            if(this.speed < 9) {
                this.speed++;
            }
        }
    }

    public void enemiesMove() {

        if (this.deadEnemies[0] != -1) {
            deleteDeadEnemies(deadEnemies);
            deadEnemies[0] = -1;
        }
        if(this.goRight) {
            for (int column = 0; column < 10; column++) {
                for (int row = 0; row < 5; row++) {
                    if (this.enemiesGroup[row][column] != null) {
                        this.enemiesGroup[row][column].setxPos(this.enemiesGroup[row][column].getxPos() + Constantes.ENEMY_X_MOVE);
                    }
                }
            }
        } else {
            for (int column = 0; column < 10; column++) {
                for (int row = 0; row < 5; row++) {
                    if (this.enemiesGroup[row][column] != null) {
                        this.enemiesGroup[row][column].setxPos(this.enemiesGroup[row][column].getxPos() - Constantes.ENEMY_X_MOVE);
                    }
                }
            }
        }
        this.playEnemySound();
        this.enemySoundCounter++;
        this.isPosition1 = !this.isPosition1;
        this.enemiesTurnDown();
    }

    public void fireTouchEnemy(SpaceshipFire spaceshipFire) {
        for (int column = 0; column < 10; column++) {
            for (int row = 0; row < 5; row++) {
                if (this.enemiesGroup[row][column] != null) {
                    if (spaceshipFire.hasKillEnemy(this.enemiesGroup[row][column])) {
                        this.enemiesGroup[row][column].alive = false;
                        spaceshipFire.yPos = -10;
                        this.deadEnemies[0] = row;
                        this.deadEnemies[1] = column;
                        break;
                    }
                }
            }
        }
    }

    private void deleteDeadEnemies(int[] deadEnemies) {
        this.enemiesGroup[deadEnemies[0]][deadEnemies[1]] = null;
        this.enemiesNbr--;
    }

    public int[] choiceFiringEnemy() {
        int[] enemyXYPos = {-1, -1};
        if (this.enemiesNbr != 0) {
            do {
                int col = random.nextInt(10);
                for (int row = 4; row >= 0; row--) {
                    if (this.enemiesGroup[row][col] != null) {
                        enemyXYPos[0] = this.enemiesGroup[row][col].getxPos();
                        enemyXYPos[1] = this.enemiesGroup[row][col].getyPos();
                        break;
                    }
                }
            } while (enemyXYPos[0] == -1);
        }
        return enemyXYPos;
    }

    private void playEnemySound() {
        int counter = this.enemySoundCounter % 4;
        if (counter == 0) {
            Sound.playSound("sounds/sonAlien1.wav");
        } else if (counter == 1) {
            Sound.playSound("sounds/sonAlien2.wav");
        } else if (counter == 2) {
            Sound.playSound("sounds/sonAlien3.wav");
        } else {
            Sound.playSound("sounds/sonAlien4.wav");
        }
    }
}
