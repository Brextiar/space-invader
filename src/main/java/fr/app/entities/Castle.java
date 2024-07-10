package fr.app.entities;

import fr.app.ressources.Constantes;

import java.awt.*;

public class Castle extends Entity {
    private final int ROW_NBR = Constantes.CASTLE_HEIGHT/Constantes.BRICK_DIMENTION;
    private final int COL_NBR = Constantes.CASTLE_WIDTH/Constantes.BRICK_DIMENTION;

    boolean[][] castleArr = new boolean[ROW_NBR][COL_NBR];

    public Castle(int xPos) {
        super.xPos = xPos;
        super.yPos = Constantes.CASTLE_POS_Y;

        this.initArrCastle();
    }

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

    public void drawCastle(Graphics graphics) {
        for (int row = 0; row < ROW_NBR; row++) {
            for (int col = 0; col < COL_NBR; col++) {
                if (this.castleArr[row][col]) {
                graphics.setColor(Color.GREEN);
                } else {
                    graphics.setColor(Color.BLACK);
                }
                graphics.fillRect(this.xPos + col * Constantes.BRICK_DIMENTION, this.yPos +row * Constantes.BRICK_DIMENTION, Constantes.BRICK_DIMENTION, Constantes.BRICK_DIMENTION);
            }
        }
    }

    public int findColFired(int xMissile) {
        return (xMissile - this.xPos) / Constantes.BRICK_DIMENTION;
    }

    public int findBottomBrickFired(int col) {
        int row = ROW_NBR - 1;
        while (row >= 0 && !this.castleArr[row][col]) {
            row--;
        }
        return row;
    }

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

    public void bottomCastleDestruction(int xMissile) {
        int col = this.findColFired(xMissile);
        this.deleteBottomBrick(this.findBottomBrickFired(col), col);
    }

    public int findTopBrickFired(int col) {
        int row = 0;
        if (col != -1) {
            while (row < ROW_NBR && !this.castleArr[row][col]) {
                row++;
            }
        }
        return row;
    }

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

    public void topCastleDestruction(int xFire) {
        int col = this.findColFired(xFire);
        this.deleteTopBrick(this.findTopBrickFired(col), col);
    }
}
