public class SortedMatrixSearch {

    // search in a rowwise and column wise sorted matrix

    // typecasted as a 1d array
    // Please note that this problem is different from Search in a row wise and
    // column wise sorted matrix. Here matrix is more strictly sorted as the first
    // element of a row is greater than the last element of the previous row.
    public static void search(int[][] matrix, int key, int low, int high) {
        System.out.println("searching bw " + low + " " + high);
        if (low > high) {
            System.out.println("key not found");
            return;
        }
        int mid = low + (high - low) / 2;
        int row = mid / matrix.length;
        int col = mid % matrix[0].length;
        System.out.println("mid= " + mid + " row= " + row + " col= " + col + " ele= " + matrix[row][col]);
        if (matrix[row][col] == key) {
            System.out.println("found key at " + row + "," + col);
            return;
        } else if (matrix[row][col] < key)
            search(matrix, key, mid + 1, high);
        else
            search(matrix, key, low, mid - 1);
    }

    // Search in a row wise and column wise sorted matrix

    /**
     * Approach: The simple idea is to remove a row or column in each comparison
     * until an element is found. Start searching from the top-right corner of the
     * matrix. There are three possible cases.
     * 
     * 
     * The given number is greater than the current number: This will ensure that
     * all the elements in the current row are smaller than the given number as the
     * pointer is already at the right-most elements and the row is sorted. Thus,
     * the entire row gets eliminated and continues the search for the next row.
     * Here, elimination means that a row needs not be searched.
     * 
     * 
     * The given number is smaller than the current number: This will ensure that
     * all the elements in the current column are greater than the given number.
     * Thus, the entire column gets eliminated and continues the search for the
     * previous column, i.e. the column on the immediate left.
     * 
     * 
     * The given number is equal to the current number: This will end the search.
     * 
     * 
     * Time Complexity: O(n). Only one traversal is needed, i.e, i from 0 to n and j
     * from n-1 to 0 with at most 2*n steps. The above approach will also work for m
     * x n matrix (not only for n x n). Complexity would be O(m + n). Space
     * Complexity: O(1). No extra space is required.
     * 
     * @param matrix
     */

    public static void search2(int[][] matrix, int key, int curRow, int curCol) {
        if (curRow >= matrix.length || curCol < 0) {
            System.out.println("element not found");
            return;
        }
        System.out.println("currently at " + curRow + " " + curCol);
        if (matrix[curRow][curCol] == key) {
            System.out.println("key found at " + curRow + "," + curCol);
            return;
        } else if (matrix[curRow][curCol] > key) {
            // search in the previous column because all elements in this column will be
            // larger
            search2(matrix, key, curRow, curCol - 1);
        } else {

            // search in the next row because all element in this row will be smaller
            search2(matrix, key, curRow + 1, curCol);
        }
    }

    public static void main(String[] args) {
        // int[][] matrix = { { 1, 5, 9 }, { 14, 20, 21 }, { 30, 34, 43 } };
        // search(matrix, -1, 0, 8);

        int mat[][] = { { 10, 20, 30, 40 }, { 15, 25, 35, 45 }, { 27, 29, 37, 48 }, { 32, 33, 39, 50 } };

        search2(mat, 51, 0, 3);
    }
}
