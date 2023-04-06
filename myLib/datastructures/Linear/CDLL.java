package mylib.datastructures.linear;
/**
 * @authors Evan Barker & Karam Baroud
 * @version 1.0
 * @since 1.0
 */
import mylib.datastructures.nodes.DNode;
public class CDLL extends DLL {


    @Override
    public void print() {
        DNode current = this.head;
        int i = 1;
        DNode last = current.back;
        System.out.print("List Information:\n");
        System.out.printf("List length: %d\n", this.size);
        System.out.printf("Sorted status: " + this.isSorted() +   "\n");               // still need to implement a method for this.
        do{
            System.out.printf("Data in list item #%d: %d\n", i, current.data);
            current = current.next;
            i++;
        } while(current != this.head) ;

    }

    private DNode head;
    private DNode tail;
    private int size;

    // public CDLL constructor method, calls the DLL constructor.
    public CDLL() {
        super();
    }

    public CDLL(DNode input) {
        // head and tail both point to the same node.
        this.head = input;
        this.tail = input;
        // extra add-on for circular LL.
        this.head.back = this.tail;
        this.tail.next = this.head;
        this.size += 1;
    }

    @Override
    public void insertHead(DNode newNode){
        // One of two scenarios while trying to insert a new head.
        if(this.head==null) {
            this.head = newNode;
            this.tail = newNode; // both tail and head point to the same node IF this.head == null;
            this.head.next = this.tail;
            this.tail.back = this.head;


            this.size += 1;
        } else if (this.head != null) {
            DNode temporary = newNode;
            temporary.next = this.head;
            temporary.back = this.tail;
            this.tail.next = temporary;
            this.head.back = temporary;
            this.head = temporary;
            this.size += 1;
        }
    }

    @Override
    public void insertTail(DNode newNode) {
        if(this.head==null) {
            this.head = newNode;
            this.tail = newNode;
            this.size += 1;
        } else if (this.head != null) {
            DNode temporary = this.tail;
            temporary.next = newNode;
            newNode.back = temporary;
            newNode.next = this.head;
            this.head.back = newNode;
            this.size += 1;
        }
    }

    @Override
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

    @Override
    public void deleteHead() {
        if(this.head==null){return;}
        DNode nextUp = this.head.next;
        this.tail.next = nextUp;
        nextUp.back = this.tail;
        this.head.next = null;
        this.head = nextUp;
    }

    @Override
    public void deleteTail() {
        DNode newTail = this.tail.back;
        newTail.next = this.head;
        this.tail.back = null;
        this.tail = newTail;
    }






}
