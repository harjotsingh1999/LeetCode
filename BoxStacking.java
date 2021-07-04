import java.util.Arrays;

public class BoxStacking {

    /**
     * You are given a set of N types of rectangular 3-D boxes, where the ith box
     * has height h, width w and length l. You task is to create a stack of boxes
     * which is as tall as possible, but you can only stack a box on top of another
     * box if the dimensions of the 2-D base of the lower box are each strictly
     * larger than those of the 2-D base of the higher box. Of course, you can
     * rotate a box so that any side functions as its base.It is also allowable to
     * use multiple instances of the same type of box. You task is to complete the
     * function maxHeight which returns the height of the highest possible stack so
     * formed.
     * 
     * 
     * Note: Base of the lower box should be strictly larger than that of the new
     * box we're going to place. This is in terms of both length and width, not just
     * in terms of area. So, two boxes with same base cannot be placed one over the
     * other.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: n = 4 height[] = {4,1,4,10} width[] = {6,2,5,12} length[] = {7,3,6,32}
     * Output: 60 Explanation: One way of placing the boxes is as follows in the
     * bottom to top manner: (Denoting the boxes in (l, w, h) manner) (12, 32, 10)
     * (10, 12, 32) (6, 7, 4) (5, 6, 4) (4, 5, 6) (2, 3, 1) (1, 2, 3) Hence, the
     * total height of this stack is 10 + 32 + 4 + 4 + 6 + 1 + 3 = 60. No other
     * combination of boxes produces a height greater than this. Example 2:
     * 
     * Input: n = 3 height[] = {1,4,3} width[] = {2,5,4} length[] = {3,6,1} Output:
     * 15 Explanation: One way of placing the boxes is as follows in the bottom to
     * top manner: (Denoting the boxes in (l, w, h) manner) (5, 6, 4) (4, 5, 6) (3,
     * 4, 1), (2, 3, 1) (1, 2, 3). Hence, the total height of this stack is 4 + 6 +
     * 1 + 1 + 3 = 15 No other combination of boxes produces a height greater than
     * this. Your Task: You don't need to read input or print anything. Your task is
     * to complete the function maxHeight() which takes three arrays height[],
     * width[], length[], and N as a number of boxes and returns the highest
     * possible stack height which could be formed.
     * 
     * 
     * Expected Time Complexity : O(N*N) Expected Auxiliary Space: O(N)
     */

    // can create at most 3 configurations of boxes of each type by rotating
    // sort these 3xn boxes based on base area in ascending/descending order
    // after sorting there may be some boxes in between that will not be "strictly
    // less" in base area from the one below
    // strictly less means smaller in both length and width(but will be smaller in
    // area cuz array is sorted)
    // we have to remove these unusual boxes
    // that is sort the stack in terms of strictly increasing area(l1<l2, w1<w2)
    // hence approach is longest increasing subsequence
    public static int maxHeight(int[] height, int[] width, int[] length, int n) {

        Box[] boxes = new Box[3 * n];
        for (int i = 0; i < n; i += 1) {
            boxes[i] = new Box(width[i], length[i], height[i]);
            boxes[i + n] = new Box(height[i], width[i], length[i]);
            boxes[i + 2 * n] = new Box(length[i], height[i], width[i]);
        }
        System.out.println("boxes= " + Arrays.toString(boxes));
        Arrays.sort(boxes);
        System.out.println("boxes= " + Arrays.toString(boxes));

        int[] dp = new int[n * 3];
        int max = -1;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = boxes[i].h;
            // max = Math.max(max, boxes[i].h);
        }

        for (int i = 1; i < dp.length; i++) {
            int j = 0;
            while (j < i) {
                System.out.println("i= " + i + " j= " + j);
                if (boxes[j].l > boxes[i].l && boxes[j].w > boxes[i].w) {
                    System.out.print("dp at " + i + " was previously " + dp[i]);
                    dp[i] = Math.max(dp[i], boxes[i].h + dp[j]);
                    System.out.print(" now " + dp[i]);
                    System.out.println();
                }
                j += 1;
            }
            max = Math.max(max, dp[i]);
            System.out.println("max now= " + max);
            // try {
            // Thread.sleep(2000);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }

        System.out.println(max);
        return max;
    }

    public static void main(String[] args) {
        BoxStacking.maxHeight(new int[] { 4, 1, 4, 10 }, new int[] { 6, 2, 5, 12 }, new int[] { 7, 3, 6, 32 }, 4);
    }
}

class Box implements Comparable<Box> {
    int l, w, h;

    public Box(int l, int w, int h) {
        this.l = l;
        this.w = w;
        this.h = h;
    }

    @Override
    public int compareTo(Box o) {
        return o.l * o.w - this.l * this.w;
    }

    @Override
    public String toString() {
        return "Box: " + this.l + "," + this.w + "," + this.h;
    }

}

// boxes= [Box: 2,1,3, Box: 3,1,2, Box: 2,3,1, Box: 5,4,6, Box: 6,4,7, Box:
// 6,4,5, Box: 7,4,6, Box: 5,6,4, Box: 6,7,4, Box: 12,10,32, Box: 32,10,12, Box:
// 12,32,10]