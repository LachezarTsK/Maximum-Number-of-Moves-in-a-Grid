
public class Solution {

    private static final int START_COLUMN = 0;
    private static final int INITIAL_DISTANCE_FROM_START = 0;

    private static final int[] UP_RIGHT = {-1, 1};
    private static final int[] RIGHT = {0, 1};
    private static final int[] DOWN_RIGHT = {1, 1};
    private static final int[][] MOVES = {UP_RIGHT, RIGHT, DOWN_RIGHT};

    private int rows;
    private int columns;
    private int maxPossibleMovesFromStart;
    private int[][] visitedByMovesFromStart;

    public int maxMoves(int[][] matrix) {
        rows = matrix.length;
        columns = matrix[0].length;
        visitedByMovesFromStart = new int[rows][columns];

        for (int startRow = 0; startRow < rows; ++startRow) {
            findMaxPossibleMovesFromStart(matrix, INITIAL_DISTANCE_FROM_START, startRow, START_COLUMN);
        }

        return maxPossibleMovesFromStart;
    }

    private void findMaxPossibleMovesFromStart(int[][] matrix, int distanceFromStart, int row, int column) {
        maxPossibleMovesFromStart = Math.max(maxPossibleMovesFromStart, distanceFromStart);

        for (int[] move : MOVES) {
            int nextRow = row + move[0];
            int nextColumn = column + move[1];

            if (isInMatrix(nextRow, nextColumn)
                    && matrix[row][column] < matrix[nextRow][nextColumn]
                    && visitedByMovesFromStart[nextRow][nextColumn] < distanceFromStart + 1) {

                visitedByMovesFromStart[nextRow][nextColumn] = distanceFromStart + 1;
                findMaxPossibleMovesFromStart(matrix, distanceFromStart + 1, nextRow, nextColumn);
            }
        }
    }

    private boolean isInMatrix(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
}
