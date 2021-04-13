/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests methods from sorts.java
 * @author Hetsvi
 * @since 29 oct 2018
 */
public class SortsTester {

    ArrayList<Pair> sorted = new ArrayList<Pair>();
    ArrayList<Pair> reversed = new ArrayList<Pair>();
    ArrayList<Pair> mostSorted = new ArrayList<Pair>();
    ArrayList<Pair> completeRandom = new ArrayList<Pair>();

    /**
     * runs before each test. adds to empty list
     */
    @Before
    public void setUp(){
        sorted = new ArrayList<Pair>();  // make list
        sorted.add(new Pair("one", 0));
        sorted.add(new Pair("two", 5));
        sorted.add(new Pair("three", 10));
        sorted.add(new Pair("four", 15));
        sorted.add(new Pair("five", 20));

        reversed = new ArrayList<Pair>();
        reversed.add(new Pair("five", 100));
        reversed.add(new Pair("four", 10));
        reversed.add(new Pair("three", 8));
        reversed.add(new Pair("two", 5));
        reversed.add(new Pair("one", 1));


        mostSorted = new ArrayList<Pair>();
        mostSorted.add(new Pair("a", 1));
        mostSorted.add(new Pair("b", 5));
        mostSorted.add(new Pair("c", 2));
        mostSorted.add(new Pair("d", 10));

        completeRandom = new ArrayList<Pair>();
        completeRandom.add(new Pair("a", -1));
        completeRandom.add(new Pair("b", 100));
        completeRandom.add(new Pair("c", 98));
        completeRandom.add(new Pair("d", 0));
        completeRandom.add(new Pair("e", 0));
    }

    /**
     * tests insertion
     */
    @Test
    public void testInsertion(){
        Sorts.insertionSort(sorted, 0, sorted.size());
        assertEquals(5, sorted.get(1).count);

        Sorts.insertionSort(reversed, 0, 5);
        assertEquals(100, reversed.get(4).count);

        Sorts.insertionSort(mostSorted, 0, 4);
        assertEquals(1, mostSorted.get(0).count);

        Sorts.insertionSort(completeRandom, 0, 5);
        assertEquals(-1, completeRandom.get(0).count);

    }

    /**
     * tests quick sort
     */
    @Test
    public void testQuick(){
        Sorts.quickSort(sorted, 0, 5);
        assertEquals(20, sorted.get(4).count);

        Sorts.quickSort(reversed, 0, 5);
        assertEquals(5, reversed.get(1).count);

        Sorts.quickSort(mostSorted, 0, 4);
        assertEquals(10, mostSorted.get(3).count);

        Sorts.quickSort(completeRandom, 0, 5);
        assertEquals(0, completeRandom.get(1).count);

    }

    /**
     * tests quick with index
     */
    @Test
    public void testIndexQuick(){
        ArrayList<Pair> iList = new ArrayList<Pair>();
        iList = new ArrayList<>();
        iList.add(new Pair("a", 7));
        iList.add(new Pair("b", 27 ));
        iList.add(new Pair("c", 6));
        iList.add(new Pair("d", 25));
        iList.add(new Pair("e", 8));
        iList.add(new Pair("f", 81));

        Sorts.quickSort(iList, 1, 4);
        assertEquals(6, iList.get(1).count);

    }

    /**
     * tests insertion with index
     */
    @Test
    public void testIndexInsertion(){
        ArrayList<Pair> iList = new ArrayList<Pair>();
        iList = new ArrayList<>();
        iList.add(new Pair("a", 7));
        iList.add(new Pair("b", 27 ));
        iList.add(new Pair("c", 6));
        iList.add(new Pair("d", 25));
        iList.add(new Pair("e", 8));
        iList.add(new Pair("f", 81));

        Sorts.insertionSort(iList, 1, 4);
        assertEquals(7, iList.get(1).count);

    }

    /**
     * tests merge with index
     */
    @Test
    public void testIndexMerge(){
        ArrayList<Pair> iList = new ArrayList<Pair>();
        iList = new ArrayList<>();
        iList.add(new Pair("a", 7));
        iList.add(new Pair("b", 27 ));
        iList.add(new Pair("c", 6));
        iList.add(new Pair("d", 25));
        iList.add(new Pair("e", 8));
        iList.add(new Pair("f", 81));

        Sorts.mergeSort(iList, 1, 4);
        assertEquals(6, iList.get(1).count);

    }

    /**
     * tests merge
     */
    @Test
    public void testMerge(){
        Sorts.mergeSort(sorted, 0, sorted.size());
        assertEquals(0, sorted.get(0).count);

        Sorts.mergeSort(reversed, 0, reversed.size());
        assertEquals(5, reversed.get(1).count);

        Sorts.mergeSort(mostSorted, 0, mostSorted.size());
        assertEquals(5, mostSorted.get(2).count);

        Sorts.mergeSort(completeRandom, 0, completeRandom.size());
        assertEquals(100, completeRandom.get(4).count);

    }

}