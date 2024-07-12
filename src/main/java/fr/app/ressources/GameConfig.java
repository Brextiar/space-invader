package fr.app.ressources;

public abstract class GameConfig {

    /***** CHRONO *****/
    public static final int PAUSE = 5;

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
    public static final int SPACESHIP_X_POS = (SCREEN_WIDTH - SPACESHIP_WIDTH) / 2;
    public static final int SPACESHIP_Y_POS = 490;

    // Spaceship move
    public static final int SPACESHIP_X_MOVE = 1;

    //Spaceship's Moves limits
    public static final int SPACESHIP_LIMIT_LEFT = 60;
    public static final int SPACESHIP_LIMIT_RIGHT = 500;

    /***** ENEMY *****/
    // Enemy dimensions
    public static final int ENEMY_WIDTH = 33;
    public static final int ENEMY_HEIGHT = 25;

    // Enemy initial position
    public static final int ENEMY_INIT_Y_POS = 120;
    public static final int ENEMY_INIT_X_POS = 29 + SCREEN_MARGIN;
    public static final int ENEMY_RAW_GAP = 40;
    public static final int ENEMY_COL_GAP = 10;

    // Enemy move
    public static final int ENEMY_X_MOVE = 2;
    public static final int ENEMY_Y_MOVE = 20;
    public static final int ENEMY_SPEED = 1;

    // Total number of enemies left
    public static final int TOTAL_ENEMIES = 50;

    /***** Spaceship fire *****/
    public static final int SPACESHIP_FIRE_WIDTH = 3;
    public static final int SPACESHIP_FIRE_HEIGHT = 13;
    public static final int SPACESHIP_FIRE_Y_MOVE = 2;

    /***** CASTLE *****/
    //Brick dimension
    public static final int BRICK_DIMENTION = 2;
    // castle dimension
    public static final int CASTLE_WIDTH = 72;
    public static final int CASTLE_HEIGHT = 54;
    // castle position
    public static final int CASTLE_POS_Y = 400;
    public static final int CASTLE_INIT_POS_X = 39;
    public static final int CASTLE_GAP = 42;

    /***** EnemyFire ****/
    // fire dimension
    public static final int ENEMY_FIRE_WIDTH = 5;
    public static final int ENEMY_FIRE_HEIGHT = 15;
    // fire move
    public static final int ENEMY_FIRE_Y_MOVE = 3;

    /***** OVNI *****/
    public static final int OVNI_WIDTH = 42;
    public static final int OVNI_HEIGHT = 22;
    public static final int OVNI_X_MOVE = 1;
    public static final int OVNI_Y_POSITION = 50;
    public static final int OVNI_INIT_X_POSITION = SCREEN_WIDTH;

    /***** Score *****/
    public static int TOP_ENEMY_POINT = 50;
    public static int MIDDLE_ENEMY_POINT = 40;
    public static int BOTTOM_ENEMY_POINT = 20;
    public static int OVNI_POINT = 100;
}
