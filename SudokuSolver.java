
public class SudokuSolver {
    int N = 9;

    // to check if the num to be put in index row, col is valid
    boolean isValidPlacement(int[][] grid, int num, int row, int col) {
        // not valid if num already exists in current column
        for (int i = 0; i < N; i++) {
            if (grid[i][col] == num)
                return false;
        }

        // not valid if num already exists in current row
        for (int i = 0; i < N; i++) {
            if (grid[row][i] == num)
                return false;
        }

        // not valid if num already exists in this submatrix
        // Check if we find the same num
        // in the particular 3*3
        // matrix, we return false
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    boolean solveSudoku(int[][] grid, int currentRow, int currentCol) {
        // sudoku has been solved
        if (currentRow == N - 1 && currentCol == N)
            return true;

        // current row is solved, solve for next row
        // Check if column value becomes 9 ,
        // we move to next row
        // and column start from 0
        if (currentCol == N) {
            currentRow += 1;
            currentCol = 0;
        }

        // Check if the current position
        // of the grid already
        // contains value >0, we iterate
        // for next column
        if (grid[currentRow][currentCol] != 0)
            return solveSudoku(grid, currentRow, currentCol + 1);

        for (int i = 1; i <= N; i++) {
            // Check if it is safe to place
            // the num (1-9) in the
            // given row ,col ->we move to next column
            if (isValidPlacement(grid, i, currentRow, currentCol)) {

                /*
                 * assigning the num in the current (row,col) position of the grid and assuming
                 * our assigned num in the position is correct
                 */
                grid[currentRow][currentCol] = i;

                // Checking for next
                // possibility with next column

                if (solveSudoku(grid, currentRow, currentCol + 1))
                    return true;
            }

            /*
             * removing the assigned num , since our assumption was wrong , and we go for
             * next assumption with diff num value
             */
            grid[currentRow][currentCol] = 0;
        }
        return false;
    }

    void print(int[][] grid) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(grid[i][j] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

        if (solver.solveSudoku(grid, 0, 0))
            solver.print(grid);
        else
            System.out.println("No Solution exists");
    }
}