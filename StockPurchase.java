public class StockPurchase {
    public int maxProfit2(int[] prices) {
        int maxpro = 0;
        int currentProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                currentProfit = prices[j] - prices[i];
                if (currentProfit > maxpro)
                    maxpro = currentProfit;
            }
        }
        return maxpro;
    }

    // Time complexity : O(n)). Only a single pass is needed.
    // Space complexity : O(1). Only two variables are used.

    public int maxProfit1(int[] prices) {
        // buying day will be when the price is the lowest
        int buyingDay = 0, maxProfit = 0;
        for (int sellingDay = 1; sellingDay < prices.length; sellingDay++) {
            if (prices[sellingDay] < prices[buyingDay])
                buyingDay = sellingDay;
            else
                maxProfit = Math.max(maxProfit, prices[sellingDay] - prices[buyingDay]);
        }
        return maxProfit;
    }

    // Say you have an array prices for which the ith element is the price of a
    // given stock on day i.

    // Design an algorithm to find the maximum profit. You may complete as many
    // transactions as you like (i.e., buy one and sell one share of the stock
    // multiple times).

    // Note: You may not engage in multiple transactions at the same time (i.e., you
    // must sell the stock before you buy again).

    // Example 1:

    // Input: [7,1,5,3,6,4]
    // Output: 7
    // Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit =
    // 5-1 = 4.
    // Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 =
    // 3.

    // Example 2:

    // Input: [1,2,3,4,5]
    // Output: 4
    // Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit =
    // 5-1 = 4.
    // Note that you cannot buy on day 1, buy on day 2 and sell them later, as you
    // are
    // engaging multiple transactions at the same time. You must sell before buying
    // again.

    // Example 3:

    // Input: [7,6,4,3,1]
    // Output: 0
    // Explanation: In this case, no transaction is done, i.e. max profit = 0.

    // Constraints:

    // 1 <= prices.length <= 3 * 10 ^ 4
    // 0 <= prices[i] <= 10 ^ 4

    public int maxProfit(int[] prices) {
        int maxPro = 0;
        int buyingPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buyingPrice) {
                buyingPrice = prices[i];
            } else {
                if (prices[i] - buyingPrice > 0) {
                    maxPro += (prices[i] - buyingPrice);
                    buyingPrice = prices[i];
                }

            }
        }
        return maxPro;
    }

    public static void main(String[] args) {
        StockPurchase stockPurchase = new StockPurchase();
        int[] arr = { 7, 6, 4, 3, 1 };
        System.out.println(stockPurchase.maxProfit(arr));
    }
}
