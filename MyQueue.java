import java.util.Stack;

class MyQueue {

    // Implement a first in first out (FIFO) queue using only two stacks. The
    // implemented queue should support all the functions of a normal queue (push,
    // peek, pop, and empty).

    // Implement the MyQueue class:

    // void push(int x) Pushes element x to the back of the queue.
    // int pop() Removes the element from the front of the queue and returns it.
    // int peek() Returns the element at the front of the queue.
    // boolean empty() Returns true if the queue is empty, false otherwise.

    // Notes:

    // You must use only standard operations of a stack, which means only push to
    // top, peek/pop from top, size, and is empty operations are valid.
    // Depending on your language, the stack may not be supported natively. You may
    // simulate a stack using a list or deque (double-ended queue) as long as you
    // use only a stack's standard operations.

    /** Initialize your data structure here. */

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (stack1.isEmpty()) {
            stack1.push(x);
        } else {
            stack2.clear();
            System.out.println("MyQueue.push() stack 1= " + stack1);
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            System.out.println("MyQueue.push() stack2= " + stack2);
            stack1.push(x);
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
            System.out.println("MyQueue.push() stack 1 again= " + stack1);
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack1.pop();
    }

    /** Get the front element. */
    public int peek() {
        return stack1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.empty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.push(3); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.peek()); // return 1
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.empty()); // return false
    }
}
