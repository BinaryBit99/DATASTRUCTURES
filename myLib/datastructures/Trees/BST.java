package mylib.datastructures.trees;
import mylib.datastructures.nodes.TNode;

import java.util.*;

public class BST {
    // 'root' references the root of the tree.
    TNode root;



    public BST() {
        this.root = null;
    }

    BST(int val) {
        root.setData(val);  // ensure that setters are used in constructor to align with OOP principles.
    }

    BST(TNode obj) {
        this.root = obj;
    }

    public void setRoot(TNode root) {
        this.root = root;
    }

    public TNode getRoot() {
        return this.root;
    }

    // Straight-forward implementation of an insert method.
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

    // Same as above method except arg is of type 'TNode'.
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

    /** Helper function to get the minimum key **/
    public TNode getMinKey(TNode current) {
        while(current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

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
    public void inOrder(TNode current) {

        if(current==null){
            return;
        }
        inOrder(current.getLeft());
        System.out.printf("Node value for item #%d : %d\n", mover, current.getData());
        mover += 1;
        inOrder(current.getRight());

    }

    public void printInOrder() {
        // Thinking we can just implement a recursive approach to in-order traversing...
        System.out.println("\n----\n");
        inOrder(this.root);  // pass - in root node as an argument.
    }
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