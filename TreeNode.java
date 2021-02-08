import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public void printPreOrderRecursive(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val + " ");
        printPreOrderRecursive(root.left);
        printPreOrderRecursive(root.right);
    }

    public void printPostOrderRecursive(TreeNode root) {
        if (root == null)
            return;
        printPostOrderRecursive(root.left);
        printPostOrderRecursive(root.right);
        System.out.print(root.val + " ");
    }

    public void printInOrderRecursive(TreeNode root) {
        if (root == null)
            return;
        printInOrderRecursive(root.left);
        System.out.print(root.val + " ");
        printInOrderRecursive(root.right);
    }

    public void printPreOrderIterative(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.print(current.val + " ");
            if (current.right != null)
                stack.add(current.right);
            if (current.left != null)
                stack.add(current.left);
        }
    }

    public void printLevelOrder(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }
    }

    public void printTreeLevelByLevel(TreeNode root) {
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // count the nodes in a current level by usigng queue.size()
            // then change a line after all nodes in this level are read

            int levelSize = queue.size();
            while (levelSize > 0) {
                TreeNode current = queue.poll();
                System.out.print(current.val + " ");
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
                levelSize -= 1;
            }
            System.out.println();
        }
    }

    public int heightOfTree(TreeNode root) {
        if (root == null)
            return 0;
        else
            return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
    }

    // Given a binary tree, return the bottom-up level order traversal of its nodes'
    // values. (ie, from left to right, level by level from leaf to root).

    // For example:
    // Given binary tree [3,9,20,null,null,15,7],

    // 3
    // / \
    // 9 20
    // / \
    // 15 7

    // return its bottom-up level order traversal as:

    // [
    // [15,7],
    // [9,20],
    // [3]
    // ]

    // instead of adding currentrow to index 0 we can use a stack as well
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> out = new ArrayList<List<Integer>>();
        if (root == null)
            return out;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // count the nodes in a current level by usigng queue.size()
            // then change a line after all nodes in this level are read

            int levelSize = queue.size();
            List<Integer> currentRow = new ArrayList<Integer>();
            while (levelSize > 0) {
                TreeNode current = queue.poll();
                System.out.print(current.val + " ");
                currentRow.add(current.val);
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
                levelSize -= 1;
            }
            out.add(0, currentRow);
            System.out.println();
        }
        return out;
    }

    // Given the roots of two binary trees p and q, write a function to check if
    // they are the same or not.

    // Two binary trees are considered the same if they are structurally identical,
    // and the nodes have the same value.
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return checkSameTree(p, q);
    }

    public boolean checkSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        else
            return p.val == q.val && checkSameTree(p.left, q.left) && checkSameTree(p.right, q.right);
    }

    public boolean isSymmetric(TreeNode root) {
        return checkSymmetric(root, root);
    }

    public boolean checkSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        return (root1.val == root2.val) && checkSymmetric(root1.left, root2.right)
                && checkSymmetric(root1.right, root2.left);
    }

    // Time complexity : O(n). Because we traverse the entire input tree
    // once, the total run time is O(n), where nnn is the total number of
    // nodes in the tree.

    // Space complexity : There is additional space required for the search queue.
    // In the worst case, we have to insert O(n) nodes in the queue.
    // Therefore, space complexity is O(n).

    public boolean isSymmetricIterative(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null)
                continue;
            if (t1 == null || t2 == null)
                return false;
            if (t1.val != t2.val)
                return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    // Given an array where elements are sorted in ascending order, convert it to a
    // height balanced BST.

    // For this problem, a height-balanced binary tree is defined as a binary tree
    // in which the depth of the two subtrees of every node never differ by more
    // than 1.

    public TreeNode sortedArrayToBST(int[] nums) {
        return create(0, nums.length - 1, nums);
    }

    private TreeNode create(int lo, int hi, int[] nums) {
        if (lo > hi)
            return null;
        int mid = (lo + hi) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = create(lo, mid - 1, nums);
        root.right = create(mid + 1, hi, nums);
        return root;
    }

    // Given a binary tree, determine if it is height-balanced.

    // For this problem, a height-balanced binary tree is defined as:

    // a binary tree in which the left and right subtrees of every node differ in
    // height by no more than 1.

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return Math.abs(heightOfTree(root.left) - heightOfTree(root.right)) <= 1 && isBalanced(root.left)
                && isBalanced(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.printTreeLevelByLevel(root);
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(7);
        root2.printTreeLevelByLevel(root2);

        // System.out.println(root.checkSameTree(root, root2));
        int[] arr = { -10, -3, 0, 5, 9 };
        root.printTreeLevelByLevel(root.sortedArrayToBST(arr));
    }
}
