import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree {

    // works for undirected weighted graphs
    // almost same as dijkstra's
    public void PrimsAlgo(WeightedGraph graph, int source) {
        boolean[] visited = new boolean[graph.nVertices];
        double[] distances = new double[graph.nVertices];
        int[] parent = new int[graph.nVertices];
        Arrays.fill(parent, -1);
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(source, 0));
        visited[source] = true;
        distances[source] = 0;
        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            visited[current.nodeTo] = true;

            if (current.edgeWt > distances[current.nodeTo])
                continue;
            System.out.println("Current Node= " + current);
            for (Pair edge : graph.adjencyList.get(current.nodeTo)) {
                System.out.println("Current Node's edge= " + edge + " visited= " + visited[edge.nodeTo]);
                if (visited[edge.nodeTo])
                    continue;

                // only difference is here that we don't conside the distances[current.nodeTo]
                // only the weight of this edge, unlike dijkstra
                if (edge.edgeWt < distances[edge.nodeTo]) {
                    distances[edge.nodeTo] = edge.edgeWt;
                    parent[edge.nodeTo] = current.nodeTo;
                    pq.offer(new Pair(edge.nodeTo, edge.edgeWt));
                }
            }
        }
        System.out.println("distances[]= " + Arrays.toString(distances));
        System.out.println("parent[]= " + Arrays.toString(parent));
    }

    // again, works for only undirected graphs

    // 1. Sort all the edges in non-decreasing order of their weight.
    // 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree
    // formed so far. If cycle is not formed, include this edge. Else, discard it.
    // 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
    public void KruskalsAlgorithm(int nVertices, List<Edge> graph) {
        Collections.sort(graph, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        // to store the valid edges that will create the MST
        List<Edge> mst = new ArrayList<>();

        int[] parent = new int[nVertices];
        int[] rank = new int[nVertices];
        // each node is in its own component initially
        Arrays.fill(parent, -1);
        Arrays.fill(rank, 1);

        // if this graph contains V vertices, then MST will contain V-1 edges
        int edgesInMST = 0;
        int index = 0;
        while (edgesInMST != nVertices - 1 && index < graph.size()) {
            int parent1 = findAbsoluteParent(graph.get(index).from, parent);
            int parent2 = findAbsoluteParent(graph.get(index).to, parent);

            // if the nodes at both ends of this edge have the same absolute parents
            // means they are already connected, and this edge will create a cycle
            // hence ignore this edge
            if (parent1 == parent2) {
                index += 1;
                continue;
            }

            // this edge will not create a cycle, hence union
            else {
                union(parent1, parent2, rank, parent);
                mst.add(graph.get(index));
                index += 1;
                edgesInMST += 1;
            }
        }
        System.out.println(mst);
    }

    private int findAbsoluteParent(int node, int[] parent) {
        if (parent[node] == -1)
            return node;
        else {
            // path compression
            parent[node] = findAbsoluteParent(parent[node], parent);
            return parent[node];
        }

    }

    // union of roots of two trees
    // p, and q are abosulte roots
    private void union(int p, int q, int[] rank, int[] parent) {
        if (rank[p] > rank[q]) {
            parent[q] = p;
        } else if (rank[p] < rank[q]) {
            parent[p] = q;
        }
        // if both ranks of p and q are same
        else {
            // any one becomes the parent
            // and its rank increases by 1
            parent[q] = p;
            rank[p] += 1;
        }
    }

    public static void main(String[] args) {
        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree();
        // WeightedGraph graph = new WeightedGraph(5);
        // graph.addEdge(0, 1, 2);
        // graph.addEdge(1, 0, 2);
        // graph.addEdge(0, 2, 5);
        // graph.addEdge(2, 0, 5);
        // graph.addEdge(2, 1, 2);
        // graph.addEdge(1, 2, 2);
        // graph.addEdge(1, 3, 4);
        // graph.addEdge(3, 1, 4);
        // graph.addEdge(2, 3, 0);
        // graph.addEdge(3, 2, 0);
        // graph.addEdge(3, 4, 5);
        // graph.addEdge(4, 3, 5);
        // // graph.Dijkstra(graph, 5, 0);
        // minimumSpanningTree.PrimsAlgo(graph, 0);

        List<Edge> graph = new ArrayList<>();
        graph.add(new Edge(0, 1, 1));
        graph.add(new Edge(0, 2, 2));
        graph.add(new Edge(1, 2, 3));
        graph.add(new Edge(1, 3, 1));
        graph.add(new Edge(1, 4, 3));
        graph.add(new Edge(2, 3, 2));
        graph.add(new Edge(2, 4, 10));
        graph.add(new Edge(3, 4, 2));
        graph.add(new Edge(3, 5, 4));
        graph.add(new Edge(4, 5, 3));
        minimumSpanningTree.KruskalsAlgorithm(5, graph);
    }
}

class Edge implements Comparator<Edge> {
    int from, to, weight;

    @Override
    public int compare(Edge o1, Edge o2) {
        return o1.weight - o2.weight;
    }

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.from + "->" + this.to + " with " + this.weight;
    }
}
