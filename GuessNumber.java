public class GuessNumber {

    // We are playing the Guess Game. The game is as follows:

    // I pick a number from 1 to n. You have to guess which number I picked.

    // Every time you guess wrong, I will tell you whether the number I picked is
    // higher or lower than your guess.

    // You call a pre-defined API int guess(int num), which returns 3 possible
    // results:

    // -1: The number I picked is lower than your guess (i.e. pick < num).
    // 1: The number I picked is higher than your guess (i.e. pick > num).
    // 0: The number I picked is equal to your guess (i.e. pick == num).

    // Return the number that I picked.

    /**
     * Forward declaration of guess API.
     * 
     * @param num your guess
     * @return -1 if num is lower than the guess number 1 if num is higher than the
     *         guess number otherwise return 0 int guess(int num);
     */

    public int guessNumber(int n) {
        // return guessTheNumber(0, n);

        // or do it with while loop
        int low = 1, high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int result = guess(mid);
            System.out.println("GuessNumber.guessTheNumber() low= " + low + " and high= " + high + " and mid= " + mid
                    + " and result= " + result);
            if (result == 0)
                return mid;
            else if (result > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public int guessTheNumber(int low, int high) {
        int mid = low + (high - low) / 2;
        System.out.println("GuessNumber.guessTheNumber() low= " + low + " and high= " + high + " and mid= " + mid);
        if (low > high)
            return -1;
        if (guess(mid) == 0)
            return mid;
        else if (guess(mid) > 0)
            return guessTheNumber(low, mid - 1);
        else
            return guessTheNumber(mid + 1, high);
    }

    public int guess(int n) {
        if (n == 6)
            return 0;
        return n > 6 ? 1 : -1;
    }

    public static void main(String[] args) {
        GuessNumber guessNumber = new GuessNumber();
        System.out.println(guessNumber.guessNumber(10));
    }
}
