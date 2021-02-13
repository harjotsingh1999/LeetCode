import java.util.HashSet;
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

    public boolean hasCycle1(ListNode head) {
        // to check visited nodes
        HashSet<ListNode> set = new HashSet<>();
        ListNode temp = head;
        while (temp != null) {
            if (set.contains(temp))
                return true;
            set.add(temp);
            temp = temp.next;
        }
        return false;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        ListNode slow = head, fast = head.next;
        while (fast != slow) {

            // if we reach the end of list
            // return false
            // else continue until fast!=slow
            // and return true
            if (fast == null || fast.next == null)
                return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        if (headA == headB)
            return headA;
        int lenA = 0;
        ListNode tempA = headA;
        while (tempA != null) {
            lenA += 1;
            tempA = tempA.next;
        }
        int lenB = 0;
        ListNode tempB = headB;
        while (tempB != null) {
            lenB += 1;
            tempB = tempB.next;
        }

        tempA = headA;
        tempB = headB;

        // give a headstart to the longer list
        // so that both pointers are at the same distance from the end(or intersection)
        if (lenA < lenB) {
            for (int i = 1; i <= lenB - lenA; i++) {
                tempB = tempB.next;
            }
        } else if (lenB < lenA) {
            for (int i = 1; i <= lenA - lenB; i++) {
                tempA = tempA.next;
            }
        }

        while (tempA != null && tempB != null) {
            if (tempA == tempB)
                return tempA;
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return null;
    }

    // Remove all elements from a linked list of integers that have value val.
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        ListNode prev = null, current = head, tempHead = head;
        while (current != null) {
            if (current.val == val) {
                // if current is head
                if (current == tempHead) {
                    tempHead = current.next;
                    current = current.next;
                    prev = null;
                }
                // if current is last node
                else if (current.next == null) {
                    if (prev != null) {
                        current = current.next;
                        prev.next = current;
                    }
                }
                // otherwise
                else {
                    current = current.next;
                    prev.next = current;
                }
            } else {
                prev = current;
                current = current.next;
            }
        }
        return tempHead;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null)
            return null;

        ListNode prev = null, current = head, temp = head.next;
        while (temp != null) {
            if (current == head) {
                current.next = prev;
                prev = current;
                current = temp;
                temp = temp.next;
            } else {
                current.next = prev;
                prev = current;
                current = temp;
                temp = temp.next;
            }
        }
        current.next = prev;
        return current;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode();
        System.out.println("Enter length of first list");
        int l1 = listNode.read.nextInt();
        // // System.out.println("Enter len of second list");
        // // int l2 = listNode.read.nextInt();
        ListNode list1 = listNode.createList(l1);
        listNode.printList(list1);
        // // ListNode list2 = listNode.createList(l2);
        // // listNode.printList(list2);
        // ListNode list3 = listNode.deleteDuplicates(list1);5
        // listNode.printList(list3);
        ListNode l = listNode.reverseList(list1);
        listNode.printList(l);
        listNode.read.close();

        // ListNode head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = head;
        // System.out.println(head.hasCycle(head));
    }
}
