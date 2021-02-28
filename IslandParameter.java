public class IslandParameter {
    // You are given row x col grid representing a map where grid{i][j] = 1
    // represents land and grid[i][j] = 0 represents water.

    // Grid cells are connected horizontally/vertically (not diagonally). The grid
    // is completely surrounded by water, and there is exactly one island (i.e., one
    // or more connected land cells).

    // The island doesn't have "lakes", meaning the water inside isn't connected to
    // the water around the island. One cell is a square with side length 1. The
    // grid is rectangular, width and height don't exceed 100. Determine the
    // perimeter of the island.

    // Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
    // Output: 16
    // Explanation: The perimeter is the 16 yellow stripes in the image above.

    // Example 2:

    // Input: grid = [[1]]
    // Output: 4

    // Example 3:

    // Input: grid = [[1,0]]
    // Output: 4

    // Runtime: 6 ms, faster than 60.67% of Java online submissions for Island
    // Perimeter.
    // Memory Usage: 40.3 MB, less than 58.04% of Java online submissions for Island
    // Perimeter.
    public int islandPerimeter(int[][] grid) {
        int peri = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {

                    // at both east and west end of grid
                    if (i == 0 && i == grid.length - 1)
                        peri += 2;
                    // cell at west end of grid
                    else if (i == 0) {
                        peri += 1;
                        if (grid[i + 1][j] == 0)
                            peri += 1;

                    } // cell at east end of grid
                    else if (i == grid.length - 1) {
                        peri += 1;
                        if (grid[i - 1][j] == 0)
                            peri += 1;

                    } // neither at east or west end
                    else {
                        if (grid[i - 1][j] == 0)
                            peri += 1;
                        if (grid[i + 1][j] == 0)
                            peri += 1;
                    }
                    if (j == 0 && j == grid[0].length - 1)
                        peri += 2;
                    else if (j == 0) {
                        peri += 1;
                        if (grid[i][j + 1] == 0)
                            peri += 1;
                    } else if (j == grid[0].length - 1) {
                        peri += 1;
                        if (grid[i][j - 1] == 0)
                            peri += 1;
                    } else {
                        if (grid[i][j - 1] == 0)
                            peri += 1;
                        if (grid[i][j + 1] == 0)
                            peri += 1;
                    }
                }
            }
        }
        return peri;
    }

    public static void main(String[] args) {
        IslandParameter islandParameter = new IslandParameter();
        int[][] grid = { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } };
        // int[][] grid = { { 1 } };
        // int[][] grid = { { 1, 0 } };
        System.out.println(islandParameter.islandPerimeter(grid));
    }
}
