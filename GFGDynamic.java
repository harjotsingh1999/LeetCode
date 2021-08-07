import java.util.Arrays;
import java.util.HashMap;

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

    int longestIncreasingSubsequence(int size, int a[]) {
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
        return max;
    }

    // Given an array arr of N positive integers, the task is to find the maximum
    // sum increasing subsequence of the given array.

    // Example 1:

    // Input: N = 5, arr[] = {1, 101, 2, 3, 100}
    // Output: 106
    // Explanation:The maximum sum of a
    // increasing sequence is obtained from
    // {1, 2, 3, 100}
    // Example 2:

    // Input: N = 3, arr[] = {1, 2, 3}
    // Output: 6
    // Explanation:The maximum sum of a
    // increasing sequence is obtained from
    // {1, 2, 3}

    // Your Task:
    // You don't need to read input or print anything. Complete the function
    // maxSumIS() which takes N and array arr as input parameters and returns the
    // maximum value.

    // Expected Time Complexity: O(N2)
    // Expected Auxiliary Space: O(N)

    // Constraints:
    // 1 ≤ N ≤ 103
    // 1 ≤ arr[i] ≤ 105

    // This problem is a variation of standard Longest Increasing Subsequence (LIS)
    // problem. We need a slight change in the Dynamic Programming solution of LIS
    // problem. All we need to change is to use sum as a criteria instead of length
    // of increasing subsequence.

    public int maxSumIncreasingSubsequence(int arr[], int n) {
        // code here.
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
        }

        int max = arr[0];

        for (int i = 1; i < n; i++) {
            int j = 0;
            while (j < i) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[j] + arr[i], dp[i]);
                }
                j += 1;
                max = Math.max(max, dp[i]);
            }
        }
        return max;
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
                else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }

                // if character mismatch, then remove either last character
                // and get the max of both
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

                // System.out.println("Strings \"" + s1.substring(0, i) + "\" and \"" +
                // s2.substring(0, j)
                // + "\" have lcs= " + dp[i][j]);
            }
        }
        System.out.println("lcs= " + dp[s1.length()][s2.length()]);

        // find the actual lCS

        return dp[s1.length()][s2.length()];
    }

    // longest common substring
    int longestCommonSubstr(String s1, String s2, int n, int m) {
        // code here

        int[][] dp = new int[n + 1][m + 1];
        int max = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // no common substring for length=0
                if (i == 0 || j == 0)
                    dp[i][j] = 0;

                // if last character of both string is same
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = 0;

                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }

    // Given an integer N denoting the Length of a line segment. You need to cut the
    // line segment in such a way that the cut length of a line segment each time is
    // either x , y or z. Here x, y, and z are integers.
    // After performing all the cut operations, your total number of cut segments
    // must be maximum.

    // Example 1:

    // Input:
    // N = 4
    // x = 2, y = 1, z = 1
    // Output: 4
    // Explanation:Total length is 4, and the cut
    // lengths are 2, 1 and 1. We can make
    // maximum 4 segments each of length 1.
    // Example 2:

    // Input:
    // N = 5
    // x = 5, y = 3, z = 2
    // Output: 2
    // Explanation: Here total length is 5, and
    // the cut lengths are 5, 3 and 2. We can
    // make two segments of lengths 3 and 2.
    // Your Task:
    // You only need to complete the function maximizeTheCuts() that takes n, x, y,
    // z as parameters and returns max number cuts.

    // Expected Time Complexity : O(N)
    // Expected Auxiliary Space: O(N)

    // Constraints
    // 1 <= N, x, y, z <= 104

    public int maximizeCuts(int n, int x, int y, int z) {
        // Your code here
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        // almost same approach as problems in SumProblems.java
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {

            // if current length is not possible, we continue
            if (dp[i] == -1)
                continue;

            // we move ahead by x, y,z steps ans get the max in each case
            if (x + i <= n) {
                dp[i + x] = Math.max(dp[i + x], 1 + dp[i]);
            }
            if (i + y <= n) {
                dp[i + y] = Math.max(dp[i] + 1, dp[i + y]);
            }
            if (z + i <= n) {
                dp[i + z] = Math.max(dp[i] + 1, dp[i + z]);
            }

        }
        System.out.println(dp[n]);
        return dp[n];
    }

    // Given a string s, return the longest palindromic substring in s.

    // Example 1:

    // Input: s = "babad"
    // Output: "bab"
    // Note: "aba" is also a valid answer.
    // Example 2:

    // Input: s = "cbbd"
    // Output: "bb"
    // Example 3:

    // Input: s = "a"
    // Output: "a"
    // Example 4:

    // Input: s = "ac"
    // Output: "a"

    public String longestPalindromeSubstring(String s) {

        int n = s.length();

        // initially, all substrings of length 1 are palindrome
        int maxL = 1;
        String lps = String.valueOf(s.charAt(0));

        // to store of substring between indices i and j are palindrome or not
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < n; i++) {

            // all substring of one character will be palindrome
            // hence diagonal elements true
            // i.e., substring at 1,1 or 2,2 etc
            dp[i][i] = true;

            // substrings of len=2 will be palindrome
            // if both characters are same
            if (i < n - 1 && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                if (maxL < 2) {
                    maxL = 2;
                    lps = s.substring(i, i + 2);
                }
            }
            // System.out.println(Arrays.toString(dp[i]));
        }

        // starting from len=3
        for (int len = 3; len <= n; len++) {
            // i will vary this way
            // if len=3, i will vary from 0 to n-3
            // and for every i, there will be a j, exactly 3(len) characters ahead
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                // substring bw i, j is palindrome if end characters match
                // and then i and j pointers move closer by one step
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];

                // if current substring length greater than prev max
                // we update
                if (dp[i][j] && maxL < len) {
                    maxL = len;
                    lps = s.substring(i, i + len);
                }
            }
            // System.out.println();
        }

        // for(int i=0;i<n;i++)
        // {
        // System.out.println(Arrays.toString(dp[i]));
        // }
        return lps;
    }

    // Given a string s, find the longest palindromic subsequence's length in s.

    // A subsequence is a sequence that can be derived from another sequence by
    // deleting some or no elements without changing the order of the remaining
    // elements.

    // Example 1:

    // Input: s = "bbbab"
    // Output: 4
    // Explanation: One possible longest palindromic subsequence is "bbbb".
    // Example 2:

    // Input: s = "cbbd"
    // Output: 2
    // Explanation: One possible longest palindromic subsequence is "bb".

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // for string of length 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                System.out.println("len= " + len + " i= " + i + " j= " + j);

                // if end characters match, we move the pointers inward
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = 2 + dp[i + 1][j - 1];

                // otherwise we get the maximum of removing either end character and getting
                // longest palin subseq
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[0][n - 1];
    }

    // Given an array of N integers arr[] where each element represents the max
    // number of steps that can be made forward from that element. Find the minimum
    // number of jumps to reach the end of the array (starting from the first
    // element). If an element is 0, then you cannot move through that element.

    // Example 1:

    // Input:
    // N = 11
    // arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
    // Output: 3
    // Explanation:
    // First jump from 1st element to 2nd
    // element with value 3. Now, from here
    // we jump to 5th element with value 9,
    // and from here we will jump to last.
    // Example 2:

    // Input :
    // N = 6
    // arr = {1, 4, 3, 2, 6, 7}
    // Output: 2
    // Explanation:
    // First we jump from the 1st to 2nd element
    // and then jump to the last element.
    // Your task:
    // You don't need to read input or print anything. Your task is to complete
    // function minJumps() which takes the array arr and it's size N as input
    // parameters and returns the minimum number of jumps.

    // Expected Time Complexity: O(N)
    // Expected Space Complexity: O(1)

    // Constraints:
    // 1 ≤ N ≤ 107
    // 0 ≤ ai ≤ 107

    int minJumps(int[] arr) {
        // your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        return jump(0, arr, map);
    }

    int jump(int curr, int[] arr, HashMap<Integer, Integer> map) {
        if (curr >= arr.length - 1)
            return 0;
        System.out.println("currently at " + curr + " value= " + arr[curr]);
        if (arr[curr] == 0)
            return -1;
        if (map.containsKey(curr))
            return map.get(curr);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= arr[curr]; i++) {
            int minWays = jump(curr + i, arr, map);
            System.out.println("jumping from " + curr + " steps= " + i + " to " + " min ways= " + minWays);
            if (minWays >= 0)
                min = Math.min(minWays + 1, min);
        }
        min = min == Integer.MAX_VALUE ? -1 : min;
        System.out.println("Min jumps from " + curr + " = " + min);
        map.put(curr, min);
        return min;
    }

    int minJumpsTab(int[] arr) {
        if (arr.length == 1)
            return 0;
        if (arr[0] == 0)
            return -1;
        int n = arr.length;
        int[] dp = new int[n];

        // initially none reachable
        Arrays.fill(dp, Integer.MAX_VALUE);

        // min jumps to reach 0 = 0, cuz e start at 0
        dp[0] = 0;

        // for each position we check the min no of jumps to reach that position
        for (int i = 1; i < dp.length; i++) {
            int j = 0;

            // for all indices lesser than i
            while (j < i) {

                // check if i is reachable from j
                // if yes, then calculate the min jumps from j to i by adding 1 to the min jumps
                // to reach j
                if (j + arr[j] >= i)
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                j += 1;
            }
        }
        System.out.println(dp[n - 1] == Integer.MAX_VALUE ? -1 : dp[n - 1]);
        return dp[n - 1] == Integer.MAX_VALUE ? -1 : dp[n - 1];
    }

    static String longestPalin(String S) {
        // code here
        boolean[][] dp = new boolean[S.length()][S.length()];
        int n = S.length();
        int lps = 1;
        String lp = String.valueOf(S.charAt(0));
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (len == 2)
                    dp[i][j] = (S.charAt(i) == S.charAt(j));
                else
                    dp[i][j] = (S.charAt(i) == S.charAt(j)) && dp[i + 1][j - 1];

                if (dp[i][j] && lps < len) {
                    lps = len;
                    lp = S.substring(i, j + 1);
                }
                System.out.println(
                        "len= " + len + " i= " + i + " j= " + j + "dp= " + dp[i][j] + " lps= " + lps + " lp= " + lp);
            }
        }
        System.out.println(lp);
        return lp;
    }

    public static void main(String[] args) {
        GFGDynamic gfgDynamic = new GFGDynamic();
        // gfgDynamic.longestSubsequence(8, new int[] { -1, 3, 4, 5, 2, 2, 2, 2 });
        // gfgDynamic.longestCommonSubsequence("aggtab", "gxtxayb");
        // gfgDynamic.longestCommonSubsequence("aab", "azb");

        // gfgDynamic.maximizeCuts(4, 2, 1, 1);
        // gfgDynamic.longestPalindromeSubseq("agbdba");
        // gfgDynamic.minJumps(new int[] { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 });
        // gfgDynamic.minJumps(new int[] { 1, 4, 3, 2, 6, 7 });
        // gfgDynamic.minJumpsTab(new int[] { 2, 3, 1, 1, 4 });
        // gfgDynamic.minJumpsTab(new int[] { 2, 3, 1, 1, 2, 4, 2, 0, 1, 1 });
        longestPalin("aaaabbaa");
    }
}
