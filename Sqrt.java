import java.util.Scanner;

public class Sqrt {
    // Given a non-negative integer x, compute and return the square root of x.

    // Since the return type is an integer, the decimal digits are truncated, and
    // only the integer part of the result is returned.

    public int mySqrt1(int x) {
        return Math.abs((int) Math.sqrt(x));
    }

    /*
     * start with a range between 1 to x/2 (square root of any number can not be
     * greater than half of the number) find the mid point and calculate the x/mid
     * as quotient we go for division instead of multiplication to avoid unwanted
     * overflow If quotient == mid; we have our answer If quotient > mid; move start
     * to mid; not to mid + 1 as mid might be a valid answer if quotient < mid; move
     * end to mid-1
     */

    public int mySqrt(int x) {
        if (x <= 1)
            return x;
        int start = 1;
        int end = x / 2;

        while (start < end) {
            // start is not always moving and hence we can get stuck in infinite loop with
            // mid calculation
            // Adding 1 to mid everytime to ensure we always move the mid
            int mid = (start + (end - start) / 2) + 1;

            // use division instead of multiplication to avoid overflow
            int div = x / mid;
            if (div == mid)
                return mid;
            if (div > mid)
                start = mid;
            else
                end = mid - 1;
        }

        return start;

    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter integer");
        int n = read.nextInt();
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.mySqrt(n));
        read.close();
    }
}
