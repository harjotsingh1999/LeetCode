import java.util.*;

public class WordSearch2 {

    /**
     * Given an m x n board of characters and a list of strings words, return all
     * words on the board.
     * 
     * Each word must be constructed from letters of sequentially adjacent cells,
     * where adjacent cells are horizontally or vertically neighboring. The same
     * letter cell may not be used more than once in a word.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * Input: board =
     * [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
     * words = ["oath","pea","eat","rain"] Output: ["eat","oath"] Example 2:
     * 
     * 
     * Input: board = [["a","b"],["c","d"]], words = ["abcb"] Output: []
     * 
     * 
     * Constraints:
     * 
     * m == board.length n == board[i].length 1 <= m, n <= 12 board[i][j] is a
     * lowercase English letter. 1 <= words.length <= 3 * 104 1 <= words[i].length
     * <= 10 words[i] consists of lowercase English letters. All the strings of
     * words are unique.
     */

    int[] rr = { -1, 1, 0, 0 };
    int[] cc = { 0, 0, -1, 1 };

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        for (String str : words) {
            if (canConstruct(str, board))
                ans.add(str);
        }
        return ans;
    }

    public boolean canConstruct(String word, char[][] board) {
        int r = board.length;
        int c = board[0].length;

        boolean[][] visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == word.charAt(0) && exists(word, board, visited, 1, i, j, r, c))
                    return true;
            }
        }
        return false;
    }

    public boolean exists(String word, char[][] board, boolean[][] visited, int pos, int curr, int curc, int r, int c) {
        if (pos == word.length())
            return true;
        visited[curr][curc] = true;
        for (int i = 0; i < 4; i++) {
            int newr = curr + rr[i];
            int newc = curc + cc[i];
            if (newr < 0 || newc < 0 || newr >= r || newc >= c)
                continue;
            if (visited[newr][newc])
                continue;
            if (board[newr][newc] == word.charAt(pos) && exists(word, board, visited, pos + 1, newr, newc, r, c))
                return true;
        }
        visited[curr][curc] = false;
        return false;
    }
}
