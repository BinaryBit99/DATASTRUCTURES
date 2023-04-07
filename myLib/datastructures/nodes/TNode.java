package mylib.datastructures.nodes;

/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.1
 * @since 1.0
 */

/**
 * TNode is a node object that is used for our BST and AVL implementations.
 * It is a triply linked node, meaning it has three pointers, one that points to the left child, 
 * one that points to the right child, and one that points to the parent node.
 * It also has a balance factor that is used for AVL trees.
 */

public class TNode {
    // Data members below
    public int data;
    public TNode left;
    public TNode right;
    public TNode parent;
    public int balance;

    /**
     * Default TNode constructor. Sets pointers to null and data/balance to 0.
     */
    public TNode() {
        this.data = 0;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.balance = 0;
    }

    /**
     * 5-argument TNode constructor. Sets data, balance, and pointers to the values passed in.
     * @param data      the data to be stored in the node.
     * @param balance   the balance factor to be stored in the node.
     * @param P         the parent node.
     * @param L         the left child.
     * @param R         the right child.
     */
    public TNode(int data, int balance, TNode P, TNode L, TNode R) {
        this.data = data;
        this.balance = balance;
        this.parent = P;
        this.right = R;
        this.left = L;
    }

    // Getters and setters below

    /**
     * @return the data stored in the node.
     */
    public int getData() {
        return this.data;
    }

    /**
     * @return the left child of the node.
     */
    public TNode getLeft() {
        return this.left;
    }

    /**
     * @return the right child of the node.
     */
    public TNode getRight() {
        return this.right;
    }

    /**
     * @return  the parent of the node.
     */
    public TNode getParent() {
        return this.parent;
    }

    /**
     * @return  the balance factor of the node.
     */
    public int getBalance(){
        return this.balance;
    }

    /**
     * @param balance   the balance factor to be stored in the node.
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * @param data  the data to be stored in the node.
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * @param left the new left child of the node.
     */
    public void setLeft(TNode left) {
        this.left = left;
    }

    /**
     * @param parent    the new parent of the node.
     */
    public void setParent(TNode parent) {
        this.parent = parent;
    }

    /**
     * @param right the new right child of the node.
     */
    public void setRight(TNode right) {
        this.right = right;
    }

    /**
     * Prints the data and balance of the node.
     */
    public void printNode() {
        System.out.printf("The data for this tree node is: %d", this.getData());
        System.out.printf("The balance for this node is: %d", this.getBalance());
    }

    /**
     * @return  the data stored in the node as a string.
     */
    public String toString() {
        return String.valueOf(this.data);
    }
}