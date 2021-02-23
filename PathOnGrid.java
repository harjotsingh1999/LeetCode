import java.util.LinkedList;
import java.util.Queue;

public class PathOnGrid {

    /*
     * Problem to find the shortest path on a 2D grid. You are trapped in a 2D
     * dungeon and need to find the quickest way out. The dungeon is composed of
     * unit cube which may or may not be filled ith rocks. It taked one minute to
     * move one unit North, South,East or West. You cannot move diagonally and the
     * maze is surrounded by solid rock on all sides Is an escape possible? If yes,
     * how long will it take?
     * 
     */

    // S= start, E= end, .= empty cell, #= rock
    // dungeon of size RxC
    // --------C--------
    // |S|.|.|#|.|.|.|
    // |.|#|.|.|.|#|.|
    // |.|#|.|.|.|.|.|
    // |.|.|#|#|.|.|.|
    // |#|.|#|E|.|#|.|

    int startR, startC, moveCount, nodesLeftInLayer, nodesInNextLayer, nRows, nColumns;
    boolean[][] visited;
    boolean reachedEnd;
    Queue<Integer> rQueue, cQueue;
    char[][] dungeon;

    PathOnGrid(char[][] dungeon, int startR, int startC) {
        this.dungeon = dungeon;
        this.nRows = dungeon.length;
        this.nColumns = dungeon[0].length;
        this.startR = startR;
        this.startC = startC;

        // to keep track of number of moves to reach end
        this.moveCount = 0;
        // tracks how many nodes we need to dequqeue before taking a step
        this.nodesLeftInLayer = 1;
        // tracks how many nodes we added in the BFS
        // so that we can updated nodesLeftInLayer accordingly in the next iteration
        this.nodesInNextLayer = 0;

        // queues to store the x and y coordinates as we do in BFS
        this.rQueue = new LinkedList<Integer>();
        this.cQueue = new LinkedList<Integer>();

        // to keep track if we have visited end cell marked with 'E'
        this.reachedEnd = false;

        // keep track of the visited nodes just as in BFS
        this.visited = new boolean[nRows][nColumns];

    }

    // we need the shortest path, not just any path
    // hence we will use the breadth first search BFS

    // for the two coordinates x and y
    // we will use two separate queues and use them simutaneously

    // assuming we have the coordinates of the start
    // otherwise we will have to find it as well
    public void findMinTime() {
        // solution starts here
        rQueue.offer(startR);
        cQueue.offer(startC);
        visited[startR][startC] = true;

        while (rQueue.size() > 0) // or yQueue.size()>0, both are of same size at any given point of time
        {
            int currentR = rQueue.poll();
            int currentC = cQueue.poll();

            // check if we reached the dungeon exit
            if (dungeon[currentR][currentC] == 'E') {
                reachedEnd = true;
                break;
            }

            // explore the neighbours of current cell
            exploreNeighbours(currentR, currentC);

            nodesLeftInLayer -= 1;

            // once this layer is complete
            // we can increment the moveCount and reset the nodesInNextLayer
            // so that it can be used again to track the next layer
            if (nodesLeftInLayer == 0) {
                nodesLeftInLayer = nodesInNextLayer;
                nodesInNextLayer = 0;
                moveCount += 1;
            }
        }
        if (reachedEnd)
            System.out.println("Minimum time= " + moveCount + " minutes");
        else
            System.out.println("You cannot escape this dungeon");
    }

    private void exploreNeighbours(int currentR, int currentC) {

        // direction row vectors for North, South, East and West respectively
        int[] directionRow = { -1, 1, 0, 0 };
        int[] directionCol = { 0, 0, 1, -1 };

        for (int i = 0; i < 4; i++) {

            // new coordinate
            int newR = currentR + directionRow[i];
            int newC = currentC + directionCol[i];

            // if we crossed the grid we should not add that coordinate to the queue
            if (newR < 0 || newC < 0 || newR > dungeon.length - 1 || newC > dungeon[0].length - 1)
                continue;

            // if we already visited that cell, or if the cell is a rock
            // we do not add it to the queue
            if (visited[newR][newC] || dungeon[newR][newC] == '#')
                continue;

            // add the current position to the queue
            rQueue.offer(newR);
            cQueue.offer(newC);
            // mark the position as visited
            visited[newR][newC] = true;
            // increment the count of nodes in this layer
            nodesInNextLayer += 1;
        }
    }

    public static void main(String[] args) {
        char[][] dungeon = { { 'S', '.', '.', '#', '.', '.', '.' }, { '.', '#', '.', '.', '.', '#', '.' },
                { '.', '#', '.', '.', '.', '.', '.' }, { '.', '.', '#', '#', '.', '.', '.' },
                { '#', '.', '#', 'E', '.', '#', '.' }, };
        PathOnGrid pathOnGrid = new PathOnGrid(dungeon, 0, 0);
        pathOnGrid.findMinTime();
    }
}
