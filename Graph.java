import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    // adjency matrix is a list containing lists of all connected nodes of all nodes
    // of the graph
    public static void addEdge(ArrayList<ArrayList<Integer>> adjMatrix, int u, int v) {
        adjMatrix.get(u).add(v);
        adjMatrix.get(v).add(u);
    }

    public static void printGraph(ArrayList<ArrayList<Integer>> adjMatrix) {
        for (int i = 0; i < adjMatrix.size(); i++) {
            System.out.println("Adjency List of vertex " + i);
            for (int j = 0; j < adjMatrix.get(i).size(); j++) {
                System.out.print("-> " + adjMatrix.get(i).get(j));
            }
            System.out.println();
        }
    }

    // Given a directed graph. The task is to do Breadth First Traversal of this
    // graph starting from 0.

    // if graph started from 1 the visited array would be of size nVertices+1
    public static void BFS(ArrayList<ArrayList<Integer>> adjMatrix, int nVertices) {
        boolean[] visited = new boolean[nVertices];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        visited[0] = true;
        queue.add(0);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            for (int i = 0; i < adjMatrix.get(current).size(); i++) {
                int vertex = adjMatrix.get(current).get(i);
                if (visited[vertex] == false) {
                    visited[vertex] = true;
                    queue.add(vertex);
                }
            }
        }
    }

    public static void main(String[] args) {
        int nVertices = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < nVertices; i++) {
            adj.add(new ArrayList<Integer>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 0, 3);
        addEdge(adj, 2, 4);
        printGraph(adj);
        BFS(adj, nVertices);
    }
}
