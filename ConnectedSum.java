import java.util.ArrayList;

public class ConnectedSum {
    int calculateSum(int nNodes, int[] graphFrom, int[] graphTo) {
        int sum = 0;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= nNodes; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < graphTo.length; i++) {
            adj.get(graphFrom[i]).add(graphTo[i]);
        }
        System.out.println(adj);
        boolean[] visited = new boolean[nNodes + 1];
        for (int i = 1; i <= nNodes; i++) {
            if (!visited[i]) {
                int nodes = dfs(i, adj, visited);
                System.out.println("nodes by traversing from " + i + " " + nodes);
                sum += Math.ceil(Math.sqrt(nodes));
            }
        }
        System.out.println(sum);
        return sum;
    }

    private int dfs(int curr, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        int n = 1;
        visited[curr] = true;
        for (int node : adj.get(curr)) {
            if (!visited[node])
                n += dfs(node, adj, visited);
        }
        return n;
    }

    public static void main(String[] args) {
        ConnectedSum cn = new ConnectedSum();
        cn.calculateSum(10, new int[] { 1, 1, 2, 3, 7 }, new int[] { 2, 3, 4, 5, 8 });
    }
}
