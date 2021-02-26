import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A topological ordering is an ordering of the nodes in a directed graph where
 * for each directed edge from node A to node B, node A appears before nodeB
 */
public class TopologicalSort {

    /*
     * Pick any random unvisited node. From that node do a DFS exploring only
     * unvisited nodes. On the recursive callback of DFS, add the current node to
     * the topological ordering in reverse order
     */

    // top sort is used to find shortest and longest paths in
    // DAG(directed acyclic graphs)
    // topological orderings are not unique, there may be several valid answers
    public void topSort(Graph graph) {
        int nVertices = graph.nVertices;
        boolean[] visited = new boolean[nVertices];

        // result of the topological ordering
        int[] topOrdering = new int[nVertices];
        // tracks the insertion position of an element in the topOrdering array
        // since we insert elements backwards, i starts at n-1
        int i = nVertices - 1;

        for (int element = 0; element < nVertices; element++) {
            if (visited[element] == false) {
                List<Integer> visitedNodes = new ArrayList<>();
                DFS(element, visited, visitedNodes, graph);
                System.out.println(
                        "TopologicalSort.topSort() visited nodes for " + element + " were " + visitedNodes.toString());
                for (Integer integer : visitedNodes) {
                    topOrdering[i] = integer;
                    i -= 1;
                }
            }
        }
        System.out.println("Topological order= " + Arrays.toString(topOrdering));
    }

    private void DFS(int element, boolean[] visited, List<Integer> visitedNodes, Graph graph) {
        visited[element] = true;
        for (Integer integer : graph.adjencyList.get(element)) {
            if (visited[integer] == false) {
                DFS(integer, visited, visitedNodes, graph);
            }
        }
        // we add the current element at last after the DFS is complete
        // i.e., we backtrack when no other way is possible now
        visitedNodes.add(element);
    }

    public int[] topSortWeightedGraph(WeightedGraph graph) {
        int nVertices = graph.nVertices;
        boolean[] visited = new boolean[nVertices];

        // result of the topological ordering
        int[] topOrdering = new int[nVertices];
        // tracks the insertion position of an element in the topOrdering array
        // since we insert elements backwards, i starts at n-1
        int i = nVertices - 1;

        for (int element = 0; element < nVertices; element++) {
            System.out.println("TopologicalSort.topSortWeightedGraph() current element= " + element
                    + " which is visited= " + visited[element]);
            if (visited[element] == false) {
                List<Integer> visitedNodes = new ArrayList<>();
                DFSForWeightedGraph(element, visited, visitedNodes, graph);
                System.out.println(
                        "TopologicalSort.topSort() visited nodes for " + element + " were " + visitedNodes.toString());
                for (Integer integer : visitedNodes) {
                    topOrdering[i] = integer;
                    i -= 1;
                }
            }
        }
        System.out.println("Topological order= " + Arrays.toString(topOrdering));
        return topOrdering;
    }

    private void DFSForWeightedGraph(int element, boolean[] visited, List<Integer> visitedNodes, WeightedGraph graph) {
        visited[element] = true;
        for (Pair pair : graph.adjencyList.get(element)) {
            System.out.println("TopologicalSort.DFSForWeightedGraph() going to " + pair.nodeTo + " from " + element
                    + " visited= " + visited[pair.nodeTo]);
            if (visited[pair.nodeTo] == false) {
                DFSForWeightedGraph(pair.nodeTo, visited, visitedNodes, graph);
            }
        }
        // we add the current element at last after the DFS is complete
        // i.e., we backtrack when no other way is possible now
        System.out.println("TopologicalSort.DFSForWeightedGraph() adding " + element + " to the list");
        visitedNodes.add(element);
    }

    public static void main(String[] args) {
        TopologicalSort topologicalSort = new TopologicalSort();
        // int nVertices = 18;
        // Graph graph = new Graph(nVertices);
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
        // // graph.addEdge(8, 1);
        // // graph.addEdge(10, 13);
        // // graph.addEdge(7, 12);
        // // graph.addEdge(17, 12);
        // graph.printGraph();
        // graph.BFS(1);
        // System.out.println();
        // graph.DFS(1);
        // System.out.println();
        // graph.countNumberOfConnectedComponents();
        // graph.printShortestPath(12, 4);
        // topologicalSort.topSort(graph);

        int nVertices = 5;
        WeightedGraph weightedGraph = new WeightedGraph(nVertices);
        weightedGraph.addEdge(0, 1, 4);
        weightedGraph.addEdge(0, 2, 1);
        weightedGraph.addEdge(2, 1, 2);
        weightedGraph.addEdge(1, 3, 1);
        weightedGraph.addEdge(2, 3, 5);
        weightedGraph.addEdge(3, 4, 3);
        topologicalSort.topSortWeightedGraph(weightedGraph);
    }
}
