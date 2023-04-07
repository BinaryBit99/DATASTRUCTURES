package mylib.datastructures.trees;
import mylib.datastructures.nodes.TNode;

import java.util.*;

/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.3
 * @since 1.0
 */

/**
 * BST is a binary search tree implementation. It is a tree data structure that is used to store data in a sorted manner.
 * It is a binary tree, meaning that each node has at most two children. The left child is always less than the parent node,
 * and the right child is always greater than or equal to the parent node.
 */
public class BST {
    // 'root' references the root of the tree.
    private TNode root;


    /**
     * Default BST constructor. Sets root to null.
     */
    public BST() {
        this.root = null;
    }

    /**
     * BST constructor that takes in an int as input. Sets root to a new TNode object with the data set to the input value.
     * @param val    the data to be stored in the root node.
     */
    public BST(int val) {
        this.root = new TNode();
        root.setData(val);  // ensure that setters are used in constructor to align with OOP principles.
    }

    /**
     * BST constructor that takes in a TNode as input. Sets root to the input value.
     * @param obj   the TNode object to be set as the root.
     */
    public BST(TNode obj) {
        this.root = obj;
    }

    /**
     * @param root The new root of the tree.
     */
    public void setRoot(TNode root) {
        this.root = root;
    }

    /**
     * @return  the root of the tree.
     */
    public TNode getRoot() {
        return this.root;
    }

    /**
     * Straight-forward implementation of an insert method.
     * @param val   the data to be inserted into the tree.
     */
    public void insert(int val) {
        TNode newNode = new TNode();
        newNode.setData(val);
        if (this.root == null) {
            this.root = newNode;
        } else {
            TNode current = root;
            TNode parent;
            while (true) {
                parent = current;
                if (val < current.getData()) {
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(newNode);
                        parent.getLeft().setParent(parent);
                        return;
                    }
                } else {
                    current = current.getRight();
                    if (current == null) {
                        parent.setRight(newNode);
                        parent.getRight().setParent(parent);
                        return;
                    }
                }
            }
        }
    }

    /** 
     * Same as above method except arg is of type 'TNode'.
     * @param node  the node to be inserted into the tree.
     */
    public void insert(TNode node) {
        if (this.root == null) {
            this.root = node;
        } else {
            TNode current = root;
            TNode parent;
            while (true) {
                parent = current;
                if (node.getData() < current.getData()) {
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(node);
                        return;
                    }
                } else {
                    current = current.getRight();
                    if (current == null) {
                        parent.setRight(node);
                        return;
                    }
                }
            }

        }
    }

    /** 
     * Helper function to get the minimum key 
     * @param current   the current node.
     * @return  the minimum key.
     */
    public TNode getMinKey(TNode current) {
        while(current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    /**
     * Deletes a node from the tree if it matches the input value. Else, it does nothing.
     * @param val   the value to be deleted from the tree.
     */
    public void delete(int val) {
        // If root is null, we can move on.
        if(this.root==null) {return;}
        // If the vertex we are deleting has no children, but has parents, then we set the parent's node pointers to null
        // Use other functions developed in the program to engage in efficiency.
        TNode deleteNode = search(val);
        TNode parent = deleteNode.getParent();


        //System.out.println(parent.getData());
        if(deleteNode.getLeft()==null && deleteNode.getRight()==null) {
            // We must determine if the node, deleteNode, is a left or right child of the parent node.
            if(parent.getLeft() == deleteNode) {
                // If the TNode object, deleteNode, has a parent, whereby the parent of this node's left-child is deleteNode itself, then
                // we set the 'left' TNode pointer attached to the parent node to equal null. All this applies in the case of the right node also for the parent.
                deleteNode.getParent().setLeft(null);
            }
            else { deleteNode.getParent().setRight(null); }

        } else if ( ! ((deleteNode.getLeft().equals(null)) && (deleteNode.getRight().equals(null))) ) {
            // fetch the in-order successor node
            TNode successor = getMinKey(deleteNode.getRight());
            int successorVal = successor.getData();
            delete(successor.getData());             // Recursively delete the successor.
            deleteNode.setData(successorVal);
        } else {
            TNode child = (deleteNode.getLeft() != null) ? deleteNode.getLeft() : deleteNode.getRight();
            if (deleteNode != this.root) {
                if (deleteNode.equals(deleteNode.getParent().getLeft())) {
                    deleteNode.getParent().setLeft(child);
                } else {
                    deleteNode.getParent().setRight(child);
                }
            } else {
                this.root = child;
            }
        }
    }

    /**
     * Searches for a node in the tree that matches the input value.
     * @param val   the value to be searched for in the tree.
     * @return  the node that matches the input value, or null if not found.
     */
    public TNode search(int val) {
        TNode current = this.root;
        while(current.getData() != val) {
            if(val < current.getData()) {
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }
            if(current == null) {
                return null;
            }
        }
        return current;
    }

    int mover = 1;
    /**
     * Recursive in-order traversal of the tree. Uses global variable 'mover' to keep track of the node number.
     * @param current   the current node.
     */
    public void inOrder(TNode current) {

        if(current==null){
            return;
        }
        inOrder(current.getLeft());
        System.out.printf("Node value for item #%d : %d\n", mover, current.getData());
        mover += 1;
        inOrder(current.getRight());

    }

    /**
     * Prints the tree in in-order traversal.
     */
    public void printInOrder() {
        // Thinking we can just implement a recursive approach to in-order traversing...
        System.out.println("\n----\n");
        inOrder(this.root);  // pass - in root node as an argument.
    }

    /**
     * Prints the tree in breadth-first traversal.
     */
    public void printBF() {
        System.out.println("\n----\n");
        LinkedList<TNode> queue = new LinkedList<>();
        queue.add(this.root);
        int ticker = 1;

        while(!queue.isEmpty()) {
            TNode current = queue.remove();
            System.out.printf("Data item #%d is: %d\n", ticker, current.getData());
            if(current.getLeft() != null){
                queue.add(current.getLeft());
            }
            if(current.getRight() != null){
                queue.add(current.getRight());
            }
            ticker++;
        }


    }
}