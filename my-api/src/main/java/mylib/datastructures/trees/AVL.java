package mylib.datastructures.trees;

import mylib.datastructures.nodes.TNode;
import java.util.*;

/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.5
 * @since 1.0
 */

/**
 * AVL is a self-balancing binary search tree that is used to store data in a sorted order.
 * It is a subclass of BST. The balance must always be either -1, 0, or 1.
 */
public class AVL extends BST {
    private TNode root;


    /**
     * @return the root node of the AVL tree.
     */
    public TNode getRootNode() {
        return this.root;
    }

    /**
     * Sets the root node of the AVL tree.
     * @param incoming  The node that will be the new root.
     */
    public void setRootNode(TNode incoming) {
        this.root = incoming;
        this.root.setParent(null);
        //change this
    }

    /**
     * Default AVL constructor. Sets the root node to null.
     */
    public AVL() {
        this.setRootNode(null);
    }

    /**
     * 1-argument AVL constructor. Sets the root node to a new node with the data passed in.
     * @param val   the data to be stored in the root node.
     */
    public AVL(int val) {
        this.setRootNode(new TNode());
        this.root.setData(val);
        this.root.setBalance(0);
    }

    /**
     * 1-argument AVL constructor. Sets the root node to the node passed in.
     * @param obj    the node that will be the root node.
     */
    public AVL(TNode obj) {
        this.setRootNode(obj);
    }


    /**
     * Determines the height of the tree/subtree rooted at the node passed in.
     * @param node  the node that is the root of the tree/subtree.
     * @return    the height of the tree/subtree.
     */
    public int height(TNode node) {
        if(node == null) {
            return 0;
        } else {
            return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        }
    }

    /**
     * Prints each node in the tree in breadth-first order.
     */
    @Override
    public void printBF() {
        LinkedList<TNode> queue = new LinkedList<>();
        queue.add(this.getRootNode());
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

    /**
     * Goes through the tree in a breadth-first order and adjusts the balance of each node.
     */
    private void balanceAdjuster() {
        LinkedList<TNode> queue = new LinkedList<>();
        queue.add(this.getRootNode());

        while (!queue.isEmpty()) {
            TNode current = queue.remove();
            current.setBalance(height(current.getRight()) - height(current.getLeft()));
            if(current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if(current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }

    /**
     * Searches for the node with the same data as the value passed in.
     * @param val   the value to be searched for.
     * @return      the node with the same data as the value passed in.
     */
    @Override
    public TNode search(int val) {
        LinkedList<TNode> queue = new LinkedList<>();
        queue.add(this.getRootNode());

        while (!queue.isEmpty() && queue.peek().getData() != val) {
            TNode current = queue.remove();

            if(current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if(current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
        if(queue.isEmpty()) {
            return null;
        } else {
            return queue.remove();
        }
    }


    /**
     * deletes the node with the same data as the value passed in.
     * @param val   the value to be deleted.
     */
    @Override
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
        this.balanceAdjuster();         // re-balances the tree after deletion.
    }

    /**
     * Inserts a node into the tree. Has an integer parameter. This method creates a new TNode object and 
     * calls the other insert method with the new TNode object as a parameter.
     */
    public void insert(int val) {
        TNode newNode = new TNode();
        newNode.setData(val);

        this.insert(newNode);
    }

    /**
     * Performs an RL rotation on the specified nodes.
     * @param ancestor  The parent of the pivot node.
     * @param pivot     The unbalanced node closest to the newly inserted node.
     * @param son       The child of the pivot node on the same side as the grandson.
     * @param grandSon  The child of the son node on the same path as the newly inserted node.
     */
    private void rlRotation(TNode ancestor, TNode pivot, TNode son, TNode grandSon ) {
        //Right rotation around grandson
        pivot.setRight(grandSon);
        grandSon.setParent(pivot);

        son.setLeft(grandSon.getRight());
        if(grandSon.getRight() != null) {
            grandSon.getRight().setParent(son);
        }

        grandSon.setRight(son);
        son.setParent(grandSon);

        //left rotation through pivot(around grandson in its new position)
        if (ancestor == null) {
            this.root = grandSon;
            grandSon.setParent(null);
        }
        else if(ancestor.getRight() == pivot) {
            ancestor.setRight(grandSon);
            grandSon.setParent(ancestor);
        }
        else {
            ancestor.setLeft(grandSon);
            grandSon.setParent(ancestor);
        }

        pivot.setRight(grandSon.getLeft());
        if(grandSon.getLeft() != null) {
            grandSon.getLeft().setParent(pivot);
        }

        grandSon.setLeft(pivot);
        pivot.setParent(grandSon);
    }

    /**
     * Performs an LR rotation on the specified nodes.
     * @param ancestor  The parent of the pivot node.
     * @param pivot     The unbalanced node closest to the newly inserted node.
     * @param son       The child of the pivot node on the same side as the grandson.
     * @param grandSon  The child of the son node on the same path as the newly inserted node.
     */
    private void lrRotation(TNode ancestor, TNode pivot, TNode son, TNode grandSon) {
        //Left rotation around grandson
        pivot.setLeft(grandSon);
        grandSon.setParent(pivot);

        son.setRight(grandSon.getLeft());
        if(grandSon.getLeft() != null) {
            grandSon.getLeft().setParent(son);
        }

        grandSon.setLeft(son);
        son.setParent(grandSon);

        //Right rotation through pivot(around grandson in its new position)
        if (ancestor == null) {
            this.root = grandSon;
            grandSon.setParent(null);
        }
        else if(ancestor.getLeft() == pivot) {
            ancestor.setLeft(grandSon);
            grandSon.setParent(ancestor);
        }
        else {
            ancestor.setRight(grandSon);
            grandSon.setParent(ancestor);
        }

        pivot.setLeft(grandSon.getRight());
        if(grandSon.getRight() != null) {
            grandSon.getRight().setParent(pivot);
        }

        grandSon.setRight(pivot);
        pivot.setParent(grandSon);
    }

    /**
     * Performs a left rotation on the specified nodes.
     * @param ancestor  The parent of the pivot node.
     * @param pivot     The unbalanced node closest to the newly inserted node.
     * @param son       The child of the pivot node on the same path as the newly inserted node.
     */
    private void leftRotation(TNode ancestor, TNode pivot, TNode son ) {

        if(pivot != this.root) {
            if(ancestor.getLeft() == pivot) {
                ancestor.setLeft(son);
                son.setParent(ancestor);
            }
            else {
                ancestor.setRight(son);
                son.setParent(ancestor);
            }
        }
        pivot.setRight(son.getLeft());
        if(son.getLeft() != null) {
            son.getLeft().setParent(pivot);
        }

        son.setLeft(pivot);
        pivot.setParent(son);

        if(this.root == pivot) {
            this.root = son;
        }

        son.setParent(ancestor);
    }

    /**
     * Performs a right rotation on the specified nodes.
     * @param ancestor  The parent of the pivot node.
     * @param pivot     The unbalanced node closest to the newly inserted node.
     * @param son       The child of the pivot node on the same path as the newly inserted node.
     */
    private void rightRotation(TNode ancestor, TNode pivot, TNode son) {

        if(pivot != this.root) {
            if(ancestor.getRight() == pivot) {
                ancestor.setRight(son);
                son.setParent(ancestor);
            }
            else {
                ancestor.setLeft(son);
                son.setParent(ancestor);
            }
        }
        pivot.setLeft(son.getRight());
        if(son.getRight() != null) {
            son.getRight().setParent(pivot);
        }

        son.setRight(pivot);
        pivot.setParent(son);

        if(this.root == pivot) {
            this.root = son;
        }

        son.setParent(ancestor);
    }

    /**
     * Inserts a new node into the tree. There are 4 cases:
     * 1. The pivot is null. The new node is inserted and balances are updated.
     * 2. The new node is added to the shorter subtree of the pivot. The new node is inserted and balances are updated.
     * 3. The new node is added to the longer subtree of the pivot. Rotations are performed and balances are updated.
     * 4. The tree is empty. The new node becomes the root.
     */
    @Override
    public void insert(TNode newNode) {

        newNode.setBalance(0);

        if (this.root == null) {
            this.root = newNode;
            return;
        }

        TNode current = this.root;
        TNode pivot = null;
        boolean foundPivot = false;

        TNode son = null;


        while(true) {
            if(current.getBalance() != 0) {
                pivot = current;
                foundPivot = true;
            }

            if(newNode.getData() <= current.getData()) {
                if(current.getLeft() == null) {
                    current.setLeft(newNode);
                    newNode.setParent(current);
                    break;
                } else {
                    current = current.getLeft();
                    son = foundPivot ? pivot.getLeft() : son;
                }

            } else {
                if(current.getRight() == null) {
                    current.setRight(newNode);
                    newNode.setParent(current);
                    break;
                } else {
                    current = current.getRight();
                    son = foundPivot ? pivot.getRight() : son;
                }
            }
            foundPivot = false;
        }

        //case 1: pivot is null
        if(pivot == null) {
            // System.out.println("case 1: pivot is null");
            this.balanceAdjuster();

            // System.out.println("inserted " + val);
            // return;
        }

        //case 2: pivot exists and the node is added to the shorter subtree
        //if pivot.getBalance() == 1
        else if(pivot.getBalance() == 1 /* && newNode.getData() <= pivot.getData() */) {
            TNode ancestor = pivot.getParent();

            // case 2
            if(newNode.getData() <= pivot.getData()) {
                // System.out.println("case 2 positive pivot");
                this.balanceAdjuster();
                // System.out.println("inserted " + val);
                // return;
            }
            else {
                //case 3a
                if(newNode.getData() > son.getData()) {
                    // System.out.println("case 3a positive pivot");

                    leftRotation(ancestor, pivot, son);

                    this.balanceAdjuster();
                }
                else {
                    //case 3b
                    // System.out.println("case 3b positive pivot");

                    TNode grandSon = son.getLeft();
                    rlRotation(ancestor, pivot, son, grandSon);

                    this.balanceAdjuster();
                }
            }
        }
        else if (pivot.getBalance() == -1) {
            TNode ancestor = pivot.getParent();
            // case 2
            if(newNode.getData() > pivot.getData()) {
                // System.out.println("case 2 negative pivot");
                this.balanceAdjuster();
                // System.out.println("inserted " + val);
                // return;
            }
            else {
                //case 3a
                if(newNode.getData() <= son.getData()) {
                    // System.out.println("case 3a negative pivot");
                    //right rotation
                    rightRotation(ancestor, pivot, son);

                    this.balanceAdjuster();
                }
                else {
                    // case 3b
                    // System.out.println("case 3b negative pivot");

                    TNode grandSon = son.getRight();
                    lrRotation(ancestor, pivot, son, grandSon);

                    this.balanceAdjuster();

                }
            }

        }
    }
}