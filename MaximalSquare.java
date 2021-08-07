public class MaximalSquare {

    // Given an m x n binary matrix filled with 0's and 1's, find the largest square
    // containing only 1's and return its area.

    // Runtime: 4 ms, faster than 88.06% of Java online submissions for Maximal
    // Square.
    // Memory Usage: 42.1 MB, less than 68.58% of Java online submissions for
    // Maximal Square.

    public int maximalSquare(char[][] matrix) {
        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = matrix[i][j] == '0' ? 0 : 1;
                else if (matrix[i][j] == '1')
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));

                max = Math.max(dp[i][j], max);
                // System.out.println("i= "+i+ " j= "+j +" val= "+dp[i][j]);
            }
        }
        return max * max;
    }

    /**
     * We use a variable to contain the size of the largest square found so far and
     * another variable to store the size of the current, both initialized to 0.
     * Starting from the left uppermost point in the matrix, we search for a 1. No
     * operation needs to be done for a 0. Whenever a 1 is found, we try to find out
     * the largest square that can be formed including that 1. For this, we move
     * diagonally (right and downwards), i.e. we increment the row index and column
     * index temporarily and then check whether all the elements of that row and
     * column are 1 or not. If all the elements happen to be 1, we move diagonally
     * further as previously. If even one element turns out to be 0, we stop this
     * diagonal movement and update the size of the largest square. Now we, continue
     * the traversal of the matrix from the element next to the initial 1 found,
     * till all the elements of the matrix have been traversed.
     */

    public static int maxSquare(String[][] matrix) {
        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // check around this point only if current is 1
                if (matrix[i][j].equals("1")) {

                    System.out.println("starting from " + i + " " + j);
                    int maxSqSizeStartingHere = 1;

                    // to keep track if any zero is found while expanding the matrix starting here
                    boolean noZeroFound = true;

                    // move below diagonally from i,j
                    while (i + maxSqSizeStartingHere < m && j + maxSqSizeStartingHere < n && noZeroFound) {
                        // make sure the row and column of this new position has only 1's

                        int newRow = i + maxSqSizeStartingHere, newCol = j + maxSqSizeStartingHere;
                        for (int k = newRow; k >= i; k--) {
                            if ("0".equals(matrix[k][newCol])) {
                                noZeroFound = false;
                                break;
                            }
                        }

                        for (int k = newCol; k >= j; k--) {
                            if ("0".equals(matrix[newRow][k])) {
                                noZeroFound = false;
                                break;
                            }
                        }
                        if (noZeroFound) {
                            maxSqSizeStartingHere += 1;
                            System.out.println(
                                    "can construct square of size " + maxSqSizeStartingHere + " from " + i + " " + j);
                        } else
                            break;
                    }
                    System.out.println(
                            "finally, can construct square of size " + maxSqSizeStartingHere + " from " + i + " " + j);
                    max = Math.max(max, maxSqSizeStartingHere);
                }
            }
        }
        return max * max;
    }

    public static void main(String[] args) {
        String[][] matrix = { { "1", "0", "1", "0", "0" }, { "1", "0", "1", "1", "1" }, { "1", "1", "1", "1", "1" },
                { "1", "0", "0", "1", "0" } };
        System.out.println(MaximalSquare.maxSquare(matrix));
    }
}
