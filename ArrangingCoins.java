public class ArrangingCoins {

    // You have a total of n coins that you want to form in a staircase shape, where
    // every k-th row must have exactly k coins.

    // Given n, find the total number of full staircase rows that can be formed.

    // n is a non-negative integer and fits within the range of a 32-bit signed
    // integer.

    // Example 1:

    // n = 5

    // The coins can form the following rows:
    // ¤
    // ¤ ¤
    // ¤ ¤

    // Because the 3rd row is incomplete, we return 2.

    // Example 2:

    // n = 8

    // The coins can form the following rows:
    // ¤
    // ¤ ¤
    // ¤ ¤ ¤
    // ¤ ¤

    // Because the 4th row is incomplete, we return 3.

    // 1, 2, 3,.... n = n(n+1)/2 <= k , is the condition
    // we get n^2+n-2k=0
    // hence n= {-1+Math.sqrt[1-(4x1x-2k)]}/2
    public int arrangeCoins(int n) {
        return (int) ((long) Math.sqrt(8 * (long) n + 1) - 1) / 2;
    }

    public static void main(String[] args) {
        ArrangingCoins arrangingCoins = new ArrangingCoins();
        System.out.println(arrangingCoins.arrangeCoins(8));
    }
}
