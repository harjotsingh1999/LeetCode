import java.util.*;

public class EditDistance {

    /**
     * 
     * Given two strings word1 and word2, return the minimum number of operations
     * required to convert word1 to word2.
     * 
     * You have the following three operations permitted on a word:
     * 
     * Insert a character Delete a character Replace a character
     * 
     * 
     * Example 1:
     * 
     * Input: word1 = "horse", word2 = "ros" Output: 3 Explanation: horse -> rorse
     * (replace 'h' with 'r') rorse -> rose (remove 'r') rose -> ros (remove 'e')
     * Example 2:
     * 
     * Input: word1 = "intention", word2 = "execution" Output: 5 Explanation:
     * intention -> inention (remove 't') inention -> enention (replace 'i' with
     * 'e') enention -> exention (replace 'n' with 'x') exention -> exection
     * (replace 'n' with 'c') exection -> execution (insert 'u')
     * 
     * 
     * Constraints:
     * 
     * 0 <= word1.length, word2.length <= 500 word1 and word2 consist of lowercase
     * English letters.
     */
    public int minDistance(String word1, String word2) {
        HashMap<String, Integer> memo = new HashMap<>();
        return find(word1, word2, 0, 0, memo);
    }

    // without memo O(3^n), space 1
    // with memo O(mxn), same space
    public int find(String word1, String word2, int cur1, int cur2, HashMap<String, Integer> memo) {

        String key = cur1 + "," + cur2;

        // if reached end of both simultaneously, then no more changes required
        if (cur1 == word1.length() && cur2 == word2.length())
            return 0;

        // if one word has ended,
        // then the number of characters remaining in other word
        // will have to be deleted
        if (cur1 == word1.length())
            return word2.length() - cur2;
        if (cur2 == word2.length())
            return word1.length() - cur1;

        if (memo.containsKey(key))
            return memo.get(key);

        char ch1 = word1.charAt(cur1);
        char ch2 = word2.charAt(cur2);

        int ans = 0;

        // if characters at both indices same, then no changes are required
        // hence move to next character
        if (ch1 == ch2)
            ans = find(word1, word2, cur1 + 1, cur2 + 1, memo);

        // otherwise find the minimum
        else
            ans = 1 + Math.min(find(word1, word2, cur1 + 1, cur2 + 1, memo), // replace a character
                    Math.min(find(word1, word2, cur1, cur2 + 1, memo), // insert a character
                            find(word1, word2, cur1 + 1, cur2, memo))); // delete a character
        memo.put(key, ans);
        return ans;
    }
}
