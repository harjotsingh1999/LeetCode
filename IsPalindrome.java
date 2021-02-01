import java.util.Scanner;

public class IsPalindrome {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        else {
            long rev = 0;
            for (long i = x; i != 0; i = i / 10) {
                long digit = i % 10;
                rev = rev * 10 + digit;
            }
            return x == rev;
        }
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter a number to check palindrome");
        int num = read.nextInt();
        IsPalindrome isPalindrome = new IsPalindrome();
        System.out.println("Number entered is palindrome: " + isPalindrome.isPalindrome(num));
        read.close();
    }
}
