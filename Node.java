import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Nary Tree
class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    public int maxDepth(Node root) {
        int max = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current == null && !queue.isEmpty()) {
                queue.offer(null);
            } else {
                if (current.children != null) {
                    for (Node child : current.children) {
                        queue.offer(child);
                    }
                }
            }
        }
        return max;
    }

    // Runtime: 2 ms, faster than 86.16% of Java online submissions for N-ary Tree
    // Level Order Traversal.
    // Memory Usage: 40.1 MB, less than 28.61% of Java online submissions for N-ary
    // Tree Level Order Traversal.
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null)
            return list;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        // as a delimiter
        queue.offer(new Node(-1));

        List<Integer> currentLevelList = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.val == -1) {
                list.add(currentLevelList);
                currentLevelList = new ArrayList<>();
                if (!queue.isEmpty())
                    queue.offer(new Node(-1));
            } else {
                currentLevelList.add(current.val);
                for (Node child : current.children)
                    queue.offer(child);
            }
        }
        return list;
    }
}