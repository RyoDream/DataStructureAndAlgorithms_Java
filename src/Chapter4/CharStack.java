package Chapter4;

public class CharStack {
    private int maxSize;
    private char[] stackArray;
    private int top;

    public CharStack(int s) {
        maxSize = s;
        stackArray = new char[maxSize];
        top = -1;
    }

    public void push(char i) {
        if(!isFull())
            stackArray[++top] = i;
    }

    public char pop() {
        // if top < 0, this method may cause error
        return stackArray[top--];
    }

    public char peak() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize-1);
    }

}

