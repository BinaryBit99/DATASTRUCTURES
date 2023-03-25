package mylib.datastructures.linear;

import mylib.datastructures.nodes.SNode;

// Documentation outlined below:
/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.1
 * @since 1.0
 */

public class SLL {
    private SNode head;
    private int size;

    SLL() {
        this.head = null;
        this.size = 0;
    }

    SLL(SNode headInput){
        this.head = headInput;
        SNode temporary = headInput;
        while(temporary != null) {   // this is a way to update the size of the L.
            this.size += 1;
            temporary = temporary.next;
        }

    }

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

    public void insert(SNode newNode, int position) {
        // One of three cases:
        // 1) Position = 1, which means to insert at the head position
        // 2) Position != 1 AND position != last position.
        // 3) Position = last position of the linked list.

        if(position==1) {
            newNode.next = this.head;  // eliminated a temp pointer here.
            this.head = newNode;
        } else if (position != this.size) {
            SNode current = this.head;
            int currentNum = 1;
            while(currentNum != position-1) {
                current = current.next;
                currentNum += 1;
            }
            newNode.next = current.next;
            current = newNode;
        }
        else{
            insertTail(newNode);  // I don't see why we can't use another method designed for this specifically...
        }
    }

    public void sortedInsert(SNode node) {
        // METHOD: for every position in the LL, we will check a 2-item array to see if
        // the data is either smaller or larger than the data in the array; the data in the array
        // contains the max and min ints SO FAR. Hence, I feel this is a fairly effecient way
        // to check if something is out of order since we have O(2) v. O(n).
        int[] maxMin = new int[2]; // storage for max and min ints
        boolean didInsert = false;
        int posCurr = 1;
        SNode current = this.head;
        while(!didInsert && current != null) {
            boolean isStart = false;
            if (maxMin[1] == 0 && maxMin[0] == 0) {
                maxMin[1] = current.data;
            }
            if (maxMin[0] == 0 && !(maxMin[0] == 0)) {
                if (current.data < maxMin[1]) {
                    maxMin[0] = current.data;
                } else {
                    maxMin[0] = maxMin[1];
                    maxMin[1] = current.data;
                }
                isStart = true; // Use this as a 'bool flag'
            }
            if (isStart) {
                if (current.data < maxMin[1] && current.data > maxMin[0]) {
                    sort(); // call sort to sort the LL
                }
            }
            if ((current.data < node.data) && (current.next.data > node.data)) {
                insert(node, posCurr);
                didInsert = true; // breaks out of while loop if true.
            }
            current = current.next;
            posCurr += 1;
        }
    }
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

    public void deleteHead() {
        if(this.head==null){return;}
        this.head = this.head.next;
    }

    public void deleteTail() {
        SNode current = this.head;
        while(current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }

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

    // decided to implement an insertion sort since that is what is asked for, although may come back to this and implement
    // a merge sort if I ever get bored...
    public void sort() {
        // we can always find a better alg. later but this is what i got for now...
        if(this.size == 0 || this.size == 1){
            return;
        }
        else{
            SNode starting = this.head;
            SNode current = this.head;
            while(current != null){
                SNode before;
                while(starting.next != null && starting.next.data <= current.data){
                    before = starting;
                    starting = starting.next;
                }
                SNode slotHolder = starting.next;
                starting.next = current.next;
                before = current;
                current.next = slotHolder;
                this.head = starting;

                current = current.next;
            }
        }
    }

    public void clear() {
        this.head = null;
    }

    public void print() {
        SNode current = this.head;
        System.out.print("List Information: \n");
        System.out.printf("List length: %d", this.size);
        System.out.print("Sorted status: ");               // still need to implement a method for this.
        for(int i = 1; current != null; i++){
            System.out.printf("Data in list item #%d: %d", i, current.data);
            current = current.next;
        }
    }
    

}


