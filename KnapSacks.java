import java.util.HashMap;

public class KnapSacks {

    // given items with weights and profits, generate maxProfit under the constraint
    // of maxWeight
    public void maxPofit(int[] weights, int[] profits, int maxWeight) {
        HashMap<String, Integer> map = new HashMap<>();
        System.out.println(maxProfitUtil(weights, profits, maxWeight, weights.length - 1, map));
    }

    private int maxProfitUtil(int[] weights, int[] profits, int maxWeight, int n, HashMap<String, Integer> map) {

        String str = maxWeight + "," + n;
        if (map.containsKey(str))
            return map.get(str);

        // if there is no weight or no elements
        // then max profit will be zero
        if (maxWeight == 0 || n == 0)
            return 0;

        // if weight of current item is more than maxWt, we skip it
        if (weights[n] > maxWeight)
            return maxProfitUtil(weights, profits, maxWeight, n - 1, map);

        // otherwise return the max of including and not including that item
        map.put(str, Math.max(maxProfitUtil(weights, profits, maxWeight, n - 1, map),
                profits[n] + maxProfitUtil(weights, profits, maxWeight - weights[n], n - 1, map)));
        return map.get(str);
    }

    public void maxProfitTab(int[] weights, int[] profits, int maxWeight) {

        // rows will indicate the number of elements we are considering
        // eg row 0 means we ara taking no elements from the array
        // row 2 means first two elements

        // columns will indicate the maxWeight constraint

        // hence any cell i,j in the dp will indicate the max profit that can be
        // generated
        // considering i number of elements from the array from start
        // with j as the maxWeight constraint

        int[][] dp = new int[weights.length + 1][maxWeight + 1];

        // INITIAL VALUES
        // if maxWeight = 0 (j==0), then it will never be possible to generate any
        // profit
        // similarly if we are considering 0 elements(i=0), no profits possible

        int nElements = weights.length;
        for (int i = 0; i <= nElements; i++) {
            for (int j = 0; j <= maxWeight; j++) {
                // if no elements are taken,
                // or if maxWt is zero
                // then maxprofits will be zero only
                if (i == 0 || j == 0)
                    dp[i][j] = 0;

                // if the weight of the current element
                // is greater than the current maxWt constraint
                // then we cannot include this item
                // hence the max profit will only be what was for the item before its
                else if (weights[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];

                // else you can take this element or not
                // if you don't take the max profit will be that of
                // the element before it

                // if you take it, you get its profit,
                // but the weight constraint reduces by the wt. of this item
                // store the max of these 2 profits
                else
                    dp[i][j] = Math.max(dp[i - 1][j], profits[i - 1] + dp[i - 1][j - weights[i - 1]]);
            }
        }
        System.out.println(dp[weights.length][maxWeight]);
    }

    // find if there is a subset whose sum equals k
    public void subsetSumEqualsK(int[] arr, int k) {
        System.out.println("sum possible= " + subsetSumEqualsKUtil(arr, k, arr.length));
    }

    private boolean subsetSumEqualsKUtil(int[] arr, int k, int n) {
        // a sum =0 can always be generated using no elements
        if (k == 0)
            return true;
        // if sum is not zero, and we have no elements
        // sum can never become zero now
        if (n == 0)
            return false;

        // if given number is more than 1
        // we have to exclude it
        if (arr[n - 1] > k)
            return subsetSumEqualsKUtil(arr, k, n - 1);

        // otherwise we can include it or exclude it
        // and if any is true, return true
        return subsetSumEqualsKUtil(arr, k, n - 1) || subsetSumEqualsKUtil(arr, k - arr[n - 1], n - 1);
    }

    public void subsetSumEqualsKTab(int[] arr, int sum) {
        boolean[][] dp = new boolean[arr.length + 1][sum + 1];

        // i loop is for elements of array
        for (int i = 0; i <= arr.length; i++) {
            // j loop is for the sum
            for (int j = 0; j <= sum; j++) {

                // always possible to generate 0 target sum
                if (i == 0 && j == 0 || j == 0)
                    dp[i][j] = true;

                // no element taken from array
                // no target sum can be generated
                else if (i == 0)
                    dp[i][j] = false;

                // if curent element greater than current sum required
                // we cannot use it
                else if (arr[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];

                // else we either skip that item
                // or include it and reduce from the sum
                else
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];

            }
        }
        System.out.println(dp[arr.length][sum]);
    }

    public void countSubsetsWithSumK(int[] arr, int sum) {
        System.out.println(countSubsetsWithSumKUtil(arr, sum, arr.length));
    }

    private int countSubsetsWithSumKUtil(int[] arr, int sum, int n) {
        if (sum == 0)
            return 1;
        if (n == 0)
            return 0;
        if (arr[n - 1] > sum)
            return countSubsetsWithSumKUtil(arr, sum, n - 1);
        return countSubsetsWithSumKUtil(arr, sum, n - 1) + countSubsetsWithSumKUtil(arr, sum - arr[n - 1], n - 1);
    }

    public void countSubsetsWithSumKTab(int[] arr, int sum) {
        int[][] dp = new int[arr.length + 1][sum + 1];
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // only 1 way to add elements upto 0
                if (i == 0 && j == 0 || j == 0)
                    dp[i][j] = 1;
                // no way to add upto j by taking no elements
                else if (i == 0)
                    dp[i][j] = 0;
                else if (arr[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i - 1]];
            }
        }
        System.out.println("no of ways= " + dp[arr.length][sum]);
    }

    public static void main(String[] args) {
        KnapSacks knapScacks = new KnapSacks();
        // knapScacks.maxProfitTab(new int[] { 10, 20, 30 }, new int[] { 60, 100, 120 },
        // 50);
        knapScacks.countSubsetsWithSumKTab(new int[] { 1, 2, 3 }, 3);
    }

}
