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

    public int maxProfit(int[] prices) {
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

}
