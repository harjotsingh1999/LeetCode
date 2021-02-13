import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ContainsDuplicate {

    // can use sorting approach for smaller arrays as hashset brings some overhead
    // hence not very efficient for smaller arrays
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i]))
                return true;
            set.add(nums[i]);
        }
        return false;
    }

    // soting aproach
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1])
                return true;
        }
        return false;
    }

    // Given an array of integers and an integer k, find out whether there are two
    // distinct indices i and j in the array such that nums[i] = nums[j] and the
    // absolute difference between i and j is at most k.

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            // if this number already exists in the map
            // with absolute difference in indices <=k
            // return true otherwise add this number
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k)
                return true;
            else
                map.put(nums[i], i);
        }
        return false;
    }

    public int binarySearch(int[] nums, int target, int low, int high) {
        if (low > high)
            return low;
        int mid = (low + high) / 2;
        if (nums[mid] == target)
            return mid;
        else if (target < nums[mid])
            return binarySearch(nums, target, low, mid - 1);
        else if (target > nums[mid])
            return binarySearch(nums, target, mid + 1, high);
        else
            return low;
    }
}
