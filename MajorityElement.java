import java.util.Arrays;
import java.util.HashMap;

public class MajorityElement {

    // time O(n) space O(n);
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
            if (map.get(i) > Math.floor(nums.length / 2))
                return i;
        }
        return 0;
    }

    // when array is sorted, the majority element will always occupy the center of
    // the array

    // time O(nlogn) space O(1);
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // Boyer-Moore Voting Algorithm
    public int majorityElement3(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int majorityCandidate = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == majorityCandidate) {
                count += 1;
            } else {
                count -= 1;
                if (count == 0) {
                    // RESET
                    count = 1;
                    majorityCandidate = nums[i];
                }
            }
        }

        // we have the majority candidate
        // just verify if it is actually majority element
        // check if its frequency is > n/2
        int freq = 0;
        for (int i : nums) {
            if (i == majorityCandidate)
                freq += 1;
        }
        if (freq > nums.length / 2)
            return majorityCandidate;
        else
            return -1;
    }

    // Runtime: 1 ms, faster than 99.86% of Java online submissions for Majority
    // Element.
    // Memory Usage: 41.9 MB, less than 97.65% of Java online submissions for
    // Majority Element.

    // time O(n) space O(1);
}
