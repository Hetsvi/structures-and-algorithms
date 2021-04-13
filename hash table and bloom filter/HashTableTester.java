/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.Scanner;

/**
 * tests the hashtable class
 * @author Hetsvi
 * @since 4 Nov
 */
public class HashTableTester {

    private HashTable hVal1;
    private HashTable hVal2;
    private HashTable hVal3;
    private HashTable hVal4;
    private HashTable hVal5;

    /**
     * runs before each one
     */
    @Before
    public void setUp(){
        hVal1 = new HashTable(5);
        hVal2 = new HashTable(10, "put.txt");
        hVal3 = new HashTable(20);
        hVal4 = new HashTable(0);

        /*hVal5 = new HashTable(1);
        try{
            File file = new File("./src/dict.txt");
            Scanner s = new Scanner(file);
            while(s.hasNext()){
                hVal5.insert(s.next());
            }

           s.close();

       }

        catch (IOException e){
            e.printStackTrace();
        }*/
    }

    /**
     * tests insert
     */
    @Test
    public void testInsert(){
        hVal1.insert("a");
        hVal1.insert("b");
        hVal1.insert("c");
        hVal1.insert("d");
       // hVal1.printTable();

        assertEquals(true, hVal2.insert("a"));
        assertEquals(true, hVal2.insert("b"));
        assertEquals(true, hVal4.insert("b"));
    }

    /**
     * tests delete
     */
    @Test
    public void testDelete(){
        hVal2.insert("a");
        assertEquals(true, hVal2.delete("a"));
        assertEquals(false, hVal4.delete("a")); // empty table

        hVal1.insert("a");
        hVal1.insert("b");
        assertEquals(true, hVal1.delete("a"));
        assertEquals(true, hVal1.delete("b"));


    }

    /**
     * tests lookup
     */
    @Test
    public void testLookup(){
        assertEquals(false, hVal1.lookup("a"));
        hVal2.insert("a");
        assertEquals(true, hVal2.lookup("a"));
        assertEquals(false, hVal4.lookup("a")); // empty table
    }

    /**
     * tests size
     */
    @Test
    public void testSize(){
        hVal1.insert("a");
        hVal1.insert("b");
        assertEquals(2, hVal1.getSize());
        assertEquals(0, hVal2.getSize());
    }

    /**
     * tests insert exception
     */
    @Test(expected = NullPointerException.class)
    public void testExceptionInsert(){
        hVal1.insert(null);
    }

    /**
     * tests delete exception
     */
    @Test(expected = NullPointerException.class)
    public void testExceptionDelete(){
        hVal1.delete(null);
    }

    /**
     * tetss lookup exception
     */
    @Test(expected = NullPointerException.class)
    public void  testExceptionLookup(){
        hVal1.lookup(null);
    }

    /**
     * tests when many methods called during same timeish
     */
    @Test
    public void testMix(){
        hVal1.insert("a");
        hVal1.delete("a");
        hVal1.insert("b");
        hVal1.insert("c");
        hVal1.lookup("b");
        hVal1.delete("b");

        assertEquals(1, hVal1.getSize());
        assertEquals(true, hVal1.delete("c"));
        assertEquals(true, hVal1.insert("d"));
        assertEquals(true, hVal1.lookup("d"));

        hVal1.printTable();
    }

}