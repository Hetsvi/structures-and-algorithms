/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */


/**
 * This has various methods like offer, poll and peek and uses dHeap.
 * @param <T>
 *
 * @author Hetsvi
 * @since 12 November
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {

    dHeap<T>pQueue;

    public MyPriorityQueue(int initialSize) {
        //TODO
        pQueue = new dHeap<>(initialSize);
    }

    /**
     * Inserts an element into the Priority Queue. The element received cannot
     * be null.
     *
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer(T element) throws NullPointerException {
        if(element == null){
            throw new NullPointerException();
        }

        pQueue.add(element);   //adds element to queue
        return true; //XXX-CHANGE-XXX
        //TODO
    }

    /**
     * Retrieves the head of this Priority Queue (largest element), or null if
     * the queue is empty.
     *
     * @return The head of the queue (largest element), or null if queue is
     * empty.
     */
    public T poll() {
        T head;
        if(pQueue.size() == 0){
            head = null;
        }

        head = pQueue.element();
        pQueue.remove();       // removes at root
        return head; //XXX-CHANGE-XXX
        //TODO
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() {
        pQueue.clear();
        //TODO
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null
     * if this queue is empty.
     *
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() {
        T head;
        if(pQueue.size() == 0){
            head = null;
        }

        else{
            head = pQueue.element();   //gets the element at root
        }
        return head; //XXX-CHANGE-XXX
    }
}

