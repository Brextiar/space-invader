package fr.app.entities;

import fr.app.ressources.Constantes;

import javax.swing.*;

public class Spaceship extends Entity {

    public Spaceship() {
        super.xPos = Constantes.SPACESHIP_XPOS;
        super.yPos = Constantes.SPACESHIP_YPOS;
        super.width = Constantes.SPACESHIP_WIDTH;
        super.height = Constantes.SPACESHIP_HEIGHT;
        super.xMove = Constantes.SPACESHIP_XMOVE;
        super.yMove = 0;
        super.strImg1 = "images/vaisseau.png";
        super.strImg2 = "images/vaisseauDetruit1.png";
        super.strImg3 = "images/vaisseauDetruit2.png";
        super.icon = new ImageIcon(getClass().getClassLoader().getResource(super.strImg1));
        super.image = this.icon.getImage();
    }
}
