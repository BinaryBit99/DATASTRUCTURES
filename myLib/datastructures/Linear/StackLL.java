/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.0
 * @since 1.0
 */

 package mylib.datastructures.linear;

 import mylib.datastructures.nodes.SNode;
 

/**
 * StackLL is an implementation of the stack data structure using a linked list.
 * It extends the SLL class and uses some of its methods.
 * Methods that are detrimental to stack behavior are overridden.
 */


 public class StackLL extends SLL {
 
 
     /**
      * Default StackLL constructor. Calls the default SLL constructor.
      */
     public StackLL() {
         super();
     }
 
     /**
      * StackLL constructor that takes another stack's head as input.
      * Calls the SLL constructor.
      * @param headInput     the head of the stack to be copied.
      */
     public StackLL(SNode headInput) {
         super(headInput);
     }
 
     /**
      * Pushes a new node to the top of the stack.
      * Calls the SLL insertHead method.
      * @param newNode
      */
     public void push(SNode newNode) {
         super.insertHead(newNode);
     }
 
     /**
      * Pops the top node off the stack.
      * Calls the SLL deleteHead method.
      */
     public void pop() {
         //Ask Evan if this should return the popped node.
         super.deleteHead();
     }
 
     // Overridden methods that are detrimental to stack behavior.
     @Override
     public void insertTail(SNode newNode) {}
 
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