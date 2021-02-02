import java.util.Scanner;

class LongestCommonPrefixString {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        String lcp = ""; // longest common prefix of all strings
        String fsp = ""; // prefix of the first String

        // incrementing characters of the first string to the lcp as long as all other
        // strings have the same prefix as well
        // the moment a mismath occurs, return the last stored common prefix
        for (char ch : strs[0].toCharArray()) {
            fsp += ch;
            System.out.println("check all Strings for prefix " + fsp);
            for (String str : strs) {
                if (!str.startsWith(fsp))
                    return lcp;
            }
            lcp = fsp;
            System.out.println("All strings start with " + lcp);
        }
        return lcp;
    }

    public static void main(String[] args) {
        System.out.println("Enter length of array");
        Scanner read = new Scanner(System.in);
        int length = read.nextInt();
        String[] arr = new String[length];
        for (int i = 0; i < length; i++) {
            System.out.println("Enter String");
            arr[i] = read.next();
        }
        LongestCommonPrefixString lString = new LongestCommonPrefixString();
        System.out.println("Longest common prefix= " + lString.longestCommonPrefix(arr));
        read.close();
    }
}