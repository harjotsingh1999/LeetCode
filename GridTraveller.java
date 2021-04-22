import java.util.HashMap;

public class GridTraveller {
    // You are at the top left of a 2D grid m*n, have to go to bottom right.
    // You can only move right or down.
    // How many ways to reach destinations

    public void gridTraveller(int m, int n) {
        // System.out.println("Number of ways for " + m + "x" + n + " are " +
        // gridTravel(m, n));
        HashMap<String, Long> memo = new HashMap<>();
        System.out.println("Number of ways for " + m + "x" + n + " are " + gridTravelMemoized(m, n, memo));
    }

    // a 1x1 grid has only 1 way to travel from start to end
    // a 0xn or a mx0 will have zero ways
    // for mxn no. of ways will be gridTravel(m-1, n) (when you take a down step) +
    // gridTravel(m,n-1)(you take a right step)

    // when you take a down step, your problem size reduces by one row,
    // hence you have to now find steps to travel m-1xn grid

    // similarly when you travel to right, your problem size reduces by 1 column
    // hance remaining grid is mxn-1

    // Bruteforce

    // Time complexity = n of function calls = no of nodes in tree= O(2^(n+m))
    // Space complexity= height of tree= O(m+n)
    public int gridTravel(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (m == 1 && n == 1)
            return 1;
        return gridTravel(m - 1, n) + gridTravel(m, n - 1);
    }

    // Time Complexity = O(m x n)
    public long gridTravelMemoized(int m, int n, HashMap<String, Long> memo) {
        String key = Integer.toString(m) + "," + Integer.toString(n);
        if (memo.containsKey(key))
            return memo.get(key);
        if (m == 0 || n == 0)
            return 0;
        if (m == 1 && n == 1)
            return 1;
        memo.put(key, gridTravelMemoized(m - 1, n, memo) + gridTravelMemoized(m, n - 1, memo));
        return memo.get(key);
    }

    public long gridTravellerTab(int m, int n) {
        long[][] dp = new long[m + 1][n + 1];
        // 1 way to reach from start to end of 1x1 grid
        dp[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if ((i == 1 && j == 1))
                    continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        System.out.println(dp[m][n]);
        return dp[m][n];
    }

    public static void main(String[] args) {
        GridTraveller gridTraveller = new GridTraveller();
        gridTraveller.gridTravellerTab(1, 1);
        gridTraveller.gridTravellerTab(3, 2);
        gridTraveller.gridTravellerTab(2, 3);
        gridTraveller.gridTravellerTab(3, 3);
        gridTraveller.gridTravellerTab(18, 18);
    }
}
