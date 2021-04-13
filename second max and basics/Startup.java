import java.util.Arrays;

/**
 * This class has a number of differnt methods and a main that
 * tests these methods.
 */
public class Startup {
    /**
     * This is the main method of the class and tests all
     * the other methods of the class
     * @param args
     */
    public static void main(String[] args){

        int [] arr = {4, 1, 2, 3};
        System.out.println(min(arr));
        System.out.println(secondMax(arr));
        System.out.println(median(arr));
        System.out.println(Arrays.toString(rearrange(arr)));
        System.out.println(brokenMirror(arr, 0));

        int [] array = {2, 4, 6, 17, 2, 4, 6};
        System.out.println(min(array));
        System.out.println(secondMax(array));
        System.out.println(median(array));
        System.out.println(Arrays.toString(rearrange(array)));
        System.out.println(brokenMirror(array, 2));

        int [] test = {};
        System.out.println(min(test));
        System.out.println(secondMax(test));
        System.out.println(brokenMirror(test, 2));
        System.out.println(median(test));
        System.out.println(Arrays.toString(rearrange(test)));


        int [] ano_test = {1, 5, 1, 2, 1};
        System.out.println(min(ano_test));
        System.out.println(secondMax(ano_test));
        System.out.println(brokenMirror(ano_test, 1));
        System.out.println(median(ano_test));
        System.out.println(Arrays.toString(rearrange(ano_test)));


        int [] testing = {0,0,1,2};
        System.out.println(min(testing));
        System.out.println(secondMax(testing));
        System.out.println(median(testing));
        System.out.println(Arrays.toString(rearrange(testing)));
        System.out.println(brokenMirror(testing, 1));

        int [] tester = {1};
        System.out.println(min(tester));
        System.out.println(secondMax(tester));
        System.out.println(median(tester));
        System.out.println(Arrays.toString(rearrange(tester)));
        System.out.println(brokenMirror(tester, 1));

        int [] tested = {3, 3, 3};
        System.out.println(min(tested));
        System.out.println(secondMax(tested));
        System.out.println(median(tested));
        System.out.println(Arrays.toString(rearrange(tested)));
        System.out.println(brokenMirror(tested, 1));


    }

    /**
     * This method returns the smallest number
     * from the array.
     * @param a
     * @return min_value
     */

    public static int min(int[] a){
        int min_value = Integer.MAX_VALUE; // sets min_value
        if (a.length == 0){          // if empty array returns the default min
            return Integer.MIN_VALUE;
        }
        for(int i = 0; i < a.length; i++){
            if (a[i] < min_value) {
                min_value = a[i];
            }
        }
        return min_value;

    }

    /**
     * This method returns the second
     * highest number of the given array.
     * @param a
     * @return int value of second highest number
     */


    public static int secondMax(int[] a){
        int first = Integer.MIN_VALUE ;
        int sec_max = Integer.MIN_VALUE ;
        // if array is empty or has one element then returns max value
        if (a.length == 0 || a.length == 1){
            return Integer.MAX_VALUE;
        }

        for(int i = 0; i < a.length; i++){
            if (a[i] > first){
                sec_max = first;
                first = a[i];
                //check = 1;
            }
            else if(a[i] > sec_max){
                sec_max = a[i];
            }

            }

        return sec_max;
    }

    /**
     * This method finds the median of the
     * given array passed in.
     * @param a
     * @return an double value
     */

   public static double median(int[] a){
       Arrays.sort(a);
       int divisior = 2;
       double median =Integer.MAX_VALUE ;
       if(a.length == 0){
           return Integer.MAX_VALUE ;
       }
// checks if the array has an even number of elements
       if(a.length%divisior == 0){
           int middle = a.length/ divisior;
           int sec_middle = middle -1;
           double value = a[middle] + a[sec_middle];
           median = value/divisior;

       }

       else{
           int middle = a.length/ divisior;
           median = a[middle];
       }

       return median;

   }

    /**
     * This method reorders the array by moving the even numbers to
     * the front and the odd numbers to the back
     * @param a
     * @return a new array
     */

    public static int [] rearrange(int[] a) {
        int[] copy_a = Arrays.copyOf(a, a.length);
        if (a.length == 0){
            return copy_a;
        }
        int temp = 0;
        int divisior = 2;
        for (int i =0; i<a.length; i++) { // starts from the start of the array
           for (int j = a.length - 1; j >= i; j--) { //start from end of array

       // sees if the starting has odd and the end ones have even if then swaps
               if (copy_a[i] % divisior != 0 && copy_a[j] % divisior == 0) {
                   temp = copy_a[i];
                   copy_a[i] = copy_a[j];
                   copy_a[j] = temp;
               }

           }
       }
       return copy_a;
    }
    // int[] odd_array = new int[];
    //int[] even_array = new int[];
    //for (int i = 0, index = 0; i < a.length; i++) {
    //   if (a[i] % 2 == 0) {
    //      even_array[index] = a[i];
    //   } else {
    //      odd_array[index] = a[i];
    //  }
    //}
    // int [] copy_a = new int[odd_array.length + even_array.length];
    // System.arraycopy(odd_array, 0, copy_a, 0,odd_array.length);
// System.arraycopy(even_array, 0, copy_a, odd_array.length, even_array.length);

    // return copy_a;
    //}

    /**
     * This method takes in an array and a range and sees if the front
     * numbers within the range and and the end numbers from the range
     * are same or not
     * @param a
     * @param range
     * @return boolean depeading on if it matches or not
     */

        public static boolean brokenMirror(int [] a, int range){
        if(a.length == 0){
            return true;
        }
        int counter = 0;
        for (int i = 0; i< range; i++) {  // goes from the starting
            // starts from the end minus range
            for (int j = a.length - (range); j < a.length; j++) {
                if (a[i] == a[j]) {
                    counter += 1;
                    break;
                }
            }
        }
        if (counter == range){ //if counter equals range then true
            return true;
        }
        else{
            return false;
        }

        }

}
