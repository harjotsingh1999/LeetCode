import java.util.ArrayList;
import java.util.List;
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

    public static void main(String[] args) {
        StringProblems stringProblems = new StringProblems();
        System.out.println(stringProblems.addStrings("0", "10"));
    }
}
