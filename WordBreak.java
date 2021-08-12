import java.util.*;

public class WordBreak {

    /**
     * Given a string A and a dictionary of n words B, find out if A can be
     * segmented into a space-separated sequence of dictionary words.
     * 
     * Note: From the dictionary B each word can be taken any number of times and in
     * any order.
     */

    public static int wordBreak(String A, String[] B) {
        HashMap<String, Boolean> memo = new HashMap<>();
        return canConstructWordFromDictionary(A, B, memo) ? 1 : 0;
    }

    private static boolean canConstructWordFromDictionary(String word, String[] dic, HashMap<String, Boolean> memo) {

        System.out.println("can construct= " + word);
        if (word.length() == 0)
            return true;

        if (memo.containsKey(word))
            return memo.get(word);

        for (String prefix : dic) {
            int index = word.indexOf(prefix);
            if (index == 0 && canConstructWordFromDictionary(word.substring(prefix.length()), dic, memo)) {
                memo.put(word, true);
                return true;
            }
        }
        memo.put(word, false);
        return false;
    }

    public static void main(String[] args) {
        // String[] B = { "i", "like", "sam", "sung", "samsung", "mobile", "ice",
        // "cream", "icecream", "man", "go",
        // "mango" };
        // String A = "ilikesamsung";
        // // System.out.println(wordBreak(A, B));
        // waysToConstructString(A, Arrays.asList(B));

        // String[] B = { "apple", "pen", "applepen", "pine", "pineapple" };
        // String A = "pineapplepenapple";
        // waysToConstructString(A, Arrays.asList(B));

        String[] B = { "cats", "dog", "sand", "and", "cat" };
        String A = "catsanddog";
        waysToConstructString(A, Arrays.asList(B));
    }

    /**
     * Given a string s and a dictionary of strings wordDict, add spaces in s to
     * construct a sentence where each word is a valid dictionary word. Return all
     * such possible sentences in any order.
     * 
     * Note that the same word in the dictionary may be reused multiple times in the
     * segmentation.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"] Output:
     * ["cats and dog","cat sand dog"] Example 2:
     * 
     * Input: s = "pineapplepenapple", wordDict =
     * ["apple","pen","applepen","pine","pineapple"] Output: ["pine apple pen
     * apple","pineapple pen apple","pine applepen apple"] Explanation: Note that
     * you are allowed to reuse a dictionary word. Example 3:
     * 
     * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"] Output:
     * []
     * 
     * 
     * Constraints:
     * 
     * 1 <= s.length <= 20 1 <= wordDict.length <= 1000 1 <= wordDict[i].length <=
     * 10 s and wordDict[i] consist of only lowercase English letters. All the
     * strings of wordDict are unique.
     */

    public static void waysToConstructString(String s, List<String> wordDict) {
        ArrayList<String> ans = new ArrayList<>();
        findWaysToConstruct(s, wordDict, ans, "");
        System.out.println(ans);
    }

    // Runtime: 4 ms, faster than 65.53% of Java online submissions for Word Break
    // II.
    // Memory Usage: 37.9 MB, less than 14.71% of Java online submissions for Word
    // Break II.

    private static void findWaysToConstruct(String s, List<String> wordDict, ArrayList<String> ans, String curr) {
        System.out.println(s + " str= " + curr.toString());
        if (s.length() == 0) {
            ans.add(curr.trim());
            return;
        }

        for (String prefix : wordDict) {
            int index = s.indexOf(prefix);
            if (index == 0) {
                findWaysToConstruct(s.substring(prefix.length()), wordDict, ans, curr + prefix + " ");
            }
        }
    }
}
