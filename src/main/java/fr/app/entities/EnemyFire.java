package fr.app.entities;

import fr.app.ressources.Chrono;
import fr.app.ressources.Constantes;

import javax.swing.*;
import java.awt.*;
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
            super.icon = new ImageIcon(getClass().getClassLoader().getResource(super.strImg1));
        } else {
            super.icon = new ImageIcon(getClass().getClassLoader().getResource(super.strImg2));
        }

        super.image = this.icon.getImage();
    }

    public int enemyFireMove() {
        if(Chrono.turnCounter % 4 == 0) {
            if (this.yPos < 600) {
                this.yPos += Constantes.ENEMY_FIRE_Y_MOVE;
            }
        }
        return this.yPos;
    }

    public void drawEnemyFire(Graphics graphics) {
        graphics.drawImage(this.image, this.xPos, this.enemyFireMove(), null);
    }


}
