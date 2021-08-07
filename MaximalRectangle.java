import java.util.*;

public class MaximalRectangle {

    // https://www.youtube.com/watch?v=dAVF2NpC3j4&t=29s watch this for
    // understanding
    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0)
            return 0;

        // calculate the histogram for each level one by one
        // and for ech level calculate max area in histogram
        int r = matrix.length;
        int c = matrix[0].length;
        int[] curRow = new int[c];

        for (int i = 0; i < c; i++) {
            curRow[i] = matrix[0][i] == '0' ? 0 : 1;
        }
        int max = largestRectangleArea(curRow);
        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {

                // if current row ka element is 0
                // that means base is zero
                // hence hisogram bar length will be 0
                if (matrix[i][j] == '0')
                    curRow[j] = 0;

                // othwise this 1 will add on the the current bar length
                else
                    curRow[j] = curRow[j] + 1;
            }
            // System.out.println("currow "+i+" = "+ Arrays.toString(curRow));

            // after current row is formed, get the max histogram area of current row
            max = Math.max(largestRectangleArea(curRow), max);
        }
        return max;
    }

    public int largestRectangleArea(int[] heights) {
        int max = Integer.MIN_VALUE;
        int n = heights.length;
        int[] closestLeftSmaller = prevSmallerElementIndex(heights, n);
        int[] closestRightSmaller = nextSmallerElement(heights, n);

        // System.out.println(Arrays.toString(closestLeftSmaller));
        // System.out.println(Arrays.toString(closestRightSmaller));

        for (int i = 0; i < n; i++) {
            max = Math.max(max, heights[i] * (closestRightSmaller[i] - closestLeftSmaller[i] + 1));
        }
        return max;

    }

    public int[] prevSmallerElementIndex(int[] arr, int n) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[n];
        stack.push(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            if (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                    ans[stack.pop()] = i + 1;
                }
                stack.push(i);
            } else {
                stack.push(i);
            }
        }
        while (!stack.isEmpty())
            ans[stack.pop()] = 0;
        return ans;
    }

    public int[] nextSmallerElement(int[] arr, int n) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[n];
        stack.push(0);
        for (int i = 1; i < n; i++) {
            if (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                    ans[stack.pop()] = i - 1;
                }
                stack.push(i);
            } else {
                stack.push(i);
            }
        }
        // System.out.println("stack= "+stack);
        while (!stack.isEmpty())
            ans[stack.pop()] = n - 1;
        return ans;
    }
}
