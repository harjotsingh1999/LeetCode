import java.io.BufferedReader;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.io.*;

public class SpaceTree {
    static HashMap<String, Node> map = new HashMap<>();
    static Semaphore db = new Semaphore(1);
    static Semaphore mutex = new Semaphore(1);
    static int rc = 0;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Scanner read = new Scanner(System.in);

        // String[] nums = br.readLine().split(" ");
        // int nNodes = read.nextInt();
        // int cCount = read.nextInt();
        // int nQueries = read.nextInt();

        int nNodes = Integer.parseInt(br.readLine());
        int cCount = Integer.parseInt(br.readLine());
        int nQueries = Integer.parseInt(br.readLine());

        String[] places = new String[nNodes];
        for (int i = 0; i < nNodes; i++) {
            places[i] = br.readLine();
            System.out.println("place= " + places[i]);
        }

        Queue<Node> q = new LinkedList<>();
        Node root = new Node(null, places[0], nNodes - 1);
        q.offer(root);
        map.put(places[0], root);
        int index = 1;
        while (!q.isEmpty() && index < nNodes) {
            Node curr = q.poll();
            int remaining = curr.noOfDescendents - cCount;
            for (int i = 1; i <= cCount; i++) {
                Node child = new Node(curr, places[index], remaining / cCount);
                map.put(places[index], child);
                q.offer(child);
                index += 1;
                curr.addChild(child);
                System.out.println("Add to queue " + child);
            }
        }

        System.out.println("map= " + map);

        // read.nextLine();
        synchronized (SpaceTree.class) {
            for (int i = 0; i < nQueries; i++) {
                // String qry = read.nextLine();
                String qry = br.readLine();
                System.out.println("query= " + qry);
                String[] qs = qry.split(" ");

                if (qs[0].equals("1")) {
                    System.out.println(lock(qs[1], qs[2]));
                } else if (qs[0].equals("2")) {
                    System.out.println(unlock(qs[1], qs[2]));
                } else {
                    System.out.println(upgrade(qs[1], qs[2]));
                }
            }
        }
        // read.close();

    }

    private static boolean upgrade(String key, String uid) {

        Node curr = map.get(key);

        if (curr.isLocked)
            return false;

        if (curr.lockedDescendents != curr.noOfDescendents)
            return false;
        if (curr.lockedMap.get(uid) != curr.lockedDescendents)
            return false;
        // int[] count = new int[1];
        // if (!checkAllDecendentsLockedBySameUser(curr, uid)) {
        // System.out.println("SpaceTree.upgrade() false");
        // return false;
        // }
        curr.lockedBy = uid;
        curr.isLocked = true;
        curr.lockedDescendents = 0;
        curr.lockedMap.clear();
        unlockAllDescendents(curr);
        reduceParentLockChildrenCount(curr, curr.lockedDescendents, uid);
        return true;
    }

    private static void reduceParentLockChildrenCount(Node curr, int count, String uid) {
        Node parent = curr.parent;
        while (parent != null) {
            parent.lockedDescendents = parent.lockedDescendents - count + 1;
            parent.lockedMap.put(uid, parent.lockedDescendents);
            parent = parent.parent;
        }
    }

    // private static boolean checkAllDecendentsLockedBySameUser(Node curr, String
    // uid) {
    // for (Node child : curr.children) {
    // if (!child.isLocked || !child.lockedBy.equals(uid)) {
    // System.out.println("SpaceTree.checkAllDecendentsLockedBySameUser() not locked
    // " + child);
    // return false;
    // }
    // return checkAllDecendentsLockedBySameUser(child, uid);
    // }
    // return true;
    // }

    private static void unlockAllDescendents(Node curr) {
        for (Node child : curr.children) {
            child.isLocked = false;
            child.lockedDescendents = 0;
            child.lockedMap.clear();
            unlockAllDescendents(child);
        }
    }

    private static boolean unlock(String key, String uid) {
        Node node = map.get(key);

        // if node not locked
        if (!isLocked(node)) {
            return false;
        }

        // if locker uid is not same
        if (!node.lockedBy.equals(uid))
            return false;

        // set node unlocked
        node.isLocked = false;
        Node parentNode = node.parent;

        while (parentNode != null) {
            parentNode.lockedDescendents -= 1;
            parentNode.lockedMap.put(uid, parentNode.lockedMap.get(uid) - 1);
            parentNode = parentNode.parent;
        }
        return true;
    }

    static boolean lock = false;

    private static boolean lock(String key, String uid) {
        Node node = map.get(key);

        // while (test_and_set());

        if (isLocked(node)) {
            // lock = false;
            return false;
        }

        node.isLocked = true;

        if (!canLock(node)) {
            node.isLocked = false;
            return false;
        }

        node.lockedBy = uid;

        Node parentNode = node.parent;

        while (parentNode != null) {
            parentNode.lockedDescendents += 1;
            parentNode.lockedMap.put(uid, parentNode.lockedMap.getOrDefault(uid, 0) + 1);
            parentNode = parentNode.parent;
        }
        // lock = false;
        return true;
    }

    // static boolean test_and_set() {
    //     boolean r = lock;
    //     lock = true;
    //     return r;
    // }

    public static boolean canLock(Node node) {

        // if any descendents locked
        // then we cannot lock this node
        if (node.lockedDescendents > 0) {
            return false;
        }

        Node parentNode = node.parent;

        // if any parent is locked
        // we cannot lock this node
        while (parentNode != null) {
            if (isLocked(parentNode)) {
                return false;
            }
            parentNode = parentNode.parent;
        }
        return true;
    }

    // if current node is locked
    private static boolean isLocked(Node node) {
        return node.isLocked;
    }

    static class Node {
        String key;
        Node parent;
        ArrayList<Node> children;
        boolean isLocked;
        String lockedBy;
        int lockedDescendents = 0, noOfDescendents;
        HashMap<String, Integer> lockedMap;

        Node(Node parent, String key, int nDescendents) {
            this.parent = parent;
            children = new ArrayList<>();
            isLocked = false;
            lockedBy = "";
            this.noOfDescendents = nDescendents;
            this.key = key;
            lockedMap = new HashMap<>();
        }

        void addChild(Node child) {
            this.children.add(child);
        }

        @Override
        public String toString() {
            return this.key + " desCount= " + this.noOfDescendents;
        }
    }
}