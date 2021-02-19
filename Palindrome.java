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

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.reverseVowels("aA"));
    }
}
