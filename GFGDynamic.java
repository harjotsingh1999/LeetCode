import java.util.Arrays;

public class GFGDynamic {

    // Given an array of integers, find the length of the longest (strictly)
    // increasing subseRquence from the given array.

    // Example 1:

    // Input:
    // N = 16
    // A[]={0,8,4,12,2,10,6,14,1,9,5
    // 13,3,11,7,15}
    // Output: 6
    // Explanation:Longest increasing subsequence
    // 0 2 6 9 13 15, which has length 6
    // Example 2:

    // Input:
    // N = 6r
    // A[] = {5,8,3,7,9,1}
    // Output: 3
    // Explanation:Longest increasing subsequence
    // 5 7 9, with length 3
    // Your Task:
    // Complete the function longestSubsequence() which takes the input array and
    // its size as input parameters and returns the length of the longest increasing
    // subsequence.

    // Expected Time Complexity : O( N*log(N) )
    // Expected Auxiliary Space: O(N)

    // Constraints:
    // 1 ≤ N ≤ 105
    // 0 ≤ A[i] ≤ 106

    int longestSubsequence(int size, int a[]) {
        // code here
        int[] dp = new int[size];
        Arrays.fill(dp, 1);

        int max = 1;
        for (int i = 1; i < dp.length; i++) {
            int j = 0;

            // for every index, we start from 0, and check if we can add current ith number
            // to the longest increasing subsequence upto j
            // by chceking if a[i]>a[j]
            // if we can, we compute the max and store
            while (j < i) {
                if (a[j] < a[i]) {
                    dp[i] = Math.max(1 + dp[j], dp[i]);
                    j += 1;
                } else {
                    j += 1;
                }
            }
            max = Math.max(dp[i], max);
        }
        System.out.println(Arrays.toString(dp));
        return -1;
    }

    public int longestCommonSubsequence(String s1, String s2) {

        // 0 chars to all chars of both strings
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                // if taking 0 characters(empty string) of either
                // lcs will be 0
                if (i == 0 || j == 0)
                    dp[i][j] = 0;

                // if last character of both strings is same
                // we add 1 and remove the last characters
                // and check for the rst of strings
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                // if character mismatch, then remove either last character
                // and get the max of both
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

                // System.out.println("Strings \"" + s1.substring(0, i) + "\" and \"" +
                // s2.substring(0, j)
                // + "\" have lcs= " + dp[i][j]);
                // try {
                // Thread.sleep(500);
                // } catch (InterruptedException e) {
                // e.printStackTrace();
                // }
            }
        }
        System.out.println("lcs= " + dp[s1.length()][s2.length()]);
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        GFGDynamic gfgDynamic = new GFGDynamic();
        // gfgDynamic.longestSubsequence(8, new int[] { -1, 3, 4, 5, 2, 2, 2, 2 });
        gfgDynamic.longestCommonSubsequence("aggtab", "gxtxayb");
        // gfgDynamic.longestCommonSubsequence("aab", "azb");
    }
}
