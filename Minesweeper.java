import java.util.Arrays;

public class Minesweeper {

    // You are given a 2D char matrix representing the game board. 'M' represents an
    // unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a
    // revealed blank square that has no adjacent (above, below, left, right, and
    // all 4 diagonals) mines, digit ('1' to '8') represents how many mines are
    // adjacent to this revealed square, and finally 'X' represents a revealed mine.

    // Now given the next click position (row and column indices) among all the
    // unrevealed squares ('M' or 'E'), return the board after revealing this
    // position according to the following rules:

    // If a mine ('M') is revealed, then the game is over - change it to 'X'.
    // If an empty square ('E') with no adjacent mines is revealed, then change it
    // to revealed blank ('B') and all of its adjacent unrevealed squares should be
    // revealed recursively.
    // If an empty square ('E') with at least one adjacent mine is revealed, then
    // change it to a digit ('1' to '8') representing the number of adjacent mines.
    // Return the board when no more squares will be revealed.

    // Example 1:

    // Input:

    // [['E', 'E', 'E', 'E', 'E'],
    // ['E', 'E', 'M', 'E', 'E'],
    // ['E', 'E', 'E', 'E', 'E'],
    // ['E', 'E', 'E', 'E', 'E']]

    // Click : [3,0]

    // Output:

    // [['B', '1', 'E', '1', 'B'],
    // ['B', '1', 'M', '1', 'B'],
    // ['B', '1', '1', '1', 'B'],
    // ['B', 'B', 'B', 'B', 'B']]

    // Input:

    // [['B', '1', 'E', '1', 'B'],
    // ['B', '1', 'M', '1', 'B'],
    // ['B', '1', '1', '1', 'B'],
    // ['B', 'B', 'B', 'B', 'B']]

    // Click : [1,2]

    // Output:

    // [['B', '1', 'E', '1', 'B'],
    // ['B', '1', 'X', '1', 'B'],
    // ['B', '1', '1', '1', 'B'],
    // ['B', 'B', 'B', 'B', 'B']]

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            System.out.println("Revealed a mine");
            board[click[0]][click[1]] = 'X';
            return board;
        }

        // not really necessary since the recursion takes care of it
        if (countAdjascentMines(board, click[0], click[1]) != 0) {
            System.out.println("Mine adjascent " + countAdjascentMines(board, click[0], click[1]));
            board[click[0]][click[1]] = String.valueOf(countAdjascentMines(board, click[0], click[1])).charAt(0);
            return board;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        revealVertices(board, click[0], click[1], visited);
        return board;
    }

    private void revealVertices(char[][] board, int i, int j, boolean[][] visited) {

        // if out of bounds or if visited, simply return
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || visited[i][j])
            return;

        // mark cell as visited
        visited[i][j] = true;

        // if cell has adjascent mines, place the number of adjascent mines
        if (countAdjascentMines(board, i, j) != 0) {
            board[i][j] = String.valueOf(countAdjascentMines(board, i, j)).charAt(0);
            return;
        }
        // otherwise, mark it as blank revealed and traverse adjascent mines recursively
        board[i][j] = 'B';
        for (int k = -1; k <= 1; k++) {
            for (int k2 = -1; k2 <= 1; k2++) {
                if (!(k == 0 && j == 0))
                    revealVertices(board, i + k, j + k2, visited);
            }
        }
    }

    public int countAdjascentMines(char[][] board, int i, int j) {
        int count = 0;
        for (int k2 = -1; k2 <= 1; k2++) {
            for (int k = -1; k <= 1; k++) {
                if (k2 == 0 && k == 0)
                    continue;
                if (i + k2 < 0 || i + k2 > board.length - 1 || j + k < 0 || j + k > board[0].length - 1)
                    continue;
                if (board[i + k2][j + k] == 'M')
                    count += 1;
            }
        }
        return count;
    }

    public void print(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        char[][] board = { { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'M', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' },
                { 'E', 'E', 'E', 'E', 'E' } };
        minesweeper.print(minesweeper.updateBoard(board, new int[] { 3, 0 }));
    }
}
