package fr.app.entities;

import fr.app.ressources.Constantes;

import javax.swing.*;
import java.awt.*;

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
        super.icon = new ImageIcon(getClass().getClassLoader().getResource(super.strImg1));
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
}
