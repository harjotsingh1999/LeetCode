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
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Min distances from start= " + startNode + " to each node= " + Arrays.toString(distances));
    }

    public static void main(String[] args) {
        int nVertices = 5;
        WeightedGraph weightedGraph = new WeightedGraph(nVertices);
        weightedGraph.addEdge(0, 1, 4);
        weightedGraph.addEdge(0, 2, 1);
        weightedGraph.addEdge(2, 1, 2);
        weightedGraph.addEdge(1, 3, 1);
        weightedGraph.addEdge(2, 3, 5);
        weightedGraph.addEdge(3, 4, 3);
        weightedGraph.Dijkstra(weightedGraph, nVertices, 0);
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
