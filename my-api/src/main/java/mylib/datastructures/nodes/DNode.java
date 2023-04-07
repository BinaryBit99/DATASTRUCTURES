package mylib.datastructures.nodes;

/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.0
 * @since 1.0
 */

/**
 * DNode is a node object that is used for our DLL and CDLL implementations.
 * It is a doubly linked node, meaning it has two pointers, one that points to the next node, and one that points to the previous node.
 */

public class DNode {
    /**
     * Default DNode constructor. Sets the data to 0.
     */
    public DNode() {this.data = 0;}

    public int data; // could be any other type of data, used to display functionality of the data structure.
    public DNode next; // represents a pointer that points to the next node object in the linked list data structure.
    public DNode back; // second pointer that points to previous node.

    /**
     * DNode constructor that takes in an int as input.
     * @param data     the data to be stored in the node.
     */
    public DNode(int data) { this.data = data; } // Constructor used to instantiate a new node object that contains data, and can be used to point to another node in the linked list.

}