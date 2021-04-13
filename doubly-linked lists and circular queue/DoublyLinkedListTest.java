/* Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.AbstractList;
import java.util.LinkedList;

/**
 * These tests test the methods from
 * doubly linked list class.
 *
 * @author Hetsvi
 * @since 20 October 2018
 */

public class DoublyLinkedListTest {

    AbstractList<Character> alpha;
    AbstractList<Integer> nothing;
    AbstractList<Integer> oneEleme;
    AbstractList<Integer> twoEleme;
    AbstractList<Integer> more;
    AbstractList<String> sentence;
    DoublyLinkedList<Character> splice;
    DoublyLinkedList<Character> testSplice;
    DoublyLinkedList<Character> testMatch;
    DoublyLinkedList<Character> testSub;
    DoublyLinkedList<Character> testMatch1;
    DoublyLinkedList<Character> testSub1;
    DoublyLinkedList<Character> testMatch2;
    DoublyLinkedList<Character> testSub2;
    DoublyLinkedList<Character> testMatch3;
    DoublyLinkedList<Character> testSub3;

    /**
     * Runs before every test and intializes the abstract list to doubly linked
     * list
     */
    @Before
    public void setUp() {
        alpha = new DoublyLinkedList<>();
        for (char ch = 'a'; ch <= 'z'; ch++){
            alpha.add(ch);
        }

        nothing = new DoublyLinkedList<>();

        oneEleme = new DoublyLinkedList<>();
        oneEleme.add(10);

        twoEleme = new DoublyLinkedList<>();
        twoEleme.add(0);
        twoEleme.add(-5);

        more = new DoublyLinkedList<>();
        more.add(7);
        more.add(-2);
        more.add(5);
        more.add(4);
        more.add(0);
        more.add(-1);

        sentence = new DoublyLinkedList<>();
        sentence.add("GoOd");
        sentence.add("cHIld");

    }
    /**
     * Tests add method
     */
    @Test
    public void testAdd(){
        nothing.add(20);
        assertTrue(!nothing.isEmpty());


        oneEleme.add(20);
        assertEquals(2, oneEleme.size());

        twoEleme.add(100);
        twoEleme.add(10);
        twoEleme.add(5);
        twoEleme.add(0);
        assertEquals(6, twoEleme.size());

        more.add(-7);
        assertEquals(7, more.size());

        sentence.add("good Girl");
        assertEquals(3, sentence.size());
    }

    /**
     * tests the exception of add method
     */
    @Test(expected = NullPointerException.class)
    public void testExceptionAdd() {
        oneEleme.add(null);

    }

    /**
     * tests the add at index method
     */
    @Test
    public void testAddSecond() {

        more.add(5, 20);
        assertEquals(new Integer(20), more.get(5));
        more.add(2, 10);
        assertEquals(new Integer(10), more.get(2));
        nothing.add(0, 96);
        assertEquals(new Integer(96), nothing.get(0));
        twoEleme.add(0, 9);
        assertEquals(new Integer(9), twoEleme.get(0));
        sentence.add(0, "children");
        assertEquals("children", sentence.get(0));

    }

    /**
     * tests the clear method
     */
    @Test
    public void testClear() {
        more.clear();
        assertTrue("More clear", more.isEmpty());
        nothing.clear();
        assertTrue("Nothing clear", nothing.isEmpty());
        oneEleme.clear();
        assertTrue("One Elem clear", oneEleme.isEmpty());
        sentence.clear();
        assertTrue("Sentence clear", sentence.isEmpty());
        alpha.clear();
        assertTrue("Alpha clear", alpha.isEmpty());


    }

    /**
     * tests the contains method
     */
    @Test
    public void testContains() {
        assertEquals(false, nothing.contains(1)); //when list empty
        assertEquals(false, more.contains(null));
        assertEquals(true, more.contains(-2));
        assertEquals(false, oneEleme.contains(6));
        assertEquals(true, oneEleme.contains(10));
        assertEquals(true, alpha.contains('j'));
        assertEquals(false, twoEleme.contains(90));
        assertEquals(false, sentence.contains("yayy"));

    }

    /**
     * tests the get method
     */
    @Test
    public void testGet() {
        more.add(5, 100);
        assertEquals(new Integer(-1), more.get(6));
        assertEquals(new Character('d'), alpha.get(3));
        assertEquals(new Integer(10), oneEleme.get(0));
        assertEquals(new Integer(0), twoEleme.get(0));
        assertEquals(new String("cHIld"), sentence.get(1));

    }

    /**
     * tests the isEmpty method
     */
    @Test
    public void testisEmpty() {
        assertEquals(true, nothing.isEmpty());
        assertEquals(false, oneEleme.isEmpty());
        assertEquals(false, twoEleme.isEmpty());
        assertEquals(false, more.isEmpty());
        assertEquals(false, alpha.isEmpty());
        assertEquals(false, sentence.isEmpty());
        sentence.clear();
        assertEquals(true, sentence.isEmpty());

    }

    /**
     * tests the remove index method
     */
    @Test
    public void testRemoveIndex() {
        oneEleme.remove(0);
        assertTrue("One Elem empty", oneEleme.isEmpty());
        assertEquals(new Integer(-5), twoEleme.remove(1));
        assertEquals(new Integer(0), more.remove(4));
        assertEquals(new String("GoOd"), sentence.remove(0));
        assertEquals(new Character('p'), alpha.remove(15));

    }

    /**
     * tests the set method
     */
    @Test
    public void testSet() {
        more.set(0, 15);
        assertEquals(new Integer(15), more.get(0));
        //System.out.println(more.set(0,15));
        twoEleme.set(1, 100);
        assertEquals(new Integer(100), twoEleme.get(1));
        alpha.set(3, 'D');
        assertEquals(new Character('D'), alpha.get(3));
        sentence.set(0, "GOOD");
        assertEquals(new String("GOOD"), sentence.get(0));
    }

    /**
     * tests the size method
     */
    @Test
    public void testSize() {
        assertEquals(1, oneEleme.size());
        assertEquals(0, nothing.size());
        assertEquals(6, more.size());
        assertEquals(2, sentence.size());
        assertEquals(26, alpha.size());
    }

    /**
     * tests the exception for add at index method
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutBoundAddIndex(){
        oneEleme.add(5, 9);
        oneEleme.add(-1, 15);
        oneEleme.add(2, 20);
        oneEleme.add(-1, 100);
        more.add(-15, 7);
        twoEleme.add(10, 6);

    }

    /**
     * tests exception for add at index method
     */
    @Test(expected = NullPointerException.class)
    public void testNullPointerAddIndex(){
        oneEleme.add(1,null);
        nothing.add(0, null);
        twoEleme.add(5, null);

    }

    /**
     * tests the exception for get method
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexGet(){
        more.get(20);
        sentence.get(4);
        twoEleme.get(-1);
        more.get(9);

    }

    /**
     * tests the exception for remove
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        nothing.remove(0);
    }

    /**
     * tests the exception for set method
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexSet(){
        twoEleme.set(3, 3);
        more.set(8, 4);
        alpha.set(27, 'y');
    }

    /**
     * tests the exception for set method
     */
    @Test(expected = NullPointerException.class)
    public void testNullSet(){
        oneEleme.set(0, null);
        twoEleme.set(0, null);
        more.set(0, null);
        alpha.set(0, null);
    }

    /**
     * Test exception of splice method
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBounds(){
        splice = new DoublyLinkedList<>();
        for (char c = 'c'; c < 'g'; c++) {
            splice.add(c);
        }

        testSplice = new DoublyLinkedList<>();
        for (char c = 't'; c < 'z'; c++) {
            testSplice.add(c);
        }

        testSplice.splice(10, splice);

    }

    /**
     * tests the splice method
     */
    @Test
    public void testSplice() {
        splice = new DoublyLinkedList<>();
        for (char c = 'c'; c < 'g'; c++) {
            splice.add(c);
        }

        testSplice = new DoublyLinkedList<>();
        for (char c = 't'; c < 'z'; c++) {
            testSplice.add(c);
        }

        testSplice.splice(1, splice);

        assertEquals(new Character('c'), testSplice.get(1));
        assertEquals(new Character('y'), testSplice.get(9));
        assertEquals(new Character('x'), testSplice.get(8));
    }

    /**
     * more test for splice method
     */
    @Test
    public void testSplice1() {
        splice = new DoublyLinkedList<>();
        splice.add('a');

        testSplice = new DoublyLinkedList<>();
       // testsplice.add('b');

        testSplice.splice(0, splice); // when main list empty
        assertEquals(new Character('a'), testSplice.get(0));
    }

    /**
     * tests the match method
     */
    @Test
    public void testMatch() {
        testMatch = new DoublyLinkedList<>();
        testMatch.add('a');
        testMatch.add('b');
        testMatch.add('a');
        testMatch.add('d');
        testMatch.add('a');
        testMatch.add('b');
        testMatch.add('a');

        testSub = new DoublyLinkedList<>();
        testSub.add('a');
        testSub.add('b');
        testSub.add('a');

        int[] test = testMatch.match(testSub);
        int[] testMatch = {0, 4};

        assertArrayEquals(testMatch, test);

        testMatch1 = new DoublyLinkedList<>();
        testMatch1.add('a');
        testMatch1.add('z');
        testMatch1.add('y');
        testMatch1.add('a');
        testMatch1.add('z');
        testMatch1.add('y');
        testMatch1.add('y');

        testSub1 = new DoublyLinkedList<>();
        testSub1.add('z');
        testSub1.add('y');

        int[] test1 = testMatch1.match(testSub1);
        int[] testMatch1 = {1,4};

        assertArrayEquals(testMatch1, test1);

        testMatch2 = new DoublyLinkedList<>();
        testMatch2.add('a');
        testMatch2.add('a');
        testMatch2.add('b');
        testMatch2.add('b');

        testSub2 = new DoublyLinkedList<>();
        testSub2.add('q');
        testSub2.add('r');

        int[] test2 = testMatch2.match(testSub2);

        assertEquals(0, test2.length);

        testMatch3 = new DoublyLinkedList<>();
        testMatch3.add('a');
        testMatch3.add('a');
        testMatch3.add('a');
        testMatch3.add('a');
        testMatch3.add('a');
        testMatch3.add('z');

        testSub3 = new DoublyLinkedList<>();
        testSub3.add('a');

        int[] test3 = testMatch3.match(testSub3);
        int[] testMatch3 = {0,1,2,3,4};

        assertArrayEquals(testMatch3, test3);


    }

    /**
     * tests the order of the list
     */
    @Test
    public void testOrder(){
        int index = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            assert (alpha.get(index++)== c);
        }
    }
}