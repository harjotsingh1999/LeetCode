import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WordBank {

    // given a array of strings and a target string, find if it is possible to make
    // target using strings from bank any number of times

    public void canConstruct(String[] bank, String target) {
        HashMap<String, Boolean> memo = new HashMap<>();
        System.out.println("Can construct " + target + " " + canConstructUtil(bank, target, memo));
    }

    // Bruteforce TC- O(m(for substring) x n^m(for recursive calls)) SC- O(m^2)

    // Memoized TC - O(n x m x m(for substring)) SC- O(m^2)
    private boolean canConstructUtil(String[] bank, String target, HashMap<String, Boolean> memo) {
        if (memo.containsKey(target))
            return memo.get(target);
        if (target.isEmpty())
            return true;
        for (String string : bank) {
            if (target.startsWith(string)) {
                System.out.println(
                        target + " starts with " + string + " substring= " + target.substring(string.length()));
                if (canConstructUtil(bank, target.substring(string.length()), memo)) {
                    memo.put(target, true);
                    return true;
                }
            }
        }
        memo.put(target, false);
        return false;
    }

    public void countConstruct(String[] bank, String target) {
        HashMap<String, Integer> memo = new HashMap<>();
        System.out.println("Count construct " + target + " " + countConstructUtil(bank, target, memo));
    }

    private int countConstructUtil(String[] bank, String target, HashMap<String, Integer> memo) {
        if (memo.containsKey(target))
            return memo.get(target);
        if (target.isEmpty())
            return 1;

        int totalCount = 0;
        for (String string : bank) {
            if (target.startsWith(string)) {
                int waysToGenerateRest = countConstructUtil(bank, target.substring(string.length()), memo);
                totalCount += waysToGenerateRest;
            }
        }
        memo.put(target, totalCount);
        return totalCount;
    }

    // return a 2D array containing number of ways to construct target
    public void allConstruct(String[] bank, String target) {
        HashMap<String, List<List<String>>> memo = new HashMap<>();
        System.out.println("Ways to construct " + target + " are " + allConstructUtil(bank, target, memo));
    }

    // if there are no ways, return empty list
    // other wise return list of ways
    // where one way is a list of String word
    private List<List<String>> allConstructUtil(String[] bank, String target,
            HashMap<String, List<List<String>>> memo) {
        if (memo.containsKey(target))
            return memo.get(target);

        // way to return [[]] using arraylist
        if (target.isEmpty())
            return new ArrayList<>(Arrays.asList(new ArrayList<>()));

        List<List<String>> allWays = new ArrayList<>();

        for (String word : bank) {
            if (target.startsWith(word)) {
                List<List<String>> restWays = allConstructUtil(bank, target.substring(word.length()), memo);
                List<List<String>> targetWays = new ArrayList<>();
                for (List<String> list : restWays) {
                    list.add(0, word);
                    targetWays.add(list);
                }
                for (List<String> list : targetWays) {
                    allWays.add(list);
                }
            }
        }
        memo.put(target, allWays);
        return allWays;
    }

    // public boolean canConstructTab(String[] bank, String target) {
    // boolean[] dp = new boolean[target.length() + 1];

    // // always possible to construct empty string
    // dp[0] = true;

    // // i =5 will tell whether it is possible to generate target.substring(0,5) or
    // // not
    // for (int i = 0; i <= target.length(); i++) {
    // // only is this string is possible to be generated
    // // we move aheaf
    // if (dp[i] == true) {
    // // current substring
    // String curStr = target.substring(0, i + 1);
    // for (String word : bank) {
    // if(word.startsWith(curStr))
    // }
    // }
    // }
    // return dp[target.length()];
    // }

    public static void main(String[] args) {
        WordBank wordBank = new WordBank();
        wordBank.allConstruct(new String[] { "purp", "p", "le", "ur", "purpl" }, "purple");
        wordBank.allConstruct(new String[] { "abc", "ab", "cd", "def", "abcd" }, "abcdef");
        wordBank.allConstruct(new String[] { "bo", "rd", "ate", "t", "ska", "sk", "boar" }, "skateboard");
        wordBank.allConstruct(new String[] { "a", "p", "ent", "enter", "ot", "o", "t" }, "enterapotentpot");
        wordBank.allConstruct(new String[] { "e", "ee", "eee", "eeee", "eeeee" },
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef");
    }
}
