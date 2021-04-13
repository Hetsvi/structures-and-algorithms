/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.*;

/**
 * This class implements an dHeap and has various methods
 * which add, element, clear, remove and more
 * @param <T>
 *
 * @author Hetsvi
 * @since 12 November
 */
public class dHeap<T extends Comparable<? super T>>
        implements dHeapInterface<T> {

    private T[] heap; //heap array
    private int d; //branching factor
    private int nelems;  // number of elements
    private boolean isMaxHeap; //boolean to indicate whether heap is max or min
    private final static int DEFAULT_SIZE = 9; // default size of the array
    private final static int DEFAULT_BINARY = 2;  // default d

    /**
     * Initializes a binary max heap with capacity = 9
     */
    public dHeap() {
        heap = (T[]) new Comparable[DEFAULT_SIZE];
        d = DEFAULT_BINARY;
        isMaxHeap = true;


    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    public dHeap(int heapSize) {
        heap = (T[]) new Comparable[heapSize];
        d = DEFAULT_BINARY;
        isMaxHeap = true;

    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d The number of child nodes each node in the heap should have.
     * @param heapSize The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap)
            throws IllegalArgumentException {

        if(d< 1){
            throw new IllegalArgumentException();
        }
        this.d = d;
        heap = (T[]) new Comparable[heapSize];
        this.isMaxHeap = isMaxHeap;
    }

    /**
     * Gives the size
     * @return nelems
     */
    @Override
    public int size() {
        return this.nelems; //XXX-CHANGE-XXX
    }

    /**
     * Adds the data and arranges according to min or max
     * @param data
     * @throws NullPointerException
     */
    @Override
    public void add(T data) throws NullPointerException {
        //TODO
        if(data == null){
            throw new NullPointerException();
        }

        if(heap.length == nelems){  // checks if needs resize
            resize();
        }

        heap[nelems] = data;
        nelems += 1;
        bubbleUp(nelems-1);
    }

    /**
     * Removes and rearranges according to min or max
     * @return
     * @throws NoSuchElementException
     */
    @Override
    public T remove() throws NoSuchElementException {

        if(size() == 0){
            throw new NoSuchElementException();
        }
        T temp = heap[0];
        heap[0] = heap[nelems -1];  // swaps the last one to root
        heap[nelems-1] = null;
        nelems -= 1;
        trickleDown(0);  // calls trickleDown to arrange
        return temp; //XXX-CHANGE-XXX  // returns the original root
    }

    /**
     * clears the elements
     */
    @Override
    public void clear() {
        //TODO
        this.nelems =0;
        heap = (T[]) new Comparable[DEFAULT_SIZE];
    }

    /**
     * gives the root
     * @return root
     */
    @Override
    public T element() {
        T root;
        if(nelems == 0){
            root = null;
        }

        else{
            root = heap[0];
        }
        return root; ////XXX-CHANGE-XXX
    }


    /**
     * Extra Credit 1 - gives elements greater than k
     * @param k
     * @return list of elements greater than k
     */
    public LinkedList findGreaterThanK(T k){
        int parentIdx = 0;
        int child = d*parentIdx+1;
        LinkedList greater = new LinkedList();
        while(d*parentIdx+1 <= nelems){
            T a = remove();
            if(a.compareTo(k) > 0){
                greater.add(a);
            }


        }
        return greater;
/*       while(d*parentIdx+1 < nelems-1) {   //loops through the list
            if (heap[parentIdx] == null) {
                return greater;
            }
            if (heap[parentIdx].compareTo(k) > 0) {  // checks if bigger
                greater.add(heap[parentIdx]);  // adds to linked list
            }
            for (int i = d * parentIdx + 1; i <= d * parentIdx + d; i++) { // children see
                if (heap[i] == null) {
                    return greater;
                }

                if (heap[i].compareTo(k) > 0) {
                    greater.add(heap[i]);
                }
            }
            return greater;

        }
        return greater;*/

    }



    /**
     * Extra Credit 2
     * It finds the sum of elements between num1 and num2. The array is arranged
     * to a min
     * @param a
     * @param num1
     * @param num2
     * @return int sum
     */

   public int findSum(int[] a, int num1, int num2){
        int sum =0;
        int d = 4;
        int size = 10;
        dHeap<Integer> sumArray = new dHeap<>(d,size, false);
        for(int i =0; i<a.length; i++){
            sumArray.add(a[i]);
        }

        for(int i =0; i< a.length; i++){
            int removed = sumArray.remove();
            if(i > num1-1 && i< num2-1){
                sum = sum +removed;
            }

        }
        return sum;
    }


    /**
     * This method is a helper for remove and uses recurssion, after the root it
     * deleted and the last element becomes root. It rebalances the properties
     * by finding the right position for the last element and new proper root.
     * @param index
     */
    private void trickleDown(int index){

        if(d*index+1 > nelems-1){
            return;
        }

        int maxC = maxChild(index);  // gets the either extreme child

        if (heap[index].compareTo(heap[maxC]) <= 0 && isMaxHeap) {  // if it is MaxHeap
            T temp = heap[index];   // swaps
            heap[index] = heap[maxC];
            heap[maxC] = temp;
            trickleDown(maxC);
        }

        else if (heap[index].compareTo(heap[maxC]) >= 0 && isMaxHeap == false) {
                T temp = heap[index];  // swaps
                heap[index] = heap[maxC];
                heap[maxC] = temp;
                trickleDown(maxC);
            }

        else{
            return;
        }

    }

    /**
     * This is the helper method to add and uses a while loop. This swaps
     * and finds the right places for new added element.
     * @param index
     */
    private void bubbleUp(int index){

            while (index > 0) {// runs till didn't find place
                if(compareBoth(index)) {
                    int parentIdx = parent(index);
                    T temp = heap[index];   // swaps
                    heap[index] = heap[parent(index)];
                    heap[parent(index)] = temp;
                    index = parentIdx;
                }

                else{
                    break;
                }
        }
    }

    /**
     * It is a helper method to add and helps double the size when its full
     */
    private void resize(){
        int len = 2;
        int newLen = heap.length*len;  // double the length
        T[] array = (T[]) new Comparable[newLen];
        for(int i=0; i<nelems; i++){
            array[i] = heap[i];
        }

        heap = array;
    }

    /**
     * Gives parent index when child index is given
     * @param index
     * @return parent index
     */
    private int parent(int index){
        return (index-1)/d;   // parent index

    }

    /**
     * It compares and according to min and max it returns a boolean
     * which tells other methods whether to swap or nah and update or nah.
     * @param index
     * @param index1
     * @return boolean
     */
    private boolean compareBoth(int index, int index1){
        T child = heap[index];
        T pIndex = heap[index1]; // for trickle down other index

        if(isMaxHeap == true){
            if(pIndex.compareTo(child) > 0){   //tells other methods whether to swap or nah
                return false;
            }

            else{
                return true;
            }
        }

        if(isMaxHeap == false){     //tells other methods whether to swap or nah
            if(pIndex.compareTo(child) < 0){
                return false;
            }

            else{
                return true;
            }
        }

        return false;
    }

    private boolean compareBoth(int index){
        return compareBoth(index, parent(index));
    }


    /**
     * It returns max or min child accordingly
     * @param index
     * @return child
     */
    private int maxChild(int index){
        int parentIdx = index;           // parent index
        int currIdx = d*parentIdx+ 1;   // current child index
        int curr = d*parentIdx+ 1;

        for(int i = d*parentIdx+1; i<= d*parentIdx+d; i++){  // sees all children
            if(heap[i] == null){
                return currIdx;
            }

            if (compareBoth(i, currIdx)) {    // compares
                currIdx = i;                  // updates according to if min or max
            }
        }

    return currIdx;

    }

}
