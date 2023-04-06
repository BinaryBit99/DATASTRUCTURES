package mylib.datastructures.Trees;

import mylib.datastructures.nodes.TNode;
import java.util.*;

public class AVL extends BST {
    private TNode root;

    public static void main(String[] args) {
        // AVL tree = new AVL();
        // tree.insert(5);
        // tree.insert(3);
        // tree.insert(7);
        // tree.insert(6);
        // tree.insert(2);
        // tree.insert(8);
        // tree.insert(4);
        // tree.insert(20);
        // tree.printBF();
        // tree.insert(21);
        // tree.printBF();

        // make a tree with 10 as the root, insert 20 new nodes with random values
        // between 0 and 40, print the tree, then print the height of the tree

        // System.out.println("inserting " + 18 + " into tree");
        // tree.insert(18);
        // tree.printBF();
        // System.out.println("inserting " + 36 + " into tree");
        // tree.insert(36);
        // tree.printBF();
        // System.out.println("inserting " + 15 + " into tree");
        // tree.insert(15);
        // tree.printBF();
        // System.out.println("inserting " + 8 + " into tree");
        // tree.insert(8);
        // tree.printBF();
        // System.out.println("inserting " + 22 + " into tree");
        // tree.insert(22);
        // tree.printBF();
        // System.out.println("inserting " + 33 + " into tree");
        // tree.insert(33);
        // tree.printBF();
        // System.out.println("inserting " + 9 + " into tree");
        // tree.insert(9);
        // tree.printBF();
        // System.out.println("inserting " + 24 + " into tree");
        // tree.insert(24);
        // tree.printBF();
        // System.out.println("inserting " + 33 + " into tree");
        // tree.insert(33);
        // tree.printBF();
        // System.out.println("inserting " + 28 + " into tree");
        // tree.insert(28);
        // tree.printBF();
        // System.out.println("inserting " + 28 + " into tree");
        // tree.insert(28);
        // tree.printBF();
        // System.out.println("inserting " + 14 + " into tree");
        // tree.insert(14);
        // tree.printBF();
        // System.out.println("inserting " + 4 + " into tree");
        // tree.insert(4);
        // System.out.println("inserting " + 15 + " into tree");
        // tree.insert(15);
        // tree.printBF();
        // System.out.println("inserting " + 23 + " into tree");
        // tree.insert(23);
        // tree.printBF();
        // System.out.println("inserting " + 2 + " into tree");
        // tree.insert(2);
        // tree.printBF();
        // System.out.println("inserting " + 15 + " into tree");
        // tree.insert(15);
        // tree.printBF();
        // System.out.println("inserting " + 14 + " into tree");
        // tree.insert(14);
        // tree.printBF();
        // System.out.println("inserting " + 39 + " into tree");
        // tree.insert(39);
        // tree.printBF();
        // System.out.println("inserting " + 5 + " into tree");
        // tree.insert(5);


        Random rand = new Random();
        for(int j = 0; j < 10; j++) {
            AVL tree = new AVL(10);
            for(int i = 0; i < 10; i++) {
                int val = rand.nextInt(40);
                // System.out.println("inserting " + val + " into tree");
                tree.insert(val);
                TNode newTNode = new TNode();
                newTNode.setData(val+1);
                tree.insert(newTNode);

            }
            tree.printBF();
            //System.out.println(tree.counter());
        }
        // tree.insert(5);
        // tree.insert(10);
        // tree.insert(7);


        // System.out.println("Height of tree is: " + tree.height(tree.root));
    }

    /** getter that allows for OOP encapsulation design **/
    public TNode getRootNode() {
        return this.root;
    }

    /** setter that allows for OOP encapsulation design **/
    public void setRootNode(TNode incoming) {
        this.root = incoming;
    }

    //this is just a helper method I used when testing
    public int counter() {
        LinkedList<TNode> queue = new LinkedList<>();
        queue.add(this.root);
        int ticker = 0;

        while(!queue.isEmpty()) {
            TNode current = queue.remove();
            if(current.getLeft() != null){
                queue.add(current.getLeft());
            }
            if(current.getRight() != null){
                queue.add(current.getRight());
            }
            ticker++;
        }
        return ticker;
    }

    public AVL() {
        this.setRootNode(null);
    }

    public AVL(int val) {
        this.setRootNode(new TNode());
        root.setData(val);
        root.setBalance(0);
    }

    public AVL(TNode nodeIn) {
        this.setRoot(nodeIn);
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
    
    
    @Override 
    public void delete(int val) {
        // If root is null, we can move on.
        if(this.root==null) {return;}
        // If the vertex we are deleting has no children, but has parents, then we set the parent's node pointers to null
        // Use other functions developed in the program to engage in efficiency.
        TNode deleteNode = search(val);
        TNode parent = deleteNode.getParent();
        System.out.println(parent.getData());
        if(deleteNode.getLeft()==null && deleteNode.getRight()==null) {
            // We must determine if the node, deleteNode, is a left or right child of the parent node.
            if(parent.left == deleteNode) {
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

    @Override
    public void insert(int val) {
        TNode newNode = new TNode();
        newNode.setData(val);
        newNode.setBalance(0);

        if (this.getRootNode() == null) {
            this.setRoot(newNode);
            return;
        }

        TNode current = this.getRootNode();
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
        // else pivot == null {
        //     System.out.println("case 1: pivot is null");
        //}
        //this.balanceAdjuster();
        // System.out.println("inserted " + val);


        // else if(pivot.getBalance() == -1 && newNode.getData() > pivot.getData()) {
        //     System.out.println("case 2 negative pivot");
        //     this.balanceAdjuster();
        //     // current = newNode;
        //     // while(current != pivot.getParent()) {
        //     //     current.setBalance(height(current.getRight()) - height(current.getLeft()));
        //     //     current = current.getParent();
        //     // }
        //     System.out.println("inserted " + val);
        //     return;
        // }

        // TNode ancestor = pivot.getParent();

        // // case 3
        // if(pivot.getBalance() == 1 && newNode.getData() > pivot.getData()) {
        //     if(newNode.getData() > son.getData()) {
        //         System.out.println("case 3a positive pivot");
        //         //left rotation
        //         leftRotation(ancestor, pivot, son);

        //         this.balanceAdjuster();
        //         // pivot.setBalance(0);

        //         // TNode balanceAdjuster = newNode.getParent();
        //         // while (balanceAdjuster != son) {
        //         //     balanceAdjuster.setBalance(height(balanceAdjuster.getRight()) - height(balanceAdjuster.getLeft()));
        //         //     balanceAdjuster = balanceAdjuster.getParent();
        //         // }
        //         //write helper methods for left & right rotation with specific nodes as arguments
        //     }
        //     else {
        //         //RL rotation
        //         System.out.println("case 3b positive pivot");

        //         TNode grandSon = son.getLeft();
        //         rlRotation(ancestor, pivot, son, grandSon);

        //         this.balanceAdjuster();

        //         // if(newNode.getData() > grandSon.getData()) {
        //         //     pivot.setBalance(-1);
        //         // }
        //         // else {
        //         //     pivot.setBalance(0);
        //         //     son.setBalance(1);
        //         // }

        //         // TNode balanceAdjuster = newNode.getParent();
        //         // while(balanceAdjuster != son && balanceAdjuster != pivot) {
        //         //     balanceAdjuster.setBalance(height(balanceAdjuster.getRight()) - height(balanceAdjuster.getLeft()));
        //         //     balanceAdjuster = balanceAdjuster.getParent();
        //         // }

        //     }


        // }

        // else if(pivot.getBalance() == -1 && newNode.getData() <= pivot.getData()) {
        //     if(newNode.getData() <= son.getData()) {
        //         System.out.println("case 3a negative pivot");
        //         //right rotation
        //         rightRotation(ancestor, pivot, son);

        //         this.balanceAdjuster();

        //         // pivot.setBalance(0);

        //         // TNode balanceAdjuster = newNode.getParent();
        //         // while (balanceAdjuster != son) {
        //         //     balanceAdjuster.setBalance(height(balanceAdjuster.getRight()) - height(balanceAdjuster.getLeft()));
        //         //     balanceAdjuster = balanceAdjuster.getParent();
        //         // }
        //     }
        //     else {
        //         //LR rotation
        //         System.out.println("case 3b negative pivot");

        //         TNode grandSon = son.getRight();
        //         lrRotation(ancestor, pivot, son, grandSon);

        //         this.balanceAdjuster();

        //         // if(newNode.getData() <= grandSon.getData()) {
        //         //     pivot.setBalance(1);
        //         // }
        //         // else {
        //         //     pivot.setBalance(0);
        //         //     son.setBalance(-1);
        //         // }

        //         // TNode balanceAdjuster = newNode.getParent();
        //         // while(balanceAdjuster != son && balanceAdjuster != pivot) {
        //         //     balanceAdjuster.setBalance(height(balanceAdjuster.getRight()) - height(balanceAdjuster.getLeft()));
        //         //     balanceAdjuster = balanceAdjuster.getParent();
        //         // }
        //     }
        // }

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
            this.setRoot(grandSon);
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
            this.setRoot(grandSon);
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

    private void leftRotation(TNode ancestor, TNode pivot, TNode son ) {
        if(pivot != this.getRootNode()) {
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

        if(this.getRootNode() == pivot) {
            this.setRoot(son);
        }

        son.setParent(ancestor);
    }

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

        if(this.getRootNode() == pivot) {
            this.setRoot(son);
        }

        son.setParent(ancestor);
    }

    @Override
    public void insert(TNode node) {

        if (this.getRootNode() == null) {
            this.setRoot(node);
            return;
        }
        TNode current = this.getRootNode();
        TNode pivot = null;
        boolean foundPivot = false;

        TNode son = null;

        while (true) {
            if (current.getBalance() != 0) {
                pivot = current;
                foundPivot = true;
            }

            if (node.getData() <= current.getData()) {
                if (current.getLeft() == null) {
                    current.setLeft(node);
                    node.setParent(current);
                    break;
                } else {
                    current = current.getLeft();
                    son = foundPivot ? pivot.getLeft() : son;
                }

            } else {
                if (current.getRight() == null) {
                    current.setRight(node);
                    node.setParent(current);
                    break;
                } else {
                    current = current.getRight();
                    son = foundPivot ? pivot.getRight() : son;
                }
            }
            foundPivot = false;
        }

        //case 1: pivot is null
        if (pivot == null) {
            // System.out.println("case 1: pivot is null");
            this.balanceAdjuster();

            // System.out.println("inserted " + val);
            // return;
        }

        //case 2: pivot exists and the node is added to the shorter subtree
        //if pivot.getBalance() == 1
        else if (pivot.getBalance() == 1 /* && newNode.getData() <= pivot.getData() */) {
            TNode ancestor = pivot.getParent();

            // case 2
            if (node.getData() <= pivot.getData()) {
                // System.out.println("case 2 positive pivot");
                this.balanceAdjuster();
                // System.out.println("inserted " + val);
                // return;
            } else {
                //case 3a
                if (node.getData() > son.getData()) {
                    // System.out.println("case 3a positive pivot");

                    leftRotation(ancestor, pivot, son);

                    this.balanceAdjuster();
                } else {
                    //case 3b
                    // System.out.println("case 3b positive pivot");

                    TNode grandSon = son.getLeft();
                    rlRotation(ancestor, pivot, son, grandSon);

                    this.balanceAdjuster();
                }
            }
        } else if (pivot.getBalance() == -1) {
            TNode ancestor = pivot.getParent();
            // case 2
            if (node.getData() > pivot.getData()) {
                // System.out.println("case 2 negative pivot");
                this.balanceAdjuster();
                // System.out.println("inserted " + val);
                // return;
            } else {
                //case 3a
                if (node.getData() <= son.getData()) {
                    // System.out.println("case 3a negative pivot");
                    //right rotation
                    rightRotation(ancestor, pivot, son);

                    this.balanceAdjuster();
                } else {
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
