/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

/**
 * This class checks if line of the queue and
 * checks what should be added according to course coins.
 *
 * @author Hetsvi
 * @since 12 November
 */
public class Registration implements Comparable<Registration> {

    private Student student;
    private Course course;
    private int coins;
    private long timestamp;

    /**
     * the constructor
     * @param student
     * @param course
     * @param coins
     */
    public Registration(Student student, Course course, int coins) {
        //TODO
        this.student = student;
        this.course = course;
        this.coins = coins;
    }

    /**
     * this method gets student
     * @return student
     */
    public Student getStudent() {

        return this.student; //XXX-CHANGE-XXX
    }

    /**
     * this method gets course
     * @return course
     */
    public Course getCourse() {

        return this.course; //XXX-CHANGE-XXX
    }

    /**
     * this method gets coins
     * @return coins
     */
    public int getCoins() {

        return this.coins; //XXX-CHANGE-XXX
    }

    /**
     * Compares this Student with another Student, by comparing their course
     * coins/timestamps If the coins of this is less, returns a negative
     * integer. If the coins of the Student received is less, returns a positive
     * integer. If the number of coins is same, use the timestamp comparison to
     * ensure FCFS. (You may want to check the implementation of System.nanoTime
     * to ensure correctness)
     *
     * @param o Student to be compared with
     * @return Result of the comparison
     */
    @Override
    public int compareTo(Registration o) {
        if(this.coins < o.getCoins()){  // checks if its less than
            return -1;
        }

        else if(this.coins >= o.getCoins()){
            return 1;
        }

        else{
            if(timestamp >= o.timestamp){  // check stamp
                return  1;
            }
            else {
                return -1;
            }
        }
    }

    /**
     * Sets the timestamp inside this registration to be the current time in
     * nano seconds.
     */
    public void setTimestamp() {
        timestamp = System.nanoTime();
    }
}
