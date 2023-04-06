package mylib.datastructures.linear;

import mylib.datastructures.trees.AVL;
import mylib.datastructures.trees.BST;
import mylib.datastructures.nodes.DNode;
import mylib.datastructures.nodes.SNode;
import mylib.datastructures.nodes.TNode;

import java.util.Random;

public class TestDataStructures {
    public static void main(String[] Args) {


        /** Testing the functionalities inside SLL **/
        System.out.print("\n/** Testing the functionalities inside SLL **/\n");

        SLL myFirst = new SLL();
        SNode newObject2 = new SNode(1);
        myFirst.insertHead(newObject2);
        SNode nodeObject2 = new SNode(20);   // head works.
        myFirst.insert(nodeObject2,1);
        SNode nodeObject5 = new SNode(21);
        myFirst.insertTail(nodeObject5);
        SNode nodeObject6 = new SNode(11);
        myFirst.insertTail(nodeObject6);
        SNode nodeObject33 = new SNode(12);
        myFirst.insertTail(nodeObject33);
        SNode nodeObject334 = new SNode(4);
        myFirst.insertTail(nodeObject334);
        SNode nodeObj10 = new SNode(40);
        myFirst.insertTail(nodeObj10);
        SNode nodeObject = new SNode(13);
        myFirst.insertTail(nodeObject);
        SNode nodeObject21 = new SNode(9);
        myFirst.insertTail(nodeObject21);
        SNode nodeObject22 = new SNode(0);
        myFirst.insertTail(nodeObject22);
        SNode nodeObject24 = new SNode(100);
        myFirst.insertTail(nodeObject24);
        myFirst.print();
        myFirst.sort();
        myFirst.print();
        System.out.print(myFirst.isSorted());     // isSorted() method works okay.

        System.out.print("\n/** Tests DLL and it's functionalities **/\n");
        /** Tests DLL and it's functionalities **/

        DLL myNew = new DLL();
        DNode myDNode = new DNode(11);
        DNode myDNode2 = new DNode(12);
        DNode myDNode3 = new DNode(13);
        DNode myDNode4 = new DNode(14);
        myNew.insertHead(myDNode);
        myNew.print();
        myNew.insertHead(myDNode4);
        myNew.insertTail(myDNode2);
        myNew.insert(myDNode3,2 );
        myNew.print();
        System.out.println(myNew.isSorted());

        myNew.sort();
        myNew.deleteTail();
        myNew.deleteHead();
        myNew.print();

        System.out.println("StackLL class test:\n");
        System.out.println("Testing no-arg constructor...");
        StackLL testStack = new StackLL();
        testStack.print();
        System.out.println();

        System.out.println("Testing push...");
        testStack.push(new SNode(2));
        testStack.push(new SNode(100));
        testStack.push(new SNode(1));
        testStack.print();
        System.out.println();

        System.out.println("Testing pop...");
        testStack.pop();
        testStack.print();
        System.out.println();

        System.out.println("Testing clear...");
        testStack.clear();
        testStack.print();
        System.out.println();

        System.out.println("Testing 1-arg constructor...");
        SNode head = new SNode(47);
        StackLL test2 = new StackLL(head);
        test2.print();
        System.out.println();

        System.out.println("QueueLL class test:\n");

        //tests no-arg constructor
        System.out.println("Testing no-arg constructor...");
        QueueLL testQueue = new QueueLL();
        testQueue.print();
        System.out.println();

        //tests enqueue
        System.out.println("Testing enqueue...");
        testQueue.enqueue(new SNode(1));
        testQueue.enqueue(new SNode(2));
        testQueue.enqueue(new SNode(100));
        testQueue.print();
        System.out.println();

        //tests dequeue
        System.out.println("Testing dequeue...");
        testQueue.dequeue();
        testQueue.print();
        System.out.println();

        //tests clear
        System.out.println("Testing clear...");
        testQueue.clear();
        testQueue.print();
        System.out.println();

        //tests 1-arg constructor
        System.out.println("Testing 1-arg constructor...");
        SNode newHead = new SNode(1);
        QueueLL testQueueLL = new QueueLL(head);
        test2.print();
        System.out.println();




        System.out.print("\n/** Tests CSLL and it's functionalities **/\n");
        /** Tests CSLL and it's functionalities **/

        CSLL test = new CSLL();
        SNode finder = new SNode(11);
        test.insertTail(new SNode(1));
        test.insertTail(new SNode(2));
        test.insertTail(new SNode(3));
        test.insertTail(new SNode(4));
        test.insertTail(new SNode(5));
        test.insertTail(new SNode(6));
        test.insertTail(new SNode(7));
        test.insertTail(new SNode(8));
        test.insertTail(new SNode(9));
        test.insertTail(new SNode(10));
        test.print();
        SNode theHead = new SNode(55);
        test.insert(theHead, 1);
        test.insert(new SNode(66), 8);
        test.insert(finder, 1000);
        test.print();
        // test.sort();
        test.sortedInsert(new SNode(56));
        test.print();
        CSLL testCSLL= new CSLL();
        testCSLL.insertTail(new SNode(5));
        System.out.println("test2 sorted?"+test2.isSorted());
        testCSLL.insertTail(new SNode(1));
        System.out.println("test2 sorted?" + test2.isSorted());
        testCSLL.sortedInsert(new SNode(3));
        testCSLL.print();

        System.out.print("\n/** Tests CDLL and it's functionalities **/\n");
        /** Tests CDLL and it's functionalities **/

        CDLL newCD = new CDLL();
        DNode myNew2 = new DNode(5);
        newCD.insertHead(myNew2);
        newCD.print();

        System.out.print("\n/** Tests the BST and it's functionalities **/\n");
        /** Tests the BST and it's functionalities **/

        System.out.println("\nWelcome to the binary search tree data stucture.\n");
        BST tree = new BST();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.printBF();
        tree.delete(3);  // method works for deletion of leaf nodes...
        tree.delete(7);
        tree.printBF();

        System.out.print("\n/** Tests the AVL and it's functionalities **/\n");

        Random rand = new Random();
        AVL treeAVL = new AVL(10);
        for(int i = 0; i < 3; i++) {
            int val = rand.nextInt(40);
            tree.insert(val);
            tree.printBF();
        }
        TNode delete = treeAVL.getRootNode();
        //tree.delete(delete.getData());
        tree.printBF();
        System.out.println("Height of tree is: " + treeAVL.height(treeAVL.getRootNode()));


    }



}