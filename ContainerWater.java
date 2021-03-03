public class ContainerWater {

    // Given n non-negative integers a1, a2, ..., an , where each represents a point
    // at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
    // of the line i is at (i, ai) and (i, 0). Find two lines, which, together with
    // the x-axis forms a container, such that the container contains the most
    // water.

    // Notice that you may not slant the container.

    // Example 1:

    // Input: height = [1,8,6,2,5,4,8,3,7]
    // Output: 49
    // Explanation: The above vertical lines are represented by array
    // [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the
    // container can contain is 49.

    // Example 2:

    // Input: height = [1,1]
    // Output: 1

    // Example 3:

    // Input: height = [4,3,2,1,4]
    // Output: 16

    // Example 4:

    // Input: height = [1,2,1]
    // Output: 2

    // Constraints:

    // n == height.length
    // 2 <= n <= 3 * 104
    // 0 <= height[i] <= 3 * 104

    // to get the max area we need the max width and height
    // hence we start with the max width
    // i.e. pointers on either ends
    // and then compensate the width for more height

    // brute force will be to get all possible wall pairs and find the volumes and
    // get the max
    public int maxArea(int[] height) {
        int max = 0, start = 0, end = height.length - 1;
        // the max volume of water it can hold will be the height of the shorter wall
        while (start < end) {
            if (height[start] < height[end]) {
                int area = height[start] * (end - start);
                max = Math.max(max, area);
                start += 1;
            } else {
                int area = height[end] * (end - start);
                max = Math.max(max, area);
                end -= 1;
            }
        }
        return max;
    }
}
