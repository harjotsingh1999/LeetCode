import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SumProblems {

    // Given a set of non-negative integers, and a value sum, determine if there is
    // a subset of the given set with sum equal to given sum.
    public void canSum(int target, int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        System.out.println(canSumutil(target, nums, map));
    }

    private boolean canSumutil(int target, int[] nums, HashMap<Integer, Boolean> map) {
        if (map.containsKey(target))
            return map.get(target);
        if (target == 0)
            return true;
        if (target < 0)
            return false;
        for (int i = 0; i < nums.length; i++) {
            System.out.println("check for target= " + target + " number= " + nums[i]);
            if (canSumutil(target - nums[i], nums, map)) {
                map.put(target - nums[i], true);
                return true;
            }
        }
        map.put(target, false);
        return false;
    }

    public void howSum(int target, int[] nums) {
        System.out.println(howSumutil(target, nums));
    }

    private ArrayList<Integer> howSumutil(int target, int[] nums) {
        if (target == 0)
            return new ArrayList<Integer>();
        if (target < 0)
            return null;
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = howSumutil(target - nums[i], nums);
            if (list != null) {
                ArrayList<Integer> arr = new ArrayList<>();
                for (Integer ele : list) {
                    arr.add(ele);
                }
                arr.add(nums[i]);
                return arr;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SumProblems sumProblems = new SumProblems();
        int[] nums = { 5, 6 };
        sumProblems.howSum(130000, nums);
    }
}