package fr.app.ressources;

public abstract class Constantes {

    /***** SCREEN *****/
    // Screen dimensions
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_MARGIN = 50;

    /***** SPACESHIP *****/
    // Spaceship dimensions
    public static final int SPACESHIP_WIDTH = 39;
    public static final int SPACESHIP_HEIGHT = 24;

    // Spaceship initial position
    public static final int SPACESHIP_XPOS = (SCREEN_WIDTH - SPACESHIP_WIDTH) / 2;
    public static final int SPACESHIP_YPOS = 490;

    // Spaceship move
    public static final int SPACESHIP_XMOVE = 1;

    //Spaceship's Moves limits
    public static final int SPACESHIP_LIMIT_LEFT = 60;
    public static final int SPACESHIP_LIMIT_RIGHT = 500;

}
