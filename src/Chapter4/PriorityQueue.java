package Chapter4;

public class PriorityQueue {
    // array in sorted order, from max at 0 to min at size-1

    private int maxSize;
    private long[] queArray;
    private int nItems;

    public PriorityQueue(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        nItems = 0;
    }

    public void insert(long item) {
        int i;

        if(isFull()) {
            System.out.println("Queue is Full. Failed to insert "+item+".");
            return;
        }

        if(nItems==0)
            queArray[nItems++] = item;
        else {
            for(i=nItems-1;i>=0;i--) {
                if(item > queArray[i])
                    queArray[i+1] = queArray[i];
                else
                    break;
            }
            queArray[i+1] = item;
            nItems++;
        }
    }

    public long remove() {
        if(!isEmpty())
            return queArray[--nItems];
        else {
            System.out.println("Queue is Empty. Fail to remove");
            return -1;
        }
    }

    public long peekMin() {
        return queArray[nItems-1];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(5);
        priorityQueue.insert(30);
        priorityQueue.insert(50);
        priorityQueue.insert(10);
        priorityQueue.insert(40);
        priorityQueue.insert(20);

        while(!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.remove() + " ");
        }
        System.out.println("");
    }
}
