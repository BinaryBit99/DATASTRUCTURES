

package mylib.datastructures.linear;

import mylib.datastructures.nodes.DNode;
import mylib.datastructures.nodes.SNode;


// Documentation outlined below:
/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.2
 * @since 1.0
 */

public class DLL extends SLL {
    private DNode head;
    private DNode tail;
    private int size;

    public static void main(String[] args) {
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

    }

    DLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    DLL(DNode headInput){
        this.head = headInput;     // head and tail both point to same node.
        this.tail = headInput;
        DNode temporary = headInput;
        while(temporary != null) {   // this is a way to update the size of the L.
            this.size += 1;
            temporary = temporary.next;
        }

    }

    public void insertHead(DNode newNode) {
        // One of two scenarios while trying to insert a new head.
        if(this.head==null) {
            this.head = newNode;
            this.tail = newNode; // both tail and head point to the same node IF this.head == null;
            this.size += 1;
        } else if (this.head != null) {
            DNode temporary = newNode;
            temporary.next = this.head;
            temporary.back = null;       // for the head node, we must make the 'back' pointer in the node null.
            this.head = temporary;
            this.size += 1;
        }
    }

    public void insertTail(DNode newNode) {
        if(this.head==null) {
            this.head = newNode;
            this.tail = newNode;
            this.size += 1;
        } else if (this.head != null) {
            DNode temporary = this.tail;
            temporary.next = newNode;
            newNode.back = temporary;
            newNode.next = null;
            this.size += 1;
        }
    }

    public void insert(DNode newNode, int position) {
        // One of three cases:
        // 1) Position = 1, which means to insert at the head position
        // 2) Position != 1 AND position != last position.
        // 3) Position = last position of the linked list.

        if(position==1) {
            insertHead(newNode);                     // may as well make use of another method...
        } else if (position != this.size) {
            DNode current = this.head;
            int currentNum = 1;
            while(currentNum != position-1) {
                current = current.next;
                currentNum += 1;
            }
            current.next.back = newNode;
            current.next.back.next = current.next;
            current.next = newNode;
            this.size += 1;
        }
        else{
            insertTail(newNode);  // I don't see why we can't use another method designed for this specifically...
        }
    }

    public boolean isSorted() {
        // Idea here: for every node, check with every other prev. node.
        int[] allData = new int[this.size];
        DNode current = this.head;
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

    public void sortedInsert(DNode node) {
        // METHOD: for every position in the LL, we will check a 2-item array to see if
        // the data is either smaller or larger than the data in the array; the data in the array
        // contains the max and min ints SO FAR. Hence, I feel this is a fairly effecient way
        // to check if something is out of order since we have O(2) v. O(n).
        int checkInt; // storage for max and min ints
        boolean didInsert = false;
        int posCurr = 1;
        DNode current = this.head;
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
    public DNode Search(DNode node) {
        DNode current = this.head;
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
        DNode nextUp = this.head.next;
        this.head.next = null;
        nextUp.back = null;
        this.head = nextUp;
    }

    public void deleteTail() {
        DNode newTail = this.tail.back;
        this.tail.back = null;
        newTail.next = null;
        this.tail = newTail;
    }

    public void delete(DNode node) {
        // First, check if node is tail or head node, make use of those methods.
        if(this.head==node){deleteHead();}
        if(this.tail==node){deleteTail();}
        DNode current = this.head;
        DNode prev = null;
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
        // we can always find a better alg. later but this is what i got for now.
        DNode current = this.head;
        DNode prevCurrent = this.head;
        while(current != null){
            DNode checker = current.next; // since all prev nodes will be sorted, idea with insertion sort...
            while(checker != null){
                if(checker.data < current.data) {
                    // Modelled the two different situations below.
                    if(prevCurrent == current){
                        // Two options if we are switching with the first node:
                        // Switch with a regular node, or the last node.
                        if(checker.next == null) {  // if we are switching out with the last node.
                            DNode goTo = current;       // goTo represents the 'new' last node...
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
                            DNode tmp = checker.next;
                            checker.next = current.next;

                            DNode tmpN = current.next;
                            DNode fwdN = tmpN;
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
                            DNode goTo = current;
                            DNode firstTo = current.next;   // goTo represents the 'new' last node...
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
                            DNode store = current;
                            current = checker;
                            checker = store;

                        } else if (checker.next != null) {
                            DNode before = current;
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

    public void clear() {
        // Simply restore everything back to null and zero.
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void print() {
        DNode current = this.head;
        System.out.print("List Information:\n");
        System.out.printf("List length: %d\n", this.size);
        System.out.printf("Sorted status: " + this.isSorted() +   "\n");               // still need to implement a method for this.
        for(int i = 1; current != null; i++){
            System.out.printf("Data in list item #%d: %d\n", i, current.data);
            current = current.next;
        }
    }

}
