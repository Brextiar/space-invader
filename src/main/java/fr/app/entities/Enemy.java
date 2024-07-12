package fr.app.entities;

import fr.app.ressources.GameConfig;

import javax.swing.*;
import java.util.Objects;

public class Enemy  extends Entity {

    /**
     * Constructor
     * @param xPos the enemy's x position
     * @param yPos the enemy's y position
     * @param strImg1 the enemy's first image (move 1)
     * @param strImg2 the enemy's second image (move 2)
     */
    public Enemy(int xPos, int yPos, String strImg1, String strImg2) {
        super.xPos = xPos;
        super.yPos = yPos;
        super.width = GameConfig.ENEMY_WIDTH;
        super.height = GameConfig.ENEMY_HEIGHT;
        super.xMove = 0;
        super.yMove = 0;
        super.alive = true;
        super.strImg1 = strImg1;
        super.strImg2 = strImg2;
        super.strImg3 = "images/alienMeurt.png";
        super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg1)));
        super.image = this.icon.getImage();
    }

    /**
     * switch the enemy's image
     *
     * @param isPosition1 a boolean to know which image to use (true for first image, false for second image)
     */
    public void choiceImg(boolean isPosition1) {
        if (this.alive) {
            if (isPosition1) {
                super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg1)));
            } else {
                super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg2)));
            }
        } else {
            super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg3)));
        }

        super.image = this.icon.getImage();
    }
}
