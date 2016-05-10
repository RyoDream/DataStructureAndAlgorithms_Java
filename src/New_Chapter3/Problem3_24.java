package New_Chapter3;

public class Problem3_24 {

    private int stackSize = 10;
    private int base1;
    private int base2;
    private Object[] obj = null;

    public Problem3_24() {
        obj = new Object[stackSize];
        base1 = 0;
        base2 = stackSize - 1;
    }

    public Problem3_24(int maxSize) {
        obj = new Object[maxSize];
        base1 = 0;
        base2 = maxSize - 1;
    }

    public boolean isEmpty(int flag) {
        if (flag == 0) {
            return base1 == 0;
        } else {
            return base2 == obj.length - 1;
        }
    }

    public boolean isFull() {
        return (base2 - base1) == 1;
    }

    public void push(Object o, int flag) {
        if (isFull()) {
            try {
                throw new Exception("栈已满");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        if (flag == 0) {
            obj[base1++] = o;
        } else if (flag == 1) {
            obj[base2--] = o;
        }
    }

    public Object pop(int flag) {
        if (isEmpty(flag)) {
            try {
                throw new Exception("栈空了");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (flag == 0) {
            return obj[--base1];
        } else {
            return obj[++base2];
        }
    }
}
