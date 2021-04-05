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

    public void countIslands2(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    count += 1;
                    traverseAdjoining(grid, visited, i, j);
                }
            }
        }
        System.out.println("No of islands= " + count);
    }

    private void traverseAdjoining(int[][] grid, boolean[][] visited, int i, int j) {

        // if out of bounds, or if already visited, or if cell contains water,
        // imediately return
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j] || grid[i][j] == 0)
            return;

        // mark current node as visited
        visited[i][j] = true;
        System.out.println("At " + i + " " + j);

        // visiting a row above
        traverseAdjoining(grid, visited, i + 1, j);

        // visiting a row below
        traverseAdjoining(grid, visited, i - 1, j);

        // visiting a column to right
        traverseAdjoining(grid, visited, i, j + 1);

        // visiting a column to left
        traverseAdjoining(grid, visited, i, j - 1);

        // if diagonal is allowed then
        // visit diagonally opposite cells also
        // traverseAdjoining(grid, visited, i + 1, j + 1);
        // traverseAdjoining(grid, visited, i - 1, j - 1);
        // traverseAdjoining(grid, visited, i - 1, j + 1);
        // traverseAdjoining(grid, visited, i + 1, j - 1);
    }

    // Given a grid of dimension nxm containing 0s and 1s. Find the unit area of the
    // largest region of 1s.
    // Region of 1's is a group of 1's connected 8-directionally(horizontally,
    // vertically, dioganally).

    public void findMaxArea(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int currentCount = 0;
                if (!visited[i][j] && grid[i][j] == 1) {
                    currentCount = traverseAdjoiningCells(grid, visited, i, j);
                }
                maxCount = Math.max(currentCount, maxCount);
            }
        }
        System.out.println("Max Area= " + maxCount);
    }

    private int traverseAdjoiningCells(int[][] grid, boolean[][] visited, int i, int j) {
        int count = 0;
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j] || grid[i][j] == 0)
            return 0;

        visited[i][j] = true;
        count += 1;
        int[] rows = { -1, 1, 0, 0, -1, 1, -1, 1 };
        int[] cols = { 0, 0, -1, 1, 1, -1, -1, 1 };
        for (int k = 0; k < rows.length; k++) {
            count += traverseAdjoiningCells(grid, visited, i + rows[k], j + cols[k]);
        }
        // count += traverseAdjoiningCells(grid, visited, i + 1, j);
        // count += traverseAdjoiningCells(grid, visited, i - 1, j);
        // count += traverseAdjoiningCells(grid, visited, i, j + 1);
        // count += traverseAdjoiningCells(grid, visited, i, j - 1);
        // count += traverseAdjoiningCells(grid, visited, i + 1, j + 1);
        // count += traverseAdjoiningCells(grid, visited, i - 1, j - 1);
        // count += traverseAdjoiningCells(grid, visited, i - 1, j + 1);
        // count += traverseAdjoiningCells(grid, visited, i + 1, j - 1);
        return count;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int[][] matrix = { { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 1 } };
        CountIslands countIslands = new CountIslands();
        countIslands.findMaxArea(matrix);
        read.close();
    }

}
