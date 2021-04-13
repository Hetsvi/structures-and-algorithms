/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * This class will explore the runtime of the method getNameCounts, and compare the performance
 * of LinkedList with MRUList. The method getNamesCounts returns an ArrayList of Pair, which
 * contains names with count of each name that appears in Pride And Prejudice.
 *
 * This class would also explore the runtime of three sorting algorithm: Insertion Sort, Merge
 * Sort and Quick Sort, by comparing the time it takes to sort the list of Pair returned 
 * by getNamesCounts
 *
 * @author Xindong Cai
 * @since ${May 4, 2018}
 */
public class RuntimeExplorer {

    // TODO: You will need many constant variables declared here, always specify their meanings
    private static int STARTING = 5000; //starts the loop
    private static int TILL = 30000;    // goes till
    private static int INCREASE = 5; // increases the thing for runtime
    private static int EACH = 5;   // number of runs
    private static int TEN = 10;   //read file limit
    private static int TIMES = 2;   // number of runs
    private static int SORTSTAART = 1000;  // start for sorts


    // Then other non-constant variables, specify their meaning when necessary
    static String prideAndPrejudice = "./src/PrideAndPredjudice.txt";
    // TODO: add variables that stores the filename of names files like above

    static String smallNames = "./src/SmallNames.txt";
    static String mediumNames = "./src/MediumNames.txt";
    static String largeNames = "./src/LargeNames.txt";

    /**
     * The main method that drives the RuntimeExplorer.
     * Requirement: To help you establish good coding habits, in this assignment, you can only
     * delare local variables and call other methods in main. You should leave all the
     * implementaion part in your other methods, as this will keep your main method
     * short and clear. You will lose style points if this requirement is not met.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Prints runtime
        printRunTime("LinkedList", smallNames, STARTING, STARTING,
                INCREASE, EACH);
        printRunTime("ArrayList", smallNames, STARTING, STARTING,
                INCREASE, EACH);
        printRunTime("LinkedList", mediumNames, STARTING, STARTING,
                INCREASE, TIMES);
        printRunTime("ArrayList", mediumNames, STARTING, STARTING,
                INCREASE, TIMES);
        printRunTime("LinkedList", largeNames, STARTING, STARTING,
                INCREASE, TIMES);
        printRunTime("ArrayList", largeNames, STARTING, STARTING,
                INCREASE, TIMES) ;

        //Helps see the main character and the second main and third main
        AbstractList<String> names = readNames(smallNames, "LinkedList");
        ArrayList<String> word = readWords(prideAndPrejudice, TEN, true);
        ArrayList<Pair> count = getNameCounts(names, word);
        Sorts.insertionSort(count, 0, count.size());
        printCharacterQuestion(count);

       // Sorts runtime
        AbstractList<String> namesSort = readNames(largeNames, "LinkedList");
        ArrayList<String> wordSort= readWords(prideAndPrejudice, TEN, true);
        ArrayList<Pair> sortCount= getNameCounts(namesSort, wordSort);
        ArrayList<Pair> deepInsertion1 = deepCopyArrayList(sortCount);
        ArrayList<Pair> deepMerge1 = deepCopyArrayList(sortCount);
        ArrayList<Pair> deepQuick1 = deepCopyArrayList(sortCount);
        printSortsTime(deepMerge1, "MergeSort", SORTSTAART, TIMES);
        printSortsTime(deepQuick1, "QuickSort", SORTSTAART, TIMES);
        printSortsTime(deepInsertion1, "InsertionSort", SORTSTAART, TIMES);

//        ArrayList<Pair> sorted = new ArrayList<Pair>();
//        sorted = new ArrayList<Pair>();
//        sorted.add(new Pair("one", 0));
//        sorted.add(new Pair("two", 5));
//        sorted.add(new Pair("three", 10));
//        sorted.add(new Pair("four", 15));
//        sorted.add(new Pair("five", 20));
//        printSortsTime(deepCopyArrayList(sorted), "InsertionSort", sorted.size(), 2);
//        printSortsTime(deepCopyArrayList(sorted), "MergeSort", sorted.size(), 2);
//        printSortsTime(deepCopyArrayList(sorted), "QuickSort", sorted.size(), 2);


       }

    /**
     * This method would read the names from the given file and store them into an LinkedList or
     * MRUList based on the given boolean useMRU.
     *
     * @param fileName the given file to be read
     * //@param useMRU   if true, store names into MRUList. Otherwise, store names into LinkedList
     * @param listType "LinkedList" or "MRUList" or "ArrayList" which will be used in getNameCounts()
     * @return an LinkedList or MRUList containing all the names from the given file
     */
    public static AbstractList<String> readNames(String fileName, String listType) {

        // TODO
        LinkedList<String> linkedList = new LinkedList<String>();
        MRUList<String> list = new MRUList<String>();
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            File wantFile = new File(fileName);
            Scanner file = new Scanner(wantFile);
            if (listType.equals("LinkedList")) {
                while (file.hasNext()) {
                    linkedList.add(file.next());
                }
                file.close();
                return linkedList;
            }
            else if (listType.equals("ArrayList")) {
                while (file.hasNext()) {
                    arrayList.add(file.next());
                }
                file.close();
                return arrayList;
            }

            else if(listType.equals("MRUList")){
                while (file.hasNext()) {
                    list.add(file.next());
                }
                file.close();
                return list;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * This method would read certain number of words from the given file and store them into
     * an ArrayList
     *
     * @param fileName the given file to be read
     * @param numWords the number of words to read from given file
     * @param readAll  if true, read all words from given file. Otherwise, only read numWords
     * @return an ArrayList containing all the words from the given file
     */
    public static ArrayList<String> readWords(String fileName, int numWords,
                                             boolean readAll) {

        // TODO
        ArrayList<String> words = new ArrayList<String>();
        try {
            File read = new File(fileName);
            Scanner wordScanner = new Scanner(read);
            if (readAll == true) {
                while (wordScanner.hasNext()) {
                    words.add(wordScanner.next());
                }
                wordScanner.close();
            } else {
                for (int i = 0; i < numWords; i++) {
                    words.add(wordScanner.next());
                }
                wordScanner.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words;
    }

    /**
     * This method would return an ArrayList of Pair, which contains names with count of each name
     * that appears in the given ArrayList words. You must implement this method as efficient as
     * possible to save time later.
     *
     * @param names the given names to find in words
     * @param words the given words from Pride and Prejudice
     * @return an ArrayList of Pair, which contains names with count of each name
     */
    public static ArrayList<Pair> getNameCounts(AbstractList<String> names,
                                                ArrayList<String> words) {
        ArrayList<Pair> pList = new ArrayList<Pair>();
        Boolean count = false;

        for (int i = 0; i < words.size(); i++) {
            if (names.contains(words.get(i))) {    // checks if it has the word
                count = false;
                for (int j = 0; j < pList.size(); j++) {
                    if (pList.get(j).name.equals(words.get(i))) {
                        pList.get(j).count += 1;    // increases the count
                        count = true;
                    }
                }

                if (count == false) {
                    Pair newWord = new Pair(words.get(i));
                    pList.add(newWord);          // adds new word to list
                }
            }
        }
        return pList;
    }

    /**
     * Print run time of getNameCounts with different parameters (See details below)
     *
     * @param listType      LinkedList or MRUList which will be used in getNameCounts()
     * @param fileName      the input file which contains names
     * @param startSize     the initial size of words
     * @param incSize       the increased size for each time we run the test
     * @param numTest       the number of tests we need to run
     * @param eachTestTimes the number of times we run each test to take average runtime
     */
    public static void printRunTime(String listType, String fileName,
                                    int startSize, int incSize, int numTest,
                                    int eachTestTimes) {

        System.out.println("Class: " + listType + " (" + fileName + ")");
        System.out.println("=================================");

        if(listType.equals("LinkedList")){
            int counter = 1;
            for (int number = startSize; number <= numTest * incSize;
                 number += incSize) {

                long total = 0;
                long averageTime = 0;

                    for (int i = 0; i < eachTestTimes; i++) {
                        AbstractList<String> names = readNames(fileName,
                                "LinkedList");
                        ArrayList<String> word = readWords(prideAndPrejudice,
                                number, false);
                        long startTime = System.currentTimeMillis();
                        ArrayList<Pair> count = getNameCounts(names, word);
                        long endTime = System.currentTimeMillis();
                        long totalTime = endTime - startTime;
                        total += totalTime;
                    }
                averageTime = total/eachTestTimes;
                System.out.println(counter + ": "  + number + " words in "
                        + averageTime + " milliseconds");
                counter++;

            }
        }

        else if(listType.equals("ArrayList")){
            int counter = 1;
            for (int number = startSize; number <= numTest * incSize;
                 number += incSize) {

                long total = 0;
                long averageTime = 0;

                for (int i = 0; i < eachTestTimes; i++) {
                    AbstractList<String> names = readNames(fileName,
                            "ArrayList");
                    ArrayList<String> word = readWords(prideAndPrejudice,
                            number, false);
                    long startTime = System.currentTimeMillis();
                    ArrayList<Pair> count = getNameCounts(names, word);
                    long endTime = System.currentTimeMillis();
                    long totalTime = endTime - startTime;
                    total += totalTime;
                }
                averageTime = total/eachTestTimes; // average calculation
                System.out.println(counter + ": "  + number + " words in "
                        + averageTime + " milliseconds");
                counter++;

            }
        }


        else if(listType.equals("MRUList")){
            int counter = 1;
            for (int number = startSize; number <= numTest * incSize;
                 number += incSize) {

                long total = 0;
                long averageTime = 0;

                for (int i = 0; i < eachTestTimes; i++) {
                    AbstractList<String> names = readNames(fileName,
                            "MRUList");
                    ArrayList<String> word = readWords(prideAndPrejudice,
                            number, false);
                    long startTime = System.currentTimeMillis();
                    ArrayList<Pair> count = getNameCounts(names, word);
                    long endTime = System.currentTimeMillis();
                    long totalTime = endTime - startTime;
                    total += totalTime;
                }
                averageTime = total/eachTestTimes;
                System.out.println(counter + ": "  + number + " words in "
                        + averageTime + " milliseconds");
                counter++;

            }
        }
    }

    /**
     * Print the time it takes to sort nameCounts using Insertion Sort, Merge Sort or Quick Sort,
     * based on the given string sortAlg.
     *
     * @param namesCount the given ArrayList to sort
     * @param sortAlg if equals "QuickSort", use Quick Sort. If equals "MergeSort", use Merge Sort.
     *                If equals "InsertionSort", use Insertion Sort.
     * @oaram numPairs the number of pairs to sort
     * @param testTimes the number of times we run each test to take average runtime
     */
    public static void printSortsTime(ArrayList<Pair> namesCount, String sortAlg,
                                      int numPairs, int testTimes) {

        if(sortAlg.equals("QuickSort")) {
            long totalTime = 0;
            long averageTime = 0; // Keeps average
            System.out.println("Sorting nameCounts using " + sortAlg);
            for (int i = 0; i < testTimes; i++) {
                long startTime = System.nanoTime();
                Sorts.quickSort(namesCount, 0, numPairs);
                long endTime = System.nanoTime();
                long total = endTime - startTime;
                totalTime = totalTime +total;
            }
            averageTime = totalTime/testTimes;
            System.out.println(sortAlg + " takes " + averageTime +
                    " nanoseconds to sort " + numPairs +
                    " pairs in nameCounts\n");
        }

        else if(sortAlg.equals("MergeSort")){
            long totalTime = 0;
            long averageTime = 0;
            System.out.println("Sorting nameCounts using " + sortAlg);
            for (int i = 0; i < testTimes; i++) {
                long startTime = System.nanoTime();
                Sorts.mergeSort(namesCount, 0, numPairs);
                long endTime = System.nanoTime();
                long total = endTime - startTime;
                totalTime = totalTime +total;
            }
            averageTime = totalTime/testTimes; // finds average
            System.out.println(sortAlg + " takes " + averageTime +
                    " nanoseconds to sort " + numPairs +
                    " pairs in nameCounts\n");

        }

        else if(sortAlg.equals("InsertionSort")){
            long totalTime = 0;
            long averageTime = 0;
            System.out.println("Sorting nameCounts using " + sortAlg);
            for (int i = 0; i < testTimes; i++) {
                long startTime = System.nanoTime();
                Sorts.insertionSort(namesCount, 0, numPairs);
                long endTime = System.nanoTime();
                long total = endTime - startTime;
                totalTime = totalTime +total;

            }
            averageTime = totalTime/testTimes; // average by testTimes
            System.out.println(sortAlg + " takes " + averageTime +
                    " nanoseconds to sort " + numPairs +
                    " pairs in nameCounts\n");
        }

    }

    /**
     * Returns a deep copy of given ArrayList
     *
     * @param old the given old ArrayList
     */
    private static ArrayList<Pair> deepCopyArrayList(ArrayList<Pair> old) {
        ArrayList<Pair> copy = new ArrayList<Pair>(old.size());
        for (Pair i : old){
            copy.add(new Pair(i.getName(), i.getCount()));
        }
        return copy;
    }


        /**
         * Print the main character, the character appeared least times and the third main character
         * in Pride and Prejudice
         *
         * @param sorted a sorted ArrayList of Pair, which contains names with count of each name.
         */
        public static void printCharacterQuestion (ArrayList <Pair> sorted){
            // TODO
            int first = 1;
            int second = 2;
            int third = 3;

            String mainChar = sorted.get(sorted.size()-first).name;
            String secondChar = sorted.get(sorted.size()-second).name;
            String thirdChar = sorted.get(sorted.size()-third).name;

            System.out.println("In Pride and Prejudice: ");
            System.out.println("The main character is " + mainChar);
            System.out.println("The second main character is " + secondChar);
            System.out.println("The third main character is " + thirdChar);

        }
    }
