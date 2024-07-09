package fr.app.entities;

import fr.app.ressources.Constantes;

import java.awt.*;

public class EnemiesGroup {

    private final Enemy[][] enemiesGroup = new Enemy[5][10];

    public EnemiesGroup() {

        this.enemiesGroupInit();
    }

    private void enemiesGroupInit() {

        for (int column = 0; column < 10; column++) {
            this.enemiesGroup[0][column] = new Enemy(
                    Constantes.ENEMY_INIT_XPOS + (Constantes.ENEMY_WIDTH + Constantes.ENEMY_COL_GAP) * column,
                    Constantes.ENEMY_INIT_YPOS,
                    "images/alienHaut1.png",
                    "images/alienHaut2.png");
            for (int row = 1; row < 3; row++) {
                this.enemiesGroup[row][column] = new Enemy(
                        Constantes.ENEMY_INIT_XPOS + (Constantes.ENEMY_WIDTH + Constantes.ENEMY_COL_GAP) * column,
                        Constantes.ENEMY_INIT_YPOS + Constantes.ENEMY_RAW_GAP * row,
                        "images/alienMilieu1.png",
                        "images/alienMilieu2.png");
            }
            for (int row = 3; row < 5; row++) {
                this.enemiesGroup[row][column] = new Enemy(
                        Constantes.ENEMY_INIT_XPOS + (Constantes.ENEMY_WIDTH + Constantes.ENEMY_COL_GAP) * column,
                        Constantes.ENEMY_INIT_YPOS + Constantes.ENEMY_RAW_GAP * row,
                        "images/alienBas1.png",
                        "images/alienBas2.png");
            }
        }
    }

    public void drawEnemies(Graphics graphics) {
        for (int column = 0; column < 10; column++) {
            for (int row = 0; row < 5; row++) {
                if (this.enemiesGroup[row][column] != null) {
                    graphics.drawImage(this.enemiesGroup[row][column].getImage(), this.enemiesGroup[row][column].getxPos(), this.enemiesGroup[row][column].getyPos(), null);
                }
            }
        }
    }
}
