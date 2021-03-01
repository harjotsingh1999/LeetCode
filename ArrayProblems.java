import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ArrayProblems {

    // Given integer array nums, return the third maximum number in this array. If
    // the third maximum does not exist, return the maximum number.

    // could also be done with HashSet since it sorts and keeps the elements
    public int thirdMax(int[] nums) {
        long max = Long.MIN_VALUE, sMax = Long.MIN_VALUE, tMax = Long.MIN_VALUE;
        for (int i : nums) {
            if (i > max) {
                tMax = sMax;
                sMax = max;
                max = i;
            } else if (i > sMax && i != max) {
                tMax = sMax;
                sMax = i;
            } else if (i > tMax && i != sMax && i != max) {
                tMax = i;
            }
            System.out.println("ArrayProblems.thirdMax() max= " + max + " smax= " + sMax + " tmax= " + tMax);
        }
        return tMax == Long.MIN_VALUE ? (int) max : (int) tMax;
    }

    // Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some
    // elements appear twice and others appear once.

    // Find all the elements of [1, n] inclusive that do not appear in this array.

    // Could you do it without extra space and in O(n) runtime? You may assume the
    // returned list does not count as extra space.

    // Example:

    // Input:
    // [4,3,2,7,8,2,3,1]

    // Output:
    // [5,6]

    // O(n) space O(n) time
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (!set.contains(n))
                set.add(n);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i))
                list.add(i);
        }
        return list;
    }

    // O(1) space O(n) time
    // using the fact that 1 ≤ a[i] ≤ n (n = size of array),
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> list = new ArrayList<>();

        System.out.println("ArrayProblems.findDisappearedNumbers2() arr= " + Arrays.toString(nums));
        for (int i : nums) {
            // using the numbers as indices and marking the numbers at those positions as
            // negative
            int index = Math.abs(i);
            if (nums[index - 1] > 0) {
                nums[index - 1] *= -1;
            }
        }

        // so that numbers in whichever index is positive
        // it will mean that that index is not within the numbers
        System.out.println("ArrayProblems.findDisappearedNumbers2() arr= " + Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                list.add(i + 1);
        }
        return list;
    }

    // Given a binary array, find the maximum number of consecutive 1s in this
    // array.

    // Example 1:

    // Input: [1,1,0,1,1,1]
    // Output: 3
    // Explanation: The first two digits or the last three digits are consecutive
    // 1s.
    // The maximum number of consecutive 1s is 3.

    // Note:

    // The input array will only contain 0 and 1.
    // The length of input array is a positive integer and will not exceed 10,000

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            System.out.println("i= " + i + " and ele= " + nums[i] + " count= " + count + " max= " + max);
            if (nums[i] == 1) {
                count = 1;
                while (i < nums.length - 1 && nums[i + 1] == 1) {
                    System.out.println("while i= " + i + " and ele= " + nums[i] + " count= " + count + " max= " + max);
                    i += 1;
                    count += 1;
                }
                max = Math.max(max, count);
                System.out.println("while end i= " + i + " and ele= " + nums[i] + " count= " + count + " max= " + max);
            }
            System.out.println("out of if i= " + i + " and ele= " + nums[i] + " count= " + count + " max= " + max);
            count = 0;
        }
        return max;
    }

    public static void main(String[] args) {
        ArrayProblems arrayProblems = new ArrayProblems();
        int[] arr = { 1, 1, 1 };
        System.out.println(arrayProblems.check(arr));
    }
}
