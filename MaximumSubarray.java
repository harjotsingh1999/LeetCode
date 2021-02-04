import java.util.Scanner;

public class MaximumSubarray {

    // Kadene's Algorithm
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0], maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {

            // add the current element to the current sum if it makes the total sum more
            // than current sum
            // else make start current sum again with the current element
            currentSum = Math.max(currentSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter length of array");
        int n1 = read.nextInt();
        int[] a = new int[n1];
        for (int i = 0; i < n1; i++) {
            System.out.println("enter element for array");
            a[i] = read.nextInt();
        }
        MaximumSubarray mSubarray = new MaximumSubarray();
        System.out.println(mSubarray.maxSubArray(a));
        read.close();
    }
}
