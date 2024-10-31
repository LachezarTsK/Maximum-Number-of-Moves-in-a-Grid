
package main

import "fmt"

const START_COLUMN = 0
const INITIAL_DISTANCE_FROM_START = 0

var UP_RIGHT = []int{-1, 1}
var RIGHT = []int{0, 1}
var DOWN_RIGHT = []int{1, 1}
var MOVES = [][]int{UP_RIGHT, RIGHT, DOWN_RIGHT}

var rows int
var columns int
var maxPossibleMovesFromStart int
var visitedByMovesFromStart [][]int

func maxMoves(matrix [][]int) int {
    rows = len(matrix)
    columns = len(matrix[0])
    maxPossibleMovesFromStart = 0

    visitedByMovesFromStart = make([][]int, rows)
    for row := 0; row < rows; row++ {
        visitedByMovesFromStart[row] = make([]int, columns)
    }

    for startRow := 0; startRow < rows; startRow++ {
        findMaxPossibleMovesFromStart(matrix, INITIAL_DISTANCE_FROM_START, startRow, START_COLUMN)
    }

    return maxPossibleMovesFromStart
}

func findMaxPossibleMovesFromStart(matrix [][]int, distanceFromStart int, row int, column int) {
    maxPossibleMovesFromStart = max(maxPossibleMovesFromStart, distanceFromStart)

    for _, move := range MOVES {
        nextRow := row + move[0]
        nextColumn := column + move[1]

        if isInMatrix(nextRow, nextColumn) &&
               matrix[row][column] < matrix[nextRow][nextColumn] &&
               visitedByMovesFromStart[nextRow][nextColumn] < distanceFromStart+1 {

            visitedByMovesFromStart[nextRow][nextColumn] = distanceFromStart + 1
            findMaxPossibleMovesFromStart(matrix, distanceFromStart+1, nextRow, nextColumn)
        }
    }
}

func isInMatrix(row int, column int) bool {
    return row >= 0 && row < rows && column >= 0 && column < columns
}
