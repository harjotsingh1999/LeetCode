import java.util.ArrayList;

public class ConnectedComputers {

    // There are n computers numbered from 0 to n-1 connected by ethernet cables
    // connections forming a network where connections[i] = [a, b] represents a
    // connection between computers a and b. Any computer can reach any other
    // computer directly or indirectly through the network.

    // Given an initial computer network connections. You can extract certain cables
    // between two directly connected computers, and place them between any pair of
    // disconnected computers to make them directly connected. Return the minimum
    // number of times you need to do this in order to make all the computers
    // connected. If it's not possible, return -1.

    // Input: n = 4, connections = [[0,1],[0,2],[1,2]]
    // Output: 1
    // Explanation: Remove cable between computer 1 and 2 and place between
    // computers 1 and 3.

    // Example 2:

    // Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
    // Output: 2

    // Example 3:

    // Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
    // Output: -1
    // Explanation: There are not enough cables.

    // Example 4:

    // Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
    // Output: 0

    // Constraints:

    // 1 <= n <= 10^5
    // 1 <= connections.length <= min(n*(n-1)/2, 10^5)
    // connections[i].length == 2
    // 0 <= connections[i][0], connections[i][1] < n
    // connections[i][0] != connections[i][1]
    // There are no repeated connections.
    // No two computers are connected by more than one cable.

    // Hints
    // As long as there are at least (n - 1) connections, there is definitely a way
    // to connect all computers.

    // Use DFS to determine the number of isolated computer clusters.

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1)
            return -1;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < connections.length; i++) {
            adj.get(connections[i][0]).add(connections[i][1]);
            adj.get(connections[i][1]).add(connections[i][0]);
        }
        // System.out.println(adj);
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count += 1;
                DFS(i, visited, adj);
                // System.out.println("i= "+i+" visited[]= "+Arrays.toString(visited));
            }
        }
        return count - 1;
    }

    public void DFS(int currentNode, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        visited[currentNode] = true;
        for (int node : adj.get(currentNode)) {
            if (!visited[node])
                DFS(node, visited, adj);
        }
    }
}
