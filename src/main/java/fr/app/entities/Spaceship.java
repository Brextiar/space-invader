package fr.app.entities;

import fr.app.ressources.Chrono;
import fr.app.ressources.GameConfig;
import fr.app.game.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Spaceship extends Entity {

    private int counter = 0;

    /**
     * Constructor
     */
    public Spaceship() {
        super.xPos = GameConfig.SPACESHIP_X_POS;
        super.yPos = GameConfig.SPACESHIP_Y_POS;
        super.width = GameConfig.SPACESHIP_WIDTH;
        super.height = GameConfig.SPACESHIP_HEIGHT;
        super.xMove = 0;
        super.yMove = 0;
        super.strImg1 = "images/vaisseau.png";
        super.strImg2 = "images/vaisseauDetruit1.png";
        super.strImg3 = "images/vaisseauDetruit2.png";
        super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg1)));
        super.image = this.icon.getImage();
        super.alive = true;
    }

    /**
     *
     * @return the new x position of the spaceship
     */
    public int moveSpaceship() {
        if (this.xMove < 0) {
            if (this.xPos > GameConfig.SPACESHIP_LIMIT_LEFT){
                this.xPos = this.xPos + this.xMove;
            }
        } else if (this.xMove > 0) {
            if (this.xPos + this.xMove < GameConfig.SPACESHIP_LIMIT_RIGHT){
                this.xPos = this.xPos + this.xMove;
            }
        }
        return this.xPos;
    }

    /**
     * Display the spaceship on the screen
     * @param graphics the graphics object
     */
    public void drawSpaceship(Graphics graphics) {
        if (!this.alive) {
            this.destructionOfSpaceship();
        }
        graphics.drawImage(this.image, this.moveSpaceship(), this.yPos, null);
    }

    /**
     * Delete the spaceship when it is destroyed
     */
    public void destructionOfSpaceship() {
        if (counter < 300) {
            if (Chrono.turnCounter % 2 == 0) {
                super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg2)));
            } else {
                super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg3)));
            }
            counter++;
        } else {
            Main.game = false;
        }
        super.image = this.icon.getImage();
    }
}
