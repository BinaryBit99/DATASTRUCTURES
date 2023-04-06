/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.0
 * @since 1.0
 */

/*
 * QueueLL is an implementation of the queue data structure using a linked list.
 * It extends the SLL class and uses some of its methods.
 * Methods that are detrimental to queue behavior are overridden.
 */

package mylib.datastructures.linear;

import mylib.datastructures.nodes.SNode;

public class QueueLL extends SLL {

    public static void main(String[] args) {
        //Ask Evan about one big main method to test all data structures
        //or Junit unit tests to test all data structures

        QueueLL test = new QueueLL();
        test.enqueue(new SNode(1));
        test.enqueue(new SNode(2));
        test.enqueue(new SNode(100));
        test.print();

        test.dequeue();
        test.print();

        test.enqueue(new SNode(3));
        test.enqueue(new SNode(57));
        test.enqueue(new SNode(34));
        test.enqueue(new SNode(86));
        test.enqueue(new SNode(12));
        test.enqueue(new SNode(72));
        test.sort();

        // System.out.println(test.Search(new SNode(57)).data);
        test.print();

        test.dequeue();
        test.dequeue();
        test.print();

        test.clear();
        test.print();
    }

    /**
     * Default QueueLL constructor. Calls the default SLL constructor.
     */
    public QueueLL() {
        super();
    }

    /**
     * QueueLL constructor that takes another queue's head as input.
     * Calls the SLL constructor.
     * @param headInput     the head of the queue to be copied.
     */
    public QueueLL(SNode headInput) {
        super(headInput);
    }

    /**
     * Enqueues a new node to the end of the queue.
     * @param newNode
     */
    public void enqueue(SNode newNode) {
        super.insertTail(newNode);
    }

    /**
     * Dequeues the first node in the queue.
     */
    public void dequeue() {
        //should dequeue return the data from the dequeued node?
        super.deleteHead();
    }

    // Overridden methods that are detrimental to stack behavior.
    @Override
    public void insertHead(SNode newNode) { return; }

    @Override
    public void insert(SNode newNode, int position) {}

    @Override
    public void sortedInsert(SNode node) {}

    @Override
    public void deleteTail() {}

    @Override
    public void delete(SNode node) {}

    @Override
    public void sort() {}
}
