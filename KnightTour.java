import java.util.Arrays;

public class KnightTour {

    /**
     * Given a N*N board with the Knight placed on the first block of an empty
     * board. Moving according to the rules of chess knight must visit each square
     * exactly once. Print the order of each the cell in which they are visited.
     */

    static int N = 8;

    // at every cell, knight has 8 possibilities
    static int[] xMoves = { 2, 1, -1, -2, -2, -1, 1, 2 };
    static int[] yMoves = { 1, 2, 2, 1, -1, -2, -2, -1 };

    static void knightTour() {

        int[][] soln = new int[N][N];

        for (int i = 0; i < soln.length; i++) {
            for (int j = 0; j < soln.length; j++) {
                soln[i][j] = -1;
            }
        }

        // starting from here

        int stepCount = 0;
        soln[0][0] = 0;

        if (canTour(0, 0, stepCount + 1, soln)) {
            printSolution(soln);
        } else {
            System.out.println("Tour not possible");
        }
    }

    private static void printSolution(int[][] soln) {
        for (int i = 0; i < soln.length; i++) {
            for (int j = 0; j < soln.length; j++) {
                System.out.print(soln[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean canTour(int curRow, int curCol, int stepCount, int[][] soln) {

        System.out.println("currently at " + curRow + " " + curCol + " steps= " + stepCount);
        // if knight has covered all cells
        if (stepCount == N * N)
            return true;

        // else traverse

        // knight has 8 possibilities
        for (int i = 0; i < 8; i++) {
            int newRow = curRow + xMoves[i];
            int newCol = curCol + yMoves[i];

            // if new movement is out of bounds
            if (newRow < 0 || newCol < 0 || newRow >= N || newCol >= N)
                continue;

            // if already visited
            if (soln[newRow][newCol] != -1)
                continue;

            // mark this as visited with the stepcount
            soln[newRow][newCol] = stepCount;

            // if this is valid and tour can be completed, return true
            if (canTour(newRow, newCol, stepCount + 1, soln))
                return true;
            // else backtrack by marking this unvisited
            else
                soln[newRow][newCol] = -1;
        }
        return false;
    }

    public static void main(String[] args) {
        KnightTour.knightTour();
    }
}