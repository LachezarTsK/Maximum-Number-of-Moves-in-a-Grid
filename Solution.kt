
import kotlin.math.max

class Solution {

    private companion object {
        const val START_COLUMN = 0
        const val INITIAL_DISTANCE_FROM_START = 0

        val UP_RIGHT = intArrayOf(-1, 1)
        val RIGHT = intArrayOf(0, 1)
        val DOWN_RIGHT = intArrayOf(1, 1)
        val MOVES = arrayOf(UP_RIGHT, RIGHT, DOWN_RIGHT)
    }

    private var rows = 0
    private var columns = 0
    private var maxPossibleMovesFromStart = 0
    private lateinit var visitedByMovesFromStart: Array<IntArray>

    fun maxMoves(matrix: Array<IntArray>): Int {
        rows = matrix.size
        columns = matrix[0].size
        visitedByMovesFromStart = Array(rows) { IntArray(columns) }

        for (startRow in 0..<rows) {
            findMaxPossibleMovesFromStart(matrix, INITIAL_DISTANCE_FROM_START, startRow, START_COLUMN)
        }

        return maxPossibleMovesFromStart
    }

    private fun findMaxPossibleMovesFromStart(matrix: Array<IntArray>, distanceFromStart: Int, row: Int, column: Int) {
        maxPossibleMovesFromStart = max(maxPossibleMovesFromStart, distanceFromStart)

        for (move in MOVES) {
            val nextRow = row + move[0]
            val nextColumn = column + move[1]

            if (isInMatrix(nextRow, nextColumn)
                    && matrix[row][column] < matrix[nextRow][nextColumn]
                    && visitedByMovesFromStart[nextRow][nextColumn] < distanceFromStart + 1) {

                visitedByMovesFromStart[nextRow][nextColumn] = distanceFromStart + 1
                findMaxPossibleMovesFromStart(matrix, distanceFromStart + 1, nextRow, nextColumn)
            }
        }
    }

    private fun isInMatrix(row: Int, column: Int): Boolean {
        return row in 0..<rows && column in 0..<columns
    }
}
