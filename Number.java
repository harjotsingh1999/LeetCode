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

    // The Hamming distance between two integers is the number of positions at which
    // the corresponding bits are different.

    // Given two integers x and y, calculate the Hamming distance.

    // Note:
    // 0 ≤ x, y < 231.

    // Example:

    // Input: x = 1, y = 4

    // Output: 2

    // Explanation:
    // 1 (0 0 0 1)
    // 4 (0 1 0 0)
    // -----↑-- ↑

    // The above arrows point to positions where the corresponding bits are
    // different.

    // 5% faster 20% less space
    public int hammingDistance(int x, int y) {
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(y));
        String s1 = Integer.toBinaryString(x);
        String s2 = Integer.toBinaryString(y);
        if (s1.length() < s2.length()) {
            while (s1.length() < s2.length()) {
                s1 = "0" + s1;
            }
        }
        if (s2.length() < s1.length()) {
            while (s2.length() < s1.length()) {
                s2 = "0" + s2;
            }
        }
        System.out.println(s1 + " " + s2);
        int c = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                c += 1;
        }
        return c;
    }

    // using bit manipulation
    // note that xor gives 1 for odd number of 1s
    // or gives 1 when bits are different
    // hence, counting the number of set bits
    // in x^y will be the number of different bits in x and y
    public int hammingDistance2(int x, int y) {
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(y));
        System.out.println(Integer.toBinaryString(x ^ y));
        return countSetBits(x ^ y);
    }

    public int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            System.out.println(n + " " + Integer.toBinaryString(n));

            // check if the least significant bit is set or not
            count += (n & 1);
            // right shift the bits of the number by 1 unit
            // 100 becomes 010 then 001 etc.
            n >>= 1;
            System.out.println(n + " " + Integer.toBinaryString(n));
        }
        return count;
    }

    // Number : Given Number
    // Value : A number with all bits set in given number.
    // Flipped number = Value – Number.

    // Example :
    // Number = 23,
    // Binary form: 10111;
    // After flipping digits number will be: 01000;
    // Value: 11111 = 31;
    public int findComplement(int num) {

        int n1 = num;
        int n = 0;
        int c = 0;

        // keep dividing the number by 2
        while (n1 != 0) {
            n += (int) Math.pow(2, c);
            n1 >>= 1;
            c += 1;
        }
        System.out.println(n);
        return n - num;
    }

    // A perfect number is a positive integer that is equal to the sum of its
    // positive divisors, excluding the number itself. A divisor of an integer x is
    // an integer that can divide x evenly.

    // Given an integer n, return true if n is a perfect number, otherwise return
    // false.

    // Example 1:

    // Input: num = 28
    // Output: true
    // Explanation: 28 = 1 + 2 + 4 + 7 + 14
    // 1, 2, 4, 7, and 14 are all divisors of 28.

    // Example 2:

    // Input: num = 6
    // Output: true

    // Example 3:

    // Input: num = 496
    // Output: true

    // Example 4:

    // Input: num = 8128
    // Output: true

    // Example 5:

    // Input: num = 2
    // Output: false

    // Constraints:

    // 1 <= num <= 108

    public boolean checkPerfectNumber(int num) {
        if (num == 1)
            return false;
        int sum = 1;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0)
                sum += i;
        }
        return sum == num;
    }

    // Approach #4 Euclid-Euler Theorem [Accepted]

    // Algorithm

    // Euclid proved that 2^{p−1}(2^p − 1) is an even perfect
    // number whenever 2^p − 1 is prime, where p is prime.

    // For example, the first four perfect numbers are generated by the formula
    // 2^{p−1}(2^p − 1), with p a prime number, as follows:

    // for p = 2: 21(22 − 1) = 6
    // for p = 3: 22(23 − 1) = 28
    // for p = 5: 24(25 − 1) = 496
    // for p = 7: 26(27 − 1) = 8128.

    // Prime numbers of the form 2^p − 1 are known as Mersenne primes. For
    // 2^p − 1 to be prime, it is necessary that p itself be prime.
    // However, not all numbers of the form 2^p − 1 with a prime p are
    // prime; for example, 211−1=2047=23×892^{11} − 1 = 2047 = 23 ×
    // 89211−1=2047=23×89 is not a prime number.

    // You can see that for small value of p, its related perfect number goes very
    // high. So, we need to evaluate perfect numbers for some primes
    // (2,3,5,7,13,17,19,31)(2, 3, 5, 7, 13, 17, 19, 31)(2,3,5,7,13,17,19,31) only,
    // as for bigger prime its perfect number will not fit in 64 bits.

    public int pn(int p) {
        return (1 << (p - 1)) * ((1 << p) - 1);
    }

    // (6, 28, 496, 8128, 33550336) are the only Integer numbers that are perfect
    public boolean checkPerfectNumber2(int num) {
        int[] primes = new int[] { 2, 3, 5, 7, 13, 17, 19, 31 };
        for (int prime : primes) {
            if (pn(prime) == num)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Number number = new Number();
        System.out.println(number.checkPerfectNumber(6));
    }
}
