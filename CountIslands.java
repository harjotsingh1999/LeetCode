import java.util.Scanner;

public class CountIslands {
    // Given a 2D matrix with 0 and 1, where 0 represents water and 1 represents an
    // island, find the total number of island groups connected only vertically and
    // horizontally, not diagonally.

    public void countIslands(int[][] matrix) {
        // boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    DFSTraverseAdjoiningCells(matrix, i, j);
                    count += 1;
                }
                // else {
                // visited[i][j] = true;
                // }
            }
        }
        System.out.println(count);
    }

    private void DFSTraverseAdjoiningCells(int[][] matrix, int i, int j) {
        if (i < 0 || i > matrix.length - 1 || j < 0 || j > matrix[0].length - 1 || matrix[i][j] != 1)
            return;
        matrix[i][j] = 2;
        DFSTraverseAdjoiningCells(matrix, i + 1, j);
        DFSTraverseAdjoiningCells(matrix, i - 1, j);
        DFSTraverseAdjoiningCells(matrix, i, j + 1);
        DFSTraverseAdjoiningCells(matrix, i, j - 1);
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int[][] matrix = { { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
        CountIslands countIslands = new CountIslands();
        countIslands.countIslands(matrix);
        read.close();
    }

}
