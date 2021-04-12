import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

    // There are a total of numCourses courses you have to take, labeled from 0 to
    // numCourses - 1. You are given an array prerequisites where prerequisites[i] =
    // [ai, bi] indicates that you must take course bi first if you want to take
    // course ai.

    // For example, the pair [0, 1], indicates that to take course 0 you have to
    // first take course 1.

    // Return true if you can finish all courses. Otherwise, return false.

    // Example 1:

    // Input: numCourses = 2, prerequisites = [[1,0]]
    // Output: true
    // Explanation: There are a total of 2 courses to take.
    // To take course 1 you should have finished course 0. So it is possible.

    // Example 2:

    // Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
    // Output: false
    // Explanation: There are a total of 2 courses to take.
    // To take course 1 you should have finished course 0, and to take course 0 you
    // should also have finished course 1. So it is impossible.

    // Constraints:

    // 1 <= numCourses <= 105
    // 0 <= prerequisites.length <= 5000
    // prerequisites[i].length == 2
    // 0 <= ai, bi < numCourses
    // All the pairs prerequisites[i] are unique.

    // basically checking if cycle exists or not in the directed graph

    // using Kahn's algo here
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        int[] incoming = new int[numCourses];
        for (int[] preq : prerequisites) {
            adj.get(preq[0]).add(preq[1]);
            incoming[preq[1]] += 1;
        }
        System.out.println(adj);
        System.out.println(Arrays.toString(incoming));
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < incoming.length; i++) {
            if (incoming[i] == 0)
                q.offer(i);
        }
        int count = 0;
        while (!q.isEmpty()) {
            int currentNode = q.poll();
            count += 1;
            for (int v : adj.get(currentNode)) {
                incoming[v] -= 1;
                if (incoming[v] == 0)
                    q.offer(v);
            }
        }
        return count == numCourses;
    }

    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        int[][] prerequisites = { { 1, 0 } };
        System.out.println(courseSchedule.canFinish(2, prerequisites));
    }
}
