
#include <span>
#include <array>
#include <vector>
#include <algorithm>
using namespace std;

/*
The code will run faster with ios::sync_with_stdio(0).
However, this should not be used in production code and interactive problems.
In this particular problem, it is ok to apply ios::sync_with_stdio(0).

Many of the top-ranked C++ solutions for time on leetcode apply this trick,
so, for a fairer assessment of the time percentile of my code
you could outcomment the lambda expression below for a faster run.
*/

/*
const static auto speedup = [] {
    ios::sync_with_stdio(0);
    return nullptr;
}();
*/

class Solution {

    static const int START_COLUMN = 0;
    static const int INITIAL_DISTANCE_FROM_START = 0;

    static constexpr array<int, 2> UP_RIGHT = { -1, 1 };
    static constexpr array<int, 2> RIGHT = { 0, 1 };
    static constexpr array<int, 2> DOWN_RIGHT = { 1, 1 };
    static constexpr array<array<int, 2>, 4> MOVES = { UP_RIGHT, RIGHT, DOWN_RIGHT };

    size_t rows = 0;
    size_t columns = 0;
    int maxPossibleMovesFromStart = 0;
    vector<vector<int>> visitedByMovesFromStart;

public:
    int maxMoves(const vector<vector<int>>& matrix) {
        rows = matrix.size();
        columns = matrix[0].size();
        visitedByMovesFromStart.resize(rows, vector<int>(columns));

        for (size_t startRow = 0; startRow < rows; ++startRow) {
            findMaxPossibleMovesFromStart(matrix, INITIAL_DISTANCE_FROM_START, startRow, START_COLUMN);
        }

        return maxPossibleMovesFromStart;
    }

private:
    void findMaxPossibleMovesFromStart(span<const vector<int>> matrix, int distanceFromStart, size_t row, size_t column) {
        maxPossibleMovesFromStart = max(maxPossibleMovesFromStart, distanceFromStart);

        for (const auto& move : MOVES) {
            size_t nextRow = row + move[0];
            size_t nextColumn = column + move[1];

            if (isInMatrix(nextRow, nextColumn)
                && matrix[row][column] < matrix[nextRow][nextColumn]
                && visitedByMovesFromStart[nextRow][nextColumn] < distanceFromStart + 1) {

                visitedByMovesFromStart[nextRow][nextColumn] = distanceFromStart + 1;
                findMaxPossibleMovesFromStart(matrix, distanceFromStart + 1, nextRow, nextColumn);
            }
        }
    }

    bool isInMatrix(size_t row, size_t column) const {
        return row < rows && column < columns;
    }
};
