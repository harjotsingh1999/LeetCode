import java.util.Stack;

class MinStack {

    /** initialize your data structure here. */
    Stack<int[]> stack;
    int currentMin;

    // the first element of every stack item contains the value
    // and the second item contains the minimum value up to this point

    public MinStack() {
        stack = new Stack<int[]>();
        currentMin = Integer.MAX_VALUE;
    }

    public void push(int x) {
        int[] arr = new int[2];
        if (stack.empty()) {
            currentMin = x;
            arr[0] = x;
            arr[1] = currentMin;
            stack.push(arr);
        } else {
            if (x < currentMin)
                currentMin = x;
            arr[0] = x;
            arr[1] = currentMin;
            stack.push(arr);
        }
    }

    public void pop() {
        stack.pop();
        if (!stack.empty()) {
            currentMin = stack.peek()[1];
        } else {
            currentMin = Integer.MAX_VALUE;
        }
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return currentMin;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top()); // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}