import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

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

    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;
        if (head.next == null)
            return true;
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            length += 1;
        }
        System.out.println("length= " + length);
        temp = head;
        Stack<Integer> stack = new Stack<>();
        if (length % 2 == 0) {
            for (int i = 0; i < length; i++) {
                if (i < length / 2) {
                    System.out.println("ListNode.isPalindrome() index= " + i);
                    System.out.println("ListNode.isPalindrome() push to stack " + temp.val);
                    stack.push(temp.val);
                    temp = temp.next;
                } else {
                    System.out.println("ListNode.isPalindrome() index= " + i);
                    if (temp.val != stack.peek())
                        return false;
                    else {
                        temp = temp.next;
                        stack.pop();
                    }
                }
            }
            if (!stack.empty())
                return false;
            return true;
        } else {
            for (int i = 0; i < length; i++) {
                if (i < length / 2) {
                    System.out.println("ListNode.isPalindrome() index= " + i);
                    stack.push(temp.val);
                    System.out.println("ListNode.isPalindrome() push to stack " + temp.val);
                    temp = temp.next;
                } else {
                    System.out.println("ListNode.isPalindrome() index= " + i);
                    if (i == length / 2) {
                        temp = temp.next;
                        continue;
                    }
                    if (temp.val != stack.peek())
                        return false;
                    else {
                        temp = temp.next;
                        stack.pop();
                    }
                }
            }
            if (!stack.empty())
                return false;
            return true;
        }
    }

    public boolean isPalindrome2(ListNode head) {
        if (head == null)
            return true;
        if (head.next == null)
            return true;
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            length += 1;
        }
        System.out.println("length= " + length);
        temp = head;
        int[] arr = new int[length];
        int i = 0;
        while (temp != null) {
            arr[i] = temp.val;
            i += 1;
            temp = temp.next;
        }
        int start = 0, end = length - 1;
        while (start < end) {
            if (arr[start] == arr[end]) {
                start += 1;
                end -= 1;
            } else
                return false;
        }
        return true;
    }

    // Write a function to delete a node in a singly-linked list. You will not be
    // given access to the head of the list, instead you will be given access to the
    // node to be deleted directly.

    // It is guaranteed that the node to be deleted is not a tail node in the list.
    public void deleteNode(ListNode node) {
        // since this is not the last node
        // we can just copy the value of next node to this node
        // and point this node to where the next node is pointing
        // essentially removing the next node after this
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // You are given two non-empty linked lists representing two non-negative
    // integers. The digits are stored in reverse order, and each of their nodes
    // contains a single digit. Add the two numbers and return the sum as a linked
    // list.

    // You may assume the two numbers do not contain any leading zero, except the
    // number 0 itself.
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1, temp2 = l2;
        ListNode head = null, temp = null;
        int carry = 0;
        while (temp1 != null || temp2 != null) {

            int val1 = temp1 != null ? temp1.val : 0;
            int val2 = temp2 != null ? temp2.val : 0;
            int s = (val1 + val2 + carry) % 10;
            carry = (val1 + val2 + carry) / 10;
            if (head == null) {
                head = new ListNode(s);
                temp = head;
            } else {
                temp.next = new ListNode(s);
                temp = temp.next;
            }
            if (temp1 != null)
                temp1 = temp1.next;
            if (temp2 != null)
                temp2 = temp2.next;
        }
        if (carry != 0) {
            temp.next = new ListNode(carry);
            temp = temp.next;
        }
        return head;
    }

    // Given the head of a linked list, remove the nth node from the end of the list
    // and return its head.
    // Input: head = [1,2,3,4,5], n = 2
    // Output: [1,2,3,5]

    // Example 2:

    // Input: head = [1], n = 1
    // Output: []

    // Example 3:

    // Input: head = [1,2], n = 1
    // Output: [1]

    // Constraints:

    // The number of nodes in the list is sz.
    // 1 <= sz <= 30
    // 0 <= Node.val <= 100
    // 1 <= n <= sz
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // nth node from end => len-n+1 th node from front
        if (head == null)
            return null;
        ListNode temp = head, prev = null;
        int len = 0;
        while (temp != null) {
            temp = temp.next;
            len += 1;
        }
        System.out.println("length= " + len);

        // n from the end means first from the beginning
        if (len == n) {
            head = head.next;
            return head;
        }

        // make prev point to the predecessor of the node to be removed
        // temp points to the node that is to be removed
        temp = head;
        int index = 1;
        while (index != len - n + 1) {
            index += 1;
            prev = temp;
            temp = temp.next;
        }

        // final skip one node by pointing the prev node to the next of next node
        prev.next = temp.next;
        temp = null;
        return head;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode();
        System.out.println("Enter length of first list");
        int l1 = listNode.read.nextInt();
        ListNode list1 = listNode.createList(l1);
        listNode.printList(list1);
        // System.out.println("Enter len of second list");
        // int l2 = listNode.read.nextInt();
        // ListNode list2 = listNode.createList(l2);
        // listNode.printList(list2);
        // ListNode sum = listNode.addTwoNumbers(list1, list2);
        // listNode.printList(sum);
        list1 = listNode.removeNthFromEnd(list1, 1);
        listNode.printList(list1);
        listNode.read.close();
    }
}
