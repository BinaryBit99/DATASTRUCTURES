package mylib.datastructures.nodes;

// * Making habit of frequent use of docstring in programs.
/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.0
 * @since 1.0
 */


public class TNode {
    // Data members below
    int data;
    TNode left;
    TNode right;
    TNode parent;
    int balance;

    // Default Constructor
    public TNode() {
        this.data = 0;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.balance = 0;
    }

    // Non-default constructor
    public TNode(int data, int balance, TNode P, TNode L, TNode R) {
        this.data = data;
        this.balance = balance;
        this.parent = P;
        this.right = R;
        this.left = L;
    }

    // Getters and setters below

    public int getData() {
        return this.data;
    }

    public TNode getLeft() {
        return this.left;
    }

    public TNode getRight() {
        return this.right;
    }
    public TNode getParent() {
        return this.parent;
    }

    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(TNode left) {
        this.left = left;
    }

    public void setParent(TNode parent) {
        this.parent = parent;
    }

    public void setRight(TNode right) {
        this.right = right;
    }

    public void print() {
        System.out.printf("The data for this tree node is: %d", this.getData());
        System.out.printf("The balance for this node is: %d", this.getBalance());
    }

    public String toString() {
        return String.valueOf(this.data);
    }
}
