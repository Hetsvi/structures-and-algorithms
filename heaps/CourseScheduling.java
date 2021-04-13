/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

//import sun.jvm.hotspot.asm.Register;

import java.io.*;
import java.util.*;

/**
 * Course Registration Manager
 *
 * @author Hetsvi, A13595252
 * @version 1.0
 * @since 12 November
 */
public class CourseScheduling {

    public static List<Course> courseList = new LinkedList<>();
    public static List<Student> studentList = new LinkedList<>();

    /**
     * Read info file to establish course catalog and student list
     *
     * @param fname
     */
    public static void populateCourseandStudents(String fname) {
        File file = new File(fname);
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            boolean loadCourse = true;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine.isEmpty()) {
                    loadCourse = false;
                    scanner.nextLine();
                    continue;
                }
                Scanner wordscan = new Scanner(nextLine);
                if (loadCourse) {
                    String id = wordscan.next();
                    String cname = wordscan.next();
                    int capacity = wordscan.nextInt();
                    Course course = new Course(cname, id, capacity);
                    courseList.add(course);
                } else {
                    String sname = wordscan.next();
                    String pid = wordscan.next();
                    int coins = wordscan.nextInt();
                    Student student = new Student(pid, sname, coins);
                    studentList.add(student);
                }
                wordscan.close();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * Output successful course enrollment/waitlisting
     *
     * @param s student identifier
     * @param c desired course
     * @param coins request weighting
     * @param enroll true if the student was enrolled
     */
    public static void print(Student s, Course c, int coins, boolean enroll) {
        String signup = ((enroll) ? "Enrolling " : "Waitlisting ");
        String amount = coins + ((coins == 1) ? " coin" : " coins");
        System.out.println(signup + s + " to Course " + c + " with " + amount);
    }

    /**
     * Output unsuccessful course enrollment/waitlisting
     *
     * @param s student identifier
     * @param c desired course
     * @param enroll true if the student was enrolled
     */
    public static void printFail(Student s, Course c, boolean enroll) {
        String signup = ((enroll) ? "enrolled" : "waitlisted");
        System.out.println(s + "is already " + signup + " in Course " + c);
    }

    /**
     * Output course capacity message
     *
     * @param c desired course
     */
    public static void printCapacity(Course c) {
        System.out.println(c + " has already reached maximum capacity."
                + " There are no more seats left");
    }

    /**
     * Output insufficient tokens message
     *
     * @param s student identifier
     * @param c desired course
     */
    public static void printNoCoins(Student s, Course c) {
        System.out.println("Insufficient course coins."
                + " Cannot waitlist " + s + " to " + c);
    }

    /**
     * Output general registration information
     */
    public static void generateOutput() {
        System.out.println("\n\n########COURSE INFORMATION######");
        ListIterator<Course> iter = courseList.listIterator();
        while (iter.hasNext()) {
            Course temp = iter.next();
            System.out.println(temp.getCourseCode()
                    + "  " + temp.getCourseName());
            System.out.println("\tRoster: " + temp.getCourseRoster());
        }

        System.out.println("\n\n########STUDENT INFORMATION######");
        ListIterator<Student> iterS = studentList.listIterator();
        while (iterS.hasNext()) {
            Student temp = iterS.next();
            System.out.println(temp.toString());
            System.out.println("\t Enrolled courses: "
                    + temp.getmyEnrolledCourses());
            System.out.println("\t Waitlisted courses: "
                    + temp.getmyWaitlist());
        }
    }

    //**********************YOUR WORK STARTS HERE*****************************
    /**
     * Returns a reference to the Course object whose courseCode is code
     *
     * @param code Course code of the Course to be returned
     * @return Course Course with the course code passed as a parameter
     */
    public static Course getCourse(String code) {
        ListIterator<Course> iter = courseList.listIterator();  // iterator
        while(iter.hasNext()){
            Course course = iter.next();   //gets the next one
            if(course.getCourseCode().equals(code)){ // checks if same
                return course;       // return course obj
            }
        }
        return null;
    }

    /**
     * Returns a reference to the Student object whose StudentID is pid
     *
     * @param pid StudentID of the Student to be returned
     * @return Student student with the id passed as a parameter
     */
    public static Student getStudent(String pid) {
        ListIterator<Student> iter = studentList.listIterator();
        while(iter.hasNext()){
            Student student = iter.next();    //gets the next one
            if(student.getStudentID().equals(pid)){   // checks if it equals
                return student;
            }
        }
        return null;
    }

    /**
     * this is the main method where students are added to the waitlist and
     * are enrolled and various print statements are called.
     * @param args
     */
    public static void main(String[] args) {
        populateCourseandStudents(args[0]);
        File file = new File(args[1]);

        try {
            Scanner scLine = new Scanner(file);	//scan lines in file
            System.out.println("####START COURSE SCHEDULING####\n");
            while (scLine.hasNextLine()) {//if the file has next line
                Scanner scWord = new Scanner(scLine.nextLine());
                String property;
                String ID;
                String course;
                String coin;
                String times;

                if (scWord.hasNext()) {//if the file has next word
                    property = scWord.next();

                } else {
                    break;
                }

                if (property.equals("register")) {
                    //add a new student to the course list
                    if (scWord.hasNext()) { //if the file has next word
                        ID = scWord.next ();
                        course = scWord.next();
                        coin = scWord.next();
                       // System.out.println("print" + ID);

                    } else {
                        break;
                    }
                    // makes new registration
                    Registration r = new Registration(getStudent(ID),
                            getCourse(course), Integer.parseInt(coin));

                    // checks if enrolled in course
                    if(getStudent(ID).getmyEnrolledCourses().contains(getCourse(course)) &&
                            getCourse(course).getCourseRoster().contains(getStudent(ID))){
                        printFail(getStudent(ID), getCourse(course), true);
                    }

                    // checks if waitlisted in the course
                    else if(getStudent(ID).getmyWaitlist().contains(getCourse(course))){
                        printFail(getStudent(ID), getCourse(course), false);
                    }

                    //checks the coins are less
                    else if(getStudent(ID).getCoins() < Integer.parseInt(coin)){
                        printNoCoins(getStudent(ID), getCourse(course));
                    }

                    // add to waitlist
                    else{
                        getCourse(course).addToWaitlist(r);
                        getStudent(ID).deductCoins(Integer.parseInt(coin));
                        print(getStudent(ID), getCourse(course),
                                Integer.parseInt(coin) ,false);

                    }

                }

                else if (property.equals("enroll")) {
                    //process registrations in the waitlist
                    System.out.println("\n####STARTING BATCH ENROLLMENT####");
                    if (scWord.hasNext()) {  // sees if has next
                        times = scWord.next();
                    } else {
                        break;
                    }
                    int time = Integer.parseInt(times);  // parses
                  //  ListIterator<Course> iter = courseList.listIterator();
                   // while(iter.hasNext()){
                     //   Course elem = iter.next();

                    for(int i =0; i < time; i++) {       // capacity of enrolling
                        for (int j = 0; j < courseList.size(); j++) {
                            Course elem = courseList.get(j);

                            if (elem.isFull()) {  // checks it is full and then prints
                                printCapacity(elem);
                            }


                            if (elem.waitlist().peek() != null && !elem.isFull()) {
                                Registration waitlist = elem.processWaitlist();
                                print(waitlist.getStudent(), waitlist.getCourse(),
                                        waitlist.getCoins(), true);
                            }
                        }
                    }
                        //TODO
                        System.out.println("####ENDING BATCH ENROLLMENT####\n");

                }

                else {
                    break;
                }

                scWord.close();
            }
            scLine.close();
            System.out.println("\n####END COURSE SCHEDULING####");
        } catch (FileNotFoundException e) {
            System.err.println("Failed to open " + file);
            System.exit(1);
        }

        generateOutput();
    }//end of main method
}
