import java.util.*;

public class Test {
    public long fun(int[] l, int[] r, int[] A, int[] B, int n, int m, int maxh) {
        ArrayList<ArrayList<Integer>> low = new ArrayList<>();
        ArrayList<ArrayList<Integer>> high = new ArrayList<>();

        for (int i = 0; i < n + 2; i++) {
            low.add(new ArrayList<Integer>());
            high.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            low.get(l[i]).add(A[i]);
            low.get(r[i] + 1).add(-A[i]);
            high.get(l[i]).add(B[i]);
            high.get(r[i] + 1).add(-B[i]);
        }

        PriorityQueue<Integer> lo = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> hi = new PriorityQueue<>();
        long ans = 0L, mod = 1000000007L;

        for (int i = 0; i <= n; i++) {
            for (int it : low.get(i)) {
                if (it > 0) {
                    lo.offer(it);
                } else
                    lo.remove(-it);
            }

            for (int it : high.get(i)) {
                if (it > 0) {
                    hi.offer(it);
                } else
                    hi.remove(-it);
            }

            long left = 1L;
            if (!lo.isEmpty())
                left *= lo.peek();
            long right = maxh;
            if (!hi.isEmpty())
                right *= hi.peek();
            if (left > right) {
                ans = 0;
                return ans;
            }
            ans *= (right - left + 1);
            ans %= mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        pq.offer(2);
        pq.offer(1);
        pq.offer(23);
        System.out.println(pq);
        System.out.println(pq.toArray()[pq.size() - 1]);
    }
}