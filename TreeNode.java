import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;;

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

    @Override
    public String toString() {
        return "Node" + this.val;
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
        stack.push(root);
        while (!stack.empty()) {
            TreeNode current = stack.pop();
            System.out.print(current.val + " ");
            if (current.right != null)
                stack.push(current.right);
            if (current.left != null)
                stack.push(current.left);
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

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        else if (root.left == null)
            return 1 + minHeightOfTree(root.right);
        else if (root.right == null)
            return 1 + minHeightOfTree(root.left);
        return minHeightOfTree(root);
    }

    public int minHeightOfTree(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        if (null == root.left)
            return 1 + minHeightOfTree(root.right);
        if (null == root.right)
            return 1 + minHeightOfTree(root.left);
        else
            return 1 + Math.min(minHeightOfTree(root.left), minHeightOfTree(root.right));
    }

    // Given the root of a binary tree and an integer targetSum, return true if the
    // tree has a root-to-leaf path such that adding up all the values along the
    // path equals targetSum.
    // A leaf is a node with no children.

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && root.val == targetSum)
            return true;
        else
            return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // Invert a binary tree.

    // Example:

    // Input:

    // 4
    // / \
    // 2 7
    // / \ / \
    // 1 3 6 9

    // Output:

    // 4
    // / \
    // 7 2
    // / \ / \
    // 9 6 3 1

    // Since each node in the tree is visited only once, the time complexity is
    // O(n), where nnn is the number of nodes in the tree. We cannot do
    // better than that, since at the very least we have to visit each node to
    // invert it.

    // Because of recursion, O(h)O(h)O(h) function calls will be placed on the stack
    // in the worst case, where hhh is the height of the tree. Because h∈O(n)h\in
    // O(n)h∈O(n), the space complexity is O(n).
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null)
            return root;

        TreeNode left = root.left, right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }

    // Since each node in the tree is visited / added to the queue only once, the
    // time complexity is O(n), where nnn is the number of nodes in the
    // tree.

    // Space complexity is O(n), since in the worst case, the queue will
    // contain all nodes in one level of the binary tree. For a full binary tree,
    // the leaf level has ⌈n2⌉=O(n)\lceil \frac{n}{2}\rceil=O(n)⌈2n​⌉=O(n) leaves.
    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }
        return root;
    }

    // Given a binary search tree (BST), find the lowest common ancestor (LCA) of
    // two given nodes in the BST.
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {

        // if both values are smaller than root
        // they both lie on the left side of tre
        // hence traverse down further
        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestorBST(root.left, p, q);

        // opposite case of the above
        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestorBST(root.right, p, q);

        // if both values lie on either side of the root
        // means root is the only common ancestor
        return root;

    }

    // This is possible without using a stack or recursion since we don't need to
    // backtrace to find the LCA node. In essence of it the problem is iterative, it
    // just wants us to find the split point.
    // this is for a BST
    public TreeNode lowestCommonAncestorBSTIterative(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        int pval = p.val;
        int qval = q.val;
        TreeNode current = root;
        while (current != null) {
            if (current.val > pval && current.val > qval)
                current = current.left;
            else if (current.val < pval && current.val < qval)
                current = current.right;
            else
                return current;
        }
        return null;
    }

    // for a normal tree
    // you can store the nodes incurred in the paths from the root to that node
    // in two arrays
    // and then traverse the arrays to return the lowest common ancestor

    // below is the recursive approach

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        // if root matches with any of these nodes
        // we return the same to the parent call
        if (root == p || root == q)
            return root;
        // if this is a leaf node
        // we return null to the parent call
        else if (root.left == null && root.right == null)
            return null;

        // we recursively travel the left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // this means that p and q lie on different sides of root
        // hence root is lca
        if (left != null && right != null)
            return root;
        // neither p and q were found on the left or right side of root
        // return null
        else if (left == null && right == null)
            return null;

        // return whichever is not null
        else {
            if (left == null)
                return right;
            return left;
        }
        // return left != null ? right != null ? root : left : right != null ? right :
        // null;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null)
            return list;
        rootToLeafPathsDFS(root, new StringBuilder(), list);
        return list;
    }

    private void rootToLeafPathsDFS(TreeNode root, StringBuilder stringBuilder, List<String> paths) {
        stringBuilder.append(root.val);
        if (root.left == null && root.right == null) {
            // if a lead node
            // end current loop and add the current path to the list
            paths.add(stringBuilder.toString());
        }

        // if not a leaf node
        // append an arrow
        // and continue down further
        if (root.left != null) {
            rootToLeafPathsDFS(root.left, new StringBuilder(stringBuilder).append("->"), paths);
        }
        if (root.right != null) {
            rootToLeafPathsDFS(root.right, new StringBuilder(stringBuilder).append("->"), paths);
        }
    }

    // Find the sum of all left leaves in a given binary tree.

    // Example:

    // -----3
    // ----/ \
    // ---9--20
    // ----- / \
    // ---15---7

    // There are two left leaves in the binary tree, with values 9 and 15
    // respectively. Return 24.

    public int sumOfLeftLeaves(TreeNode root) {

        // Queue approach
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();

            // if the leaf node of this node is a leaf node
            // then sum it
            // and add the right node to the queue if present
            if (curNode.left != null && curNode.left.left == null && curNode.left.right == null) {
                System.out.println("TreeNode.sumOfLeftLeaves() adding " + curNode.left.val);
                sum += curNode.left.val;
                if (curNode.right != null)
                    queue.offer(curNode.right);
            } else {
                // if not a leaf node
                // then add the left and right nodes to the queue, if present
                if (curNode.left != null)
                    queue.offer(curNode.left);
                if (curNode.right != null)
                    queue.offer(curNode.right);
            }
        }
        return sum;

    }

    public int sumOfLeftLeavesRecursive(TreeNode root) {
        if (root == null)
            return 0;
        int[] sumA = new int[1];
        sumOfLeftLeavesUtil(root, false, sumA);
        return sumA[0];
    }

    public void sumOfLeftLeavesUtil(TreeNode root, boolean isLeftNode, int[] sum) {
        if (root == null)
            return;
        if (isLeftNode && root.left == null && root.right == null) {
            sum[0] += root.val;
            sumOfLeftLeavesUtil(root.right, false, sum);
        } else {
            sumOfLeftLeavesUtil(root.left, true, sum);
            sumOfLeftLeavesUtil(root.right, false, sum);
        }
    }

    // Given a binary search tree (BST) with duplicates, find all the mode(s) (the
    // most frequently occurred element) in the given BST.
    public int[] findMode(TreeNode root) {
        if (root == null)
            return new int[0];
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() != 0) {
            TreeNode current = queue.poll();
            map.put(current.val, map.getOrDefault(current.val, 0) + 1);
            if (current.left != null)
                queue.offer(current.left);
            if (current.right != null)
                queue.offer(current.right);
        }
        System.out.println(map);
        int maxCount = -1;
        for (Integer key : map.keySet()) {
            if (map.get(key) > maxCount)
                maxCount = map.get(key);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == maxCount)
                list.add(key);
        }
        int[] arr = new int[list.size()];
        int ind = 0;
        for (int i : list) {
            arr[ind] = i;
            ind += 1;
        }
        return arr;
    }

    // Two nodes of a binary tree are cousins if they have the same depth, but have
    // different parents.
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null)
            return false;
        Queue<TreeNode> queue = new LinkedList<>();
        int currentLevel = 1;
        queue.offer(root);
        queue.offer(new TreeNode(-1));
        int parentx = -1, parenty = -1;
        int levelx = -1, levely = -1;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.val == -1) {
                currentLevel += 1;
                if (!queue.isEmpty())
                    queue.offer(new TreeNode(-1));
            } else {
                if (current.left != null) {
                    queue.offer(current.left);
                    if (current.left.val == x) {
                        parentx = current.val;
                        levelx = currentLevel + 1;
                    } else if (current.left.val == y) {
                        parenty = current.val;
                        levely = currentLevel + 1;
                    }
                }
                if (current.right != null) {
                    queue.offer(current.right);
                    if (current.right.val == x) {
                        parentx = current.val;
                        levelx = currentLevel + 1;
                    } else if (current.right.val == y) {
                        parenty = current.val;
                        levely = currentLevel + 1;
                    }
                }
            }
            if (parentx != parenty && levelx == levely)
                return true;
        }
        return false;
    }

    // return level in which sum of nodes is maximum
    // Constraints:

    // The number of nodes in the tree is in the range [1, 104].
    // -105 <= Node.val <= 105

    public int maxLevelSum(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currentLevel = 1;
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int maxSumLevel = 0;

        // as a delimitter for level change
        queue.offer(new TreeNode(Integer.MAX_VALUE));
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            // incurred a delimitter
            if (currentNode.val == Integer.MAX_VALUE) {
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    maxSumLevel = currentLevel;
                }
                // increment a level
                currentLevel += 1;
                // if there are more nodes, add a delimitter
                if (!queue.isEmpty()) {
                    queue.offer(new TreeNode(Integer.MAX_VALUE));
                }
            } else {
                currentSum += currentNode.val;
                if (currentNode.left != null)
                    queue.offer(currentNode.left);
                if (currentNode.right != null)
                    queue.offer(currentNode.right);
            }
        }
        return maxSumLevel;
    }

    // Given the root of a binary tree, return the leftmost value in the last row of
    // the tree.

    // Example 1:

    // Input: root = [2,1,3]
    // Output: 1

    // Example 2:

    // Input: root = [1,2,3,4,null,5,6,null,null,7]
    // Output: 7

    // Constraints:

    // The number of nodes in the tree is in the range [1, 104].
    // -231 <= Node.val <= 231 - 1

    // Runtime: 1 ms, faster than 67.81% of Java online submissions for Find Bottom
    // Left Tree Value.
    // Memory Usage: 38 MB, less than 99.78% of Java online submissions for Find
    // Bottom Left Tree Value.

    public int findBottomLeftValue(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // as a delimiter
        queue.offer(null);
        int lastLeft = root.val;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                if (!queue.isEmpty())
                    queue.offer(null);
            } else {
                if (current.right != null) {
                    queue.offer(current.right);
                    lastLeft = current.right.val;
                }
                if (current.left != null) {
                    queue.offer(current.left);
                    lastLeft = current.left.val;
                }
            }
        }
        return lastLeft;
    }

    // Given the root of a binary tree, imagine yourself standing on the right side
    // of it, return the values of the nodes you can see ordered from top to bottom.

    // Example 1:

    // Input: root = [1,2,3,null,5,null,4]
    // Output: [1,3,4]

    // Example 2:

    // Input: root = [1,null,3]
    // Output: [1,3]

    // Example 3:

    // Input: root = []
    // Output: []

    // Constraints:

    // The number of nodes in the tree is in the range [0, 100].
    // -100 <= Node.val <= 100

    // Runtime: 1 ms, faster than 72.60% of Java online submissions for Binary Tree
    // Right Side View.
    // Memory Usage: 37.3 MB, less than 92.23% of Java online submissions for Binary
    // Tree Right Side View.
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        list.add(root.val);
        // as a delimiter
        queue.offer(new TreeNode(-1000));

        int prevRightmost = root.val;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.val == -1000) {
                if (!queue.isEmpty()) {
                    queue.offer(new TreeNode(-1000));
                    list.add(prevRightmost);
                }
            } else {
                if (current.left != null) {
                    prevRightmost = current.left.val;
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    prevRightmost = current.right.val;
                    queue.offer(current.right);
                }
            }
        }
        return list;
    }

    public void vertialOrderTraversal(TreeNode root) {
        HashMap<Integer, List<TreeNode>> map = new HashMap<>();
        VOTUtil(root, map, 0);
        System.out.println("Vertical order= " + map);
    }

    public void VOTUtil(TreeNode root, HashMap<Integer, List<TreeNode>> map, int x) {
        if (!map.containsKey(x))
            map.put(x, new ArrayList<>());
        map.get(x).add(root);
        if (root.left != null)
            VOTUtil(root.left, map, x - 1);
        if (root.right != null)
            VOTUtil(root.right, map, x + 1);
    }

    public void antiClockWiseSpiralLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> levels = new ArrayList<>();
        int h = heightOfTree(root);
        for (int i = 0; i < h; i++) {
            levels.add(new ArrayList<Integer>());
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                TreeNode curr = q.poll();
                levels.get(level).add(curr.val);
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
                size -= 1;
            }
            level += 1;
        }
        System.out.println(levels);

        int lo = 0, hi = levels.size() - 1;
        while (lo < hi) {
            Collections.reverse(levels.get(lo));
            System.out.print(levels.get(lo) + " ");
            System.out.print(levels.get(hi) + " ");
            lo += 1;
            hi -= 1;
        }
        if (lo == hi) {
            Collections.reverse(levels.get(lo));
            System.out.print(levels.get(lo) + " ");
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.right.right = new TreeNode(10);
        // root.printTreeLevelByLevel(root);
        // root.antiClockWiseSpiralLevelOrder(root);
        // System.out.println(Arrays.toString(root.findMode(root)));
        // root.vertialOrderTraversal(root);
        // System.out.println(root.binaryTreePaths(root).toString());
        // TreeNode root2 = new TreeNode(1);
        // root2.left = new TreeNode(2);
        // root2.right = new TreeNode(3);
        // root2.left.left = new TreeNode(4);
        // root2.left.right = new TreeNode(5);
        // root2.right.left = new TreeNode(6);
        // root2.right.right = new TreeNode(7);
        // root2.printTreeLevelByLevel(root2);

        // System.out.println(root.checkSameTree(root, root2));
        // int[] arr = { -10, -3, 0, 5, 9 };
        // root.printTreeLevelByLevel(root.sortedArrayToBST(arr));

        // TreeNode r = root.invertTree(root);
        // root.printTreeLevelByLevel(r);

        TreeNode cons = root.constructFromPreAndIn(new int[] { 4, 2, 5, 1, 3, 6 }, new int[] { 1, 2, 4, 5, 3, 6 });
        root.printTreeLevelByLevel(cons);
    }

    /**
     * Given a Binary Tree, find its Boundary Traversal. The traversal should be in
     * the following order:
     * 
     * Left boundary nodes: defined as the path from the root to the left-most node
     * ie- the leaf node you could reach when you always travel preferring the left
     * subtree over the right subtree.
     * 
     * 
     * Leaf nodes: All the leaf nodes except for the ones that are part of left or
     * right boundary.
     * 
     * 
     * Reverse right boundary nodes: defined as the path from the right-most node to
     * the root. The right-most node is the leaf node you could reach when you
     * always travel preferring the right subtree over the left subtree. Exclude the
     * root from this as it was already included in the traversal of left boundary
     * nodes.
     * 
     * 
     * Note: If the root doesn't have a left subtree or right subtree, then the root
     * itself is the left or right boundary.
     */

    ArrayList<Integer> printBoundary(TreeNode node) {
        ArrayList<Integer> ans = new ArrayList<>();

        ans.add(node.val);

        // first print left boundary
        leftBoundary(node.left, ans);

        // then all leaves
        getLeaves(node, ans);

        // then right boundary from bottom up
        rightBoundaryReverse(node.right, ans);
        return ans;
    }

    private void leftBoundary(TreeNode node, ArrayList<Integer> ans) {
        if (node == null)
            return;

        ans.add(node.val);

        // if left present, go to left, else the right will be boundary if left is
        // absent
        if (node.left != null) {
            leftBoundary(node.left, ans);
        } else if (node.right != null) {
            leftBoundary(node.right, ans);
        }
    }

    private void getLeaves(TreeNode node, ArrayList<Integer> ans) {

        if (node == null)
            return;

        // go to the leftmost leaf first
        getLeaves(node.left, ans);

        // if leaf, add to ans
        if (node.left == null && node.right == null)
            ans.add(node.val);

        // then traverse for right leaf
        getLeaves(node.right, ans);
    }

    private void rightBoundaryReverse(TreeNode node, ArrayList<Integer> ans) {

        if (node == null)
            return;

        // first traverse all right boundary
        if (node.right != null) {
            rightBoundaryReverse(node.right, ans);
        } else if (node.left != null) {
            rightBoundaryReverse(node.left, ans);
        }

        // then add to ans cuz we need reverse order
        ans.add(node.val);
    }

    // construct tree from preorder and level order traversal

    int preStart = 0;

    public TreeNode constructFromPreAndIn(int[] inorder, int[] preorder) {
        preStart = 0;

        return constructTree(inorder, preorder, 0, inorder.length - 1);

    }

    private TreeNode constructTree(int[] inorder, int[] preorder, int start, int end) {
        if (start > end || start > inorder.length - 1 || end > inorder.length - 1)
            return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        preStart += 1;
        // if (start == end)
        // return root;
        int indexInInorder = findIndexOf(root.val, inorder, start, end);
        root.left = constructTree(inorder, preorder, start, indexInInorder - 1);
        root.right = constructTree(inorder, preorder, indexInInorder + 1, end);
        return root;
    }

    private int findIndexOf(int rootVal, int[] inorder, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == rootVal)
                return i;
        }
        return -1;
    }
}
