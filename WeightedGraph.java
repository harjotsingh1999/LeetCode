import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class WeightedGraph {
    int nVertices;
    List<List<Pair>> adjencyList;

    WeightedGraph(int n) {
        nVertices = n;
        adjencyList = new ArrayList<List<Pair>>(nVertices);
        for (int i = 0; i < nVertices; i++) {
            adjencyList.add(new ArrayList<Pair>());
        }
    }

    public void addEdge(int u, int v, int edgeWt) {
        adjencyList.get(u).add(new Pair(v, edgeWt));
        // since this is a directed graph
        // we cannot move reverse
        // adjencyList.get(v).add(new Pair(u, edgeWt));
    }

    // only for positive edge weights
    public void Dijkstra(WeightedGraph graph, int nNodes, int startNode) {
        boolean[] visited = new boolean[nNodes];
        Integer[] distances = new Integer[nNodes];

        // to store which node was seen before the current node
        // this is to generate the path of the shortest distance
        int[] prevNode = new int[nNodes];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(startNode, 0));
        distances[startNode] = 0;
        while (pq.size() != 0) {
            System.out.println("pqueue= " + pq.toString());

            // getting the next most promising node
            // i.e. node at minimum distance/ edge wt.

            Pair current = pq.poll();
            visited[current.nodeTo] = true;
            System.out.println("current node= " + current.toString());
            System.out.println("current node's edges= " + adjencyList.get(current.nodeTo));

            // skip the nodes for which we have already found a better path routing through
            // other nodes
            if (current.edgeWt > distances[current.nodeTo])
                continue;
            for (Pair pair : adjencyList.get(current.nodeTo)) {
                System.out.println("current edge= " + pair + " visited= " + visited[pair.nodeTo]);

                // helps detect if an edge points towards itself or an ancestor
                // basically a cycle
                // hence we skip the node

                // You cannot get a shorter path by revisiting
                // a node you have already visited before.
                if (visited[pair.nodeTo])
                    continue;

                // relaxation step
                int newDist = pair.edgeWt + distances[current.nodeTo];
                System.out.println("current distance= " + distances[pair.nodeTo] + " new distance= " + newDist);
                if (distances[pair.nodeTo] == null) {
                    distances[pair.nodeTo] = newDist;
                    prevNode[pair.nodeTo] = current.nodeTo;
                    pq.offer(new Pair(pair.nodeTo, newDist));
                } else if (newDist < distances[pair.nodeTo]) {
                    distances[pair.nodeTo] = newDist;
                    prevNode[pair.nodeTo] = current.nodeTo;
                    pq.offer(new Pair(pair.nodeTo, newDist));
                }
            }
            // System.out.println("distances array= " + Arrays.toString(distances));
            // try {
            // Thread.sleep(5000);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }
        System.out.println("min distances from " + startNode + " to each node= " + Arrays.toString(distances));
        System.out.println("min dstance parents= " + Arrays.toString(prevNode));
        printShortestPath(1, prevNode);
    }

    public void printShortestPath(int endNode, int[] parentNodes) {
        int current = endNode;
        ArrayList<Integer> parents = new ArrayList<>();
        while (current != 0) {
            parents.add(current);
            current = parentNodes[current];
        }
        parents.add(current);
        Collections.reverse(parents);
        System.out.println(parents);
    }

    public void bellmanFordAlgo(WeightedGraph graph, int startNode) {

        // Initialize the distance to all nodes to be infinity
        // except for the start node which is zero.
        double[] distances = new double[graph.nVertices];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[startNode] = 0;

        // for each vertex, apply relaxation to all edges

        // you can stop the loop early if there is no update in a loop
        // by keeping a "wasAnyDistanceUpdated"

        boolean wasAnyDistanceUpdated = true;
        for (int i = 0; i < graph.nVertices - 1; i++) {
            wasAnyDistanceUpdated = false;
            for (int j = 0; j < graph.nVertices; j++) {
                for (Pair edge : graph.adjencyList.get(j)) {
                    if (distances[j] + edge.edgeWt < distances[edge.nodeTo]) {
                        distances[edge.nodeTo] = distances[j] + edge.edgeWt;
                        wasAnyDistanceUpdated = true;

                    }
                }
            }
            if (!wasAnyDistanceUpdated)
                break;
        }

        // repeat the above entire code to check if there is a negative edge wt cycle
    }

    public void shortestPathUsingTopSort(WeightedGraph graph, int nNodes, int startNode) {
        int[] topOrdering = new TopologicalSort().topSortWeightedGraph(graph);
        Integer[] distances = new Integer[nNodes];
        distances[startNode] = 0;

        // traverse the nodes in topological order
        for (int i = 0; i < nNodes; i++) {
            int currentNode = topOrdering[i];
            System.out.println("i= " + i + " and current node= " + currentNode);
            if (distances[currentNode] != null) {

                // get all edges from the node
                List<Pair> edges = graph.adjencyList.get(currentNode);
                System.out.println("edges of node " + currentNode + " = " + edges);
                if (edges != null) {
                    for (Pair pair : edges) {
                        // relaxation step
                        System.out.println("current edge of " + currentNode + " is " + pair + " distances[currentNode] "
                                + distances[currentNode]);
                        int newDist = distances[currentNode] + pair.edgeWt;
                        System.out.println("new distance= " + newDist);
                        if (distances[pair.nodeTo] == null)
                            distances[pair.nodeTo] = newDist;
                        else
                            distances[pair.nodeTo] = Math.min(newDist, distances[pair.nodeTo]);
                    }
                }
            }
            System.out.println("current distances array= " + Arrays.toString(distances));
            // try {
            // Thread.sleep(5000);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }
        System.out.println("Min distances from start= " + startNode + " to each node= " + Arrays.toString(distances));
    }

    // USING DFS
    public boolean isCyclic(WeightedGraph graph) {
        boolean[] visited = new boolean[nVertices];
        boolean[] recStack = new boolean[nVertices];
        for (int i = 0; i < graph.nVertices; i++) {
            System.out.println("WeightedGraph.isCyclic() currently at " + i);
            if (isCyclicUtil(graph, visited, recStack, i))
                return true;
        }
        return false;
    }

    private boolean isCyclicUtil(WeightedGraph graph, boolean[] visited, boolean[] recStack, int node) {
        if (recStack[node])
            return true;
        if (visited[node])
            return false;
        visited[node] = true;
        recStack[node] = true;
        List<Pair> edges = graph.adjencyList.get(node);
        System.out.println("Edges of " + node + " are " + edges);
        for (Pair pair : edges) {
            if (isCyclicUtil(graph, visited, recStack, pair.nodeTo))
                return true;
        }
        recStack[node] = false;
        return false;
    }

    // CYCLIC using graph coloring
    // WHITE= -1 ->unprocessed
    // GRAY= 0 -> processing (in stack)
    // BLACK= 1 -> processed(including all children)

    public boolean isCyclic2(WeightedGraph graph) {
        int[] colors = new int[graph.nVertices];
        int[] parent = new int[graph.nVertices];

        // mark all nodes as unprocessed
        Arrays.fill(colors, -1);
        Arrays.fill(parent, -1);
        for (int i = 0; i < graph.nVertices; i++) {
            if (colors[i] == -1) {
                parent[i] = -1;
                if (isCyclicUtil2(graph, colors, i, parent)) {
                    System.out.println("Parent[]= " + Arrays.toString(parent));
                    return true;
                }
            }
        }
        System.out.println("Parent[]= " + Arrays.toString(parent));
        return false;
    }

    private void printCycle(int[] parent, int start, int end) {
        System.out.println("start= " + start + " end= " + end);
    }

    private boolean isCyclicUtil2(WeightedGraph graph, int[] colors, int node, int[] parent) {

        System.out.println("processing " + node + " colors= " + Arrays.toString(colors));
        // if incurred an already processing node
        if (colors[node] == 0)
            return true;

        // no need to check for node that is already processed completely
        if (colors[node] == 1)
            return false;

        // mark as processing
        colors[node] = 0;
        for (Pair edge : graph.adjencyList.get(node)) {
            parent[edge.nodeTo] = node;
            if (isCyclicUtil2(graph, colors, edge.nodeTo, parent)) {
                printCycle(parent, node, edge.nodeTo);
                return true;
            }
        }
        // mark as processed
        colors[node] = 1;
        return false;
    }

    public static void main(String[] args) {
        int nVertices = 5;
        WeightedGraph weightedGraph = new WeightedGraph(nVertices);
        // weightedGraph.addEdge(0, 1, 1);
        // weightedGraph.addEdge(2, 1, 1);
        // weightedGraph.addEdge(2, 3, 1);
        // weightedGraph.addEdge(3, 4, 1);
        // weightedGraph.addEdge(4, 2, 1);
        // weightedGraph.addEdge(4, 0, 1);
        weightedGraph.addEdge(0, 1, 4);
        weightedGraph.addEdge(0, 2, 1);
        weightedGraph.addEdge(2, 1, 2);
        weightedGraph.addEdge(1, 3, 1);
        weightedGraph.addEdge(2, 3, 5);
        weightedGraph.addEdge(3, 4, 3);
        weightedGraph.Dijkstra(weightedGraph, nVertices, 0);
        // System.out.println(weightedGraph.isCyclic2(weightedGraph));
    }
}

class Pair implements Comparator<Pair>, Comparable<Pair> {
    int nodeTo, edgeWt;

    Pair(int nodeTo, int edgeWt) {
        this.nodeTo = nodeTo;
        this.edgeWt = edgeWt;
    }

    public int getEdgeWt() {
        return edgeWt;
    }

    public int getNodeTo() {
        return nodeTo;
    }

    @Override
    public int compare(Pair o1, Pair o2) {
        return (int) Math.signum(o1.edgeWt - o2.edgeWt);
    }

    @Override
    public int compareTo(Pair o) {
        return (int) Math.signum(this.edgeWt - o.edgeWt);
    }

    @Override
    public String toString() {
        return "to " + String.valueOf(nodeTo) + " with weight/distance= " + String.valueOf(edgeWt);
    }
}
