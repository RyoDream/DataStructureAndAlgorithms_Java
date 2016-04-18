package New_Chapter3;

import java.util.Stack;

public class Problem3_25 {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> min = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (min.isEmpty() || min.peek() >= x) // = means multiple minimum element
            min.push(x);
    }

    public void pop() {
        if (stack.peek().equals(min.peek())) {
            stack.pop();
            min.pop();
        } else
            stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
