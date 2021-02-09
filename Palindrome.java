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

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindrome2("A man, a plan, a canal: Panama"));
    }
}
