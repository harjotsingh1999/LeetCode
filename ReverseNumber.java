import java.util.Scanner;

public class ReverseNumber {
    public int reverse(int x) {
        long rev = 0;

        for (long i = Math.abs(x); i != 0; i = i / 10) {
            long digit = i % 10;
            rev = rev * 10 + digit;
            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
                return 0;
        }
        return x > 0 ? (int) rev : (int) (0 - rev);
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter a number to reverse");
        int num = read.nextInt();
        ReverseNumber solution = new ReverseNumber();
        System.out.println(solution.reverse(num));
        read.close();
    }
}
