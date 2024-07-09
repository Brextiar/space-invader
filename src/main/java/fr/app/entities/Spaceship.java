package fr.app.entities;

import fr.app.ressources.Constantes;

import javax.swing.*;
import java.awt.*;

public class Spaceship extends Entity {

    public Spaceship() {
        super.xPos = Constantes.SPACESHIP_X_POS;
        super.yPos = Constantes.SPACESHIP_Y_POS;
        super.width = Constantes.SPACESHIP_WIDTH;
        super.height = Constantes.SPACESHIP_HEIGHT;
        super.xMove = 0;
        super.yMove = 0;
        super.strImg1 = "images/vaisseau.png";
        super.strImg2 = "images/vaisseauDetruit1.png";
        super.strImg3 = "images/vaisseauDetruit2.png";
        super.icon = new ImageIcon(getClass().getClassLoader().getResource(super.strImg1));
        super.image = this.icon.getImage();
    }

    public int moveSpaceship() {
        if (this.xMove < 0) {
            if (this.xPos > Constantes.SPACESHIP_LIMIT_LEFT){
                this.xPos = this.xPos + this.xMove;
            }
        } else if (this.xMove > 0) {
            if (this.xPos + this.xMove < Constantes.SPACESHIP_LIMIT_RIGHT){
                this.xPos = this.xPos + this.xMove;
            }
        }
        return this.xPos;
    }

    public void drawSpaceship(Graphics graphics) {
        graphics.drawImage(this.image, this.moveSpaceship(), this.yPos, null);
    }
}
