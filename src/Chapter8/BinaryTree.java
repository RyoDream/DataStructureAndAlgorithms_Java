package Chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BinaryTree {
    private Node root;

    public BinaryTree() {
        root = null;
    }

    public Node find(int key) {
        Node current = root;

        while (current != null && current.iData != key) {
            if (key < current.iData)
                current = current.leftChild;
            else
                current = current.rightChild;
        }

        // if current == null, means didn't find the key
        // otherwise found the key
        return current;
    }

    public void insert(int value) {
        Node newNode = new Node(value);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent;

        while (true) {
            parent = current;

            if (value < current.iData) {
                current = current.leftChild;
                if (current == null) {
                    parent.leftChild = newNode;
                    return;
                }
            } else {
                current = current.rightChild;
                if (current == null) {
                    parent.rightChild = newNode;
                    return;
                }
            }
        }

    }

    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while (current != null && current.iData != key) {
            parent = current;
            if (key < current.iData) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
        }

        // if current == null, means didn't find the key
        // otherwise found the key
        if (current == null)
            return false;

        // target node has no children, simply delete it
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        }
        // if no right child, replace with left subtree
        else if (current.rightChild == null) {
            if (current == root)
                root = root.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        }
        // if no left child, replace with right subtree
        else if (current.leftChild == null) {
            if (current == root)
                root = root.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        }
        // target node has two children, so replace with inorder successor
        else {
            Node successor = getSuccessor(current);

            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;

            // connect successor to current's left child
            successor.leftChild = current.leftChild;
        }

        return true;
    }


    // 找到delNode的右子树的最左子节点作为successor
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        // 如果successor == delNode.rightChild
        // 此时successorParent == delNode
        // 则不需要经过下面代码（处理successor的右子树)时,否则会丢失delNode左子树
        if (successor != delNode.rightChild) {
            // successor的右子树连到successorParent的左边
            successorParent.leftChild = successor.rightChild;
            // 将delNode的右子树连到successor的右边
            successor.rightChild = delNode.rightChild;
        }

        return successor;
    }

    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.println("\n PreOrder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.println("\n InOrder traversal: ");
                inOrder(root);
                break;
            case 3:
                System.out.println("\n PostOrder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println("");
    }

    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    public void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    public void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }

    public Node minimum() {
        Node current = root;

        while (current != null && current.leftChild != null)
            current = current.leftChild;

        return current;
    }

    public Node maximum() {
        Node current = root;

        while (current != null && current.rightChild != null)
            current = current.rightChild;

        return current;
    }

    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        // 光标移动的初始值
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("**********************************************************************");

        // 如果下一层是空,则结束循环,整棵树均打印完成
        while (!isRowEmpty) {
            Stack localStack = new Stack();
            // 默认下一层是空
            isRowEmpty = true;

            for (int i = 0; i < nBlanks; i++)
                System.out.print(' ');

            // globalStack存储的是当前这一层的节点
            // 每次while循环,将打印当前层节点,并更新下一层节点到localStack中
            // 如果下一层localStack中有非null元素,则表示需要打印下一层,isRowEmpty=false
            // 如果该层节点均无子节点,则isRowEmpty=true,整棵树打印完成
            while (!globalStack.isEmpty()) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    // 该层节点有子节点,则下一层不为空
                    if (temp.leftChild != null || temp.rightChild != null)
                        isRowEmpty = false;
                } else {
                    // 为了打印出节点的对应位置,需要维护空节点的位置
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }

                // 移动光标位置,将要打印同一层的兄弟节点(父节点的右子节点)
                for (int i = 0; i < nBlanks * 2 - 2; i++)
                    System.out.print(' ');
            }

            // 打印完了本层节点,换行准备打印下一层节点
            System.out.println();
            // 减小光标移动的位置
            nBlanks /= 2;
            // 将下一层节点信息更新到globalStack中
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop());
        }
        System.out.println("**********************************************************************");
    }

    public static void main(String[] args) throws IOException {
        int value;
        BinaryTree binaryTree = new BinaryTree();


        binaryTree.insert(50);
        binaryTree.insert(25);
        binaryTree.insert(75);
        binaryTree.insert(12);
        binaryTree.insert(37);
        binaryTree.insert(43);
        binaryTree.insert(30);
        binaryTree.insert(43);
        binaryTree.insert(30);
        binaryTree.insert(33);
        binaryTree.insert(87);
        binaryTree.insert(93);
        binaryTree.insert(97);


        while (true) {
            System.out.print("Enter first letter of show, ");
            System.out.print("insert, find, delete, or traverse: ");
            int choice = getChar();

            switch (choice) {
                case 's':
                    binaryTree.displayTree();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    binaryTree.insert(value);
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = getInt();
                    Node found = binaryTree.find(value);
                    if (found != null) {
                        System.out.print("Found: ");
                        found.displayNode();
                        System.out.println("");
                    } else
                        System.out.println("Could not find " + value);
                    break;
                case 'd':
                    System.out.print("Enter value to delete: ");
                    value = getInt();
                    boolean didDelete = binaryTree.delete(value);
                    if (didDelete)
                        System.out.println("Deleted " + value);
                    else
                        System.out.println("Could not delete " + value);
                    break;
                case 't':
                    System.out.print("Enter type 1,2 or 3: ");
                    value = getInt();
                    binaryTree.traverse(value);
                    break;
                default:
                    System.out.println("Invalid entry.");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}


