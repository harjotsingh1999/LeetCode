import java.util.HashMap;

public class StockPurchase {

    // bruteforce
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

    // valley peak approach, just add the difference between each transaction
    // when the price of second day is more than price of first day
    public int maxProfitAnyNumberOfTransactions(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        }
        return profit;
    }

    // max 2 transactions allowed

    // buy at minima ans sell at maxima to make a profit
    // but point is to decide which minima and maxima to choose

    // at every point we have 3 choices, to buy, to sell or to skip
    // under terms and conditions that
    // cannot sell before buy
    // cannot buy and sell on same day
    public void maxProfitInTwoTransactions(int[] prices) {
        HashMap<String, Integer> memo = new HashMap<>();

        System.out.println(maxPro2TransactionsRecur(prices, 0, 2, false, memo));
    }

    public int maxPro2TransactionsRecur(int[] prices, int curDay, int remaining, boolean stockInHand,
            HashMap<String, Integer> memo) {

        System.out.println(curDay + " " + remaining + " " + stockInHand);
        if (curDay >= prices.length || remaining == 0)
            return 0;

        String key = curDay + "," + remaining + "," + stockInHand;
        if (memo.containsKey(key))
            return memo.get(key);
        // profit i can gain if I skip the current days
        int result = maxPro2TransactionsRecur(prices, curDay + 1, remaining, stockInHand, memo);

        // if stock in hand, will have to sell, and get max of selling vs skipping at
        // this point
        if (stockInHand)
            result = Math.max(result,
                    maxPro2TransactionsRecur(prices, curDay + 1, remaining - 1, false, memo) + prices[curDay]);

        // otherwise I can try buying, and get max of buying vs skipping
        else
            result = Math.max(result,
                    maxPro2TransactionsRecur(prices, curDay + 1, remaining, true, memo) - prices[curDay]);

        return result;
    }

    public static void main(String[] args) {
        StockPurchase stockPurchase = new StockPurchase();
        // int[] arr = { 7, 6, 4, 3, 1 };
        // System.out.println(stockPurchase.maxProfit(arr));

        stockPurchase.maxProfitInTwoTransactions(new int[] { 3, 3, 5, 0, 0, 3, 1, 4 });
    }
}
