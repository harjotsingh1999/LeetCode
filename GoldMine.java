public class GoldMine {

    // Given a gold mine of n*m dimensions. Each field in this mine contains a
    // positive integer which is the amount of gold in tons. Initially the miner is
    // at first column but can be at any row. He can move only (right->,right up
    // /,right down\) that is from a given cell, the miner can move to the cell
    // diagonally up towards the right or right or diagonally down towards the
    // right. Find out maximum amount of gold he can collect.
    // Examples:

    // Input : mat[][] = {{1, 3, 3},
    // {2, 1, 4},
    // {0, 6, 4}};
    // Output : 12
    // {(1,0)->(2,1)->(2,2)}

    // Input: mat[][] = { {1, 3, 1, 5},
    // {2, 2, 4, 1},
    // {5, 0, 2, 3},
    // {0, 6, 1, 2}};
    // Output : 16
    // (2,0) -> (1,1) -> (1,2) -> (0,3) OR
    // (2,0) -> (3,1) -> (2,2) -> (2,3)

    // Input : mat[][] = {{10, 33, 13, 15},
    // {22, 21, 04, 1},
    // {5, 0, 2, 3},
    // {0, 6, 14, 2}};
    // Output : 83

    public int goldMine(int[][] grid) {
        int max = Integer.MIN_VALUE;
        // initially miner can be in any row in the first column
        // hence checking for all rows which will give max

        int[][] memo = new int[grid.length][grid[0].length];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            System.out.println("Starting at row" + i);
            int goldObtained = collectGold(i, 0, grid, memo);
            System.out.println("gold obtained= " + goldObtained + " current max= " + max);
            max = Math.max(max, goldObtained);
        }
        return max;
    }

    private int collectGold(int row, int col, int[][] grid, int[][] memo) {
        if (row < 0 || row > grid.length - 1 || col < 0 || col > grid[0].length - 1)
            return 0;
        if (memo[row][col] != -1)
            return memo[row][col];
        System.out.println("at pos " + row + "," + col + " gold here= " + grid[row][col]);

        // can move right, or right+up or right+down
        memo[row][col] = grid[row][col] + Math.max(collectGold(row, col + 1, grid, memo),
                Math.max(collectGold(row - 1, col + 1, grid, memo), collectGold(row + 1, col + 1, grid, memo)));
        return memo[row][col];
    }

    public static void main(String[] args) {
        GoldMine goldMine = new GoldMine();
        int[][] grid = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };
        System.out.println(goldMine.goldMine(grid));
    }
}
