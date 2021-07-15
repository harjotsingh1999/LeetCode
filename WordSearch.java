
public class WordSearch {

    // Given an m x n grid of characters board and a string word, return true if
    // word exists in the grid.

    // The word can be constructed from letters of sequentially adjacent cells,
    // where adjacent cells are horizontally or vertically neighboring. The same
    // letter cell may not be used more than once.

    public boolean exist(char[][] board, String word) {

        int r = board.length;
        int c = board[0].length;

        boolean[][] visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == word.charAt(0) && !visited[i][j]
                        && canConstruct(i, j, r, c, board, visited, word, 1)) {
                    System.out.println("can construct " + word + " from " + i + "," + j);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canConstruct(int i, int j, int r, int c, char[][] board, boolean[][] visited, String word, int pos) {
        visited[i][j] = true;
        if (pos == word.length())
            return true;
        int[] rr = { 1, -1, 0, 0 };
        int[] cc = { 0, 0, -1, 1 };
        for (int k = 0; k < 4; k++) {
            int newr = i + rr[k];
            int newc = j + cc[k];

            if (newr < 0 || newc < 0 || newr >= r || newc >= c)
                continue;

            if (visited[newr][newc])
                continue;

            if (board[newr][newc] != word.charAt(pos))
                continue;

            if (canConstruct(newr, newc, r, c, board, visited, word, pos + 1)) {
                System.out.println("can construct " + word.substring(pos) + " from " + newr + "," + newc);
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();

        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        wordSearch.exist(board, "ABCB"); // false
        wordSearch.exist(board, "ABCCED"); // true
        wordSearch.exist(board, "SEE"); // true
    }
}