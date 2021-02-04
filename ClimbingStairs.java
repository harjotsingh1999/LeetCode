import java.util.Scanner;

public class ClimbingStairs {
    public int climbStairs(int n) {
        // initial cases showed that a fibonacci series is followed
        // 1,2,3,5,8....
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int a = 1, b = 2, c = 0;
        for (int i = 2; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter number of stairs");
        int n = read.nextInt();
        System.out.println(new ClimbingStairs().climbStairs(n));
        read.close();
    }
}
