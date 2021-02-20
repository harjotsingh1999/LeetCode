public class ValidPerfectSquare {
    // Given a positive integer num, write a function which returns True if num is a
    // perfect square else False.

    public boolean isPerfectSquare(int num) {
        int sq = (int) Math.sqrt(num);

        return sq * sq == num;
    }

    // Follow up: Do not use any built-in library function such as sqrt.
    // using binary search
    public boolean isPerfectSquare2(int num) {
        long l = 1;
        long h = num;
        while (l <= h) {
            long mid = (l + h) / 2;
            System.out.println(
                    "ValidPerfectSquare.isPerfectSquare2() low= " + l + " and high= " + h + " and mid= " + mid);
            if (mid * mid == num)
                return true;
            else if (mid * mid < num) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ValidPerfectSquare validPerfectSquare = new ValidPerfectSquare();
        System.out.println(validPerfectSquare.isPerfectSquare2(14));
    }
}
