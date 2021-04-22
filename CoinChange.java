import java.util.Arrays;
import java.util.HashMap;

public class CoinChange {

    // Given a value N, find the number of ways to make change for N cents, if we
    // have infinite supply of each of S = { S1, S2, .. , SM } valued coins.

    // Example 1:

    // Input:
    // n = 4 , m = 3
    // S[] = {1,2,3}
    // Output: 4
    // Explanation: Four Possible ways are:
    // {1,1,1,1},{1,1,2},{2,2},{1,3}.

    // ‹Example 2:

    // Input:
    // n = 10 , m = 4
    // S[] ={2,5,3,6}
    // Output: 5
    // Explanation: Five Possible ways are:
    // {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5}
    // and {5,5}.

    // Your Task:
    // You don't need to read input or print anything. Your task is to complete the
    // function count() which accepts an array S[] its size m and n as input
    // parameter and returns the number of ways to make change for N cents.

    // Expected Time Complexity: O(m*n).
    // Expected Auxiliary Space: O(n).

    // Constraints:
    // 1 <= n,m <= 103

    public long count(int S[], int m, int n) {
        // code here.
        HashMap<Integer, Long> map = new HashMap<>();
        return countNumberOfWays(S, n, map);
    }

    // wrong because it counts sum = 4 with {1,1,2},{2,1,1},{1,2,1}
    // as three separate answers
    // when they should be counted as 1

    // essentially, it does not count unique ways
    // TODO
    private long countNumberOfWays(int[] s, int target, HashMap<Integer, Long> map) {
        if (map.containsKey(target))
            return map.get(target);
        if (target == 0)
            return 1L;
        if (target < 0)
            return 0L;
        long count = 0L;
        for (int i = 0; i < s.length; i++) {
            count += countNumberOfWays(s, target - s[i], map);
            // if (re != 0L)
            // count += 1;
        }
        System.out.println("ways to make " + target + " = " + count);
        map.put(target, count);
        return count;
    }

    public void countWaysTab(int[] coins, int sum) {
        int[][] dp = new int[coins.length + 1][sum + 1];

        for (int i = 0; i <= coins.length; i++) {
            for (int j = 0; j <= sum; j++) {
                if (i == 0 && j == 0 || j == 0)
                    dp[i][j] = 1;
                else if (i == 0)
                    dp[i][j] = 0;

                // if this coin value is more than sum
                // no option but to skip it

                else if (coins[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];

                // otherwise
                // don't use the coin or
                // use the coin, reduce the sum
                // but stays in the same row
                // because we can use unlimited instances of that coin
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];

            }
        }
        System.out.println(dp[coins.length][sum]);
    }

    // You are given an integer array coins representing coins of different
    // denominations and an integer amount representing a total amount of money.

    // Return the fewest number of coins that you need to make up that amount. If
    // that amount of money cannot be made up by any combination of the coins,
    // return -1.

    // You may assume that you have an infinite number of each kind of coin.

    // Example 1:

    // Input: coins = [1,2,5], amount = 11
    // Output: 3
    // Explanation: 11 = 5 + 5 + 1

    // Example 2:

    // Input: coins = [2], amount = 3
    // Output: -1

    // Example 3:

    // Input: coins = [1], amount = 0
    // Output: 0

    // Example 4:

    // Input: coins = [1], amount = 1
    // Output: 1

    // Example 5:

    // Input: coins = [1], amount = 2
    // Output: 2

    // Constraints:

    // 1 <= coins.length <= 12
    // 1 <= coins[i] <= 231 - 1
    // 0 <= amount <= 104

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        // uncached values
        Arrays.fill(dp, -2);
        return coinChangeUtil(coins, amount, dp);
    }

    private int coinChangeUtil(int[] coins, int amount, int[] dp) {

        /*
         * The minimum coins needed to make change for 0 is always 0 coins no matter
         * what coins we have.
         */
        if (amount == 0)
            return 0;

        /*
         * Minimum coins to make change for a negative amount is -1. This is just a base
         * case we arbitrarily define.
         */
        if (amount < 0)
            return -1;
        if (dp[amount] != -2)
            return dp[amount];

        /*
         * No answer yet. Try each coin as the last coin in the change that we make for
         * the amount
         */
        int minCount = Integer.MAX_VALUE;
        for (int coin : coins) {
            /*
             * If making change was possible (changeResult >= 0) and the change result beats
             * our present minimum, add one to that smallest value
             * 
             * We accept that coin as the last coin in our change making sequence up to this
             * point since it minimizes the coins we need
             */
            int minWaysForRest = coinChangeUtil(coins, amount - coin, dp);
            if (minWaysForRest >= 0 && minWaysForRest < minCount) {
                minCount = minWaysForRest + 1;
            }
        }

        /*
         * If no answer is found (minimum == Integer.MAX_VALUE) then the sub problem
         * answer is just arbitrarily made to be -1, otherwise the sub problem's answer
         * is "minimum"
         */
        minCount = (minCount == Integer.MAX_VALUE) ? -1 : minCount;
        dp[amount] = minCount;
        return minCount;
    }

    public int coinChangeTab(int[] coins, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, target + 1);

        // min no of coins required to sum to 0 is 0
        dp[0] = 0;

        for (int i = 1; i <= target; i++) {
            for (int coin : coins) {
                // bounds check
                if (i - coin >= 0)
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        System.out.println(Arrays.toString(dp));

        /*
         * dp[amount] has our answer. If we do not have an answer then dp[amount] will
         * be amount + 1 and hence dp[amount] > amount will be true. We then return -1.
         * 
         * Otherwise, dp[amount] holds the answer
         */
        return dp[target] > target ? -1 : dp[target];
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChangeTab(new int[] { 2, 4 }, 13));
    }
}
