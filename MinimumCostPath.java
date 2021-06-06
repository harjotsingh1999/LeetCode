import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// Given a square grid of size N, each cell of which contains integer cost which represents a cost to traverse through that cell, we need to find a path from top left cell to bottom right cell by which the total cost incurred is minimum.
// From the cell (i,j) we can go (i,j-1), (i, j+1), (i-1, j), (i+1, j). 

// Note: It is assumed that negative cost cycles do not exist in the input matrix.

class MinimumCostPath {
    // Function to return the minimum cost to react at bottom
    // right cell from top left cell.

    // using DIJKSTRA'S algo
    public int minimumCostPath(int[][] grid) {

        double[][] distances = new double[grid.length][grid[0].length];
        for (int i = 0; i < distances.length; i++) {
            Arrays.fill(distances[i], Double.POSITIVE_INFINITY);
        }
        // Code here
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[] rr = { 0, 0, -1, 1 };
        int[] cc = { -1, 1, 0, 0 };
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        pq.offer(new Cell(0, 0, grid[0][0]));
        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            System.out.println("Currently " + curr.toString());
            visited[curr.x][curr.y] = true;
            for (int i = 0; i < 4; i++) {
                int newr = curr.x + rr[i];
                int newc = curr.y + cc[i];
                if (newr < 0 || newc < 0 || newr > grid.length - 1 || newc > grid[0].length - 1)
                    continue;
                if (visited[newr][newc])
                    continue;
                if (curr.weight + grid[newr][newc] < distances[newr][newc]) {
                    distances[newr][newc] = curr.weight + grid[newr][newc];
                    pq.offer(new Cell(newr, newc, curr.weight + grid[newr][newc]));
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return (int) distances[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        MinimumCostPath mPath = new MinimumCostPath();
        int[][] grid = { { 9, 4, 9, 9 }, { 6, 7, 6, 4 }, { 8, 3, 3, 7 }, { 7, 4, 9, 10 }, };
        mPath.minimumCostPath(grid);
    }
}

class Cell implements Comparator<Cell>, Comparable<Cell> {
    int x, y, weight;

    Cell(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.weight = w;
    }

    @Override
    public int compare(Cell o1, Cell o2) {
        return o1.weight - o2.weight;
    }

    @Override
    public String toString() {
        return "At(" + x + "," + y + ") ->" + weight;
    }

    @Override
    public int compareTo(Cell o) {
        return this.weight - o.weight;
    }
}