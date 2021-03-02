import java.util.Arrays;
import java.util.HashMap;

public class RelativeRanks {

    // You are given an integer array score of size n, where score[i] is the score
    // of the ith athlete in a competition. All the scores are guaranteed to be
    // unique.

    // The athletes are placed based on their scores, where the 1st place athlete
    // has the highest score, the 2nd place athlete has the 2nd highest score, and
    // so on. The placement of each athlete determines their rank:

    // The 1st place athlete's rank is "Gold Medal".
    // The 2nd place athlete's rank is "Silver Medal".
    // The 3rd place athlete's rank is "Bronze Medal".
    // For the 4th place to the nth place athlete, their rank is their placement
    // number (i.e., the xth place athlete's rank is "x").

    // Return an array answer of size n where answer[i] is the rank of the ith
    // athlete.

    // Example 1:

    // Input: score = [5,4,3,2,1]
    // Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
    // Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].

    // Example 2:

    // Input: score = [10,3,8,9,4]
    // Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
    // Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].

    // Constraints:

    // n == score.length
    // 1 <= n <= 104
    // 0 <= score[i] <= 106
    // All the values in score are unique.

    // Runtime: 6 ms, faster than 87.62% of Java online submissions for Relative
    // Ranks.
    // Memory Usage: 39.7 MB, less than 94.55% of Java online submissions for
    // Relative Ranks.
    public String[] findRelativeRanks(int[] score) {

        // to store tha positions of each element
        // to be used later
        HashMap<Integer, Integer> map = new HashMap<>(score.length);
        for (int i = 0; i < score.length; i++) {
            map.put(score[i], i);
        }
        System.out.println(map);

        // sort the array so that the max score is at last
        Arrays.sort(score);
        String[] ans = new String[score.length];
        Arrays.fill(ans, "");

        // finding the position of the max score from the map
        // and putting the required String in that position of the ans array
        // similar for second max, third max and rest of the positions as well
        if (score.length >= 1)
            ans[map.get(score[score.length - 1])] = "Gold Medal";
        if (score.length >= 2)
            ans[map.get(score[score.length - 2])] = "Silver Medal";
        if (score.length >= 3)
            ans[map.get(score[score.length - 3])] = "Bronze Medal";

        for (int i = 4; i <= ans.length; i++) {
            ans[map.get(score[score.length - i])] = String.valueOf(i);
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }

    public static void main(String[] args) {
        RelativeRanks relativeRanks = new RelativeRanks();
        int[] score = { 1 };
        relativeRanks.findRelativeRanks(score);
    }

}
