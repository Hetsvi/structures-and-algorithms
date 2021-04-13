/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.*;

/**
 * This class solves the cookie question.
 * It tries to find the perfect sweetness by adding
 *
 * @author Hetsvi
 * @since 12 November
 */

public class Cookie {


    /**
     * This is the main class which calls the cookies method to help test
     * @param args
     */
    public static void main(String[] args){
        int[] a = {1, 2 ,3 ,9 ,10, 12};
        int number = 7;
        System.out.println(cookies(number, a));  // calls method

    }


    /**
     * This methods takes in k and an array of numbers
     * and gives a number of how much to mix to get sweetness
     * @param k
     * @param A
     * @return number of times to mix cookies
     */
    public static int cookies(int k, int[] A) {
        int d = 2;
        int sum1 = 0;
        int sum2 = 0;
        int counter =1;
        int parentIdx =0;

        dHeap<Integer> cookieQ = new dHeap<>(d, A.length, false); // makes a heap

        for(int i =0; i<A.length; i++){   // adds numbers from array to heap
            cookieQ.add(A[i]);
        }

        for(int i =0; i<d; i++){
            if(i == 0) {
                int remove = cookieQ.remove();   // removes the first 1
                sum1 = 1*remove;                // sums with the formula
            }
            if(i == 1) {
                int remove = cookieQ.remove();  // removes the second
                sum2 = d*remove;                // sums with formula
            }
        }
        cookieQ.add(sum1 + sum2);              // adds new number

        for (int i = 0; i <= cookieQ.size(); i++){  // goes children
            if(cookieQ.element() < k){
                sum1 =0;
                sum2 =0;
                for(int j =0; j<d; j++){          // removes the first two
                    if(j == 0) {
                        int remove = cookieQ.remove();
                        sum1 = 1*remove;
                    }
                    if(j == 1) {
                        int remove = cookieQ.remove();
                        sum2 = d*remove;
                    }
                }
                cookieQ.add(sum1 + sum2);   // adds new number

                counter = counter+1;        // keeps track how many times new number added

            }

            if(i == cookieQ.size() && cookieQ.element() < k){   // can't find
                return -1;
            }

        }
        return counter;    // return number of times
    }

}
