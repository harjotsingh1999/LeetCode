import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    // You are given a sorted unique integer array nums.

    // Return the smallest sorted list of ranges that cover all the numbers in the
    // array exactly. That is, each element of nums is covered by exactly one of the
    // ranges, and there is no integer x such that x is in one of the ranges but not
    // in nums.

    // Each range [a,b] in the list should be output as:

    // "a->b" if a != b
    // "a" if a == b

    // Example 1:

    // Input: nums = [0,1,2,4,5,7]
    // Output: ["0->2","4->5","7"]
    // Explanation: The ranges are:
    // [0,2] --> "0->2"
    // [4,5] --> "4->5"
    // [7,7] --> "7"

    // Example 2:

    // Input: nums = [0,2,3,4,6,8,9]
    // Output: ["0","2->4","6","8->9"]
    // Explanation: The ranges are:
    // [0,0] --> "0"
    // [2,4] --> "2->4"
    // [6,6] --> "6"
    // [8,9] --> "8->9"

    // Example 3:

    // Input: nums = []
    // Output: []

    // Example 4:

    // Input: nums = [-1]
    // Output: ["-1"]

    // Example 5:

    // Input: nums = [0]
    // Output: ["0"]

    // Constraints:

    // 0 <= nums.length <= 20
    // -231 <= nums[i] <= 231 - 1
    // All the values of nums are unique.
    // nums is sorted in ascending order.

    // Runtime: 6 ms, faster than 70.37% of Java online submissions for Summary
    // Ranges.
    // Memory Usage: 37 MB, less than 94.58% of Java online submissions for Summary
    // Ranges.
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<String>();
        if (nums.length == 0)
            return list;
        int start = 0, end = 0;
        int i;
        for (i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 1) {
                // as long as the numbers are continuous
                // increment the end pointer
                end = i;
            } else {
                // as soon as there is a missing number
                System.out.println("SummaryRanges.summaryRanges() start= " + start);
                System.out.println("SummaryRanges.summaryRanges() end= " + end);
                if (start == end) {
                    // if start and end pointers are same
                    // meaning this is a single number between two discrepancies
                    // add this number to list
                    list.add(String.valueOf(nums[start]));
                    end = i;
                    start = end;
                } else {
                    // if the pointers are different
                    // add the numbers in the format given with arrow
                    // and update the pointers to next
                    String str = String.valueOf(nums[start]) + "->" + String.valueOf(nums[end]);
                    list.add(str);
                    end = i;
                    start = end;
                }
            }
        }
        // if anything remains that was not added
        if (start == end) {
            list.add(String.valueOf(nums[start]));
        } else {
            String str = String.valueOf(nums[start]) + "->" + String.valueOf(nums[end]);
            list.add(str);
            end = i;
            start = end;
        }
        return list;
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        int[] nums = { 0, 1, 2, 4, 5, 7 };
        System.out.println(summaryRanges.summaryRanges(nums));
    }
}
