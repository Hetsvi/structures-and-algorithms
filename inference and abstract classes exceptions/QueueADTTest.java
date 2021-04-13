/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * It tests QueueADT
 *
 * @author Hetsvi
 * @since Dec 2018
 */
public class QueueADTTest {

    public static CircularArrayQueue empty;
    public static CircularArrayQueue one;
    public static CircularArrayQueue many;
    public static CircularArrayQueue doubleMany;
    public static CircularArrayQueue cArray;

    /**
     * Sets up
     */
    @Before
    public void setUp(){
        empty = new CircularArrayQueue();
        one = new CircularArrayQueue();
        one.add(2);
        many = new CircularArrayQueue();
        for(int i = 1; i<= 5; i++){
            many.add(i);
        }

        doubleMany = new CircularArrayQueue();
        for(int i = 10; i<0; i--){
            doubleMany.add(i);
        }

    }

    /**
     * Tests add
     */
    @Test
    public void testAdd() {
        empty.add(1);    //adds to empty
        assertEquals(1, empty.size());

        one.add(4);
        assertEquals(2, one.size());

        many.add(20);
        many.add(50);
        many.add(6);
        many.add(10);
        assertEquals(9, many.size());

        doubleMany.add(11);
        doubleMany.add(12);
        assertEquals(12, doubleMany.size());

    }

    /**
     * Tests empty
     */
    @Test
    public void testEmpty() {
        assertTrue(empty.isEmpty());
        assertTrue(!one.isEmpty());
        assertTrue(!many.isEmpty());
        assertTrue(!doubleMany.isEmpty());
    }

    /**
     * Tests peek
     */
    @Test
    public void testPeek() {
        assertEquals(0, empty.peek());
        assertEquals(2, one.peek());
        assertEquals(1, many.peek());
        assertEquals(10, doubleMany.peek());
    }

    /**
     * Tests remove
     */
    @Test
    public void testRemove() {
        assertEquals(2, one.remove());
        assertEquals(1, many.remove());
        assertEquals(10, doubleMany.remove());
        assertEquals(9, doubleMany.remove());
        assertEquals(0, empty.remove());
    }

    /**
     * tests clear
     */
    @Test
    public void testClear() {
        empty.clear();
        assertEquals(0, empty.size());
        one.clear();
        assertEquals(0, one.size());
        many.clear();
        assertEquals(0, many.size());
        doubleMany.clear();
        assertEquals(0, doubleMany.size());

    }

    /**
     * tests size
     */
    @Test
    public void testSize() {
        assertEquals(0, empty.size());
        assertEquals(1, one.size());
        assertEquals(5, many.size());
        assertEquals(10, doubleMany.size());
    }

    /**
     * tests many methods together
     */
    @Test
    public void testArray(){
        cArray = new CircularArrayQueue();
        cArray.add(2);
        cArray.add(10);
        cArray.add(4);
        cArray.add(50);
        assertEquals(4, cArray.size());
        cArray.add(100);
        cArray.add(90);
        cArray.remove();
        assertEquals(5, cArray.size());
        cArray.clear();
        assertEquals(0, cArray.size());
    }

    /**
     * tests when capacity is less than 0
     */
    @Test
    public void testCapacity(){
        cArray = new CircularArrayQueue(-1);
        for(int i =0; i< 10; i++){
            cArray.add(i);
        }

        assertEquals(10, cArray.size());

        cArray = new CircularArrayQueue(-10);
        assertEquals(1, cArray.size());

        cArray = new CircularArrayQueue(-1);
        cArray.add(2);
        assertEquals(1, cArray.size());   // checks if it resets to 1

    }

    /**
     * Tests many together
     */
    @Test
    public void testFunc(){
        cArray = new CircularArrayQueue();
        cArray.add(2);
        cArray.add(10);
        cArray.add(100);
        cArray.add(1000);
        cArray.remove();
        cArray.remove();
        assertEquals(100, cArray.peek());
        assertEquals(2, cArray.size());
        cArray.add(100);
        cArray.add(90);
        assertEquals(4, cArray.size());

    }
}