package Chapter4;

public class Stack {
    private int maxSize;
    private long[] stackArray;
    private int top;

    public Stack(int s) {
        maxSize = s;
        stackArray = new long[maxSize];
        top = -1;
    }

    public void push(long i) {
        if(!isFull())
            stackArray[++top] = i;
    }

    public long pop() {
        // if top < 0, this method may cause error
        return stackArray[top--];
    }

    public long peak() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize-1);
    }

    public static void main(String[] args) {
        Stack stack = new Stack(10);

        stack.push(100);
        stack.push(40);
        stack.push(30);
        stack.push(80);
        stack.push(10);
        stack.push(70);
        stack.push(20);

        while(!stack.isEmpty()) {
            long value = stack.pop();
            System.out.print(value+" ");
        }
        System.out.println(" ");
    }

}



