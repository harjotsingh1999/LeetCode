import java.util.Arrays;

public class ProductExceptSelf {

    // Given an Integer array Arr[] , return an array Ans[] such that Ans[i] is
    // equal to product of all elements except Arr[i].
    // Example 1 :
    // Input: Arr= [1,2,3,4]

    // Output: [24,12,8,6]
    // Example 2:
    // Input : Arr =[-1,1,0-3,3]
    // Output : [0,0,9,0,0]

    static int[] productExceptSelf(int[] arr) {

        int prod = 1;
        int[] ans = new int[arr.length];
        ans[0] = 1;
        for (int i = 1; i < ans.length; i++) {
            ans[i] = prod * arr[i - 1];
            prod *= arr[i - 1];
        }

        System.out.println(Arrays.toString(ans));

        prod = 1;
        for (int i = ans.length - 2; i >= 0; i--) {
            prod *= arr[i + 1];
            ans[i] = ans[i] * prod;
        }

        System.out.println(Arrays.toString(ans));

        return ans;
    }

    public static void main(String[] args) {
        productExceptSelf(new int[] { -1, 1, 0, -3, 3 });
    }

}