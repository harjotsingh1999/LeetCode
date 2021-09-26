public class LeftmostColumnWithAtleastAOne {

    /**
     * A binary matrix means that all elements are 0 or 1. For each individual row
     * of the matrix, this row is sorted in non-decreasing order.
     * 
     * Given a row-sorted binary matrix binaryMatrix, return leftmost column
     * index(0-indexed) with at least a 1 in it. If such index doesn't exist, return
     * -1.
     * 
     * You can't access the Binary Matrix directly. You may only access the matrix
     * using a BinaryMatrix interface:
     * 
     * BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y)
     * (0-indexed). BinaryMatrix.dimensions() returns a list of 2 elements [m, n],
     * which means the matrix is m * n. Submissions making more than 1000 calls to
     * BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that
     * attempt to circumvent the judge will result in disqualification.
     * 
     * For custom testing purposes you're given the binary matrix mat as input in
     * the following four examples. You will not have access the binary matrix
     * directly.
     */

    // find the first occuring 1, in each row, and return the min of them

    // matix(rxc)
    // Tc(rxlog(c)), since for each row, we're doing binary search
    public static int findColumn(int[][] matrix) {
        int col = Integer.MAX_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            col = Math.min(col, findFirstOne(matrix[i]));
            System.out.println("first 1 in row " + i + " " + findFirstOne(matrix[i]));
        }
        return col;
    }

    private static int findFirstOne(int[] arr) {
        int firstOccurance = Integer.MAX_VALUE;
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == 0) {
                low = mid + 1;
            } else {
                firstOccurance = Math.min(firstOccurance, mid);
                high = mid - 1;
            }
        }
        return firstOccurance;
    }

    public static int findMinColumn(int[][] matrix) {

        // starting from top end
        int row = 0, col = matrix[0].length - 1;
        int ans = Integer.MAX_VALUE;
        while (row < matrix.length && col >= 0) {
            System.out.println("at " + row + " " + col);

            // if 1, we update the ans, and check in previous column
            if (matrix[row][col] == 1) {
                ans = Math.min(col, ans);
                col -= 1;
            }
            // if 0, no need to check all columns after this in any row, and move down
            else
                row += 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 0, 0, 0, 1 }, { 0, 0, 1, 1 }, { 0, 1, 1, 1 } };
        System.out.println(findMinColumn(matrix));
        System.out.println(findColumn(matrix));
    }
}
