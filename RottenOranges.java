import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

    // Given a grid of dimension nxm where each cell in the grid can have values 0,
    // 1 or 2 which has the following meaning:
    // 0 : Empty cell
    // 1 : Cells have fresh oranges
    // 2 : Cells have rotten oranges

    // We have to determine what is the minimum time required to rot all oranges. A
    // rotten orange at index [i,j] can rot other fresh orange at indexes [i-1,j],
    // [i+1,j], [i,j-1], [i,j+1] (up, down, left and right) in unit time.

    // Example 1:

    // Input: grid = {{0,1,2},{0,1,2},{2,1,1}}
    // Output: 1
    // Explanation: The grid is-
    // 0 1 2
    // 0 1 2
    // 2 1 1
    // Oranges at positions (0,2), (1,2), (2,0)
    // will rot oranges at (0,1), (1,1), (2,2) and
    // (2,1) in unit time.

    // Example 2:

    // Input: grid = {{2,2,0,1}}
    // Output: -1
    // Explanation: The grid is-
    // 2 2 0 1
    // Oranges at (0,0) and (0,1) can't rot orange at
    // (0,3).

    // Your Task:
    // You don't need to read or print anything, Your task is to complete the
    // function orangesRotting() which takes grid as input parameter and returns the
    // minimum time to rot all the fresh oranges. If not possible returns -1.

    // Expected Time Compelxity: O(n*m)
    // Expected Auxiliary Space: O(1)

    public int orangesRotting(int[][] grid) {
        // Code here

        int[][] copy = grid.clone();
        // System.out.println("Copy array= " + Arrays.toString(copy[0]));
        int[] rt = { 1, -1, 0, 0 };
        int[] ct = { 0, 0, -1, 1 };

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // add currently rotten oranges position to queue
                if (grid[i][j] == 2)
                    queue.offer(new int[] { i, j });
            }
        }

        // System.out.println(" Earliest rotten oranges added to queue");
        // add a differentiator(delimiter) to the queue
        // to mark the beginning of the next time frame
        queue.add(new int[] { -1, -1 });

        int nDays = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            // System.out.println("Current orange= " + Arrays.toString(current) + " days= "
            // + nDays);

            if (current[0] == -1 && current[1] == -1) {
                // all oranges of a particular day have been visited and rotten
                // time to add a new differentiator and increase number of days
                if (!queue.isEmpty()) {
                    // this condition checks that (-1,-1) do not keep getting added to the queue
                    // indefinitely even after all oranges have rotten
                    nDays += 1;
                    queue.add(new int[] { -1, -1 });
                }
            } else {
                // if this is a rotten orange, add make its adjascent oranges as rotten, and add
                // them to the queue
                for (int k = 0; k < 4; k++) {
                    int newR = current[0] + rt[k];
                    int newC = current[1] + ct[k];

                    // if the adjascent cell of the current if out of bounds
                    // do not continue further
                    if (newR < 0 || newR >= grid.length || newC < 0 || newC >= grid[0].length)
                        continue;

                    // if adjascent cell has a fresh orange
                    // make it rotten and add it to queue
                    if (copy[newR][newC] == 1) {
                        copy[newR][newC] = 2;
                        queue.offer(new int[] { newR, newC });
                    }
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (copy[i][j] == 1)
                    nDays = -1;
            }
        }
        return nDays;
    }

    public static void main(String[] args) {
        RottenOranges rottenOranges = new RottenOranges();
        int[][] grid = { { 2, 2, 0, 1 } };
        System.out.println(rottenOranges.orangesRotting(grid));
    }
}