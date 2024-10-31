
function maxMoves(matrix: number[][]): number {
    const START_COLUMN = 0;
    const INITIAL_DISTANCE_FROM_START = 0;

    const UP_RIGHT = [-1, 1];
    const RIGHT = [0, 1];
    const DOWN_RIGHT = [1, 1];
    this.MOVES = [UP_RIGHT, RIGHT, DOWN_RIGHT];

    this.rows = matrix.length;
    this.columns = matrix[0].length;
    this.maxPossibleMovesFromStart = 0;
    this.visitedByMovesFromStart = Array.from(new Array(this.rows), () => new Array(this.columns).fill(0));

    for (let startRow = 0; startRow < this.rows; ++startRow) {
        findMaxPossibleMovesFromStart(matrix, INITIAL_DISTANCE_FROM_START, startRow, START_COLUMN);
    }

    return this.maxPossibleMovesFromStart;
};

function findMaxPossibleMovesFromStart(matrix: number[][], distanceFromStart: number, row: number, column: number): void {
    this.maxPossibleMovesFromStart = Math.max(this.maxPossibleMovesFromStart, distanceFromStart);

    for (let move of this.MOVES) {
        const nextRow = row + move[0];
        const nextColumn = column + move[1];

        if (isInMatrix(nextRow, nextColumn)
            && matrix[row][column] < matrix[nextRow][nextColumn]
            && this.visitedByMovesFromStart[nextRow][nextColumn] < distanceFromStart + 1) {

            this.visitedByMovesFromStart[nextRow][nextColumn] = distanceFromStart + 1;
            findMaxPossibleMovesFromStart(matrix, distanceFromStart + 1, nextRow, nextColumn);
        }
    }
}

function isInMatrix(row: number, column: number): boolean {
    return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
}
