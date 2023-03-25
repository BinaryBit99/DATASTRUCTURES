/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.0
 * @since 1.0
 */

/*
 * LLStack is an implementation of the stack data structure using a linked list.
 * It extends the SLL class and uses some of its methods.
 * Methods that are detrimental to stack behavior are overridden.
 */

package myLib.datastructures.Linear;

import myLib.datastructures.nodes.SNode;

public class LLStack extends SLL {
    public static void main(String[] args) {

        LLStack test = new LLStack();
        test.push(new SNode(1));
        test.push(new SNode(2));
        test.push(new SNode(100));
        test.print();

        test.pop();
        test.print();

        test.push(new SNode(3));
        test.push(new SNode(57));
        test.push(new SNode(34));
        test.push(new SNode(86));
        test.push(new SNode(12));
        test.push(new SNode(72));

        // System.out.println(test.Search(new SNode(57)).data);
        test.print();

        test.clear();
        test.print();
    }

    /**
     * Default LLStack constructor. Calls the default SLL constructor.
     */
    public LLStack() {
        super();
    }

    /**
     * LLStack constructor that takes another stack's head as input.
     * Calls the SLL constructor.
     * @param headInput     the head of the stack to be copied.
     */
    public LLStack(SNode headInput) {
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
    public void insertTail(SNode newNode) { return; }

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
