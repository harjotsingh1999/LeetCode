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

    // There are a total of n courses you have to take labelled from 0 to n - 1.

    // Some courses may have prerequisites, for example, if prerequisites[i] = [ai,
    // bi] this means you must take the course bi before the course ai.

    // Given the total number of courses numCourses and a list of the prerequisite
    // pairs, return the ordering of courses you should take to finish all courses.

    // If there are many valid answers, return any of them. If it is impossible to
    // finish all courses, return an empty array.

    // Example 1:

    // Input: numCourses = 2, prerequisites = [[1,0]]
    // Output: [0,1]
    // Explanation: There are a total of 2 courses to take. To take course 1 you
    // should have finished course 0. So the correct course order is [0,1].

    // Example 2:

    // Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
    // Output: [0,2,1,3]
    // Explanation: There are a total of 4 courses to take. To take course 3 you
    // should have finished both courses 1 and 2. Both courses 1 and 2 should be
    // taken after you finished course 0.
    // So one correct course order is [0,1,2,3]. Another correct ordering is
    // [0,2,1,3].

    // Example 3:

    // Input: numCourses = 1, prerequisites = []
    // Output: [0]

    // Constraints:

    // 1 <= numCourses <= 2000
    // 0 <= prerequisites.length <= numCourses * (numCourses - 1)
    // prerequisites[i].length == 2
    // 0 <= ai, bi < numCourses
    // ai != bi
    // All the pairs [ai, bi] are distinct.

    // Runtime: 3 ms, faster than 93.35% of Java online submissions for Course
    // Schedule II.
    // Memory Usage: 39.8 MB, less than 84.20% of Java online submissions for Course
    // Schedule II.

    // Using Kahn's algorithm
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] incoming = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(i, new ArrayList<Integer>());
        }
        for (int[] pre : prerequisites) {
            incoming[pre[0]] += 1;
            adj.get(pre[1]).add(pre[0]);
        }
        // System.out.println(adj);
        int[] top = new int[numCourses];
        int index = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (incoming[i] == 0) {
                top[index] = i;
                q.offer(i);
                index += 1;
            }
        }
        int count = 0;
        while (!q.isEmpty()) {
            int current = q.poll();
            count += 1;
            for (int node : adj.get(current)) {
                incoming[node] -= 1;
                if (incoming[node] == 0) {
                    top[index] = node;
                    q.offer(node);
                    index += 1;
                }
            }
        }
        if (count != numCourses)
            return new int[0];
        return top;
    }

    // There are a total of n courses you have to take, labeled from 0 to n-1.

    // Some courses may have direct prerequisites, for example, to take course 0 you
    // have first to take course 1, which is expressed as a pair: [1,0]

    // Given the total number of courses n, a list of direct prerequisite pairs and
    // a list of queries pairs.

    // You should answer for each queries[i] whether the course queries[i][0] is a
    // prerequisite of the course queries[i][1] or not.

    // Return a list of boolean, the answers to the given queries.

    // Please note that if course a is a prerequisite of course b and course b is a
    // prerequisite of course c, then, course a is a prerequisite of course c.

    // Example 1:

    // Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
    // Output: [false,true]
    // Explanation: course 0 is not a prerequisite of course 1 but the opposite is
    // true.

    // Example 2:

    // Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
    // Output: [false,false]
    // Explanation: There are no prerequisites and each course is independent.

    // Example 3:

    // Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
    // Output: [true,true]

    // Example 4:

    // Input: n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
    // Output: [false,true]

    // Example 5:

    // Input: n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries =
    // [[0,4],[4,0],[1,3],[3,0]]
    // Output: [true,false,true,false]

    // Constraints:

    // 2 <= n <= 100
    // 0 <= prerequisite.length <= (n * (n - 1) / 2)
    // 0 <= prerequisite[i][0], prerequisite[i][1] < n
    // prerequisite[i][0] != prerequisite[i][1]
    // The prerequisites graph has no cycles.
    // The prerequisites graph has no repeated edges.
    // 1 <= queries.length <= 10^4
    // queries[i][0] != queries[i][1]

    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] incoming = new int[n];
        for (int i = 0; i < n; i++) {
            adj.add(i, new ArrayList<Integer>());
        }
        for (int[] pre : prerequisites) {
            adj.get(pre[0]).add(pre[1]);
            incoming[pre[1]] += 1;
        }
        System.out.println(adj);
        System.out.println(Arrays.toString(incoming));
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] top = new int[n];
        int index = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (incoming[i] == 0) {
                top[index] = i;
                q.offer(i);
                index += 1;
                parent[i] = -1;
            }
        }
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int node : adj.get(current)) {
                incoming[node] -= 1;
                if (incoming[node] == 0) {
                    top[index] = node;
                    q.offer(node);
                    index += 1;
                    parent[node] = current;
                }
            }
        }
        System.out.println(Arrays.toString(top));
        System.out.println(Arrays.toString(parent));
        List<Boolean> out = new ArrayList<>(queries.length);
        for (int i = 0; i < queries.length; i++) {
            out.add(i, checkIfPrerequisite(queries[i][0], queries[i][1], parent));
        }
        return out;
    }

    // You should answer for each queries[i] whether the course queries[i][0] is a
    // prerequisite of the course queries[i][1] or not.

    private Boolean checkIfPrerequisite(int c1, int c2, int[] parent) {

        for (int i = c2; i != -1; i = parent[i]) {
            if (i == c1)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        int[][] prerequisites = { { 1, 0 } };
        // int[][] prerequisites = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
        System.out.println(courseSchedule.checkIfPrerequisite(2, prerequisites, new int[][] { { 1, 0 }, { 0, 1 } }));
    }
}
