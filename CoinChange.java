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

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.count(new int[] { 1, 2, 3 }, 3, 4));
    }
}
