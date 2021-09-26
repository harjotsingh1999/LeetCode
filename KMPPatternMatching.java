import java.util.*;

public class KMPPatternMatching {

    // O(m+n)
    static void isASubstring(String str1, String str2) {
        int[] suffixAlsoPrefix = new int[str2.length()];

        int low = 0, high = 1;
        suffixAlsoPrefix[0] = 0;
        while (high < str2.length()) {
            // if no match, then at this pos value=0
            // that is longest suffix that is also a prefix is 0
            // reset low to 0
            if (str2.charAt(high) != str2.charAt(low)) {
                suffixAlsoPrefix[high] = 0;
                high += 1;
                low = 0;
            }
            // othewise if match, go to next character
            else {
                suffixAlsoPrefix[high] = low + 1;
                low += 1;
                high += 1;
            }
        }
        System.out.println("arr= " + Arrays.toString(suffixAlsoPrefix));

        int p1 = 0, p2 = 0;
        boolean found = false;
        while (p1 < str1.length() && p2 < str2.length()) {

            // if match then increment both
            if (str1.charAt(p1) == str2.charAt(p2)) {
                p1 += 1;
                p2 += 1;

                // if p2 reached end, meaning pattern found
                if (p2 == str2.length()) {
                    found = true;
                    break;
                }
            } else {

                // if no match, then go to the point where prefix=suffix
                if (p2 > 0)
                    p2 = suffixAlsoPrefix[p2 - 1];
                else {
                    // if there is no match, and p2 is at starting of str2, just increment p1
                    p1 += 1;
                }
            }
        }
        if (found)
            System.out.println("pattern found starting " + (p1 - p2));
        else
            System.out.println("pattern not found " + -1);
    }

    public static void main(String[] args) {
        isASubstring("AABAACAADAABAABA", "AABAABC");
        isSubstring("AABAACAADAABAABA", "AABAABC");
    }

    // brute force approach O(mn)

    public static void isSubstring(String s1, String s2) {
        int p1 = 0;
        boolean found = false;
        for (p1 = 0; p1 <= s1.length() - s2.length(); p1++) {
            int p2 = 0;
            if (s1.charAt(p1) == s2.charAt(p2)) {
                int i = p1;
                while (i < s1.length() && p2 < s2.length() && s1.charAt(i) == s2.charAt(p2)) {
                    System.out.println("match found " + i + " " + p2);
                    i += 1;
                    p2 += 1;
                }
                if (p2 == s2.length()) {
                    found = true;
                    break;
                }
            }
        }
        if (found)
            System.out.println("pattern found starting " + p1);
        else
            System.out.println("pattern not found");
    }
}
