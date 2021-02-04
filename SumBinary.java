import java.util.Scanner;

public class SumBinary {
    public String addBinary(String a, String b) {
        String str = "";
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i >= 0 && j >= 0) {
            char ch1 = a.charAt(i);
            char ch2 = b.charAt(j);
            int sum = Integer.parseInt(String.valueOf(ch1)) + Integer.parseInt(String.valueOf(ch2)) + carry;
            System.out.println("sum= " + sum);
            if (sum == 0) {
                str = "0" + str;
                carry = 0;
            } else if (sum == 1) {
                str = "1" + str;
                carry = 0;
            } else if (sum == 2) {
                str = "0" + str;
                carry = 1;
            } else {
                str = "1" + str;
                carry = 1;
            }
            i -= 1;
            j -= 1;
            System.out.println("current str= " + str);
        }

        if (a.length() > b.length()) {
            while (i >= 0) {
                char ch1 = a.charAt(i);
                int sum = Integer.parseInt(String.valueOf(ch1)) + carry;
                System.out.println("sum= " + sum);
                if (sum == 0) {
                    str = "0" + str;
                    carry = 0;
                } else if (sum == 1) {
                    str = "1" + str;
                    carry = 0;
                } else if (sum == 2) {
                    str = "0" + str;
                    carry = 1;
                } else {
                    str = "1" + str;
                    carry = 1;
                }
                i -= 1;
                System.out.println("current str= " + str);
            }
        } else if (b.length() > a.length()) {
            while (j >= 0) {
                char ch1 = b.charAt(j);
                int sum = Integer.parseInt(String.valueOf(ch1)) + carry;
                System.out.println("sum= " + sum);
                if (sum == 0) {
                    str = "0" + str;
                    carry = 0;
                } else if (sum == 1) {
                    str = "1" + str;
                    carry = 0;
                } else if (sum == 2) {
                    str = "0" + str;
                    carry = 1;
                } else {
                    str = "1" + str;
                    carry = 1;
                }
                j -= 1;
                System.out.println("current str= " + str);
            }
        }

        if (carry != 0)
            str = "1" + str;
        return str;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter string 1");
        String str1 = read.next();

        System.out.println("Enter string 2");
        String str2 = read.next();

        SumBinary sumBinary = new SumBinary();
        System.out.println("Sum binary= " + sumBinary.addBinary(str1, str2));
        read.close();
    }
}
