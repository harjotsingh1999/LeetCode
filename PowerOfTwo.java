public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;
        return (int) Math.pow(2, log2(n)) == n;
    }

    int log2(int x) {
        int res = 0;
        while (x != 0) {
            res++;
            x >>= 1;
        }
        return res - 1;
    }

    public static void main(String[] args) {
        PowerOfTwo powerOfTwo = new PowerOfTwo();
        System.out.println(powerOfTwo.isPowerOfTwo3(16));
    }

    // Bitwise Solution: If we subtract a number which is a power of 2 1 then all of
    // it's unset bits after the only set bit become set; and the set bit become
    // unset.

    // For example, consider 4 ( Binary representation: 100) and 16(Binary
    // representation: 10000), we get following after subtracting 1 from them:

    // 3 –> 011
    // 15 –> 01111

    // You can clearly see that bitwise-AND(&) of 4 and 3 gives zero, similarly 16
    // and 15 also gives zero.

    // So, if a number N is a power of 2 then bitwise-AND(&) of N and N-1 will be
    // zero. We can say that N is a power of 2 or not based on the value of N&(N-1).

    // fastest solution bit manipulation
    public boolean isPowerOfTwo2(int n) {
        if (n <= 0)
            return false;
        if ((n & (n - 1)) == 0)
            return true;
        return false;
    }

    public boolean isPowerOfTwo3(int n) {
        if (n % 2 == 1 && n != 1 || n <= 0) {
            return false;
        } else if (n == 1) {
            return true;
        } else {
            return isPowerOfTwo3(n / 2);
        }
    }

}
