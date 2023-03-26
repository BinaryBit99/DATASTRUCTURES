package mylib.datastructures.linear;

import mylib.datastructures.nodes.SNode;

// Documentation outlined below:
/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.4
 * @since 1.0
 */

public class SLL {

    public static void main(String[] args) {
        SLL myFirst = new SLL();

        SNode nodeObject = new SNode(21);
        myFirst.insertHead(nodeObject);

        SNode nodeObject2 = new SNode(20);   // head works.
        myFirst.insert(nodeObject2,1);
        SNode nodeObject5 = new SNode(26);
        myFirst.insertTail(nodeObject5);
        SNode nodeObj7 = new SNode(25);
        myFirst.insertTail(nodeObj7);
        SNode nodeObj6 = new SNode(24);
        myFirst.insertTail(nodeObj6);

        myFirst.print();
        myFirst.sort();
        myFirst.print();
        System.out.print(myFirst.isSorted());     // isSorted() method works okay.

    }
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

    // helper method.
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
                            while(before.next != checker){before = before.next;}
                            before.next = checker.next;
                            prevCurrent.next = checker;
                            checker.next = before;
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
    public void clear() {
        this.size = 0;
        this.head = null;
    }

    public void print() {
        SNode current = this.head;
        System.out.print("List Information:\n");
        System.out.printf("List length: %d\n", this.size);
        System.out.printf("Sorted status: " + this.isSorted() +   "\n");               // still need to implement a method for this.
        for(int i = 1; current != null; i++){
            System.out.printf("Data in list item #%d: %d\n", i, current.data);
            current = current.next;
        }
    }
}

