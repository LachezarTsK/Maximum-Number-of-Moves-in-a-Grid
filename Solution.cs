
using System;

public class Solution
{
    private static readonly int START_COLUMN = 0;
    private static readonly int INITIAL_DISTANCE_FROM_START = 0;

    private static readonly int[] UP_RIGHT = { -1, 1 };
    private static readonly int[] RIGHT = { 0, 1 };
    private static readonly int[] DOWN_RIGHT = { 1, 1 };
    private static readonly int[][] MOVES = { UP_RIGHT, RIGHT, DOWN_RIGHT };

    private int rows;
    private int columns;
    private int maxPossibleMovesFromStart;
    private int[,] visitedByMovesFromStart;

    public int MaxMoves(int[][] matrix)
    {
        rows = matrix.Length;
        columns = matrix[0].Length;
        visitedByMovesFromStart = new int[rows, columns];

        for (int startRow = 0; startRow < rows; ++startRow)
        {
            FindMaxPossibleMovesFromStart(matrix, INITIAL_DISTANCE_FROM_START, startRow, START_COLUMN);
        }

        return maxPossibleMovesFromStart;
    }

    private void FindMaxPossibleMovesFromStart(int[][] matrix, int distanceFromStart, int row, int column)
    {
        maxPossibleMovesFromStart = Math.Max(maxPossibleMovesFromStart, distanceFromStart);

        foreach (int[] move in MOVES)
        {
            int nextRow = row + move[0];
            int nextColumn = column + move[1];

            if (IsInMatrix(nextRow, nextColumn)
                    && matrix[row][column] < matrix[nextRow][nextColumn]
                    && visitedByMovesFromStart[nextRow, nextColumn] < distanceFromStart + 1)
            {

                visitedByMovesFromStart[nextRow, nextColumn] = distanceFromStart + 1;
                FindMaxPossibleMovesFromStart(matrix, distanceFromStart + 1, nextRow, nextColumn);
            }
        }
    }

    private bool IsInMatrix(int row, int column)
    {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
}
