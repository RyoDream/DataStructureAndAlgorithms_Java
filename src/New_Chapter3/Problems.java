package New_Chapter3;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Problems {
    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }
    }

    // 3_1
    public static <AnyType> void printLots(List<AnyType> L, List<Integer> P) {
        Iterator<AnyType> iterL = L.iterator();
        Iterator<Integer> iterP = P.iterator();

        AnyType itemL = null;
        Integer itemP = 0;
        int start = 0;

        while (iterL.hasNext() && iterP.hasNext()) {
            itemP = iterP.next();

            while (start < itemP && iterL.hasNext()) {
                start++;
                itemL = iterL.next();
            }
            System.out.println(itemL);
        }
    }

    // 3_2_a. for singly linked lists:
    // beforep is the cell before the two adjacent cells that are to be swapped.
    // error checks are omitted for clarity.
    public static void swapWithNext1(Node beforep) {
        Node p, afterp;

        p = beforep.next;
        afterp = p.next;

        beforep.next = afterp;
        p.next = afterp.next;
        afterp.next = p;
    }

    // 3_2_a. for doubly linked lists:
    // p and afterp are cells to be swapped.
    // error checks are omitted for clarity.
    public static void swapWithNext2(Node p) {
        Node beforep, afterp;

        beforep = p.prev;
        afterp = p.next;

        beforep.next = afterp;
        p.next = afterp.next;

        afterp.next = p;
        p.next.prev = p;

        p.prev = afterp;
        afterp.prev = beforep;
    }

    // 3_4
    public static <AnyTpye extends Comparable<? super AnyTpye>>
    void intersection(List<AnyTpye> L1, List<AnyTpye> L2, List<AnyTpye> Intersect) {

        ListIterator<AnyTpye> iterL1 = L1.listIterator();
        ListIterator<AnyTpye> iterL2 = L2.listIterator();

        AnyTpye itemL1 = null, itemL2 = null;

        if (iterL1.hasNext() && iterL2.hasNext()) {
            itemL1 = iterL1.next();
            itemL2 = iterL2.next();
        }

        while (itemL1 != null && itemL2 != null) {
            int compareResult = itemL1.compareTo(itemL2);

            if (compareResult == 0) {
                Intersect.add(itemL1);
                itemL1 = iterL1.hasNext() ? iterL1.next() : null;
                itemL2 = iterL2.hasNext() ? iterL2.next() : null;
            } else if (compareResult < 0) {
                itemL1 = iterL1.hasNext() ? iterL1.next() : null;
            } else
                itemL2 = iterL2.hasNext() ? iterL2.next() : null;
        }
    }

    // 3_5
    public static <AnyType extends Comparable<? super AnyType>>
    void union(List<AnyType> L1, List<AnyType> L2, List<AnyType> Result) {

        ListIterator<AnyType> iterL1 = L1.listIterator();
        ListIterator<AnyType> iterL2 = L2.listIterator();

        AnyType itemL1 = null, itemL2 = null;

        if (iterL1.hasNext() && iterL2.hasNext()) {
            itemL1 = iterL1.next();
            itemL2 = iterL2.next();
        }

        while (itemL1 != null && itemL2 != null) {
            int CompareResult = itemL1.compareTo(itemL2);

            if (CompareResult == 0) {
                Result.add(itemL1);
                itemL1 = iterL1.hasNext() ? iterL1.next() : null;
                itemL2 = iterL2.hasNext() ? iterL2.next() : null;
            } else if (CompareResult < 0) {
                Result.add(itemL1);
                itemL1 = iterL1.hasNext() ? iterL1.next() : null;
            } else {
                Result.add(itemL2);
                itemL2 = iterL2.hasNext() ? iterL2.next() : null;
            }
        }
    }
}
