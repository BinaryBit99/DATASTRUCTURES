package myLib.datastructures.Trees;

import myLib.datastructures.nodes.TNode;

public class AVL extends BST {
    private TNode root;

    public static void main(String[] args) {
        AVL tree = new AVL();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.printBF();
        // tree.delete(5);
        // tree.printInOrder();
    }

    public AVL() {
        this.root = null;
    }

    public AVL(int val) {
        root.setData(val);
        root.setBalance(0);
    }

    public int height(TNode node) {
        if(node == null) {
            return 0;
        } else {
            return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        }
    }

    public insert(int val) {
        TNode newNode = new TNode();
        newNode.setData(val);
        newNode.setBalance(0);

        if (this.root == null) {
            this.root = newNode;
            return;
        }

        TNode current = this.root;
        TNode pivot = null;


        while(true) {
            pivot = current.getBalance() != 0 ? current : pivot;

            if(val < current.getData()) {
                if(current.getLeft() == null) {
                    current.setLeft(newNode);
                    newNode.setParent(current);
                    break;
                } else {
                    current = current.getLeft();
                }

            } else {
                if(current.getRight() == null) {
                    current.setRight(newNode);
                    newNode.setParent(current);
                    break;
                } else {
                    current = current.getRight();
                }
            }
        }

        //case 1: pivot is null
        if(pivot == null) {
            current = newNode;
            while(current != null) {
                current.setBalance(height(current.getRight()) - height(current.getLeft()));
                current = current.getParent();
            }
        }

        //case 2: pivot exists and the node is added to the shorter subtree
        else if(pivot.getBalance() == 1 && newNode.getData() < pivot.getData()) {
            current = newNode;
            while(current != pivot.getParent()) {
                current.setBalance(height(current.getRight()) - height(current.getLeft()));
                current = current.getParent();
            }
        } else if(pivot.getBalance() == -1 && newNode.getData() > pivot.getData()) {
            current = newNode;
            while(current != pivot.getParent()) {
                current.setBalance(height(current.getRight()) - height(current.getLeft()));
                current = current.getParent();
            }
        }



        // if(val < current.getData()) {
        //     current = current.getLeft();
        //     if(current == null) {
        //         current.setLeft(newNode);
        //         newNode.setParent(current);
        //         pivot = current;
        //     }
        // } else {
        //     current = current.getRight();
        //     if(current == null) {
        //         current.setRight(newNode);
        //         newNode.setParent(current);
        //         pivot = current;
        //     }
        // }

    


    }
}
