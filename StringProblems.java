import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class StringProblems {

    // Given a string, find the first non-repeating character in it and return its
    // index. If it doesn't exist, return -1.

    // Examples:

    // s = "leetcode"
    // return 0.

    // s = "loveleetcode"
    // return 2.

    // Note: You may assume the string contains only lowercase English letters.

    public int firstUniqChar(String s) {
        if (s.isEmpty())
            return -1;
        // array to store occurance of each character
        // at index (int)char-(int)'a'
        // i.e. from 0-25
        int[] charCount = new int[26];

        // counting the occurance of each character
        for (char ch : s.toCharArray()) {
            charCount[(int) ch - (int) 'a'] += 1;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] == 1) {

                // if occurance is 1
                // means it is unique
                // hence computing the ndex first unique character
                int index = s.indexOf((char) (97 + i));
                if (index < min)
                    min = index;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // You are given two strings s and t.

    // String t is generated by random shuffling string s and then add one more
    // letter at a random position.

    // Return the letter that was added to t.

    // Example 1:

    // Input: s = "abcd", t = "abcde"
    // Output: "e"
    // Explanation: 'e' is the letter that was added.

    // Example 2:

    // Input: s = "", t = "y"
    // Output: "y"

    // Example 3:

    // Input: s = "a", t = "aa"
    // Output: "a"

    // Example 4:

    // Input: s = "ae", t = "aea"
    // Output: "a"

    public char findTheDifference(String s, String t) {
        // the above array approach can be used here too

        int[] charCount = new int[26];

        // counting the occurance of each character
        for (char ch : s.toCharArray()) {
            charCount[(int) ch - (int) 'a'] += 1;
        }
        for (char ch : t.toCharArray()) {
            charCount[(int) ch - (int) 'a'] -= 1;
        }

        for (int i = 0; i < charCount.length; i++) {

            // for the character that is extra in 't' string
            // its count will be negative
            if (charCount[i] < 0) {
                return (char) (97 + i);
            }
        }
        return 'a';
    }

    // Given two strings s and t, check if s is a subsequence of t.

    // A subsequence of a string is a new string that is formed from the original
    // string by deleting some (can be none) of the characters without disturbing
    // the relative positions of the remaining characters. (i.e., "ace" is a
    // subsequence of "abcde" while "aec" is not).

    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length())
            return false;
        if (s.isEmpty())
            return true;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);

            // if more characters remain in s but not in t
            // simple return false
            // else continue to check
            if (j < t.length()) {
                char ch2 = t.charAt(j);
                // if same then move on
                if (ch1 == ch2) {
                    j += 1;
                } else {
                    // increment j pointer until ch1 is same as ch2 or until the end of t string
                    while (ch1 != ch2 && j < t.length() - 1) {
                        j += 1;
                        ch2 = t.charAt(j);
                    }

                    // we continue further if both are same
                    if (ch1 == ch2) {
                        j += 1;
                        continue;
                    } else {
                        // this means we reached the end of the t string
                        // hence return false
                        return false;
                    }
                }
            } else
                return false;

        }
        return true;
    }

    // Write a program that outputs the string representation of numbers from 1 to
    // n.

    // But for multiples of three it should output “Fizz” instead of the number and
    // for the multiples of five output “Buzz”. For numbers which are multiples of
    // both three and five output “FizzBuzz”.
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<String>(n);
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0)
                list.add("FizzBuzz");
            else if (i % 3 == 0)
                list.add("Fizz");
            else if (i % 5 == 0)
                list.add("Buzz");
            else
                list.add(String.valueOf(i));
        }
        return list;
    }

    // Given two non-negative integers num1 and num2 represented as string, return
    // the sum of num1 and num2.

    // Note:

    // The length of both num1 and num2 is < 5100.
    // Both num1 and num2 contains only digits 0-9.
    // Both num1 and num2 does not contain any leading zero.
    // You must not use any built-in BigInteger library or convert the inputs to
    // integer directly.

    public String addStrings(String num1, String num2) {

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        String sum = "";
        int carry = 0;
        while (i >= 0 && j >= 0) {
            int d1 = Integer.parseInt(String.valueOf(num1.charAt(i)));
            int d2 = Integer.parseInt(String.valueOf(num2.charAt(j)));
            int s = d1 + d2 + carry;
            sum = String.valueOf(s % 10) + sum;
            carry = s / 10;
            i -= 1;
            j -= 1;
        }
        while (i >= 0) {
            int d1 = Integer.parseInt(String.valueOf(num1.charAt(i)));
            int s = d1 + carry;
            sum = String.valueOf(s % 10) + sum;
            carry = s / 10;
            i -= 1;
        }
        while (j >= 0) {
            int d2 = Integer.parseInt(String.valueOf(num2.charAt(j)));
            int s = d2 + carry;
            sum = String.valueOf(s % 10) + sum;
            carry = s / 10;
            j -= 1;
        }
        if (carry > 0)
            sum = String.valueOf(carry) + sum;
        return sum;
    }

    // You are given a string s, return the number of segments in the string.

    // A segment is defined to be a contiguous sequence of non-space characters.

    // Example 1:

    // Input: s = "Hello, my name is John"
    // Output: 5
    // Explanation: The five segments are ["Hello,", "my", "name", "is", "John"]

    // Example 2:

    // Input: s = "Hello"
    // Output: 1

    // Example 3:

    // Input: s = "love live! mu'sic forever"
    // Output: 4

    // Example 4:

    // Input: s = ""
    // Output: 0

    // 100% faster
    // accepted solution
    public int countSegments(String s) {
        StringTokenizer st = new StringTokenizer(s);
        return st.countTokens();
    }

    // Given a string s, check if it can be constructed by taking a substring of it
    // and appending multiple copies of the substring together.

    // Example 1:

    // Input: s = "abab"
    // Output: true
    // Explanation: It is the substring "ab" twice.

    // Example 2:

    // Input: s = "aba"
    // Output: false

    // Example 3:

    // Input: s = "abcabcabcabc"
    // Output: true
    // Explanation: It is the substring "abc" four times or the substring "abcabc"
    // twice.

    public boolean repeatedSubstringPattern(String s) {

        // to get the substrings of all lengths upto s.length()/2
        for (int i = 1; i < s.length(); i++) {

            // if s.length is not divisible by i
            // means the substring cannot be used repeatedly
            if (s.length() % i != 0)
                continue;
            boolean found = true;

            // getting the substring
            String substr = s.substring(0, i);
            System.out.println("checking for substring " + substr);

            // getting all other substring of the same length
            for (int j = substr.length(); j < s.length(); j += substr.length()) {
                String nsubstr = s.substring(j, j + substr.length());
                System.out.println("matching with " + nsubstr);
                // matching the first substring with all others
                // if first mismatch occurs
                // move on with the next length of substring
                if (!substr.equals(nsubstr)) {
                    found = false;
                    break;
                }
            }
            // if all substrigs matched
            // return true
            // else continue with substring of a greater length
            if (found)
                return true;
            continue;
        }

        // if nothing was returned until now
        // means not found
        return false;
    }

    // You are given a license key represented as a string S which consists only
    // alphanumeric character and dashes. The string is separated into N+1 groups by
    // N dashes.

    // Given a number K, we would want to reformat the strings such that each group
    // contains exactly K characters, except for the first group which could be
    // shorter than K, but still must contain at least one character. Furthermore,
    // there must be a dash inserted between two groups and all lowercase letters
    // should be converted to uppercase.

    // Given a non-empty string S and a number K, format the string according to the
    // rules described above.

    // Example 1:

    // Input: S = "5F3Z-2e-9-w", K = 4

    // Output: "5F3Z-2E9W"

    // Explanation: The string S has been split into two parts, each part has 4
    // characters.
    // Note that the two extra dashes are not needed and can be removed.

    // Example 2:

    // Input: S = "2-5g-3-J", K = 2

    // Output: "2-5G-3J"

    // Explanation: The string S has been split into three parts, each part has 2
    // characters except the first part as it could be shorter as mentioned above.

    // Note:

    // The length of string S will not exceed 12,000, and K is a positive integer.
    // String S consists only of alphanumerical characters (a-z and/or A-Z and/or
    // 0-9) and dashes(-).
    // String S is non-empty.
    public String licenseKeyFormatting(String S, int K) {
        System.out.println(S);
        String newStr = S.replace("-", "");
        newStr = newStr.toUpperCase();
        System.out.println(newStr);
        String out = "";
        int i = newStr.length();
        for (i = newStr.length(); i - K >= 0; i -= K) {

            if (out.isEmpty()) {
                out += newStr.substring(i - K, i);
            } else {
                out = newStr.substring(i - K, i) + "-" + out;
            }
        }
        if (out.isEmpty()) {
            out += newStr.substring(i - K < 0 ? 0 : i - K, i);
        } else {
            out = newStr.substring(i - K < 0 ? 0 : i - K, i) + "-" + out;
        }
        if (out.startsWith("-"))
            out = out.substring(1);
        return out;
    }

    // Given an array of strings words, return the words that can be typed using
    // letters of the alphabet on only one row of American keyboard like the image
    // below.

    // In the American keyboard:

    // the first row consists of the characters "qwertyuiop",
    // the second row consists of the characters "asdfghjkl", and
    // the third row consists of the characters "zxcvbnm".

    // Example 1:

    // Input: words = ["Hello","Alaska","Dad","Peace"]
    // Output: ["Alaska","Dad"]

    // Example 2:

    // Input: words = ["omk"]
    // Output: []

    // Example 3:

    // Input: words = ["adsdf","sfd"]
    // Output: ["adsdf","sfd"]

    // Constraints:

    // 1 <= words.length <= 20
    // 1 <= words[i].length <= 100
    // words[i] consists of English letters (both lowercase and uppercase).

    // 100% fast 92% less space
    public String[] findWords(String[] words) {
        List<String> out = new ArrayList<String>();
        HashSet<Character> set1 = new HashSet<>();
        for (char ch : "qwertyuiop".toCharArray()) {
            set1.add(ch);
        }
        HashSet<Character> set2 = new HashSet<>();
        for (char ch : "asdfghjkl".toCharArray()) {
            set2.add(ch);
        }
        HashSet<Character> set3 = new HashSet<>();
        for (char ch : "zxcvbnm".toCharArray()) {
            set3.add(ch);
        }

        for (int i = 0; i < words.length; i++) {
            String word1 = words[i];
            String word = word1.toLowerCase();
            boolean valid = true;
            char ch1 = word.charAt(0);
            if (set1.contains(ch1)) {
                for (int j = 1; j < word.length(); j++) {
                    if (!set1.contains(word.charAt(j))) {
                        valid = false;
                        break;
                    }
                }
            } else if (set2.contains(ch1)) {
                for (int j = 1; j < word.length(); j++) {
                    if (!set2.contains(word.charAt(j))) {
                        valid = false;
                        break;
                    }
                }
            } else {
                for (int j = 1; j < word.length(); j++) {
                    if (!set3.contains(word.charAt(j))) {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid)
                out.add(word1);
        }
        String[] outp = new String[out.size()];
        int i = 0;
        for (String s : out) {
            outp[i] = s;
            i += 1;
        }
        return outp;
    }

    // You are given a string s consisting only of the characters '0' and '1'. In
    // one operation, you can change any '0' to '1' or vice versa.

    // The string is called alternating if no two adjacent characters are equal. For
    // example, the string "010" is alternating, while the string "0100" is not.

    // Return the minimum number of operations needed to make s alternating.

    // Example 1:

    // Input: s = "0100"
    // Output: 1
    // Explanation: If you change the last character to '1', s will be "0101", which
    // is alternating.

    // Example 2:

    // Input: s = "10"
    // Output: 0
    // Explanation: s is already alternating.

    // Example 3:

    // Input: s = "1111"
    // Output: 2
    // Explanation: You need two operations to reach "0101" or "1010".

    // Constraints:

    // 1 <= s.length <= 104
    // s[i] is either '0' or '1'.

    public int minOperations(String s) {
        if (s.length() == 0 || s.length() == 1)
            return 0;
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                sb1.append("0");
                sb2.append("1");
            } else {
                sb1.append("1");
                sb2.append("0");
            }
        }
        // String o1 = sb1.toString();
        // String o2 = sb2.toString();
        int c1 = 0, c2 = 0;
        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != sb1.charAt(i))
                c1 += 1;
            if (s.charAt(i) != sb2.charAt(i))
                c2 += 1;
        }
        return Math.min(c1, c2);
    }

    // Given a word, you need to judge whether the usage of capitals in it is right
    // or not.

    // We define the usage of capitals in a word to be right when one of the
    // following cases holds:

    // All letters in this word are capitals, like "USA".
    // All letters in this word are not capitals, like "leetcode".
    // Only the first letter in this word is capital, like "Google".

    // Otherwise, we define that this word doesn't use capitals in a right way.

    // Example 1:

    // Input: "USA"
    // Output: True

    // Example 2:

    // Input: "FlaG"
    // Output: False

    public boolean detectCapitalUse(String word) {
        if (word.length() <= 1)
            return true;
        boolean firstCap = Character.isUpperCase(word.charAt(0));
        boolean secondCap = Character.isUpperCase(word.charAt(1));
        if (!firstCap && secondCap)
            return false;
        for (int i = 2; i < word.length(); i++) {
            if (!firstCap && Character.isUpperCase(word.charAt(i)))
                return false;
            else if (firstCap && secondCap && Character.isLowerCase(word.charAt(i)))
                return false;
            else if (firstCap && !secondCap && Character.isUpperCase(word.charAt(i)))
                return false;
        }
        return true;
    }

    // Given a string s, find the length of the longest substring without repeating
    // characters.

    // Example 1:

    // Input: s = "abcabcbb"
    // Output: 3
    // Explanation: The answer is "abc", with the length of 3.

    // Example 2:

    // Input: s = "bbbbb"
    // Output: 1
    // Explanation: The answer is "b", with the length of 1.

    // Example 3:

    // Input: s = "pwwkew"
    // Output: 3
    // Explanation: The answer is "wke", with the length of 3.
    // Notice that the answer must be a substring, "pwke" is a subsequence and not a
    // substring.

    // Example 4:

    // Input: s = ""
    // Output: 0

    // Constraints:

    // 0 <= s.length <= 5 * 104
    // s consists of English letters, digits, symbols and spaces.

    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        HashSet<Character> set = new HashSet<>(); // current index of character
        int i = 0, j = 0;
        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                // if current character not already seen
                set.add(s.charAt(j));

                // increase the window size
                j += 1;

                // update the max length
                // set.size indicates the number of unique characters
                // hence the length of the substring
                ans = Math.max(set.size(), ans);
            } else {
                // if the current character already in the set
                // we need to update the start pointer to after the first occurance of this
                // character
                // essentially removing all characters from the set, that are in the string
                // before the first occurance of this character
                set.remove(s.charAt(i));
                i += 1;
            }
        }
        return ans;
    }

    // Approach 3: Sliding Window Optimized

    // The above solution requires at most 2n steps. In fact, it could be optimized
    // to require only n steps. Instead of using a set to tell if a character exists
    // or not, we could define a mapping of the characters to its index. Then we can
    // skip the characters immediately when we found a repeated character.

    // The reason is that if s[j] have a duplicate in the range [i,j)
    // with index j′, we don't need to increase i little by little. We
    // can skip all the elements in the range [i,j′] and let i to be
    // j′+1 directly.

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
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

    // Constraints:

    // 1 <= s.length <= 1000
    // s consist of only digits and English letters (lower-case and/or upper-case),

    public String longestPalindromicSubstring(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        String lps = String.valueOf(s.charAt(0));

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (i == j)
                    dp[i][j] = true;
                else if (j - i == 1 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    lps = s.substring(i, j + 1);
                    System.out.println("current lps= " + lps);
                }
            }
        }
        System.out.println("lps before second loop= " + lps);

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (j - i > 1) {
                    System.out.println("i= " + i + " and j= " + j);
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
                    if (dp[i][j] && j - i + 1 > lps.length()) {
                        lps = s.substring(i, j + 1);
                        System.out.println("i= " + i + " and j= " + j + " lps= " + lps);
                    }
                }
            }
        }
        return lps;
    }

    // TODO:
    public int longestPalindromeSubseq(String s) {
        System.out.println(s);
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int k = 0; k < dp.length; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i == j) {
                        dp[i][j] = 1;
                    } else if (j - i == 1) {
                        dp[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 1;
                    } else if (i > 0 && j > 0 && i < j) {
                        if (s.charAt(i) == s.charAt(j))
                            dp[i][j] = 2 + dp[i - 1][j - 1];
                        else
                            dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                    System.out.println("i= " + i + " j= " + j + " ans= " + dp[i][j]);
                }
            }
        }
        {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i == j) {
                        dp[i][j] = 1;
                    } else if (j - i == 1) {
                        dp[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 1;
                    } else if (i > 0 && j > 0 && i < j) {
                        if (s.charAt(i) == s.charAt(j))
                            dp[i][j] = 2 + dp[i - 1][j - 1];
                        else
                            dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                    System.out.println("i= " + i + " j= " + j + " ans= " + dp[i][j]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[n - 1][n - 1];
    }

    public void removeDuplicates(String str) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length();) {
            if (st.empty()) {
                st.push(str.charAt(i));
                i += 1;
            } else {
                if (str.charAt(i) == st.peek()) {
                    while (i < str.length() && str.charAt(i) == st.peek())
                        i += 1;
                    st.pop();
                } else {
                    st.push(str.charAt(i));
                    i += 1;
                }
            }
            System.out.println("stack= " + st + " i= " + i);
        }

        StringBuilder sb = new StringBuilder();
        st.forEach((ch) -> sb.append(ch));
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        StringProblems stringProblems = new StringProblems();
        // String[] words = { "adsdf", "sfd" };
        // System.out.println(Arrays.toString(stringProblems.findWords(words)));
        // System.out.println(stringProblems.longestPalindromicSubstring("babab"));
        // System.out.println(stringProblems.longestPalindromeSubseq("bbbad"));
        stringProblems.removeDuplicates("geeksforgeeg");
    }
}
