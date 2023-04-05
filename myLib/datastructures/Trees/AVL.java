package mylib.datastructures.Trees;

import mylib.datastructures.nodes.TNode;
import java.util.*;

public class AVL extends BST {
    private TNode root;

    public static void main(String[] args) {



        // make a tree with 10 as the root, insert 20 new nodes with random values
        // between 0 and 40, print the tree, then print the height of the tree

        AVL tree = new AVL(10);
        Random rand = new Random();
        for(int i = 0; i < 10; i++) {
            int val = rand.nextInt(40);
            System.out.println("inserting " + val + " into tree");
            tree.insert(val);
        }


        tree.printBF();
        System.out.println("Height of tree is: " + tree.height(tree.root));
    }

    public AVL() {
        this.root = null;
    }

    public AVL(int val) {
        this.root = new TNode();
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

    private void balanceAdjuster() {
        LinkedList<TNode> queue = new LinkedList<>();
        queue.add(this.root);

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

            if(val <= current.getData()) {
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
        System.out.println(newNode.getData());
        System.out.println(current.getData());
        //case 1: pivot is null
        if(pivot == null) {
            System.out.println("case 1: pivot is null");
            this.balanceAdjuster();

            // current = newNode;
            // while(current != null) {
            //     current.setBalance(height(current.getRight()) - height(current.getLeft()));

            //     System.out.println("current data is: " + current.getData() + " and balance is: " + current.getBalance());
            //     if(current.getParent() != null) {
            //         System.out.println("parent data is: " + current.getParent().getData() + " and balance is: " + current.getParent().getBalance());
            //     }

            //     current = current.getParent();

            // }
            System.out.println("inserted " + val);
            return;
        }

        //case 2: pivot exists and the node is added to the shorter subtree
        else if(pivot.getBalance() == 1 && newNode.getData() <= pivot.getData()) {
            System.out.println("case 2a");

            this.balanceAdjuster();
            // current = newNode;
            // while(current != pivot.getParent()) {
            //     current.setBalance(height(current.getRight()) - height(current.getLeft()));
            //     current = current.getParent();
            // }
            System.out.println("inserted " + val);
            return;
        }
        else if(pivot.getBalance() == -1 && newNode.getData() > pivot.getData()) {
            System.out.println("case 2b");
            this.balanceAdjuster();
            // current = newNode;
            // while(current != pivot.getParent()) {
            //     current.setBalance(height(current.getRight()) - height(current.getLeft()));
            //     current = current.getParent();
            // }
            System.out.println("inserted " + val);
            return;
        }

        TNode ancestor = pivot.getParent();

        // case 3 : adding to the longer subtree
        // 3a -> adding to the outer longer subtree of the pivot node.
        // 3b -> adding to the inner longer subtree of the pivot node
        if(pivot.getBalance() == 1 && newNode.getData() > pivot.getData()) {
            if(newNode.getData() > son.getData()) {                             // adding to the very end long subtree...
                System.out.println("case 3a positive pivot");
                //left rotation
                leftRotation(ancestor, pivot, son);

                this.balanceAdjuster();
                // pivot.setBalance(0);

                // TNode balanceAdjuster = newNode.getParent();
                // while (balanceAdjuster != son) {
                //     balanceAdjuster.setBalance(height(balanceAdjuster.getRight()) - height(balanceAdjuster.getLeft()));
                //     balanceAdjuster = balanceAdjuster.getParent();
                // }
                //write helper methods for left & right rotation with specific nodes as arguments
            }
            else {
                //RL rotation
                System.out.println("case 3b positive pivot");

                TNode grandSon = son.getLeft();
                rlRotation(ancestor, pivot, son, grandSon);

                this.balanceAdjuster();

                // if(newNode.getData() > grandSon.getData()) {
                //     pivot.setBalance(-1);
                // }
                // else {
                //     pivot.setBalance(0);
                //     son.setBalance(1);
                // }

                // TNode balanceAdjuster = newNode.getParent();
                // while(balanceAdjuster != son && balanceAdjuster != pivot) {
                //     balanceAdjuster.setBalance(height(balanceAdjuster.getRight()) - height(balanceAdjuster.getLeft()));
                //     balanceAdjuster = balanceAdjuster.getParent();
                // }

            }


        }

        else if(pivot.getBalance() == -1 && newNode.getData() <= pivot.getData()) {
            if(newNode.getData() <= son.getData()) {
                System.out.println("case 3a negative pivot");
                //right rotation
                rightRotation(ancestor, pivot, son);

                this.balanceAdjuster();

                // pivot.setBalance(0);

                // TNode balanceAdjuster = newNode.getParent();
                // while (balanceAdjuster != son) {
                //     balanceAdjuster.setBalance(height(balanceAdjuster.getRight()) - height(balanceAdjuster.getLeft()));
                //     balanceAdjuster = balanceAdjuster.getParent();
                // }
            }
            else {
                //LR rotation
                System.out.println("case 3b negative pivot");
                System.out.println(son); // checker
                TNode grandSon = son.getRight();
                lrRotation(ancestor, pivot, son, grandSon);

                this.balanceAdjuster();

                // if(newNode.getData() <= grandSon.getData()) {
                //     pivot.setBalance(1);
                // }
                // else {
                //     pivot.setBalance(0);
                //     son.setBalance(-1);
                // }

                // TNode balanceAdjuster = newNode.getParent();
                // while(balanceAdjuster != son && balanceAdjuster != pivot) {
                //     balanceAdjuster.setBalance(height(balanceAdjuster.getRight()) - height(balanceAdjuster.getLeft()));
                //     balanceAdjuster = balanceAdjuster.getParent();
                // }
            }
        }
        System.out.println("inserted " + val);
    }

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
        else if(pivot.getData() > ancestor.getData()) {
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

    private void lrRotation(TNode ancestor, TNode pivot, TNode son, TNode grandSon) {
        //Right rotation around grandson
        pivot.setLeft(grandSon);
        grandSon.setParent(pivot);

        son.setRight(grandSon.getLeft());
        if(grandSon.getLeft() != null) {
            grandSon.getLeft().setParent(son);
        }

        grandSon.setLeft(son);
        son.setParent(grandSon);

        //left rotation through pivot(around grandson in its new position)
        if (ancestor == null) {
            this.root = grandSon;
            grandSon.setParent(null);
        }
        else if(pivot.getData() <= ancestor.getData()) {
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

    private void leftRotation(TNode ancestor, TNode pivot, TNode son ) {
        // TNode temp = pivot;
        if(this.root == pivot && son.getLeft() == null && pivot.getLeft() == null) {
            this.root = son;
            pivot.setParent(son);
            pivot.setRight(null);
            son.setParent(null);
            son.setLeft(pivot);
        }

        else {
            if(pivot != this.root) {
                ancestor.setRight(son);
                son.setParent(ancestor);
            }
            pivot.setRight(son.getLeft());
            if(son.getLeft() != null) {
                son.getLeft().setParent(pivot);
            }
            son.setLeft(pivot);
            pivot.setParent(son);
        }

    }

    private void rightRotation(TNode ancestor, TNode pivot, TNode son) {
        if(this.root == pivot && son.getRight() == null && pivot.getRight() == null) {
            this.root = son;
            pivot.setParent(son);
            pivot.setLeft(null);
            son.setParent(null);
            son.setRight(pivot);
        }
        else {
            if(pivot != this.root) {
                ancestor.setLeft(son);
                son.setParent(ancestor);
            }
            pivot.setLeft(son.getRight());
            if(son.getRight() != null) {
                son.getRight().setParent(pivot);
            }

            son.setRight(pivot);
            pivot.setParent(son);
        }

    }
}
