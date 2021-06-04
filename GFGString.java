import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class GFGString {

    /**
     * Reverse words in a given string Easy Accuracy: 50.0% Submissions: 37871
     * Points: 2
     * 
     * Given a String S, reverse the string without reversing its individual words.
     * Words are separated by dots.
     * 
     * Example 1:
     * 
     * Input: S = i.like.this.program.very.much Output:
     * much.very.program.this.like.i Explanation: After reversing the whole
     * string(not individual words), the input string becomes
     * much.very.program.this.like.i
     * 
     * Example 2:
     * 
     * Input: S = pqr.mno Output: mno.pqr Explanation: After reversing the whole
     * string , the input string becomes mno.pqr
     * 
     * 
     * Your Task: You dont need to read input or print anything. Complete the
     * function reverseWords() which takes string S as input parameter and returns a
     * string containing the words in reversed order. Each word in the returning
     * string should also be separated by '.'
     * 
     * 
     * Expected Time Complexity: O(|S|) Expected Auxiliary Space: O(|S|)
     * 
     * 
     * Constraints: 1 <= |S| <= 2000
     * 
     */

    String reverseWords(String S) {
        // code here
        StringTokenizer st = new StringTokenizer(S, ".");
        String s = "";
        while (st.hasMoreTokens()) {
            s = st.nextToken() + "." + s;
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }

    public List<String> find_permutation(String S) {
        // System.out.println("current string= " + S);
        if (S.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            List<String> allOther = find_permutation(S.substring(0, i) + S.substring(i + 1));
            // System.out.println("allother= " + allOther);
            if (!allOther.isEmpty()) {
                for (String string : allOther) {
                    list.add(String.valueOf(ch) + string);
                }
            } else {
                list.add(String.valueOf(ch));
            }
        }

        // to sort the list in lexicographical order, if required
        list.sort(null);
        return list;
    }

    // generate all sbstrings and check for all if palindrome
    // O(n^3)
    public String longestPalinSubstring(String s) {
        int max = 0;
        String out = String.valueOf(s.charAt(0));
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j <= s.length(); j++) {
                String str = s.substring(i, j);
                if (isPalindrome(str)) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        out = str;
                    }
                }
            }
        }
        return out;
    }

    public boolean isPalindrome(String s) {
        System.out.println("check for " + s);
        int start = 0, end = s.length() - 1;
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end))
                return false;
            start += 1;
            end -= 1;
        }
        return true;
    }

    // O(n^2) dp approach
    public String lps(String s) {
        String ans = String.valueOf(s.charAt(0));
        int maxLength = 1;

        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            if (i < s.length() - 1) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
                if (dp[i][i + 1] && maxLength < 2) {
                    maxLength = 2;
                    ans = s.substring(i, i + 2);
                }
            }
        }

        // for strings of length 3 and above
        for (int k = 3; k <= s.length(); k++) {

            // for length k, i varies from 0 to n-k+1
            for (int i = 0; i < s.length() - k + 1; i++) {
                // for start at i and length k, j is
                int j = i + k - 1;
                System.out.println("length= " + k + " i, j= " + i + "," + j);
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                if (dp[i][j] && maxLength < k) {
                    maxLength = k;
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    /**
     * Recursively remove all adjacent duplicates Medium Accuracy: 36.74%
     * Submissions: 1402 Points: 4
     * 
     * Given a string s, remove all its adjacent duplicate characters recursively.
     * 
     * Example 1:
     * 
     * Input: S = "geeksforgeek" Output: "gksforgk" Explanation: g(ee)ksforg(ee)k ->
     * gksforgkâ€‹
     * 
     * 
     * Example 2:
     * 
     * Input: S = "acaaabbbacdddd" Output: "acac" Explanation: ac(aaa)(bbb)ac(dddd)
     * -> acac
     * 
     */

    String remove(String s) {
        // code here
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty())
                stack.push(s.charAt(i));
            else {
                if (stack.peek() == s.charAt(i)) {
                    char ch = stack.pop();
                    System.out.println("match found for " + ch + " at " + i);
                    // i += 1;
                    while (i < s.length() - 1 && ch == s.charAt(i + 1)) {
                        System.out.println("match found for " + ch + " at " + i);
                        i += 1;
                    }
                    System.out.println("stack= " + stack);
                } else
                    stack.push(s.charAt(i));
            }
        }
        String str = "";
        while (!stack.isEmpty()) {
            str = stack.pop() + str;
        }
        return str;
    }

    public static void main(String[] args) {

        GFGString gfgString = new GFGString();
        System.out.println(gfgString.remove("abccbccba"));
    }
}
