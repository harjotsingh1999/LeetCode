import java.util.Arrays;

public class Palindrome {

    // faster than 5%
    public boolean isPalindrome(String s) {
        if (s.isEmpty())
            return true;
        String newStr = "", currentStr = "";
        for (char ch : s.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                newStr = ch + newStr;
                currentStr = currentStr + ch;
            }
        }
        return newStr.equalsIgnoreCase(currentStr);
    }

    // Given a string, determine if it is a palindrome, considering only
    // alphanumeric characters and ignoring cases.

    // Note: For the purpose of this problem, we define empty string as valid
    // palindrome.

    // Example 1:

    // Input: "A man, a plan, a canal: Panama"
    // Output: true

    // Example 2:

    // Input: "race a car"
    // Output: false

    // faster than 98%
    public boolean isPalindrome2(String s) {
        if (s.isEmpty())
            return true;
        int start = 0, end = s.length() - 1;
        while (start <= end) {
            if (!Character.isLetterOrDigit(s.charAt(start)))
                start += 1;
            else if (!Character.isLetterOrDigit(s.charAt(end)))
                end -= 1;
            else if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end)))
                return false;
            else {
                start += 1;
                end -= 1;
            }
        }
        return true;
    }

    // Write a function that reverses a string. The input string is given as an
    // array of characters char[].

    // Do not allocate extra space for another array, you must do this by modifying
    // the input array in-place with O(1) extra memory.

    // You may assume all the characters consist of printable ascii characters.
    public void reverseString(char[] s) {
        int start = 0, end = s.length - 1;
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    // Write a function that takes a string as input and reverse only the vowels of
    // a string.

    // Example 1:

    // Input: "hello"
    // Output: "holle"

    // Example 2:

    // Input: "leetcode"
    // Output: "leotcede"

    // Note:
    // The vowels does not include the letter "y".

    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int start = 0, end = arr.length - 1;
        while (start < end) {
            if (!isVowel(arr[start]) && !isVowel(arr[end])) {
                start += 1;
                end -= 1;
            } else if (!isVowel(arr[start])) {
                start += 1;
            } else if (!isVowel(arr[end])) {
                end -= 1;
            } else {
                char temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start += 1;
                end -= 1;
            }
        }
        return new String(arr);
    }

    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I'
                || ch == 'O' || ch == 'U';
    }

    // Given a string s which consists of lowercase or uppercase letters, return the
    // length of the longest palindrome that can be built with those letters.

    // Letters are case sensitive, for example, "Aa" is not considered a palindrome
    // here.

    // Example 1:

    // Input: s = "abccccdd"
    // Output: 7
    // Explanation:
    // One longest palindrome that can be built is "dccaccd", whose length is 7.

    // Example 2:

    // Input: s = "a"
    // Output: 1

    // Example 3:

    // Input: s = "bb"
    // Output: 2

    public int longestPalindrome(String s) {
        int[] occurrancesLC = new int[26];
        int[] occurrancesUC = new int[26];
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                occurrancesUC[(int) ch - (int) 'A'] += 1;
            } else {
                occurrancesLC[(int) ch - (int) 'a'] += 1;
            }
        }

        int pairs = 0;
        int singles = 0;

        // i/2 for counting pairs
        // i%2 for counting single occurances
        for (int i : occurrancesLC) {
            pairs += i / 2;
            singles += i % 2;
        }
        for (int i : occurrancesUC) {
            pairs += i / 2;
            singles += i % 2;
        }
        // if there are more than 0 singles
        // one of them can be used as center of palindrome

        return pairs * 2 + (singles > 0 ? 1 : 0);
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.longestPalindrome("bb"));
    }
}
