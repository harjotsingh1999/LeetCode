import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NeighbouringCities {

    // There are n cities numbered from 0 to n-1. Given the array edges where
    // edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge
    // between cities fromi and toi, and given the integer distanceThreshold.

    // Return the city with the smallest number of cities that are reachable through
    // some path and whose distance is at most distanceThreshold, If there are
    // multiple such cities, return the city with the greatest number.

    // Notice that the distance of a path connecting cities i and j is equal to the
    // sum of the edges' weights along that path.

    // Example 1:

    // Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold =
    // 4
    // Output: 3
    // Explanation: The figure above describes the graph.
    // The neighboring cities at a distanceThreshold = 4 for each city are:
    // City 0 -> [City 1, City 2]
    // City 1 -> [City 0, City 2, City 3]
    // City 2 -> [City 0, City 1, City 3]
    // City 3 -> [City 1, City 2]
    // Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we
    // have to return city 3 since it has the greatest number.

    // Example 2:

    // Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]],
    // distanceThreshold = 2
    // Output: 0
    // Explanation: The figure above describes the graph.
    // The neighboring cities at a distanceThreshold = 2 for each city are:
    // City 0 -> [City 1]
    // City 1 -> [City 0, City 4]
    // City 2 -> [City 3, City 4]
    // City 3 -> [City 2, City 4]
    // City 4 -> [City 1, City 2, City 3]
    // The city 0 has 1 neighboring city at a distanceThreshold = 2.

    // Constraints:

    // 2 <= n <= 100
    // 1 <= edges.length <= n * (n - 1) / 2
    // edges[i].length == 3
    // 0 <= fromi < toi < n
    // 1 <= weighti, distanceThreshold <= 10^4
    // All pairs (fromi, toi) are distinct.

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<List<int[]>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(0, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new int[] { edge[1], edge[2] });
            adj.get(edge[1]).add(new int[] { edge[0], edge[2] });
        }

        // print adj
        // for (List<int[]> list : adj) {
        // for (int[] arr : list)
        // System.out.print(Arrays.toString(arr));
        // System.out.println();
        // }

        int[] citiesUT = new int[n];
        for (int i = 0; i < n; i++) {
            // visited[i]=true;
            System.out.println("checking for " + i);
            citiesUT[i] = countValidCities(i, adj, distanceThreshold, n);
        }
        System.out.println(Arrays.toString(citiesUT));
        return 0;
    }

    // using Dijkstra algo

    // find min distance to all cities
    // then find the cities with distance under the threshold and return the count
    private int countValidCities(int start, List<List<int[]>> adj, int distanceThreshold, int n) {
        boolean[] visited = new boolean[n];
        double[] distances = new double[n];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(NeighbouringCities.Pair o1, NeighbouringCities.Pair o2) {
                return (int) Math.signum(o1.weight - o2.weight);
            }
        });
        pq.offer(new Pair(start, 0));
        distances[start] = 0;
        while (!pq.isEmpty()) {
            Pair currentEdge = pq.poll();
            visited[currentEdge.nodeTo] = true;
            if (distances[currentEdge.nodeTo] < currentEdge.weight)
                continue;
            for (int[] edge : adj.get(currentEdge.nodeTo)) {
                if (!visited[edge[0]] && currentEdge.weight + edge[1] < distances[edge[0]]) {
                    distances[edge[0]] = currentEdge.weight + edge[1];
                    pq.add(new Pair(edge[0], distances[edge[0]]));
                }
            }
            System.out.println("currently at " + currentEdge + " pq= " + pq);
        }
        int count = 0;
        for (int i = 0; i < distances.length; i++) {
            if (i != start && distances[i] <= distanceThreshold)
                count += 1;
        }
        return count;
    }

    class Pair {
        int nodeTo;
        double weight;

        Pair(int nodeTo, double weight) {
            this.nodeTo = nodeTo;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "to " + nodeTo + " with " + weight;
        }
    }

    public static void main(String[] args) {
        NeighbouringCities neighbouringCities = new NeighbouringCities();
        // int[][] edges = { { 0, 1, 3 }, { 1, 2, 1 }, { 1, 3, 4 }, { 2, 3, 1 } };
        // neighbouringCities.findTheCity(4, edges, 4);
        // int[][] edges = { { 0, 1, 2 }, { 0, 4, 8 }, { 1, 2, 3 }, { 1, 4, 2 }, { 2, 3,
        // 1 }, { 3, 4, 1 } };
        // neighbouringCities.findTheCity(5, edges, 2);
        int[][] edges = { { 0, 1, 10 }, { 0, 2, 1 }, { 2, 3, 1 }, { 1, 3, 1 }, { 1, 4, 1 }, { 4, 5, 10 } };
        neighbouringCities.findTheCity(6, edges, 20);

    }
}
