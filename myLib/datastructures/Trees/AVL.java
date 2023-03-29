package mylib.datastructures.Trees;

import mylib.datastructures.nodes.TNode;
import java.util.*;

public class AVL extends BST {
    private TNode root;

    public static void main(String[] args) {
        AVL tree = new AVL();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(6);
        tree.insert(2);
        tree.insert(8);
        tree.insert(4);
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

    @Override
    public void printBF() {
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

    @Override
    public void insert(int val) {
        TNode newNode = new TNode();
        newNode.setData(val);
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

            if(val < current.getData()) {
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

        TNode ancestor = pivot.getParent();
        TNode grandparent = ancestor.getParent();

        if(pivot.getBalance() == 1 && newNode.getData() > pivot.getData()) {
            //left rotation
            

        } 
        
        else if(pivot.getBalance() == -1 && newNode.getData() < pivot.getData()) {

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
