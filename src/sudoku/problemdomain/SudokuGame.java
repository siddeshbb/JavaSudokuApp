package sudoku.problemdomain;

import sudoku.constants.GameState;

import java.io.Serializable;

public class SudokuGame implements Serializable {

    private final GameState gameState;
    private final int[][] gridState;

    public static final int GRID_BOUNDARY=9;

    public SudokuGame(GameState gameState, int[][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int[][] getCopyOfGridState() {
        //return SudokuUtilites.copyToNewArray(gridState);
        return gridState;
    }

    public static int getGridBoundary() {
        return GRID_BOUNDARY;
    }

}
