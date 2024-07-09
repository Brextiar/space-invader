package fr.app.entities;

import fr.app.ressources.Constantes;

public class Castle extends Entity {
    private final int ROW_NBR = Constantes.CASTLE_HEIGHT/Constantes.BRICK_DIMENTION;
    private final int COL_NBR = Constantes.CASTLE_WIDTH/Constantes.BRICK_DIMENTION;

    boolean[][] castleArr = new boolean[ROW_NBR][COL_NBR];
}
