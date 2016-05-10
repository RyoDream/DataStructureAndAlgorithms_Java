package New_Chapter3;


public class Problem3_26 {
    int stackSize = 100;
    int[] buffer = new int[stackSize * 3];
    int[] stackPointer = {0, 0, 0};

    public void push(int stackNum, int value) {
        int index = stackNum * stackSize + stackPointer[stackNum] + 1;
        stackPointer[stackNum]++;
        buffer[index] = value;
    }

    public int pop(int stackNum) {
        int index = stackNum * stackSize + stackPointer[stackNum];
        stackPointer[stackNum]--;
        int value = buffer[index];
        buffer[index] = 0;
        return value;
    }

    int peek(int stackNum) {
        int index = stackNum * stackSize + stackPointer[stackNum];
        return buffer[index];
    }

    boolean isEmpty(int stackNum) {
        return stackPointer[stackNum] == stackNum * stackSize;
    }
}
