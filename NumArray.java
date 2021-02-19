import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class NumArray {

    // Given an integer array nums, find the sum of the elements between indices i
    // and j (i ≤ j), inclusive.

    // Implement the NumArray class:

    // NumArray(int[] nums) Initializes the object with the integer array nums.
    // int sumRange(int i, int j) Return the sum of the elements of the nums array
    // in the range [i, j] inclusive (i.e., sum(nums[i], nums[i + 1], ... ,
    // nums[j]))

    // Example 1:

    // Input
    // ["NumArray", "sumRange", "sumRange", "sumRange"]
    // [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
    // Output
    // [null, 1, -1, -3]

    // Explanation
    // NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
    // numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
    // numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
    // numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))

    // Constraints:

    // 0 <= nums.length <= 104
    // -105 <= nums[i] <= 105
    // 0 <= i <= j < nums.length
    // At most 10^4 calls will be made to sumRange.

    int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
    }

    // BruteForce
    public int sumRange(int i, int j) {
        int sum = 0;
        for (int j2 = i; j2 <= j; j2++) {
            sum += nums[j2];
        }
        return sum;
    }
}

// Time complexity : O(1) time per query, O(n^2) time
// pre-computation. The pre-computation done in the constructor takes
// O(n^2) time. Each sumRange query's time complexity is O(1)
// as the hash table's look up operation is constant time.

// Space complexity : O(n^2). The extra space required is
// O(n^2) as there are nnn candidates for both i and j.

class NumArray2 {
    // alternative would be to store all possible pairs of indices and the sum in
    // the hashmap when constructor is called

    HashMap<List<Integer>, Integer> sums;
    int[] nums;

    NumArray2(int[] nums) {
        this.nums = nums;
        this.sums = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                if (j >= i) {
                    sum += nums[j];
                    List<Integer> indices = new ArrayList<>();
                    indices.add(i);
                    indices.add(j);
                    sums.put(indices, sum);
                }
            }
        }
        System.out.println(sums);
    }

    public int sumRange(int i, int j) {
        List<Integer> indices = new ArrayList<>();
        indices.add(i);
        indices.add(j);
        return sums.get(indices);
    }

    public static void main(String[] args) {
        int[] nums = { -2, 0, 3, -5, 2, -1 };
        NumArray2 numArray2 = new NumArray2(nums);
        System.out.println(numArray2.sumRange(1, 3));
    }
}

// the above approach takes a lot of space
// we observe that sum(i,j) = sum(0, j+1)-sum(0,i)

class NumArray3 {
    // alternative would be to store all possible pairs of indices and the sum in
    // the hashmap when constructor is called

    int[] sums;
    int[] nums;

    // caching the cumulative sum of all elements upto i in sums
    // so that retrieval is O(1)
    NumArray3(int[] nums) {
        this.nums = nums;
        sums = new int[nums.length];
        if (nums.length > 0) {
            int sum = nums[0];
            sums[0] = sum;
            for (int i = 1; i < nums.length; i++) {
                sum = sum + nums[i];
                sums[i] = sum;
            }
        }
        System.out.println(Arrays.toString(sums));
    }

    public int sumRange(int i, int j) {
        if (i < sums.length && j < sums.length)
            return sums[j] - (i != 0 ? sums[i - 1] : 0);
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = { -2, 0, 3, -5, 2, -1 };
        NumArray3 numArray2 = new NumArray3(nums);
        System.out.println(numArray2.sumRange(1, 4));
    }
}