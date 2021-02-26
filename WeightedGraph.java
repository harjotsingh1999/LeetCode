import java.util.ArrayList;
import java.util.Arrays;
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
        adjencyList.get(v).add(new Pair(u, edgeWt));
    }

    // only for positive edge weights
    public void Dijkstra(WeightedGraph graph, int nNodes, int startNode) {
        boolean[] visited = new boolean[nNodes];
        int[] distances = new int[nNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0));
        distances[startNode] = 0;
        while (pq.size() != 0) {
            System.out.println("WeightedGraph.Dijkstra() pqueue= " + pq.toString());
            Pair current = pq.poll();
            visited[current.nodeTo] = true;

            System.out.println("WeightedGraph.Dijkstra() current node= " + current.toString());
            for (Pair pair : adjencyList.get(current.nodeTo)) {
                if (visited[pair.nodeTo])
                    continue;

                // relaxation step
                int newDist = pair.edgeWt
                        + ((distances[current.nodeTo] == Integer.MAX_VALUE) ? 0 : distances[current.nodeTo]);
                if (newDist < distances[pair.nodeTo]) {
                    distances[pair.nodeTo] = newDist;
                    pq.offer(new Pair(pair.nodeTo, newDist));
                }
            }

            System.out.println("WeightedGraph.Dijkstra() distances array= " + Arrays.toString(distances));
            // try {
            // Thread.sleep(5000);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }
        System.out.println("min distances from " + startNode + " to each node= " + Arrays.toString(distances));
    }

    public void shortestPathUsingTopSort(WeightedGraph graph, int nNodes, int startNode) {
        int[] topOrdering = new TopologicalSort().topSortWeightedGraph(graph);
        Integer[] distances = new Integer[nNodes];
        distances[startNode] = 0;
        for (int i = 0; i < nNodes; i++) {
            int nodeIndex = topOrdering[i];
            if (distances[nodeIndex] != null) {
                List<Pair> edges = graph.adjencyList.get(nodeIndex);
                if (edges != null) {
                    for (Pair pair : edges) {
                        int newDist = distances[nodeIndex] + pair.edgeWt;
                        if (distances[pair.nodeTo] == null)
                            distances[pair.edgeWt] = newDist;
                        else
                            distances[pair.nodeTo] = Math.min(newDist, distances[pair.nodeTo]);
                    }
                }
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
        weightedGraph.shortestPathUsingTopSort(weightedGraph, nVertices, 0);
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
        return String.valueOf(nodeTo) + " with weight/distance= " + String.valueOf(edgeWt);
    }
}
