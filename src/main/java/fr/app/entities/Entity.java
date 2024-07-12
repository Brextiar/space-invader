package fr.app.entities;

import javax.swing.*;
import java.awt.*;

public abstract class Entity {

    protected int width, height, xPos, yPos, xMove, yMove;
    protected boolean alive;
    protected String strImg1, strImg2, strImg3;
    protected ImageIcon icon;
    protected Image image;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxMove() {
        return xMove;
    }

    public void setxMove(int xMove) {
        this.xMove = xMove;
    }

    public int getyMove() {
        return yMove;
    }

    public void setyMove(int yMove) {
        this.yMove = yMove;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getStrImg1() {
        return strImg1;
    }

    public void setStrImg1(String strImg1) {
        this.strImg1 = strImg1;
    }

    public String getStrImg2() {
        return strImg2;
    }

    public void setStrImg2(String strImg2) {
        this.strImg2 = strImg2;
    }

    public String getStrImg3() {
        return strImg3;
    }

    public void setStrImg3(String strImg3) {
        this.strImg3 = strImg3;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
