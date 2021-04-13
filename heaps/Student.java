/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.LinkedList;
import java.util.List;

/**
 * This class implements the student interface and it has various methods
 * like getCoins, waitlistCourse etc.
 *
 * @author Hetsvi
 * @since 12 November
 */
public class Student implements Student_Interface {

    private String studentID;
    private String name;
    private List<Course> myEnrolledCourses;
    private List<Course> myWaitlist;
    private int courseCoins;

    /**
     * Constructor that populates the instance variables with parameters passed
     *
     * @param id StudentID
     * @param name Name of the student
     * @param coins Course Coins
     */
    public Student(String id, String name, int coins) {
        //TODO
        this.studentID = id;
        this.name = name;
        this.courseCoins = coins;
        this.myEnrolledCourses = new LinkedList<>();
        this.myWaitlist = new LinkedList<>();
    }

    //TODO - Implement methods from the interface

    /**
     * Adds course c to the list of enrolled courses and removes c from the
     * waitlisted courses
     *
     * @param c Course to be enrolled in
     */
    public void enrollCourse(Course c) {
        myEnrolledCourses.add(c);  // adds to student enrolled course
        myWaitlist.remove(c);      // removes from student wailist
    }

    /**
     * Adds course c to the waitlist
     *
     * @param c course to be waitlisted
     */

    public void waitlistCourse(Course c) {
        myWaitlist.add(c);
    }


    /**
     * Accessor for name
     *
     * @return name - Name of the student
     */
    public String getStudentName(){
        return name;
    }

    /**
     * Accessor for Student ID
     *
     * @return studentID - Student ID
     */
    public String getStudentID(){
        return studentID;
    }

    /**
     * Returns a list of all enrolled courses
     *
     * @return List of enrolled courses
     */
    public List<Course> getmyEnrolledCourses(){
        return myEnrolledCourses;
    }

    /**
     * Returns a list of all waitlisted courses
     *
     * @return List of waitlisted courses
     */
    public List<Course> getmyWaitlist(){
        return myWaitlist;
    }

    /**
     * Accessor for course coins
     *
     * @return course coins
     */
    public int getCoins(){
        return courseCoins;
    }

    /**
     * Deducts numCoins from coursecoins
     *
     * @param numCoins Number of coins to be deducted
     */
    public void deductCoins(int numCoins){
        courseCoins = courseCoins - numCoins;  // subtract coins
    }


    /**
     * Returns a string representation of the Student that includes the name and
     * the studentID
     *
     * @return String representation of the student
     */
    @Override
    public String toString() {
        return this.name + "(" + this.studentID + ")";
    }
}
