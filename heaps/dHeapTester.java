// ec3

/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * This is the tester for dHeap class
 * @author Hetsvi
 * @since 12 November
 */
public class dHeapTester {

    private  dHeap<Integer> dArray;
    private  dHeap<Integer> dArray1;
    private  dHeap<Integer> dArray2;


    /**
     * Initializes
     */
    @Before
    public void setUp(){

        dArray = new dHeap<Integer>(6, 10, true); // makes new
        dArray1 = new dHeap<Integer>(2, 5, false);
        dArray2 = new dHeap<Integer>(1, 10, true);
    }

    /**
     * tests add
     */
    @Test
    public void testAdd(){

        dArray.add(1);
        dArray.add(4);
        dArray.add(3);
        dArray.add(2);
        dArray.add(6);

        assertEquals(5, dArray.size());



       dArray1.add(100);
       dArray1.add(-10);
       dArray1.add(-2);
       dArray1.add(15);
       dArray1.add(15);
       dArray1.add(1);
       dArray1.add(10);
       dArray1.add(90);
       dArray1.add(-4);
       dArray1.add(12);
       dArray1.add(11);
       dArray1.add(2);
       dArray1.add(82);
       dArray1.add(9);
       dArray1.add(26);
       dArray1.add(22);
       dArray1.add(14);
       dArray1.add(8);
       assertEquals(18, dArray1.size());


    }

    /**
     * tests remove
     */
    @Test
    public void testRemove(){
        dArray.add(1);
        dArray.add(4);
        dArray.add(3);
        dArray.add(2);
        dArray.add(6);

        dArray.remove();
        assertEquals(4, dArray.size());

       dArray1.add(100);
        dArray1.add(-10);
        dArray1.add(-2);
        dArray1.add(15);
        dArray1.add(15);
        dArray1.add(1);
        dArray1.remove();

        assertEquals(5, dArray1.size());

        dArray2.add(100);
        dArray2.add(-10);
        dArray2.add(-2);
        dArray2.add(15);
        dArray2.add(15);
        dArray2.add(1);
        dArray2.add(10);
        dArray2.add(90);
        dArray2.add(-4);
        dArray2.add(12);
        dArray2.add(11);
        dArray2.add(2);
        dArray2.add(82);
        dArray2.add(9);
        dArray2.add(26);
        dArray2.add(22);
        dArray2.add(14);
        dArray2.add(8);


        dArray.add(100);
        dArray.add(-10);
        dArray.add(-2);
        dArray.add(15);
        dArray.add(15);
        dArray.add(1);
        dArray.add(10);
        dArray.add(90);
        dArray.add(-4);
        dArray.add(12);
        dArray.add(11);
        dArray.add(2);
        dArray.add(82);
        dArray.add(9);
        dArray.add(26);
        dArray.add(22);
        dArray.add(14);
        dArray.add(8);
        dArray.add(8);
        dArray.add(8);
        dArray.add(18);
        dArray.add(8);
        dArray.add(8);


    }

    /**
     * tests clear
     */
    @Test
    public void testClear(){
        dArray.add(1);
        dArray.add(4);
        dArray.add(3);
        dArray.add(2);
        dArray.add(6);

        dArray.clear();
        assertEquals(0, dArray.size());

        dArray1.add(100);
        dArray1.add(-10);
        dArray1.add(-2);
        dArray1.add(15);
        dArray1.add(15);
        dArray1.add(1);
        dArray1.clear();

        assertEquals(0, dArray1.size());

    }

    /**
     * tests element
     */
    @Test
    public void testElement(){
        dArray.add(1);
        dArray.add(4);
        assertEquals( new Integer(4), dArray.element());
    }

    /**
     * tests the size
     */
    @Test
    public void testSize(){
        dArray.add(1);
        dArray.add(4);
        dArray.add(3);
        dArray.add(2);
        dArray.add(6);

        assertEquals(5, dArray.size());
    }

    /**
     * tests add exception
     */
    @Test(expected = NullPointerException.class)
    public void testAddNull(){
        dArray.add(null);

    }

    /**
     * tests remove exception
     */
   @Test(expected = NoSuchElementException.class)
    public void testRemoveEmpty(){
        assertEquals(new Integer(5), dArray.remove());

    }

    /**
     * adds many
     */
    @Test
    public void testAddMany(){
        for(int i =0; i<30; i++){
            dArray.add(i);
        }
    }

    @Test
    public void testA(){
        for(int i =0; i<30; i++){
            dArray.add(i);
        }

        for(int i =0; i<30; i++){
            dArray.remove();
        }
    }

    /**
     * tests remove and add
     */
    @Test
    public void testAlternate(){
        dArray.add(1);
        dArray.add(2);
        dArray.add(-5);
        dArray.remove();
        dArray.add(2);
        dArray.remove();

        assertEquals(2, dArray.size());
    }

    /**
     * tests findGreaterThanK
     */
    @Test
    public void test(){
        dArray.add(1);
        dArray.add(2);
        dArray.add(4);
        dArray.add(5);
        dArray.add(6);
        dArray.add(5);
        dArray.add(6);
        dArray.add(5);
        dArray.add(6);
        dArray.add(5);
        dArray.add(6);
        LinkedList a  = dArray.findGreaterThanK(5);
    }

    /**
     * Tests findSum
     */
    @Test
    public void test1(){
        int[ ] a = {60, 5, 27, 3, 12, 9, 14};
        int b = dArray.findSum(a, 3, 6);
        System.out.println(b);
    }
}