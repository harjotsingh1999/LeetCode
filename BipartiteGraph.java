import java.util.ArrayList;
import java.util.Arrays;

public class BipartiteGraph {

    // There is an undirected graph with n nodes, where each node is numbered
    // between 0 and n - 1. You are given a 2D array graph, where graph[u] is an
    // array of nodes that node u is adjacent to. More formally, for each v in
    // graph[u], there is an undirected edge between node u and node v. The graph
    // has the following properties:

    // There are no self-edges (graph[u] does not contain u).
    // There are no parallel edges (graph[u] does not contain duplicate values).
    // If v is in graph[u], then u is in graph[v] (the graph is undirected).
    // The graph may not be connected, meaning there may be two nodes u and v such
    // that there is no path between them.

    // A graph is bipartite if the nodes can be partitioned into two independent
    // sets A and B such that every edge in the graph connects a node in set A and a
    // node in set B.

    // Return true if and only if it is bipartite.

    // Example 1:

    // Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
    // Output: false
    // Explanation: There is no way to partition the nodes into two independent sets
    // such that every edge connects a node in one and a node in the other.

    // Example 2:

    // Input: graph = [[1,3],[0,2],[1,3],[0,2]]
    // Output: true
    // Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.

    // Constraints:

    // graph.length == n
    // 1 <= n <= 100
    // 0 <= graph[u].length < n
    // 0 <= graph[u][i] <= n - 1
    // graph[u] does not contain u.
    // All the values of graph[u] are unique.
    // If graph[u] contains v, then graph[v] contains u.

    public boolean isBipartite(int[][] graph) {

        // can do without making adj list as well
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            adj.add(i, new ArrayList<>());
            for (int j = 0; j < graph[i].length; j++) {
                adj.get(i).add(graph[i][j]);
            }
        }
        System.out.println(adj);

        int[] colors = new int[graph.length];
        Arrays.fill(colors, -1);
        colors[0] = 1;
        for (int i = 0; i < colors.length; i++) {
            for (int node : adj.get(i)) {
                if (colors[node] == -1)
                    colors[node] = 1 - colors[i];
                else if (colors[node] == colors[i])
                    return false;
            }
            System.out.println("colors after processing node= " + i + " are= " + Arrays.toString(colors));
        }
        return true;
    }

    public static void main(String[] args) {
        BipartiteGraph bipartiteGraph = new BipartiteGraph();
        int[][] graph = { { 1, 2, 3 }, { 0, 2 }, { 0, 1, 3 }, { 0, 2 } };
        // int[][] graph = { { 1, 3 }, { 0, 2 }, { 1, 3 }, { 0, 2 } };
        System.out.println(bipartiteGraph.isBipartite(graph));
    }
}
