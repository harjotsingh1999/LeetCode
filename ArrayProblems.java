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

    public static void main(String[] args) {
        ArrayProblems arrayProblems = new ArrayProblems();
        int[] arr = { 4, 3, 2, 7, 8, 2, 3, 1 };
        System.out.println(arrayProblems.findDisappearedNumbers2(arr));
    }
}
