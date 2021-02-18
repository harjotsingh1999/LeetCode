public class Number {

    // Given a non-negative integer num, repeatedly add all its digits until the
    // result has only one digit.

    // Example:

    // Input: 38
    // Output: 2
    // Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
    // Since 2 has only one digit, return it.

    public int addDigits(int num) {
        int digitalRoot = 0;

        int n = num;
        do {
            System.out.println("Number.addDigits() n= " + n);
            digitalRoot = 0;
            while (n != 0) {
                digitalRoot += (n % 10);
                n = n / 10;
            }
            System.out.println("Number.addDigits() dr= " + digitalRoot);
            n = digitalRoot;
        } while (digitalRoot > 9);
        return digitalRoot;
    }

    // upon further consideration we notice that final answer is
    // n%9 for all numbers>10
    // 9->9, 0->0, 18->9
    public int addDigits2(int num) {
        if (num < 9)
            return num;
        if (num % 9 == 0)
            return 9;
        else
            return num % 9;
    }

    // Write a program to check whether a given number is an ugly number.

    // Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

    // Example 1:

    // Input: 6
    // Output: true
    // Explanation: 6 = 2 × 3

    // Example 2:

    // Input: 8
    // Output: true
    // Explanation: 8 = 2 × 2 × 2

    // Example 3:

    // Input: 14
    // Output: false
    // Explanation: 14 is not ugly since it includes another prime factor 7.
    // 1 is typically treated as an ugly number.

    public boolean isUgly(int num) {
        if (num <= 0)
            return false;
        if (num == 1)
            return true;
        while (num > 1) {

            // if it is divisible by any number other than 2,3, or 5
            // means it is not ugly
            if (num % 5 == 0)
                num /= 5;
            else if (num % 3 == 0)
                num /= 3;
            else if (num % 2 == 0)
                num /= 2;
            else
                return false;
        }

        // if dividing by 2,3,5 only makes it 1
        // then number is in fact ugly
        return true;
    }

    public static void main(String[] args) {
        Number number = new Number();
        System.out.println(number.isUgly(6));
    }
}
