/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

/**
 * This class has various methods which help build a program which
 * tell how much time it takes to pay in sunshine market.
 *
 * @author Hetsvi
 * @since dec 2018
 */
public class SunshineMarket {
    // Example customers
    private static int[] customers1 = {3, 7, 20};          // customer lines
    private static int[] customers2 = {1, 3, 5, 4, 16, 8}; // customer lines
    private static int[] customers3 = {1, 1, 2, 3, 5, 7};  // customer lines

    private static QueueADT customersQueue;    // queue
    private static QueueADT[] lines;

    /**
     * This method helps to test the sunshine market class
     * @param args
     */
    public static void main(String[] args) {
        // Run your program here to test the results
        int numberLine = 4;
        System.out.println(lines(customers1, numberLine));
        System.out.println(lines(customers2, numberLine));
        System.out.println(lines(customers3, numberLine));
    }

    /**
     * This class calculates the time taken for customers to pay and
     * the speed of the lines etc. The customer goes to shortest line
     * and all empty then idle time is calculated etc.
     * @param customers
     * @param numberOfLines
     * @return statment with number of lines, total time and ideal time
     */
    public static String lines(int[] customers,int numberOfLines) {
        int totalTime = 0;
        int emptyQueueTime = 0;
        // TODO
        int processTime = 2;

        customersQueue = new CircularArrayQueue();
        lines = new QueueADT[numberOfLines];

        for(int i =0; i<numberOfLines; i++){
            lines[i] = new CircularArrayQueue(); // instantiates each queue in line
        }

        for(int j =0; j<customers.length; j++){
            customersQueue.add(customers[j]); // adds customer to customerQueue
        }

        while(! customersQueue.isEmpty()){
            int remove = customersQueue.remove();
            QueueADT queue = getShortestLine();
            for(int k =0; k < remove + processTime; k++){
                queue.add(k);
            }
            totalTime = totalTime +1;

            for(int l =0; l< lines.length; l++){
                if(lines[l] .isEmpty()){
                    emptyQueueTime = emptyQueueTime +1;
                }
                else{
                    lines[l].remove();
                }
            }
        }

        while(!allQueuesEmpty()){   // process customers
            for(int index =0; index < lines.length; index++){
                if(lines[index].isEmpty()){
                    emptyQueueTime = emptyQueueTime +1;
                }
                else{
                    lines[index].remove();
                }
            }
            totalTime = totalTime + 1;
        }

        return "With " + numberOfLines + " lines, the total wait time was "
                + totalTime + " time units, and registers were idle for a total of "
                + emptyQueueTime + " time units.\n";
    }

    /**
     * All queue is empty or nah helper method
     * @return boolean whether the queue is empty or nah
     */
    private static boolean allQueuesEmpty() {
        // TODO
        for(int i =0; i< lines.length; i++){
            if(!lines[i].isEmpty()){
                return false;
            }
        }

        return true;
    }

    /**
     * Helper to chose the shortest line
     * @return the shortest line
     */
    private static QueueADT getShortestLine() {
        // TODO
        int minIndex =0;
        for(int i =0; i< lines.length; i++){
            if(lines[i].size() < lines[minIndex].size()){ // checks if short than
                                                          // current short
                minIndex = i;
            }
        }

        return lines[minIndex];
    }
}
