import java.util.Scanner;

class LongestCommonPrefixString {
    public String longestCommonPrefix(String[] strs) {
        String lcp = "";
        String fsp = "";
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