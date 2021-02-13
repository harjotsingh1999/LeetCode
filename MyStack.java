import java.util.LinkedList;
import java.util.Queue;

// Implement a last in first out (LIFO) stack using only two queues.
// The implemented stack should support all the functions of a normal queue (push, top, pop, and empty).

// Implement the MyStack class:

//     void push(int x) Pushes element x to the top of the stack.
//     int pop() Removes the element on the top of the stack and returns it.
//     int top() Returns the element on the top of the stack.
//     boolean empty() Returns true if the stack is empty, false otherwise.

// Notes:
//     You must use only standard operations of a queue, which means
//     only push to back, peek/pop from front, size, and is empty operations are valid.
//     Depending on your language, the queue may not be supported natively.
//     You may simulate a queue using a list or deque (double-ended queue), as long as you use only a queue's standard operations

class MyStack {

    /** Initialize your data structure here. */
    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<Integer>();
        q2 = new LinkedList<Integer>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        if (q1.isEmpty()) {
            q1.offer(x);
        } else {
            q2.clear();
            q2.addAll(q1);
            q1.clear();
            q1.offer(x);
            q1.addAll(q2);
            q2.clear();
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q1.poll();
    }

    /** Get the top element. */
    public int top() {
        return q1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // return 2
        System.out.println(myStack.pop()); // return 2
        System.out.println(myStack.empty()); // return False
    }
}