package fr.app.entities;

import fr.app.ressources.Chrono;
import fr.app.ressources.Constantes;
import fr.app.ressources.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Ovni extends Entity {
    public Sound soundOvni = new Sound("sounds/sonSoucoupePasse.wav");
    public Sound soundDestroyedOvni = new Sound("sounds/sonDestructionSoucoupe.wav");

    private int counter = 0;

    public Ovni() {
        super.xPos = Constantes.OVNI_INIT_X_POSITION;
        super.yPos = Constantes.OVNI_Y_POSITION;
        super.width = Constantes.OVNI_WIDTH;
        super.height = Constantes.OVNI_HEIGHT;
        super.xMove = Constantes.OVNI_X_MOVE;
        super.yMove = 0;
        super.strImg1 = "images/soucoupe.png";
        super.strImg2 = "images/soucoupe100.png";
        super.strImg3 = "";
        super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg1)));
        super.image = this.icon.getImage();
        super.alive = true;

        this.soundOvni.play();
        this.soundDestroyedOvni.stop();
        this.counter = 0;
    }

    public int moveOvni() {
        if (this.alive && Chrono.turnCounter % 2 == 0) {
            if (this.xPos > 0) {
                this.xPos -= this.xMove;
            } else {
                this.xPos = Constantes.OVNI_INIT_X_POSITION;
            }
        }
        return this.xPos;
    }

    public void drawOvni(Graphics graphics) {
        if (!this.alive) {
            this.ovniDestruction();
        }
        graphics.drawImage(this.image, this.moveOvni(), this.yPos, null);
    }

    public void ovniDestruction() {
        if (counter < 300) {
            super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg2)));
            super.image = this.icon.getImage();
            counter++;
        } else {
            this.xPos = Constantes.OVNI_INIT_X_POSITION;
        }
    }
}
