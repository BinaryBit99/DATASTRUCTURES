package mylib.datastructures.nodes;
// Hey Karam, you probably know about this already, but we have to build everything in maven, and I believe this is the correct package structure.
// ^ temp comment.
// Documentation outlined below:
/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.1
 * @since 1.0
 */

public class SNode {
    SNode() {this.data = 0;}
    public int data; // could be any other type of data, used to display functionality of the data structure.
    public SNode next; // represents a pointer that points to the next node object in the linked list data structure.
    public SNode(int data) { this.data = data; } // Constructor used to instantiate a new node object that contains data, and can be used to point to another node in the linked list.

}
