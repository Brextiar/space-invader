package fr.app.entities;

import fr.app.ressources.Constantes;

import javax.swing.*;

public class Enemy  extends Entity {

    public Enemy(int xPos, int yPos, String strImg1, String strImg2) {
        super.xPos = xPos;
        super.yPos = yPos;
        super.width = Constantes.ENEMY_WIDTH;
        super.height = Constantes.ENEMY_HEIGHT;
        super.xMove = 0;
        super.yMove = 0;
        super.strImg1 = strImg1;
        super.strImg2 = strImg2;
        super.strImg3 = "images/enemyDetruit.png";
        super.icon = new ImageIcon(getClass().getClassLoader().getResource(super.strImg1));
        super.image = this.icon.getImage();
    }

    public void choiceImg(boolean isPosition1) {
        if (isPosition1) {
            super.icon = new ImageIcon(getClass().getClassLoader().getResource(super.strImg1));
        } else {
            super.icon = new ImageIcon(getClass().getClassLoader().getResource(super.strImg2));
        }

        super.image = this.icon.getImage();
    }
}
