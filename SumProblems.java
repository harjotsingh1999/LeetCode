import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SumProblems {

    // Given a set of non-negative integers, and a value sum, determine if there is
    // a subset of the given set with sum equal to given sum.

    // DECISION PROBLEM
    public void canSum(int target, int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        System.out.println(canSumutil(target, nums, map));
    }

    // This is unbounded knapsack because we can tak every element any number of
    // times
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

    // COMBINATORIC PROBLEM
    public void howSum(int target, int[] nums) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        System.out.println(howSumutil(target, nums, map));
    }

    // This is unbounded knapsack because we can tak every element any number of
    // times

    // Time- O(n^m x nums.length(because of traversing the returned arraylist)) (in
    // bruteforce)
    // Space _ bruteforce- O(m) height of tree

    // DP approach
    // time- O(target x nums.length x nums.length(cuz of array traverse))
    // space - O(nums.length(keys in map) x nums.length(values in map))
    private ArrayList<Integer> howSumutil(int target, int[] nums, HashMap<Integer, ArrayList<Integer>> map) {
        System.out.println("target= " + target);
        if (map.containsKey(target))
            return map.get(target);
        if (target == 0)
            return new ArrayList<Integer>();
        if (target < 0)
            return null;
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = howSumutil(target - nums[i], nums, map);
            if (list != null) {
                ArrayList<Integer> arr = new ArrayList<>();
                for (Integer ele : list) {
                    arr.add(ele);
                }
                arr.add(nums[i]);
                map.put(target, arr);
                return arr;
            }
        }
        map.put(target, null);
        return null;
    }

    // shortest combination of numbers of array that sum up to target
    // in case of tie, return any

    // OPTIMIZATION PROBLEM
    public void bestSum(int target, int[] nums) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        System.out.println(bestSumUtil(target, nums, map));
    }

    private ArrayList<Integer> bestSumUtil(int target, int[] nums, HashMap<Integer, ArrayList<Integer>> map) {
        if (map.containsKey(target))
            return map.get(target);
        System.out.println("target= " + target);
        if (target == 0)
            return new ArrayList<>();
        if (target < 0)
            return null;
        ArrayList<Integer> shortest = null;
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> combination = bestSumUtil(target - nums[i], nums, map);
            if (combination != null) {
                ArrayList<Integer> currentCombo = new ArrayList<>();
                for (int num : combination) {
                    currentCombo.add(num);
                }
                currentCombo.add(nums[i]);
                if (shortest == null || (shortest != null && shortest.size() > currentCombo.size()))
                    shortest = currentCombo;
            }
        }
        map.put(target, shortest);
        return shortest;
    }

    public void allSum(int target, int[] nums) {
        System.out.println(allSumUtil(target, nums));
    }

    // wrong because it counts sum = 4 with {1,1,2},{2,1,1},{1,2,1} as three
    // separate
    // when they should be counted as 1
    // TODO
    private List<List<Integer>> allSumUtil(int target, int[] nums) {
        System.out.println("target= " + target);
        if (target == 0) {
            return new ArrayList<>(Arrays.asList(new ArrayList<>()));
        }
        if (target < 0)
            return null;
        List<List<Integer>> allLists = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> restWays = allSumUtil(target - nums[i], nums);
            System.out.println("for " + nums[i] + " combinations= " + restWays);
            if (restWays != null) {
                List<List<Integer>> targetWays = new ArrayList<>();
                for (List<Integer> list : restWays) {
                    if (list != null) {
                        list.add(0, nums[i]);
                        targetWays.add(list);
                    }
                }
                for (List<Integer> list : targetWays) {
                    allLists.add(list);
                }
            }
        }
        return allLists;
    }

    public boolean canSumTab(int[] nums, int target) {
        boolean[] dp = new boolean[target + 1];

        // always possible to generate 0 by taking no elements
        dp[0] = true;

        for (int i = 0; i <= target; i++) {
            // if current position is true
            // then all elements to the ahead by nums[i] will also be true
            // eg if nums={2,3,5} and dp[0]=true
            // then dp[2], dp[3], dp[5] will also be true
            // since it is possible to achieve sum 2,3,5
            // by taking the elements from the array
            if (dp[i] == true) {
                for (int j = 0; j < nums.length; j++) {

                    // out of bounds condition
                    if (i + nums[j] <= target)
                        dp[i + nums[j]] = true;
                }
            }
        }

        // what we can also do is..
        // dp[i]= true, if any of dp[i-nums[j]] is true
        System.out.println("possible to sum upto " + target + "  " + dp[target]);
        System.out.println(Arrays.toString(dp));
        return dp[target];
    }

    public List<Integer> howSumTab(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>(target + 1);
        for (int i = 0; i <= target; i++) {
            list.add(null);
        }

        list.set(0, new ArrayList<>());
        for (int i = 0; i <= target; i++) {
            if (list.get(i) != null) {
                List<Integer> curList = list.get(i);
                for (int j = 0; j < nums.length; j++) {
                    if (i + nums[j] <= target) {
                        List<Integer> newList = new ArrayList<>();
                        newList.add(nums[j]);
                        newList.addAll(curList);
                        list.set(i + nums[j], newList);
                    }
                }
            }
        }
        System.out.println(list);
        System.out.println(list.get(target));
        return list.get(target);
    }

    public List<Integer> bestSumTab(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>(target + 1);
        for (int i = 0; i <= target; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i <= target; i++) {
            // since default values are empty array
            // we had to separate i from 0
            if (i == 0) {
                for (int j = 0; j < nums.length; j++) {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(nums[j]);
                    list.set(nums[j], newList);
                }
            } else {
                // if not zero, we move further only if it was possible to get the current
                // i(target)

                // if empty it means the current i(target) could not be generated from the array
                // elements
                if (!list.get(i).isEmpty()) {
                    List<Integer> curList = list.get(i);
                    for (int j = 0; j < nums.length; j++) {
                        if (i + nums[j] <= target) {

                            // if only the list present at this target is empty
                            // or its size is more than the newly generated list
                            // we update it
                            if (list.get(i + nums[j]).isEmpty() || 1 + curList.size() < list.get(i + nums[j]).size()) {
                                List<Integer> newList = new ArrayList<>();
                                newList.add(nums[j]);
                                newList.addAll(curList);
                                list.set(i + nums[j], newList);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(list);
        System.out.println(list.get(target));
        return list.get(target);
    }

    public static void main(String[] args) {
        SumProblems sumProblems = new SumProblems();
        int[] nums = { 1, 5, 25 };
        sumProblems.bestSumTab(nums, 100);
    }
}