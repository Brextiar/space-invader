package fr.app.entities;

import fr.app.ressources.GameConfig;
import fr.app.ressources.Sound;

import java.awt.*;

public class Castle extends Entity {
    private final int ROW_NBR = GameConfig.CASTLE_HEIGHT/ GameConfig.BRICK_DIMENTION;
    private final int COL_NBR = GameConfig.CASTLE_WIDTH/ GameConfig.BRICK_DIMENTION;

    boolean[][] castleArr = new boolean[ROW_NBR][COL_NBR];

    /**
     * Constructor
     *
     * @param xPos the castle's x position
     */
    public Castle(int xPos) {
        super.xPos = xPos;
        super.yPos = GameConfig.CASTLE_POS_Y;

        this.initArrCastle();
    }

    /**
     * Initialize the castle
     */
    public void initArrCastle() {
        for (int row = 0; row < ROW_NBR; row++) {
            for (int col = 0; col < COL_NBR; col++) {
                this.castleArr[row][col] = true;
            }
        }

        for (int col = 0; col < 6; col++) {
            for (int row = 0; row < 2; row++) {
                castleArr[row][col] = false;
                castleArr[row][COL_NBR - col - 1] = false;
            }
        }
        for (int col = 0; col < 4; col++) {
            for (int row = 2; row < 4; row++) {
                castleArr[row][col] = false;
                castleArr[row][COL_NBR - col - 1] = false;
            }
        }
        for (int col = 0; col < 2; col++) {
            for (int row = 4; row < 6; row++) {
                castleArr[row][col] = false;
                castleArr[row][COL_NBR - col - 1] = false;
            }
        }

        for (int row = 18; row < ROW_NBR; row++) {
            for (int col = 10; col < COL_NBR - 10; col++) {
                castleArr[row][col] = false;
                castleArr[row][COL_NBR - col - 1] = false;
            }
        }

        for (int col = 12; col < COL_NBR - 12; col++) {
            for (int row = 16; row < 18; row++) {
                castleArr[row][col] = false;
                castleArr[row][COL_NBR - col - 1] = false;
            }
        }
        for (int col = 14; col < COL_NBR - 14; col++) {
            for (int row = 14; row < 16; row++) {
                castleArr[row][col] = false;
                castleArr[row][COL_NBR - col - 1] = false;
            }
        }
        for (int col = 0; col < 2; col++){
            for (int row = 4; row < 6; row++) {
                castleArr[row][col] = false;
                castleArr[row][COL_NBR - col - 1] = false;
            }
        }
    }

    /**
     *
     * @param graphics the graphics
     */
    public void drawCastle(Graphics graphics) {
        for (int row = 0; row < ROW_NBR; row++) {
            for (int col = 0; col < COL_NBR; col++) {
                if (this.castleArr[row][col]) {
                graphics.setColor(Color.GREEN);
                } else {
                    graphics.setColor(Color.BLACK);
                }
                graphics.fillRect(this.xPos + col * GameConfig.BRICK_DIMENTION, this.yPos +row * GameConfig.BRICK_DIMENTION, GameConfig.BRICK_DIMENTION, GameConfig.BRICK_DIMENTION);
            }
        }
    }

    /**
     *
     * @param xMissile the x position of the missile
     *
     * @return the column where the missile was fired
     */
    public int findColFired(int xMissile) {
        return (xMissile - this.xPos) / GameConfig.BRICK_DIMENTION;
    }

    /**
     *
     * @param col the x posotion where the first bottom brick is
     *
     * @return the y position where the first bottom brick is
     */
    public int findBottomBrickFired(int col) {
        int row = ROW_NBR - 1;
        while (row >= 0 && !this.castleArr[row][col]) {
            row--;
        }
        return row;
    }

    /**
     *
     * @param row the x position of the brick
     * @param col the y position of the brick
     */
    private void deleteBottomBrick(int row, int col) {
        for (int counter = 0; counter < 6; counter++) {
            if (row - counter >= 0) {
                castleArr[row - counter][col] = false;
                if (col < COL_NBR - 1) {
                    castleArr[row - counter][col + 1] = false;
                }
            }
        }
    }

    /**
     *
     * @param xMissile the x position of the missile
     */
    public void bottomCastleDestruction(int xMissile) {
        Sound.playSound("sounds/sonCasseBrique.wav");
        int col = this.findColFired(xMissile);
        this.deleteBottomBrick(this.findBottomBrickFired(col), col);
    }

    /**
     *
     * @param col the column where the missile was fired
     *
     * @return the row where the first top brick is
     */
    public int findTopBrickFired(int col) {
        int row = 0;
        if (col != -1) {
            while (row < ROW_NBR && !this.castleArr[row][col]) {
                row++;
            }
        }
        return row;
    }

    /**
     *
     * @param row the x position of the brick
     * @param col the y position of the brick
     */
    public void deleteTopBrick(int row, int col) {
        for (int counter = 0; counter < 6; counter++) {
            if (row + counter < ROW_NBR && col != -1) {
                castleArr[row + counter][col] = false;
                if (col < COL_NBR - 1) {
                    castleArr[row + counter][col + 1] = false;
                }
            }
        }
    }

    /**
     *
     * @param xFire the column where the missile was fired
     */
    public void topCastleDestruction(int xFire) {
        Sound.playSound("sounds/sonCasseBrique.wav");
        int col = this.findColFired(xFire);
        this.deleteTopBrick(this.findTopBrickFired(col), col);
    }
}
