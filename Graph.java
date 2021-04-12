import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    int nVertices;
    List<List<Integer>> adjencyList;

    Graph(int n) {
        nVertices = n;
        adjencyList = new ArrayList<List<Integer>>(nVertices);
        for (int i = 0; i < nVertices; i++) {
            adjencyList.add(new ArrayList<Integer>());
        }
    }

    // adjency matrix is a list containing lists of all connected nodes of all nodes
    // of the graph
    public void addEdge(int u, int v) {
        adjencyList.get(u).add(v);
        adjencyList.get(v).add(u);
    }

    public void printGraph() {
        for (int i = 0; i < adjencyList.size(); i++) {
            System.out.println("Adjency List of vertex " + i);
            for (int j = 0; j < adjencyList.get(i).size(); j++) {
                System.out.print("-> " + adjencyList.get(i).get(j));
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
            for (int i = 0; i < adjencyList.get(current).size(); i++) {
                int vertex = adjencyList.get(current).get(i);
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
        for (int v : adjencyList.get(vertex)) {
            if (!visited[v])
                DFSUtil(v, visited);
        }
    }

    public void DFSUsingStack(Graph graph, int startingVertex) {
        boolean[] visited = new boolean[graph.nVertices];
        Stack<Integer> stack = new Stack<>();
        // stack.push(startingVertex);
        // visited[startingVertex] = true;
        for (int i = 0; i < graph.nVertices; i++) {
            if (!visited[i]) {
                stack.push(i);
                visited[i] = true;
                while (!stack.empty()) {
                    int current = stack.pop();
                    System.out.print(current + " ");
                    // visited[current] = true;
                    for (int vertex : graph.adjencyList.get(current)) {
                        if (!visited[vertex]) {
                            // System.out.println("current= " + current + " Pushing to stack " + vertex);
                            stack.push(vertex);
                            visited[vertex] = true;
                        }
                    }
                }
                System.out.println();
            }
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
        for (int v : adjencyList.get(vertex)) {
            if (!visited[v])
                DFSUtilForConnectedComponents(v, visited, count, connectedWith);
        }
    }

    // 0 <= start, end < nVertices
    public void printShortestPath(int start, int end) {
        int[] parentOfNodes = BFSForPath(start);
        // the above array contains the parent vertex of each element
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

        // finding the parent of each node via BFS
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
            for (int i = 0; i < adjencyList.get(current).size(); i++) {
                int vertex = adjencyList.get(current).get(i);
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

    // COLORS
    // -1 -> unvisited
    // 0 -> visited and in queue
    // 2 -> removed from queue
    // if at any point you find the neighbour of any node to be 0, meaning neighbour
    // is already in the queue, means cycle is present
    // because there was an alternate path from where the neighbour was added to the
    // queue, otherwise it would have been -1
    public boolean isCyclicUndirected(Graph graph) {
        Queue<Integer> queue = new LinkedList<>();
        int[] colors = new int[graph.nVertices];
        Arrays.fill(colors, -1);
        for (int i = 0; i < graph.nVertices; i++) {
            if (colors[i] == -1) {
                queue.offer(i);
                colors[i] = 0;
                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    colors[current] = 1;
                    for (int node : graph.adjencyList.get(current)) {
                        if (colors[node] == 0)
                            return true;
                        else if (colors[node] == -1) {
                            queue.offer(node);
                            colors[node] = 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find
    // all possible paths from node 0 to node n - 1, and return them in any order.

    // The graph is given as follows: graph[i] is a list of all nodes you can visit
    // from node i (i.e., there is a directed edge from node i to node graph[i][j]).

    // Example 1:

    // Input: graph = [[1,2],[3],[3],[]]
    // Output: [[0,1,3],[0,2,3]]
    // Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

    // Runtime: 2 ms, faster than 93.66% of Java online submissions for All Paths
    // From Source to Target.
    // Memory Usage: 40.9 MB, less than 28.94% of Java online submissions for All
    // Paths From Source to Target.
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        // to store all possible paths
        List<List<Integer>> out = new ArrayList<>();

        // to store current ongoing path
        List<Integer> path = new ArrayList<>();
        path.add(0);
        traverse(0, graph, path, out);
        return out;
    }

    public void traverse(int current, int[][] graph, List<Integer> currentPath, List<List<Integer>> out) {

        // as soon as we reach the destination, add current paths to valid paths and
        // return

        System.out.println("currently at " + current + " currentPath= " + currentPath + " all paths yet= " + out);
        if (current == graph.length - 1) {
            out.add(currentPath);
            return;
        }

        // for all adjascent nodes of this node
        for (int node : graph[current]) {

            // create a new list
            List<Integer> curList = new ArrayList<>();

            // copy over the current ongoing path
            curList.addAll(currentPath);
            // add the current node to the path
            curList.add(node);
            // and traverse with this path
            traverse(node, graph, curList, out);
        }
    }

    // above problem using BFS

    // apparently slower than above
    public List<List<Integer>> printAllPaths(int[][] graph) {
        Queue<List<Integer>> queue = new LinkedList<>();
        List<List<Integer>> out = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        queue.add(path);
        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int lastNodeIncurrentPath = currentPath.get(currentPath.size() - 1);

            System.out.println("currently at " + lastNodeIncurrentPath + " currentPath= " + currentPath
                    + " all validPaths= " + out);
            // if destination reached
            if (lastNodeIncurrentPath == graph.length - 1) {
                out.add(currentPath);
                continue;
            }
            // for all connected nodes
            for (int node : graph[lastNodeIncurrentPath]) {
                List<Integer> newPath = new ArrayList<>();
                newPath.addAll(currentPath);
                newPath.add(node);
                queue.offer(newPath);
            }
        }
        return out;
    }

    public static void main(String[] args) {
        int nVertices = 4;
        Graph sgraph = new Graph(nVertices);
        // graph.addEdge(0, 8);
        // graph.addEdge(0, 13);
        // graph.addEdge(0, 4);
        // graph.addEdge(0, 14);
        // graph.addEdge(8, 4);
        // graph.addEdge(8, 14);
        // graph.addEdge(13, 14);
        // graph.addEdge(1, 5);
        // graph.addEdge(5, 16);
        // graph.addEdge(5, 17);
        // graph.addEdge(3, 9);
        // graph.addEdge(9, 15);
        // graph.addEdge(9, 2);
        // graph.addEdge(15, 2);
        // graph.addEdge(10, 15);
        // graph.addEdge(7, 6);
        // graph.addEdge(6, 11);
        // graph.addEdge(11, 7);

        // graph.addEdge(8, 1);
        // graph.addEdge(10, 13);
        // graph.addEdge(7, 12);
        // graph.addEdge(17, 12);
        // graph.printGraph();
        // graph.BFS(1);
        // System.out.println();
        // graph.DFS(1);
        // System.out.println();
        // graph.countNumberOfConnectedComponents();
        // graph.printShortestPath(12, 4);

        // graph.addEdge(0, 1);
        // graph.addEdge(1, 2);
        // graph.addEdge(2, 3);
        // graph.addEdge(3, 1);
        // graph.addEdge(3, 4);
        // graph.addEdge(4, 1);
        // graph.printGraph();
        // graph.DFS(0);
        // graph.countNumberOfConnectedComponents();
        // System.out.println(graph.isCyclicUndirected(graph));
        // int[][] graph = { { 1, 2 }, { 3 }, { 3 }, {} };

        int[][] graph = { { 4, 3, 1 }, { 3, 2, 4 }, { 3 }, { 4 }, {} };
        System.out.println(sgraph.printAllPaths(graph));
    }
}
