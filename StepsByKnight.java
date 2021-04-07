import java.util.LinkedList;
import java.util.Queue;

public class StepsByKnight {

    // Given a square chessboard, the initial position of Knight and position of a
    // target. Find out the minimum steps a Knight will take to reach the target
    // position.

    // Almost same as [@PathOnGrid.java]
    public int minStepToReachTarget(int KnightPos[], int TargetPos[], int N) {
        // Code here

        // USING BFS

        // to keep track of whether or not this node has been visited
        boolean[][] visited = new boolean[N + 1][N + 1];

        // to store the x coordinate of the nodes
        Queue<Integer> xQueue = new LinkedList<>();

        // to store the y coordinate of the nodes
        Queue<Integer> yQueue = new LinkedList<>();

        // no of steps that have been taken
        int stepCount = 0;

        // to keep track of the number of nodes in the current layer
        // initially 1, since we add the starting vertex to the queue
        int nodesLeftInLayer = 1;

        // to keep track of the number of nodes in the next layer
        int nodesInNextLayer = 0;

        // offer the starting node coordinates to the respective queues
        xQueue.offer(KnightPos[0]);
        yQueue.offer(KnightPos[1]);

        // mark the start as visited
        visited[KnightPos[0]][KnightPos[1]] = true;

        // until the queue is empty
        while (!xQueue.isEmpty()) {

            // get the current position
            int currentX = xQueue.poll();
            int currentY = yQueue.poll();

            System.out.println("Currently at " + currentX + " " + currentY + " nodesLeftInLayer= " + nodesLeftInLayer
                    + " moves= " + stepCount);

            // if end is reached at current position, simply return the number of steps that
            // were taken until now
            if (currentX == TargetPos[0] && currentY == TargetPos[1]) {
                return stepCount;
            }

            // count the number of valid neighbours of the current node and add them to the
            // queue
            nodesInNextLayer += takeNextStep(currentX, currentY, visited, xQueue, yQueue, N);

            // the current node's neighbours have been explored, hence nodes Left in current
            // layer is decremented by 1
            nodesLeftInLayer -= 1;

            // if all nodes in the current layer have been visited
            // time to take the next step
            // and reset the value of nodesInNextLayer
            if (nodesLeftInLayer == 0) {
                nodesLeftInLayer = nodesInNextLayer;
                nodesInNextLayer = 0;
                stepCount += 1;
            }
        }

        // will not be reached anyways...(if the start and end are valid)
        return stepCount;
    }

    private int takeNextStep(int currentX, int currentY, boolean[][] visited, Queue<Integer> xQueue,
            Queue<Integer> yQueue, int n) {

        // there are 8 possible moves for the knight on the chessboard
        int[] xMoves = { -1, -1, 1, 1, -2, -2, 2, 2 };
        int[] yMoves = { -2, 2, -2, 2, 1, -1, 1, -1 };

        // keep track of the number of valid neighbours that are to be checked
        int currentLayerNodes = 0;
        for (int i = 0; i < 8; i++) {

            // neighbour coordinated
            int newX = currentX + xMoves[i];
            int newY = currentY + yMoves[i];

            // if neighbour out of bounds, ignore it
            if (newX <= 0 || newX > n || newY <= 0 || newY > n)
                continue;

            // if neighbour already visited, ignore it
            if (visited[newX][newY])
                continue;

            // otherwise add it to the queue, and mark it visited
            System.out.println("Apt neighbour of " + currentX + "," + currentY + " is " + newX + "," + newY);
            xQueue.offer(newX);
            yQueue.offer(newY);
            visited[newX][newY] = true;
            currentLayerNodes += 1;
        }
        // return the number of valid neighbours so that it can be added to the
        // @nodesInNextLayer variable
        return currentLayerNodes;
    }

    // Almost same as [@PathOnGrid.java]
    public int minStepToReachTarget2(int KnightPos[], int TargetPos[], int N) {
        // Code here

        // USING BFS

        // to keep track of whether or not this node has been visited
        boolean[][] visited = new boolean[N + 1][N + 1];

        // to store the x coordinate of the nodes
        Queue<Integer> xQueue = new LinkedList<>();

        // to store the y coordinate of the nodes
        Queue<Integer> yQueue = new LinkedList<>();

        // no of steps that have been taken
        int stepCount = 0;

        // offer the starting node coordinates to the respective queues
        xQueue.offer(KnightPos[0]);
        yQueue.offer(KnightPos[1]);

        // add delimiters, like in rotten oranges
        xQueue.offer(-1);
        yQueue.offer(-1);

        // mark the start as visited
        visited[KnightPos[0]][KnightPos[1]] = true;

        // until the queue is empty
        while (!xQueue.isEmpty()) {

            // get the current position
            int currentX = xQueue.poll();
            int currentY = yQueue.poll();

            System.out.println("Currently at " + currentX + " " + currentY + " moves= " + stepCount);

            // if end is reached at current position, simply return the number of steps that
            // were taken until now
            if (currentX == TargetPos[0] && currentY == TargetPos[1]) {
                return stepCount;
            }

            // if we incur a delimiter, add a delimiter and increment moveCount
            else if (currentX == -1 && currentY == -1 && !xQueue.isEmpty()) {
                stepCount += 1;
                xQueue.offer(-1);
                yQueue.offer(-1);
            }

            // count the number of valid neighbours of the current node and add them to the
            // queue
            takeNextStep2(currentX, currentY, visited, xQueue, yQueue, N);
        }

        // will not be reached anyways...(if the start and end are valid)
        return stepCount;
    }

    private void takeNextStep2(int currentX, int currentY, boolean[][] visited, Queue<Integer> xQueue,
            Queue<Integer> yQueue, int n) {

        // there are 8 possible moves for the knight on the chessboard
        int[] xMoves = { -1, -1, 1, 1, -2, -2, 2, 2 };
        int[] yMoves = { -2, 2, -2, 2, 1, -1, 1, -1 };

        for (int i = 0; i < 8; i++) {

            // neighbour coordinated
            int newX = currentX + xMoves[i];
            int newY = currentY + yMoves[i];

            // if neighbour out of bounds, ignore it
            if (newX <= 0 || newX > n || newY <= 0 || newY > n)
                continue;

            // if neighbour already visited, ignore it
            if (visited[newX][newY])
                continue;

            // otherwise add it to the queue, and mark it visited
            System.out.println("Apt neighbour of " + currentX + "," + currentY + " is " + newX + "," + newY);
            xQueue.offer(newX);
            yQueue.offer(newY);
            visited[newX][newY] = true;
        }
    }

    public static void main(String[] args) {
        StepsByKnight stepsByKnight = new StepsByKnight();
        System.out.println(stepsByKnight.minStepToReachTarget2(new int[] { 3, 2 }, new int[] { 1, 1 }, 6));
    }
}
