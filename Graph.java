import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

    int nVertices;
    List<List<Integer>> adjencyMatrix;

    Graph(int n) {
        nVertices = n;
        adjencyMatrix = new ArrayList<List<Integer>>(nVertices);
        for (int i = 0; i < nVertices; i++) {
            adjencyMatrix.add(new ArrayList<Integer>());
        }
    }

    // adjency matrix is a list containing lists of all connected nodes of all nodes
    // of the graph
    public void addEdge(int u, int v) {
        adjencyMatrix.get(u).add(v);
        adjencyMatrix.get(v).add(u);
    }

    public void printGraph() {
        for (int i = 0; i < adjencyMatrix.size(); i++) {
            System.out.println("Adjency List of vertex " + i);
            for (int j = 0; j < adjencyMatrix.get(i).size(); j++) {
                System.out.print("-> " + adjencyMatrix.get(i).get(j));
            }
            System.out.println();
        }
    }

    // Given a directed graph. The task is to do Breadth First Traversal of this
    // graph starting from 0.

    // if graph started from 1 the visited array would be of size nVertices+1
    public void BFS(int start) {
        boolean[] visited = new boolean[nVertices];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        visited[start] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            for (int i = 0; i < adjencyMatrix.get(current).size(); i++) {
                int vertex = adjencyMatrix.get(current).get(i);
                if (visited[vertex] == false) {
                    visited[vertex] = true;
                    queue.add(vertex);
                }
            }
        }
    }

    public void DFS(int startingVertex) {
        boolean[] visited = new boolean[nVertices];
        DFSUtil(startingVertex, visited);
    }

    public void DFSUtil(int vertex, boolean[] visited) {

        // mark this vertex as visited
        visited[vertex] = true;

        // print it
        System.out.print(vertex + " ");

        // then for all its adjascent vertices
        // perform the same action if they are not already visited
        for (int v : adjencyMatrix.get(vertex)) {
            if (!visited[v])
                DFSUtil(v, visited);
        }
    }

    // number of connected components is the number of interconnected graphs
    // that are disconnected from the others
    public void countNumberOfConnectedComponents() {
        int count = 0;
        int[] connectedWith = new int[nVertices];
        boolean[] visited = new boolean[nVertices];
        for (int i = 0; i < nVertices; i++) {
            if (!visited[i]) {
                count += 1;
                DFSUtilForConnectedComponents(i, visited, count, connectedWith);
            }
        }
        System.out.println("No of connected components= " + count);
        System.out.println("connected with Array= " + Arrays.toString(connectedWith));
    }

    public void DFSUtilForConnectedComponents(int vertex, boolean[] visited, int count, int[] connectedWith) {

        // mark this vertex as visited
        visited[vertex] = true;

        // print it
        connectedWith[vertex] = count;

        // then for all its adjascent vertices
        // perform the same action if they are not already visited
        for (int v : adjencyMatrix.get(vertex)) {
            if (!visited[v])
                DFSUtilForConnectedComponents(v, visited, count, connectedWith);
        }
    }

    // 0 <= start, end < nVertices
    public void printShortestPath(int start, int end) {
        int[] parentOfNodes = BFSForPath(start);
        // the abpve array contains the parent vertex of each element
        // means parentOfNodes[i] will be the parent of i
        // from where it was reached
        reconstructPath(start, end, parentOfNodes);
    }

    private void reconstructPath(int start, int end, int[] parentOfNodes) {

        // starting from the end node
        // going to the parent of the node at each iteration
        // till we reach the end (or -1)
        List<Integer> verticesInPath = new ArrayList<>();
        for (int i = end; i != -1; i = parentOfNodes[i]) {
            verticesInPath.add(i);
        }
        // since we are adding from the end
        // we need to reverse
        Collections.reverse(verticesInPath);

        // check if "end" is actually reachable from "start"
        if (verticesInPath.get(0) == start) {
            System.out.println("Path from " + start + " to " + end);
            for (Integer integer : verticesInPath) {
                System.out.print(integer + "->");
            }
        } else {
            System.out.println(end + " is not reachable from " + start);
        }
    }

    public int[] BFSForPath(int start) {
        boolean[] visited = new boolean[nVertices];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        int[] parentOfNodes = new int[nVertices];
        Arrays.fill(parentOfNodes, -1);
        Queue<Integer> queue = new LinkedList<Integer>();
        visited[start] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            for (int i = 0; i < adjencyMatrix.get(current).size(); i++) {
                int vertex = adjencyMatrix.get(current).get(i);
                if (visited[vertex] == false) {
                    visited[vertex] = true;
                    queue.add(vertex);
                    parentOfNodes[vertex] = current;
                }
            }
        }
        System.out.println();
        return parentOfNodes;
    }

    public static void main(String[] args) {
        int nVertices = 18;
        Graph graph = new Graph(nVertices);
        graph.addEdge(0, 8);
        graph.addEdge(0, 13);
        graph.addEdge(0, 4);
        graph.addEdge(0, 14);
        graph.addEdge(8, 4);
        graph.addEdge(8, 14);
        graph.addEdge(13, 14);
        graph.addEdge(1, 5);
        graph.addEdge(5, 16);
        graph.addEdge(5, 17);
        graph.addEdge(3, 9);
        graph.addEdge(9, 15);
        graph.addEdge(9, 2);
        graph.addEdge(15, 2);
        graph.addEdge(10, 15);
        graph.addEdge(7, 6);
        graph.addEdge(6, 11);
        graph.addEdge(11, 7);
        // graph.printGraph();
        // graph.BFS(1);
        // System.out.println();
        // graph.DFS(1);
        // System.out.println();
        // graph.countNumberOfConnectedComponents();
        graph.printShortestPath(12, 4);
    }
}
