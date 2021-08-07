import java.util.*;
import java.util.ArrayList;

public class ExplodingBombs {

    public static ArrayList<Integer> destroyed(int A, ArrayList<ArrayList<Integer>> B, ArrayList<Integer> C, int D) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= A; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (ArrayList<Integer> road : B) {
            adj.get(road.get(0)).add(road.get(1));
            adj.get(road.get(1)).add(road.get(0));
        }

        System.out.println(adj);
        int[] destroyed = new int[A + 1];
        for (int i : C) {
            System.out.println("starting from " + i);
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            int dist = 0;
            while (!q.isEmpty() && dist <= D) {
                int size = q.size();
                while (size > 0) {
                    size -= 1;
                    int curr = q.poll();
                    System.out.println("curr= " + curr);
                    destroyed[curr] = 1;
                    for (int node : adj.get(curr)) {
                        if (destroyed[node] == 0)
                            q.offer(node);
                    }
                }
                dist += 1;
            }
        }

        ArrayList<Integer> status = new ArrayList<>();
        for (int i = 1; i < destroyed.length; i++) {
            status.add(destroyed[i]);
        }
        System.out.println(status);
        return status;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> B = new ArrayList<>();
        ArrayList<Integer> road1 = new ArrayList<>();
        road1.add(1);
        road1.add(2);
        B.add(road1);
        ArrayList<Integer> road2 = new ArrayList<>();
        road2.add(1);
        road2.add(3);
        B.add(road2);
        ArrayList<Integer> road3 = new ArrayList<>();
        road3.add(1);
        road3.add(4);
        B.add(road3);
        ArrayList<Integer> road4 = new ArrayList<>();
        road4.add(3);
        road4.add(5);
        B.add(road4);
        ArrayList<Integer> road5 = new ArrayList<>();
        road5.add(2);
        road5.add(5);
        B.add(road5);
        // ArrayList<Integer> road6 = new ArrayList<>();
        // road6.add(6);
        // road6.add(7);
        // B.add(road6);
        // ArrayList<Integer> road7 = new ArrayList<>();
        // road7.add(6);
        // road7.add(8);
        // B.add(road7);
        // ArrayList<Integer> road8 = new ArrayList<>();
        // road8.add(5);
        // road8.add(9);
        // B.add(road8);
        ArrayList<Integer> C = new ArrayList<>();
        // C.add(7);
        C.add(5);
        C.add(3);
        destroyed(5, B, C, 2);
    }
}
