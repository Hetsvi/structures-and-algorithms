/*
 * NAME: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.AbstractList;

/**
 * Doubly-Linked List Implementation
 * This class implements a doubly linked list and has a inner
 * class with private methods.
 *
 * @author Hetsvi
 * @since 21st October 2018
 */
public class DoublyLinkedList<T> extends AbstractList<T> {
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            //TODO: complete constructor
            this.data = element;

        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            //TODO: complete implementation
            this.data = element;
            this.prev = prevNode;
            this.next = nextNode;

        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            //TODO: complete implementation
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            //TODO: complete implementation
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            //TODO: complete implementation
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            //TODO: complete implementation
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            //TODO: complete implementation
            this.prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            //TODO: complete implementation
            return this.prev;
        }

        /**
         * Remove this node from the list. 
         * Update previous and next nodes
         */
        public void remove() {
            //TODO: complete implementation
            this.prev.next = this.next;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        //TODO: complete default constructor
        this.head = new Node(null, this.tail, null);
        this.tail = new Node (null, null, this.head);
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        //TODO: Implementation for throwing exceptions followed by
        //implementation of adding the new data
        if (element == null){
            throw new NullPointerException("Data is null");
        }

        Node first = this.tail.getPrev();
        Node newAdd = new Node(element, this.tail, first);
        first.setNext(newAdd);
        this.tail.setPrev(newAdd);
        nelems += 1;
        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param element data to be added
     * @param index index at which data is added
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        //TODO: Implementation for throwing exceptions followed by 
        //implementation of adding the new data
        if (index < 0 || this.size() < index){
            throw new IndexOutOfBoundsException("Out of Bounds");
        }

        if (element == null){
            throw new NullPointerException("Data is null");
        }

        if (isEmpty()){
            Node newAdd = new Node(element, this.head, this.tail);
            head.setNext(newAdd);
            tail.setPrev(newAdd);
        }

        //if not empty puts element at the index and moves rest below it
        else {
            Node after = this.getNth(index);
            Node before = this.getNth(index).getPrev();
            Node newAdd = new Node(element, after, before);
            after.setPrev(newAdd);
            before.setNext(newAdd);
        }

        nelems += 1;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        //TODO: implement clear
        this.tail.prev = this.head;
        this.head.next = this.tail;
        nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element to be seen if in the list
     * @return true if in the list
     * @return false if not in the list
     */
    @Override
    public boolean contains(Object element) {
        T data = (T) element;
        //TODO: Fill in implementation
        if (isEmpty()){
            return false;
        }
        Node current = this.head.getNext();
        while (current != this.tail){
            if (current.getElement(). equals(element)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index at which data wanted
     * @throw IndexOutOfBoundsException
     * @return the data by calling getElement
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        //TODO: Fill in implementation to get the node at index
        if (index < 0 || this.size() <= index){
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        Node wanted = this.getNth(index);

        return wanted.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index at which wanted
     * @return the node
     */
    private Node getNth(int index) {
        //TODO: implement
        if (isEmpty()){
            return null;
        }

        Node current = this.head.getNext();
        for (int i = 0; i< index; i++){
            current = current.getNext();
        }

        return current;
    }

    /**
     * Determine if the list empty
     *
     * @return boolean true or false
     */
    @Override
    public boolean isEmpty() {
        //TODO: implement isEmpty
        if (nelems != 0){
            return false;
        }
        return true;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index
     * @throws IndexOutOfBoundsException
     * @return the data of removed element
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        //TODO: Fill in implementation
        if (index < 0 || this.size() <= index){
            throw new IndexOutOfBoundsException("Out of Bounds");
        }

        Node remove = this.getNth(index);
        Node removeNext = remove.getNext();
        Node removePrev = remove.getPrev();
        removeNext.setPrev(removePrev);
        removePrev.setNext(removeNext);

        nelems -= 1;

        return remove.getElement();
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index at which element is set
     * @param element to be set
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     * @return previous element before it was changed
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        //TODO: Fill in implmentation
        if (index < 0 || this.size() <= index){
            throw new IndexOutOfBoundsException("Out of Bounds");
        }

        if (element == null){
            throw new NullPointerException("Data is null");
        }
        T before = get(index);  // saves the previous value
        Node want = this.getNth(index);
        want.setElement(element);

        return before;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return nelems
     */
    @Override
    public int size() {
        //TODO: complete implementation
        return nelems;
    }

    /**
     * Inserts another linked list of the same type into this one
     *
     * @param index where it will be spliced
     * @param otherList which will be spliced
     * @throws IndexOutOfBoundsException
     */
    public void splice(int index, DoublyLinkedList<T> otherList)
            throws IndexOutOfBoundsException {
        //TODO: Determine if index is valid

        if (index < 0 || index > this.size()){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        //TODO: Splicing implementation. HINT: remember DoublyLinkedList's
        // have dummy nodes

        if (this.isEmpty()){ // if empty list adds the otherlist to it
            this.head.next = otherList.head.next;
            this.tail.prev = otherList.tail.prev;
            nelems = nelems + otherList.size();
        }

        else {

            Node curr = this.getNth(index);
            Node currBefore = this.getNth(index).prev;
            Node next = otherList.head.next;
            Node prev = otherList.tail.prev;

            curr.setPrev(prev);
            prev.setNext(curr);
            currBefore.setNext(next);
            next.setPrev(currBefore);
            nelems = nelems + otherList.size();
        }

    }

    /**
     * Determine the starting indices that match the subSequence
     *
     * @param subsequence
     * @return starting indexes
     */
    public int[] match(DoublyLinkedList<T> subsequence) {

        //A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();

        //TODO: Add implementation to find the starting indices
        Node current = this.head.next;
        for (int i =0; i<= nelems-subsequence.size(); i++){
            boolean match = true;
            for (int j = 0; j< subsequence.size(); j++){
                if (!this.get(i+j).equals(subsequence.get(j))){
                    match = false;
                    break;
                }
            }
            if (match){
                indices.add(i);
            }
            current = current.getNext();
        }

        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        return startingIndices;
    }

}



