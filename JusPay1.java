import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class JusPay1 {

    public static void main(String[] args) {

        int[] arr = { 2, 2, 5, 4, 8, 3, 2, 6, 6 };
        int src = 0, dst = 8;

        int[][] levels = new int[arr.length][2];
        for (int i = 0; i < levels.length; i++) {
            levels[i][0] = -1;
            levels[i][1] = -1;
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        int count = 0;

        System.out.println("before bfs 1");
        for (int i = 0; i < levels.length; i++) {
            System.out.print(Arrays.toString(levels[i]));
        }
        System.out.println();
        while (!q.isEmpty()) {
            int curr = q.poll();
            System.out.println("bfs 1 curr= " + curr + " pointing to " + arr[curr]);
            if (curr == -1)
                break;
            if (levels[curr][0] != -1)
                break;

            q.offer(arr[curr]);
            levels[curr][0] = count;
            count += 1;
            for (int i = 0; i < levels.length; i++) {
                System.out.print(Arrays.toString(levels[i]));
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println("after bfs 1");
        for (int i = 0; i < levels.length; i++) {
            System.out.print(Arrays.toString(levels[i]));
        }
        System.out.println();
        System.out.println();
        q.clear();
        q.offer(dst);
        count = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            System.out.println("bfs 1 curr= " + curr + " pointing to " + arr[curr]);

            if (curr == -1)
                break;
            if (levels[curr][1] != -1)
                break;

            q.offer(arr[curr]);
            levels[curr][1] = count;
            count += 1;

            for (int i = 0; i < levels.length; i++) {
                System.out.print(Arrays.toString(levels[i]));
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println("after bfs 2");
        for (int i = 0; i < levels.length; i++) {
            System.out.print(Arrays.toString(levels[i]));
        }

        int minDist = Integer.MAX_VALUE;
        int nodeAtMinDist = -1;
        for (int i = 0; i < levels.length; i++) {
            if (levels[i][0] != -1 && levels[i][1] != -1) {
                if (levels[i][0] + levels[i][1] < minDist) {
                    minDist = levels[i][0] + levels[i][1];
                    nodeAtMinDist = i;
                }
            }
        }
        if (nodeAtMinDist >= 0)
            System.out.println(
                    "Node at min dist= " + nodeAtMinDist + "at dist= " + Arrays.toString(levels[nodeAtMinDist]));
    }
}