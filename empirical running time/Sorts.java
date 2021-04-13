/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * methods which sort arraylist pair. Sort methods include insertion, merge
 * and quick
 *
 * @author Hetsvi
 * @since 29th oct
 */
public class Sorts {

    //TODO: Change methods to work on ArrayList<Pair> when ready

    /**
     * Method which calls sorts helper methods
     * @param list
     */
    public static void quickSort(ArrayList<Pair> list){
        //TODO: Implement quick sort
        Sorts.sortHelper(list, 0, list.size()-1);
    }

    /**
     * Methods which calls sorts helper with other additional parameters.
     * @param list
     * @param num1
     * @param num2
     */
    public static void quickSort(ArrayList<Pair> list, int num1, int num2) {
        Sorts.sortHelper(list, num1, num2-1);

    }

    /**
     * Recursive call and smaller list
     * @param pList
     * @param numA
     * @param numB
     */
    public static void sortHelper(ArrayList<Pair> pList, int numA , int numB){
        int i =0;
        if(numA>numB){
            return;
        }
        if(numA == numB){
            return;
        }

        i = helper(pList, numA, numB);
        sortHelper(pList, numA, i-1);
        sortHelper(pList, i+1, numB);

    }

    /**
     * the actual partion of quick sort
     * @param pList
     * @param numA
     * @param numB
     * @return otherList
     */
    public static int helper(ArrayList<Pair> pList, int numA, int numB){
        int mid = 2;
        int pivotNumber = numA + ((numB-numA)/mid);
        int pivot = pList.get(pivotNumber).count;
        int number = numA;
        int otherNumber = numB;
        boolean count = false;

        while(!count){
            while(pList.get(number).count < pivot){
                number++;
            }
            while(pList.get(otherNumber).count > pivot){
                otherNumber--;
            }
            if(number >= otherNumber){
                count = true;
            }
            else{
               //int temp = pList.get(number).count;
               //pList.get(number).count = pList.get(otherNumber).count;
               //pList.get(otherNumber).count = temp;

                Pair deepPair =  new Pair(pList.get(number).name, pList.get(number).count);
                Pair temp = deepPair;
                pList.set(number, pList.get(otherNumber));
                pList.set(otherNumber, temp);

                number++;
                otherNumber--;
            }
        }

        return otherNumber;
    }

    /**
     * insertion sort and swaps elements
     * @param list
     */
/*    public static void insertionSort(ArrayList<Pair> list){
        //TODO: Implement insertion sort
        for(int i =1; i<list.size(); i++){

            Pair indexNeed = list.get(i);
            int j = i;
            while(j>0 && list.get(j-1).count > list.get(j).count){
                list.set(j, list.get(j-1));
                list.set(j-1, indexNeed);
                j--;
            }
        }

    }*/

    /**
     * insertion sort which swaps elements and has index parameters
     * @param list
     * @param num1
     * @param num2
     */
    public static void insertionSort(ArrayList<Pair> list, int num1, int num2) {
        for(int i =num1; i<num2; i++){
            Pair indexNeed = list.get(i);
            int j = i;
            while(j>0 && list.get(j-1).count > list.get(j).count){
                list.set(j, list.get(j-1)); // swaps
                list.set(j-1, indexNeed);
                j--;
            }
        }

    }


    /**
     * merge method with additional index parameters
     * @param list
     * @param num1
     * @param num2
     */
    public static void mergeSort(ArrayList<Pair> list, int num1, int num2) {
        helpM(list, num1, num2-1);
    }


    /**
     * recursive calls the list and splits it
     * @param list
     * @param numA
     * @param numB
     */
    public static void helpM (ArrayList<Pair> list, int numA, int numB) {
        int middle = 0;
        int mid = 2;
        if (numA < numB ) {
            middle = (numA + numB )/ mid;  // middle
            helpM(list, numA, middle);
            helpM(list, middle + 1, numB);
            merge(list, numA, middle, numB);
        }
    }

    /**
     * The merge method which creates new list and puts a sorted one. Method from
     * zybooks
     * @param list
     * @param numA
     * @param middle
     * @param numB
     */
   public static void  merge (ArrayList<Pair> list, int numA, int middle,  int numB){
        int listSize = numB - numA +1;
        int left = 0;
        int right = 0;
        int mergeP = 0;
        Pair[] newList = new Pair[listSize];


       left = numA;
       right = middle +1;

       while(left <= middle  &&  right<= numB){
           if(list.get(left).count <= list.get(right).count){
               Pair deepPair = new Pair(list.get(left).name, list.get(left).count);
               newList[mergeP] = deepPair;
               left += 1;
           }
           else{
               Pair deepPair = new Pair(list.get(right).name, list.get(right).count);
               newList[mergeP] = deepPair;
               right += 1;
           }
           mergeP += 1;
       }

       while(left <= middle){   // if more elements there
           Pair deepPair = new Pair(list.get(left).name, list.get(left).count);
           newList[mergeP] = deepPair;
           left +=1;
           mergeP +=1;

       }

       while(right <= numB ){   // if more elements there
           Pair deepPair = new Pair(list.get(right).name, list.get(right).count);
           newList[mergeP] = deepPair;
           right +=1;
           mergeP +=1;

       }
       for(mergeP =0 ; mergeP< listSize; mergeP++){   // copy list
           list.set((numA+mergeP), newList[mergeP]);
       }


    }

    }




