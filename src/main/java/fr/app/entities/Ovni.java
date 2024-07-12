package fr.app.entities;

import fr.app.ressources.Chrono;
import fr.app.ressources.GameConfig;
import fr.app.ressources.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Ovni extends Entity {
    public Sound soundOvni = new Sound("sounds/sonSoucoupePasse.wav");
    public Sound soundDestroyedOvni = new Sound("sounds/sonDestructionSoucoupe.wav");

    private int counter;

    /**
     * Constructor
     */
    public Ovni() {
        super.xPos = GameConfig.OVNI_INIT_X_POSITION;
        super.yPos = GameConfig.OVNI_Y_POSITION;
        super.width = GameConfig.OVNI_WIDTH;
        super.height = GameConfig.OVNI_HEIGHT;
        super.xMove = GameConfig.OVNI_X_MOVE;
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

    /**
     *
     * @return the new x position of the ovni
     */
    public int moveOvni() {
        if (this.alive && Chrono.turnCounter % 2 == 0) {
            if (this.xPos > 0) {
                this.xPos -= this.xMove;
            } else {
                this.xPos = GameConfig.OVNI_INIT_X_POSITION;
            }
        }
        return this.xPos;
    }

    /**
     * Display the ovni on the screen
     * @param graphics the graphics object
     */
    public void drawOvni(Graphics graphics) {
        if (!this.alive) {
            this.ovniDestruction();
        }
        graphics.drawImage(this.image, this.moveOvni(), this.yPos, null);
    }

    /**
     * Delete the ovni when it's destroyed by the spaceshipFire
     */
    public void ovniDestruction() {
        if (counter < 300) {
            super.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(super.strImg2)));
            super.image = this.icon.getImage();
            counter++;
        } else {
            this.xPos = GameConfig.OVNI_INIT_X_POSITION;
        }
    }
}
