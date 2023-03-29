package mylib.datastructures.Trees;
import mylib.datastructures.nodes.TNode;

import java.util.ArrayList;

public class BST {
    // 'root' references the root of the tree.
    TNode root;

    BST() {
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
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
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
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        return;
                    }
                }
            }

        }
    }

    public void delete(int val) {
        TNode possibleNode = search(val);  // may be null or contain a TNode object.
        if(possibleNode==null) {return;}
        if((possibleNode.getLeft() == null) && ((possibleNode.getRight()) == null)) { // if possibleNode has no kids
            if(possibleNode.getParent().getRight() == possibleNode) {
                possibleNode.getParent().setRight(null);
            } else {
                possibleNode.getParent().setLeft(null);
            }
            // If there is only one child.
        } else if (possibleNode.getLeft() != null && possibleNode.getRight() == null) {
            possibleNode.getParent().setLeft(possibleNode.getLeft());  // make the parent of deleted node's pointer point to only-child left
        } else if (possibleNode.getLeft() == null && possibleNode.getRight() != null) {
            possibleNode.getParent().setRight(possibleNode.getRight());  // make the parent of deleted node's pointer point to only-child left
        } else if (possibleNode.getLeft() != null && possibleNode.getRight() != null) {
            // need to figure this part out still...

        }

    }

    public TNode search(int val) {
        TNode current = root;
        while(current.getData() != val) {
            if(val < current.getData()) {
                current = current.left;
            }
            else {
                current = current.right;
            }
            if(current == null) {
                return null;
            }
        }
        return current;
    }


    public void inOrder(TNode current) {
        int ticker = 5;
        if(current==null){
            return;
        }
        inOrder(current.getLeft());
        System.out.printf("Node value %d : %d", ticker, current.getData());
        inOrder(current.getRight());
    }

    public void printInOrder() {
        // Thinking we can just implement a recursive approach to in-order traversing...
        inOrder(this.root);  // pass - in root node as an argument.
    }
    public void printBF() {
        ArrayList<TNode> intArray = new ArrayList<>();
        int ticker = 1;
        TNode current;
        intArray.add(this.root);
        while(!intArray.isEmpty()) {
            current = intArray.get(0);
            System.out.printf("The %d data item is: %d", ticker, current.getData());
            ticker++;
            if(current.getLeft() != null){
                intArray.remove(0);
                intArray.add(current.getLeft());
            }
            if(current.getRight() != null){
                intArray.remove(0);
                intArray.add(current.getRight());
            }
        }
    }
}
