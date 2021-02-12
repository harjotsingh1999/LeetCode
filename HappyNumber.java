public class HappyNumber {
    public boolean isHappy(int n) {
        if (n == 1 || n == 7)
            return true;
        if (n <= 9)
            return false;
        return isHappy(sumOfSquaresOfDigits(n));
    }

    public int sumOfSquaresOfDigits(int n) {
        int num = n;
        int sum = 0;
        while (num != 0) {
            sum = sum + ((num % 10) * (num % 10));
            num = num / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        HappyNumber happyNumber = new HappyNumber();
        System.out.println(happyNumber.isHappy(19));
    }
}
