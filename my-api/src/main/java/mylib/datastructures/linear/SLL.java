package mylib.datastructures.linear;

import mylib.datastructures.nodes.SNode;

/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.4
 * @since 1.0
 */

/**
 * SLL is a singly linked list class. It uses SNode which only has a next pointer.
 * It has methods to insert, delete, search, and more.
 * This is the base class for DLL, CSLL, QueueLL, and StackLL.
 */

public class SLL {

    private SNode head;
    private int size;

    /**
     * Default SLL constructor. Sets head to null and size to 0.
     */
    SLL() {
        this.head = null;
        this.size = 0;
    }

    /**
     * SLL constructor that takes a head node as its input.
     * @param headInput A node that will be the head of the new SLL.
     */
    SLL(SNode headInput){
        this.head = headInput;
        SNode temporary = headInput;
        while(temporary != null) {   // this is a way to update the size of the L.
            this.size += 1;
            temporary = temporary.next;
        }

    }

    /**
     * Inserts a new node at the head of the list.
     * @param newNode The node to be inserted at the head of the list.
     */
    public void insertHead(SNode newNode) {
        // One of two scenarios while trying to insert a new head.
        if(this.head==null) {
            this.head = newNode;
            this.size += 1;
        } else if (this.head != null) {
            SNode temporary = newNode;
            temporary.next = this.head;
            this.head = temporary;
            this.size += 1;
        }
    }

    /**
     * Inserts a new node at the tail of the list.
     * @param newNode The node to be inserted at the tail of the list.
     */
    public void insertTail(SNode newNode) {
        if(this.head==null) {
            this.head = newNode;
            this.size += 1;
        } else if (this.head != null) {
            SNode temporary = this.head;
            while(temporary.next != null) {
                temporary = temporary.next;
            }
            temporary.next = newNode;
            this.size += 1;
        }
    }

    /**
     * Inserts a new node at a specified position in the list.
     * @param newNode   The node to be inserted at the specified position.
     * @param position  The position at which the node will be inserted.
     */
    public void insert(SNode newNode, int position) {
        // One of three cases:
        // 1) Position = 1, which means to insert at the head position
        // 2) Position != 1 AND position != last position.
        // 3) Position = last position of the linked list.

        if(position==1) {
            newNode.next = this.head;  // eliminated a temp pointer here.
            this.head = newNode;
            this.size += 1;
        } else if (position > 1 && position < this.size) {
            SNode current = this.head;
            SNode prev = this.head;
            int currentNum = 1;
            while(currentNum != position) {
                prev = current;
                current = current.next;
                currentNum += 1;
            }
            prev.next = newNode;
            prev.next.next = current;
            this.size += 1;
        }
        else{
            insertTail(newNode);  // I don't see why we can't use another method designed for this specifically...
        }
    }

    /**
     * Checks if the list is sorted.
     * @return True if the list is sorted, false if it is not.
     */
    public boolean isSorted() {
        // Idea here: for every node, check with every other prev. node.
        int[] allData = new int[this.size];
        SNode current = this.head;
        for(int i=0; current != null; i++){
            allData[i] = current.data;
            for(int j = 0; j < i; j++) {
                if(allData[j] > current.data) {
                    return false;
                }
            }
            current = current.next;
        }
        return true;
    }

    /**
     * Inserts a node into the list in sorted order.
     * If the list is not sorted, it will call sort() first.
     * @param node  The node to be inserted.
     */
    public void sortedInsert(SNode node) {
        // METHOD: for every position in the LL, we will check a 2-item array to see if
        // the data is either smaller or larger than the data in the array; the data in the array
        // contains the max and min ints SO FAR. Hence, I feel this is a fairly effecient way
        // to check if something is out of order since we have O(2) v. O(n).
        int checkInt; // storage for max and min ints
        boolean didInsert = false;
        int posCurr = 1;
        SNode current = this.head;
        while(!didInsert && current != null) {
            if(isSorted()) {
                if ((current.data < node.data) && (current.next.data > node.data)) {
                    insert(node, posCurr);
                    didInsert = true; // breaks out of while loop if true.
                }
                current = current.next;
                posCurr += 1;
            } else if (!isSorted()) {
                sort();
                sortedInsert(node);  // Recursively call the function again to perform a proper insert
            }
        }
    }

    /**
     * Searches for a node in the list.
     * @param node The node to be searched for.
     * @return   The node if it is found, null if it is not.
     */
    public SNode Search(SNode node) {
        SNode current = this.head;
        while(current != null){
            if(current == node){
                return node;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Deletes the head of the list.
     */
    public void deleteHead() {
        if(this.head==null){return;}
        this.head = this.head.next;
    }

    /**
     * Deletes the tail of the list.
     */
    public void deleteTail() {
        SNode current = this.head;
        while(current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }

    /**
     * Deletes a specific node from the list.
     * @param node  The node to be deleted.
     */
    public void delete(SNode node) {
        if(this.head==node){deleteHead();}
        SNode current = this.head;
        SNode prev = null;
        while(current != null){
            prev = current;
            current = current.next;
            if(current==node){prev.next = current.next;}
            return;
        }
    }

    /**
     * Sorts the list using insertion sort.
     */
    public void sort() {
        // we can always find a better alg. later but this is what i got for now...
        SNode current = this.head;
        SNode prevCurrent = this.head;
        while(current != null){
            SNode checker = current.next; // since all prev nodes will be sorted, idea with insertion sort...
            while(checker != null){
                if(checker.data < current.data) {
                    // Modelled the two different situations below.
                    if(prevCurrent == current){
                        // Two options if we are switching with the first node:
                        // Switch with a regular node, or the last node.
                        if(checker.next == null) {  // if we are switching out with the last node.
                            SNode goTo = current;       // goTo represents the 'new' last node...
                            while(goTo.next != checker){
                                goTo = goTo.next;
                            }
                            goTo.next = null;
                            checker.next = current;
                            prevCurrent = checker;
                            checker = goTo;
                            current = prevCurrent;
                            this.head = current;

                        } else if (checker.next != null) {
                            SNode tmp = checker.next;
                            checker.next = current.next;

                            SNode tmpN = current.next;
                            SNode fwdN = tmpN;
                            while(fwdN.next != checker){
                                fwdN = fwdN.next;
                            }
                            fwdN.next = current;
                            current.next = tmp;
                            current = checker;

                            checker = prevCurrent;
                            prevCurrent = current;
                            this.head = current;
                        }
                    } else if (prevCurrent != current) {
                        if(checker.next == null) {  // if we are switching out with the last node.
                            SNode goTo = current;
                            SNode firstTo = current.next;   // goTo represents the 'new' last node...
                            while(goTo.next != checker){
                                goTo = goTo.next;
                            }
                            goTo.next = current;
                            checker.next = firstTo;
                            if(checker.next==checker){
                                checker.next = current;
                            }
                            prevCurrent.next = checker;
                            current.next = null;
                            SNode store = current;
                            current = checker;
                            checker = store;

                        } else if (checker.next != null) {
                            SNode before = current;
                            while(before.next != checker) {
                                before = before.next;
                            }
                            before.next = checker.next;
                            prevCurrent.next = checker;
                            checker.next = current;
                            current = checker;
                            checker = before;
                        }
                    }

                }
                checker = checker.next;
            }
            prevCurrent = current;
            current = current.next;
        }
    }

    /**
     * Empties the list.
     */
    public void clear() {
        this.size = 0;
        this.head = null;
    }

    /**
     * Prints the list information.
     */
    public void print() {
        SNode current = this.head;
        System.out.print("List Information:\n");
        System.out.printf("List length: %d\n", this.size);
        System.out.printf("Sorted status: " + this.isSorted() + "\n");
        for(int i = 1; current != null; i++){
            System.out.printf("Data in list item #%d: %d\n", i, current.data);
            current = current.next;
        }
    }
}





