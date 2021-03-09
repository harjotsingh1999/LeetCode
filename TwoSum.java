public class TwoSum {

    // Given an array of integers numbers that is already sorted in ascending order,
    // find two numbers such that they add up to a specific target number.

    // Return the indices of the two numbers (1-indexed) as an integer array answer
    // of size 2, where 1 <= answer[0] < answer[1] <= numbers.length.

    // You may assume that each input would have exactly one solution and you may
    // not use the same element twice.

    // Constraints
    // 2 <= numbers.length <= 3 * 104
    // -1000 <= numbers[i] <= 1000
    // numbers is sorted in increasing order.
    // -1000 <= target <= 1000
    // Only one valid answer exists.

    public int[] twoSum(int[] numbers, int target) {
        int firstIndex = 0, lastIndex = numbers.length - 1;
        while (firstIndex < lastIndex) {
            if (numbers[firstIndex] + numbers[lastIndex] == target) {
                int[] out = new int[2];
                out[0] = firstIndex + 1;
                out[1] = lastIndex + 1;
                return out;
            } else if (numbers[firstIndex] + numbers[lastIndex] < target) {
                firstIndex += 1;
            } else {
                lastIndex -= 1;
            }
        }
        int[] out = new int[2];
        out[0] = firstIndex + 1;
        out[1] = lastIndex + 1;
        return out;
    }

    // this was where the array was sorted
    // in the problem where array is not sorted, we use a map
}
