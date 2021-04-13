/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.NoSuchElementException;

/**
 * This class implements the circular queue array
 *
 * @author Hetsvi
 * @since dec 2018
 */
public class CircularArrayQueue implements QueueADT {
    private static final int DEFAULT_SIZE = 5; // Example for declaring magic numbers
    private static final int GROWTH_FACTOR = 2; // Example for declaring magic numbers

    private int[] circularArray;    // array
    private int length;      // length
    private int head;       // head
    private int tail;       // tail
    private int capacity;    // gives capapcity

    /**
     * Constructor
     */
    public CircularArrayQueue() {
        // TODO
        circularArray = new int[DEFAULT_SIZE];
        capacity = DEFAULT_SIZE;

    }

    /**
     * Constructor used when capacity is given
     * @param capacity
     */
    public CircularArrayQueue(int capacity) {
        // TODO
        this.capacity = capacity;
        if(capacity < 1){
            circularArray = new int[1];
            this.capacity =1;
        }

        else{
            circularArray = new int[capacity];
        }
    }

    /**
     * adds element to the circular queue array
     * @param elem integer to add
     */
    public void add(int elem) {
        // TODO
        resize();  // calls resize
        this.circularArray[this.tail] = elem;
        length = length +1;
        this.tail = (this.tail + 1) % this.capacity;  // sets the tailm

    }

    /**
     * Helper to add where the array is doubled if more elements are added
     */
    private void resize() {
        // TODO
        if(this.size() == this.capacity){
            int newTemp = this.capacity;
            this.capacity = this.capacity * GROWTH_FACTOR;  // doubles
            int [] temp = new int[capacity];

            if(this.tail <= this.head){
                int index =0;
                int count =0;

                while(count < length){
                    temp[index] = this.circularArray[this.head];  // copies
                    count = count +1;
                    index = index +1;
                    this.head = (this.head + 1)%newTemp;
                }
                this.circularArray = temp;
            }

            else{
                for(int i =0; i < length; i++, this.head ++){
                    temp[i] = this.circularArray[this.head];  // copies
                }
                this.circularArray = temp;
            }

            this.head = 0;
            this.tail = length;
        }

    }

    /**
     * Checks if empty
     * @return boolean
     */
    public boolean isEmpty() {
        // TODO
        if(length == 0){  // checks length
            return true;
        }

        return false;
    }


    /**
     * Gives the first element of queue.
     * @return head of queue
     * @throws NoSuchElementException
     */
    public int peek() throws NoSuchElementException {
        // TODO
        if(this.isEmpty()){
            throw new NoSuchElementException();
        }

        else{
            return this.circularArray[head];
        }

    }

    /**
     * Removes an element from the queue
     * @return removed element
     * @throws NoSuchElementException
     */
    public int remove() throws NoSuchElementException {
        // TODO
        if(this.isEmpty()){
            throw new NoSuchElementException();
        }

        int removElem = this.circularArray[this.head];
        this.circularArray[this.head] = 0;
        this.head = (this.head +1) % this.capacity;   // resets head
        this.length = this.length-1;
        return removElem;
    }

    /**
     * Clears the whole queue
     */
    public void clear() {
        // TODO
        while(!isEmpty()){
            this.remove();
        }
    }

    /**
     * Gives the size
     * @return int length
     */
    public int size() {
        // TODO
        return length;
    }
}
