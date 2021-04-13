/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.LinkedList;
import java.util.List;

/**
 * This course class has various methods such as the getter and setter,
 * it adds to waitlist and enroll and adds to roster etc.
 *
 * @author Hetsvi
 * @since 12 November
 */

public class Course implements Course_Interface {

	private String courseName;
	private String courseCode;
	private MyPriorityQueue<Registration> waitlistQueue;
	private List<Student> roster;
	private int maxCapacity;

	/**
	 * Constructor
	 * @param name
	 * @param code
	 * @param capacity
	 */
	public Course(String name, String code, int capacity) {
	//TODO
		this.courseName = name;
		this.courseCode = code;
		this.maxCapacity = capacity;
		waitlistQueue = new MyPriorityQueue<Registration>(capacity);
		roster = new LinkedList<>();

	}

	//TODO
	/**
	 * Accessor for course name
	 *
	 * @return course name
	 */
	public String getCourseName() {
		return this.courseName;

	}

	/**
	 * Accessor for course code
	 *
	 * @return course code
	 */
	public String getCourseCode(){
		return this.courseCode;
	}

	/**
	 * Accessor for course capacity
	 *
	 * @return course capacity
	 */
	public int getCourseCapacity(){
		return maxCapacity;
	}

	/**
	 * Accessor for Course Roster
	 *
	 * @return course roster
	 */
	public List<Student> getCourseRoster(){
		return roster;
	}

	/**
	 * Checks whether the course enrollment has reached its capacity
	 *
	 * @return Returns true if the number of enrolled students is equal to
	 * capacity, false otherwise
	 */
	public boolean isFull(){
		if(roster.size() == maxCapacity){   // checks roster with max capacity
			return true;
		}

		return false;
	}

	public MyPriorityQueue<Registration> waitlist(){
		return waitlistQueue;

	}


	/**
	 * Enqueues the student to the waitlist for this course
	 *
	 * @param r Registration to be waitlisted
	 */
	public void addToWaitlist(Registration r){
		waitlistQueue.offer(r);     // adds to waitlist
		r.getStudent().waitlistCourse(r.getCourse());   // adds to student waitlist
		r.setTimestamp();
	}



	/**
	 * Enrolls the next student in the waitlist to the course. Does nothing if
	 * the waitlist is empty
	 *
	 * @return Registration Request that was processed
	 */
	public Registration processWaitlist(){
		if(waitlistQueue.peek() == null){   //if waitlost queue empty does nothing
			return null;
		}
		Registration r = waitlistQueue.peek();
		r.getStudent().enrollCourse(r.getCourse());     // adds to student enroll
		Registration waitList = waitlistQueue.poll(); // holds the waitList removed
		roster.add(waitList.getStudent());   //removes from waitList and
		                                                   // adds to roster

		return waitList;
	}


	@Override
	public String toString() {
		return courseCode;
	}

}
