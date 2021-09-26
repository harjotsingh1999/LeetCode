public class StockSelect {

    /**
     * An investor has saved some money and wants to invest in the stock market.
     * There are a number of stocks to choose from, and they want to buy at most 1
     * share in any company. The total invested cannot exceed the funds available. A
     * friend who is a stock market expert has predicted the values of each stock
     * after 1 year. Determine the maximum profit that can be earned at the end of
     * the year assuming the predictions come true.
     * 
     * Example:- saving = 250 currentValue = [175, 133, 109, 210, 97] futureValue =
     * [200, 125, 128, 228, 133)
     * 
     * To maximize profits, the investor should buy stocks at indices 2 and 4 for an
     * investment of 109 +97 = 206. At the end of the year the stocks are sold for
     * 128 + 133 = 261, so total profit is 261 -206 = 55
     * 
     * Function Description Complete the function selectStock in the editor below.
     * The function should return an integer that denotes the maximum profit after
     * one year.
     * 
     * selectStock has the following parameter(s): int saving: amount available for
     * investment int currentValue[n]: the current stock values int futureValue[n]:
     * the values of the stocks after one year
     * 
     * Constraints 0<n<100 0<Savings<30000 0<= currrentValue[i],
     * futureValuel[i]<=300
     */

    // solved with 0/1 knapsack

    static int selectStock(int[] currentPrices, int[] futurePrices, int funds) {
        return select(currentPrices, futurePrices, funds, 0);
    }

    private static int select(int[] currentPrices, int[] futurePrices, int funds, int pos) {

        // if reached end of list, or if funds is zero, profit is 0
        if (pos == currentPrices.length || funds <= 0)
            return 0;

        // if funds less than current price, cannot buy this one
        if (funds < currentPrices[pos])
            return select(currentPrices, futurePrices, funds, pos + 1);

        // otherwise i'll get the max of selecting this, and not selecting this current
        // stock
        else
            return Math.max(select(currentPrices, futurePrices, funds, pos + 1), futurePrices[pos] - currentPrices[pos]
                    + select(currentPrices, futurePrices, funds - currentPrices[pos], pos + 1));
    }

    public static void main(String[] args) {
        int[] currentPrices = { 175, 133, 109, 210, 97 };
        int[] futurePrices = { 200, 125, 128, 228, 133 };
        System.out.println(selectStock(currentPrices, futurePrices, 250));
    }
}
