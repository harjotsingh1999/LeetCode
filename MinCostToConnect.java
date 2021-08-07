import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class MinCostToConnect {

    /**
     * You are given an array points representing integer coordinates of some points
     * on a 2D-plane, where points{i] = [xi, yi].
     * 
     * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan
     * distance between them: |xi - xj| + |yi - yj|, where |val| denotes the
     * absolute value of val.
     * 
     * Return the minimum cost to make all points connected. All points are
     * connected if there is exactly one simple path between any two points.
     * 
     * 
     * 
     * Example 1:
     * 
     * 
     * 
     * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]] Output: 20 Explanation:
     * 
     * We can connect the points as shown above to get the minimum cost of 20.
     * Notice that there is a unique path between every pair of points. Example 2:
     * 
     * Input: points = [[3,12],[-2,5],[-4,1]] Output: 18 Example 3:
     * 
     * Input: points = [[0,0],[1,1],[1,0],[-1,1]] Output: 4 Example 4:
     * 
     * Input: points = [[-1000000,-1000000],[1000000,1000000]] Output: 4000000
     * Example 5:
     * 
     * Input: points = [[0,0]] Output: 0
     * 
     * 
     * Constraints:
     * 
     * 1 <= points.length <= 1000 -106 <= xi, yi <= 106 All pairs (xi, yi) are
     * distinct.
     */

    public int minCostConnectPoints(int[][] points) {
        HashMap<String, ArrayList<Edge>> adj = new HashMap<>();
        HashMap<String, Double> distance = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            String p1 = String.valueOf(points[i][0]) + "," + String.valueOf(points[i][1]);
            distance.put(p1, Double.POSITIVE_INFINITY);
            if (!adj.containsKey(p1))
                adj.put(p1, new ArrayList<Edge>());
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    String p2 = String.valueOf(points[j][0]) + "," + String.valueOf(points[j][1]);
                    double weight = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                    adj.get(p1).add(new Edge(p1, p2, weight));
                }
            }
        }
        System.out.println(adj);

        // to store visited points
        HashSet<String> visited = new HashSet<>();

        // for lazy prims implementation
        // to get the best possible edge (with lowest cost)
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> (int) a.weight - (int) b.weight);

        // start with any node(here first is taken)
        String start = String.valueOf(points[0][0]) + "," + String.valueOf(points[0][1]);

        // set distance of first node as 0
        distance.put(start, 0.0);
        pq.offer(new Edge("", start, 0.0));

        // to keep track of total cost
        int minCost = 0;
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (visited.contains(curr.p2))
                continue;
            visited.add(curr.p2);
            minCost += (int) curr.weight;
            System.out.println("current= " + curr);
            for (Edge edge : adj.get(curr.p2)) {
                System.out.println("neighbor of curr= " + edge);
                if (!visited.contains(edge.p2) && distance.get(edge.p2) > edge.weight) {
                    distance.put(edge.p2, edge.weight);
                    pq.offer(edge);
                    System.out.println("adding this to the p queue");
                }
            }
        }
        System.out.println(minCost);
        return 0;
    }

    class Edge {
        String p1, p2;
        double weight;

        public Edge(String p1, String p2, double weight) {
            this.p1 = p1;
            this.p2 = p2;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return p1 + "->" + p2 + " wt= " + weight;
        }
    }

    public int minCostConnectPoints2(int[][] points) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            String p1 = String.valueOf(points[i][0]) + "," + String.valueOf(points[i][1]);
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    String p2 = String.valueOf(points[j][0]) + "," + String.valueOf(points[j][1]);
                    double weight = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                    edges.add(new Edge(p1, p2, weight));
                }
            }
        }
        HashMap<String, String> parents = new HashMap<>();
        ArrayList<Edge> mst = new ArrayList<>();
        edges.sort((a, b) -> (int) a.weight - (int) b.weight);
        int index = 0, mincost = 0;
        while (mst.size() < points.length - 1 && index < edges.size()) {
            Edge curr = edges.get(index);
            String p1 = getParent(curr.p1, parents);
            String p2 = getParent(curr.p2, parents);
            System.out.println("curr= " + curr + " p1=" + p1 + " p2=" + p2);
            if (p1.equals(p2)) {
                index += 1;
                System.out.println("skipping parents same");
                continue;
            }
            mst.add(curr);
            mincost += (int) curr.weight;
            union(p1, p2, parents);
            index += 1;
        }
        System.out.println(mst);
        System.err.println(mincost);
        return mincost;
    }

    private void union(String p1, String p2, HashMap<String, String> parents) {
        parents.put(p1, p2);
    }

    private String getParent(String p1, HashMap<String, String> parents) {
        if (!parents.containsKey(p1))
            return p1;
        return getParent(parents.get(p1), parents);
    }

    public static void main(String[] args) {
        MinCostToConnect minCostToConnect = new MinCostToConnect();
        // int[][] points = { { 3, 12 }, { -2, 5 }, { -4, 1 } };
        int[][] points = { { 0, 0 }, { 2, 2 }, { 3, 10 }, { 5, 2 }, { 7, 0 } };
        // int[][] points = { { 0, 0 }, { 1, 1 }, { 1, 0 }, { -1, 1 } };
        // int[][] points = { { -100000, -100000 }, { 100000, 100000 } };
        // int[][] points = { { 0, 0 } };
        minCostToConnect.minCostConnectPoints2(points);
    }
}
