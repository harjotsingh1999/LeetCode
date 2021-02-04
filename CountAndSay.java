import java.util.Scanner;

public class CountAndSay {
    public String countAndSay(int n) {
        String str = "1";

        for (int i = 0; i < n - 1; i++) {
            String newStr = "";
            System.out.println("current string= " + str);
            for (int j = 0; j < str.length();) {
                System.out.println("j= " + j);
                char ch = str.charAt(j);
                int count = 0;
                // this will give the number of times a character repeats itself
                while (j < str.length() && ch == str.charAt(j)) {
                    // System.out.println("j= " + j);
                    j++;
                    count += 1;
                }
                System.out.println("Character " + ch + " is repeated " + count);
                newStr = newStr + String.valueOf(count) + ch;
                System.out.println("j= " + j);
            }
            str = newStr;
            System.out.println("new string= " + str);
        }
        return str;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter n");
        int n = read.nextInt();
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay(n));
        read.close();
    }
}
