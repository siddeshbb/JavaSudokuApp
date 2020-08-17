package sudoku.computationlogic;

import sudoku.constants.GameState;
import sudoku.constants.Rows;
import sudoku.problemdomain.SudokuGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameLogic {

    public  static SudokuGame getNewGame(){
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid()
        );
    }

    public static GameState checkForCompletion(int[][] grid){
        if(sudokuIsInvalid(grid)){
            return GameState.ACTIVE;
        }
        if(tilesAreNotFilled(grid)){
            return GameState.ACTIVE;
        }
        return GameState.COMPLETE;
    }

    public static boolean sudokuIsInvalid(int[][] grid) {
        if(rowsAreInvalid(grid)) return true;
        if(columnsAreInvalid(grid)) return true;
        return squaresAreInvalid(grid);
    }

    private static boolean rowsAreInvalid(int[][] grid) {
        for (int yIndex = 0;yIndex < GRID_BOUNDARY; yIndex++){
            List<Integer> row = new ArrayList<>();
            for (int xIndex = 0;xIndex < GRID_BOUNDARY; xIndex++){
                row.add(grid[xIndex][yIndex]);
            }
            if(collectionHasRepeats(row)){
                return true;
            }
        }
        return false;
    }

    private static boolean columnsAreInvalid(int[][] grid) {
        for (int xIndex = 0;xIndex < GRID_BOUNDARY; xIndex++){
            List<Integer> row = new ArrayList<>();
            for (int yIndex = 0;yIndex < GRID_BOUNDARY; yIndex++){
                row.add(grid[xIndex][yIndex]);
            }
            if(collectionHasRepeats(row)){
                return true;
            }
        }
        return false;
    }

    private static boolean squaresAreInvalid(int[][] grid) {
        if(rowOfSquareIsInvalid(Rows.TOP,grid)) return true;

        if(rowOfSquareIsInvalid(Rows.MIDDLE,grid)) return true;

        return rowOfSquareIsInvalid(Rows.BOTTOM, grid);
    }

    private static boolean rowOfSquareIsInvalid(Rows value, int[][] grid) {
        switch(value){
            case TOP:
                if(squareAreInvalid(0,0,grid)) return true;
                if(squareAreInvalid(0,3,grid)) return true;
                return squareAreInvalid(0, 6, grid);
            case MIDDLE:
                if(squareAreInvalid(3,0,grid)) return true;
                if(squareAreInvalid(3,3,grid)) return true;
                return squareAreInvalid(3, 6, grid);
            case BOTTOM:
                if(squareAreInvalid(6,0,grid)) return true;
                if(squareAreInvalid(6,3,grid)) return true;
                return squareAreInvalid(6, 6, grid);
            default:
                return false;

        }
    }

    private static boolean squareAreInvalid(int xIndex, int yIndex, int[][] grid) {
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();
        while (yIndex < yIndexEnd){
            while (xIndex < xIndexEnd){
                square.add(
                        grid[xIndex][yIndex]
                );
                xIndex++;
            }
            xIndex -=3;
            yIndex++;
        }
        return collectionHasRepeats(square);
    }

    public static boolean collectionHasRepeats(List<Integer> collections) {
        for(int index=1;index< GRID_BOUNDARY;index++){
            if(Collections.frequency(collections,index) > 1) return true;
        }
        return  false;
    }

    public static boolean tilesAreNotFilled(int[][] grid) {
        for (int xIndex=0; xIndex<GRID_BOUNDARY;xIndex++){
            for (int yIndex=0; yIndex<GRID_BOUNDARY;yIndex++){
                if(grid[xIndex][yIndex] == 0){
                    return true;
                }
            }
        }
        return false;
    }
}
