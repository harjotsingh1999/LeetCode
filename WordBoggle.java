import java.util.HashSet;

public class WordBoggle {

    // Given a dictionary of distinct words and an M x N board where every cell has
    // one character. Find all possible words from the dictionary that can be formed
    // by a sequence of adjacent characters on the board. We can move to any of 8
    // adjacent characters, but a word should not have multiple instances of the
    // same cell.

    // Example 1:
    // Input:
    // N = 1
    // dictionary = {"CAT"}
    // R = 3, C = 3
    // board = {{C,A,P},{A,N,D},{T,I,E}}
    // Output:
    // CAT
    // Explanation:
    // C A P
    // A N D
    // T I E
    // Words we got is denoted using same color.

    // Example 2:
    // Input:
    // N = 4
    // dictionary = {"GEEKS","FOR","QUIZ","GO"}
    // R = 3, C = 3
    // board = {{G,I,Z},{U,E,K},{Q,S,E}}
    // Output:
    // GEEKS QUIZ
    // Explanation:
    // G I Z
    // U E K
    // Q S E
    // Words we got is denoted using same color.

    // Your task:
    // You don’t need to read input or print anything. Your task is to complete the
    // function wordBoggle() which takes the dictionary contaning N space-separated
    // strings and R*C board as input parameters and returns a list of words that
    // exist on the board.

    // Expected Time Complexity: O(N*W + R*C^2)
    // Expected Auxiliary Space: O(N*W + R*C)

    // Constraints:
    // 1 ≤ N ≤ 15
    // 1 ≤ R, C ≤ 50
    // 1 ≤ length of Word ≤ 60

    int[] rr = { -1, -1, -1, 1, 1, 1, 0, 0 };
    int[] cc = { 1, -1, 0, 1, -1, 0, 1, -1 };

    public void wordBoggle(char board[][], String[] dictionary) {
        // Write your code here

        HashSet<String> dic = new HashSet<>();
        for (String string : dictionary) {
            dic.add(string);
        }
        HashSet<String> found = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        String str = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWords(i, j, str, board, visited, dic, found);
                // try {
                // Thread.sleep(1000);
                // } catch (InterruptedException e) {
                // //
                // e.printStackTrace();
                // }
            }
        }

        System.out.println(found);
    }

    private void findWords(int i, int j, String str, char board[][], boolean[][] visited, HashSet<String> dic,
            HashSet<String> found) {
        visited[i][j] = true;
        str += board[i][j];

        if (dic.contains(str) && !found.contains(str))
            found.add(str);
        System.out.println("Currently at " + i + "," + j + " str= " + str);
        for (int k = 0; k < 8; k++) {
            int newr = i + rr[k];
            int newc = j + cc[k];
            if (newr < 0 || newr > board.length - 1 || newc < 0 || newc > board[0].length - 1)
                continue;
            else if (visited[newr][newc])
                continue;
            System.out.println("i,j= " + i + "," + j + " and newr, newc= " + newr + "," + newc + " str= " + str);
            findWords(newr, newc, str, board, visited, dic, found);
            // try {
            // Thread.sleep(1000);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }

        }
        str = str.substring(0, str.length() - 1);
        visited[i][j] = false;
    }

    public static void main(String[] args) {
        WordBoggle wordBoggle = new WordBoggle();
        String[] dictionary = { "CAT" };
        char[][] board = { { 'C', 'A', 'P' }, { 'A', 'N', 'D' }, { 'T', 'I', 'E' } };
        wordBoggle.wordBoggle(board, dictionary);
    }
}
