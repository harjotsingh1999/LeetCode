import java.util.Scanner;

public class ListNode {
    int val;
    ListNode next;
    Scanner read = new Scanner(System.in);

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode temp1 = l1, temp2 = l2, temp3, newNode;
        if (temp1.val < temp2.val) {
            newNode = new ListNode(temp1.val);
            temp3 = newNode;
            temp1 = temp1.next;
        } else {
            newNode = new ListNode(temp2.val);
            temp3 = newNode;
            temp2 = temp2.next;
        }

        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                temp3.next = new ListNode(temp1.val);
                temp3 = temp3.next;
                temp1 = temp1.next;
            } else {
                temp3.next = new ListNode(temp2.val);
                temp3 = temp3.next;
                temp2 = temp2.next;
            }
        }

        if (temp1 != null) {
            // this iteration can acutally be removed.
            // simply put temp3.next= temp1
            while (temp1 != null) {
                temp3.next = new ListNode(temp1.val);
                temp3 = temp3.next;
                temp1 = temp1.next;
            }
        }
        if (temp2 != null) {
            // this iteration can acutally be removed.
            // simply put temp3.next= temp2
            while (temp2 != null) {
                temp3.next = new ListNode(temp2.val);
                temp3 = temp3.next;
                temp2 = temp2.next;
            }
        }
        return newNode;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = null, current = head;
        while (current != null) {
            if (prev != null && current.val == prev.val) {
                prev.next = current.next;
                current = current.next;
            } else {
                prev = current;
                current = current.next;
            }
        }
        return head;
    }

    public void printList(ListNode listNode) {
        ListNode temp = listNode;
        while (temp != null) {
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println();
    }

    public ListNode createList(int size) {
        if (size == 0)
            return null;
        ListNode head = null, temp = null;
        for (int i = 1; i <= size; i++) {
            System.out.println("enter a number");
            int num = read.nextInt();
            if (head == null) {
                head = new ListNode(num);
                temp = head;
            } else {
                temp.next = new ListNode(num);
                temp = temp.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode();
        System.out.println("Enter length of first list");
        int l1 = listNode.read.nextInt();
        // System.out.println("Enter len of second list");
        // int l2 = listNode.read.nextInt();
        ListNode list1 = listNode.createList(l1);
        listNode.printList(list1);
        // ListNode list2 = listNode.createList(l2);
        // listNode.printList(list2);
        ListNode list3 = listNode.deleteDuplicates(list1);
        listNode.printList(list3);
        listNode.read.close();
    }
}
