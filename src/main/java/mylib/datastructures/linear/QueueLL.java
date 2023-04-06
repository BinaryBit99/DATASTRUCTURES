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
