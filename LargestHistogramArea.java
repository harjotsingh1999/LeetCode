import java.util.Arrays;
import java.util.Stack;

public class LargestHistogramArea {

    public int largestRectangleArea(int[] heights) {
        int max = Integer.MIN_VALUE;
        int n = heights.length;

        // to store the index of nearest smaller number to the left of i(before i)
        int[] closestLeftSmaller = prevSmallerElementIndex(heights, n);

        // to store the closest index with smaller number to the right of i
        int[] closestRightSmaller = nextSmallerElementIndex(heights, n);

        System.out.println(Arrays.toString(closestLeftSmaller));
        System.out.println(Arrays.toString(closestRightSmaller));

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

    public int[] nextSmallerElementIndex(int[] arr, int n) {
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

    public static void main(String[] args) {

    }

    // next smaller element
    public static long[] nextSmallerElement(long[] arr, int n) {
        Stack<Integer> stack = new Stack<>();
        long[] ans = new long[n];
        stack.push(0);
        for (int i = 1; i < n; i++) {
            if (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                    ans[stack.pop()] = arr[i];
                }
                stack.push(i);
            } else {
                stack.push(i);
            }
        }
        // those indices that do not have a next smaller element
        while (!stack.isEmpty())
            ans[stack.pop()] = -1;
        return ans;
    }

    // next smaller element from behind gives previous smaller element
    public static long[] prevSmallerElement(long[] arr, int n) {
        Stack<Integer> stack = new Stack<>();
        long[] ans = new long[n];
        stack.push(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            if (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                    ans[stack.pop()] = arr[i];
                }
                stack.push(i);
            } else {
                stack.push(i);
            }
        }

        // those indices that do not have a previous smaller element
        while (!stack.isEmpty())
            ans[stack.pop()] = -1;
        return ans;
    }

    // Next greater element
    public static long[] nextLargerElement(long[] arr, int n) {
        // Your code here
        Stack<Integer> stack = new Stack<>();
        long[] ans = new long[n];
        stack.push(0);
        for (int i = 1; i < n; i++) {
            if (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                    ans[stack.pop()] = arr[i];
                }
                stack.push(i);
            } else {
                stack.push(i);
            }
        }
        // those indices that do not have a next greater element
        while (!stack.isEmpty())
            ans[stack.pop()] = -1;
        return ans;
    }
}
