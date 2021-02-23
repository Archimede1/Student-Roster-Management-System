package project3;
/**
 * This class is a represent of a roster that will be populated with students. The roster is represented as an array of Student elements.
 * Class Roster will also contain a waitlist which will populate with Students once the Roster array is at capacity.
 * @author Archimede Cornely
 */
import java.util.LinkedList;
import java.util.Queue;

public class Roster {
	protected Student[] roster;
	protected Queue<Student> waitlist = new LinkedList<Student>();
	protected int capacity = 10;

	protected int index = 0;
	
	public Roster() {
		roster = new Student[capacity];
	}
	
	public String toString() {
		String str = "";
		int count = 1;
		for(int i = 0; i < index; i++) {
			str += count + ". " + roster[i] + "\n";
			count++;
		}
		return str;
	}
	/**
	 * Allows user to print all element within the Waitlist queue
	 * @return returns a String representation of all the elements within the Waitlist queue
	 */
	public String printWaitlist() {
		String str = "";
		int count = 1;
		for(int i = 0; i < waitlist.size(); i++) {
			Student d = waitlist.remove();
			str += count + ". " + d + "\n";
			waitlist.add(d);
			count++;
 		}
		return str;
	}
	
	/**
	 * Checks whether the Roster array is full or at capacity.
	 * @return  returns true if Roster array is at capacity, otherwise it will return false
	 */
	public boolean isFull() {
		if(index == capacity) {
			return true;
		}
		return false;
	}
	/**
	 * |Time Complexity for Array Add/Insert- Average Case: Θ(n), Worst Case: O(n)|
	 * Allows user to add Student elements into the Roster array.
	 * If the Roster array is at capacity, the extra Student element will be added to a waitlist queue
	 * @param d - the Student data that will be added to the Roster array
	 */
	public void addStudent(Student d) {
		if(!isFull()) {
			roster[this.index] = d;
			sortRoster();
			this.index++;
		}
		else {
			waitlist.add(d);
		}
	}
	/**
	 *  |Time Complexity for Array Remove/Delete - Average Case: Θ(n), Worst Case: O(n)|
	 * Allows user to remove Student elements from the Roster array.
	 * This is done by wiping the data of an element that has a matching ID number as the one passed in as a parameter.
	 * If waitlist queue is populated, once a Student element is removed, a Waitlist element will be dequeued and add into the Roster array 
	 * @param ID - returns the Student that has a matching six digit number as the parameters used as reference 
	 */
	public void removeStudent(int ID) {
		boolean flag = false;
		for(int i = 0; i < index; i++) {
			if(roster[i].getIDNo() == ID) {
				roster[i] = new Student();
				sortRoster();
				index--;
				transfer();
				flag = true;
				System.out.println("Student Removed \n");
			}
		}
		if(waitlist.size() != 0 && flag == true) {
			addStudent(waitlist.remove());
		}
		if (flag == false) {
			System.out.println("Student Not Found");
		}
	}
	
	/**
	 *  |Time Complexity for Array Search - Average Case: Θ(n), Worst Case: O(n)|
	 * Searches for a Student element within the Roster array by using Student ID as references
	 * This method will search for the name by matching the Student ID with the parameters passed in 
	 * @param ID - the six digit ID number that will be used as a reference to help with search
	 * @return returns the Student that has a matching six digit number as the parameters used as reference 
	 */
	public Student searchByID(int ID) {
		boolean flag = false;
		Student result = new Student();
		for(int i = 0; i < index; i++) {
			if(ID == roster[i].getIDNo()) {
				roster[i] = result;
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("Student Not Found");
		}
		return result;
	}
	
	/**
	 * |Time Complexity for Array Search - Average Case: Θ(n), Worst Case: O(n)|
	 * Searches for a Student element within the Roster array by using Student firstname and lastname as references.
	 * This method will search for the name by matching the Student firstname and lastname with the parameters passed in. 
	 * @param fname - the firstname that will be used as a reference to help with search
	 * @param lname - the lastname that will be used as a reference to help with search
	 * @return returns the Student that has a matching firstname and lastname as the parameters used as reference 
	 */
	public Student searchByName(String fname, String lname) {
		boolean flag = false;
		Student result = new Student();
		for(int i = 0; i < index; i++) {
			if(fname == roster[i].getFirstName() && lname == roster[i].getLastName()) {
				roster[i] = result;
				flag = true;
			}
		}
		if (flag == false) {
			System.out.println("Student Not Found");
		}
		return result;
	}
	/**
	 * |Time Complexity for Bubble Sort - Best Case: Ω(n), Average Case: Θ(n^2), Worst Case: O(n^2)|
	 * Sorts the Roster array in alphabetical order by lastname.
	 * This sorting method is an example of the sorting algorithm called BubbleSort.
	 */
	public void sortRoster() {
		Student temp;
		for(int i = 0; i < index + 1; i++) { 
			for(int j = 0; j < index + 1; j++) {
				if(roster[i].getLastName().compareTo(roster[j].getLastName()) < 0) {
					temp = roster[j];
					roster[j] = roster[i];
					roster[i] = temp;
				}
			}
		}
	}
	/**
	 * This is a helper method that transfer all elements in Roster array to a new Roster array. 
	 * This method is called usually when a Student is "removed" from the Roster array.
	 * When this happens, there remains a blank element as a result of the "removal". 
	 * As the elements from the old array are transfered to the new array the blanks element will be left behind 
	 */
	//private method for the remove method. Transfers all elements in the roster array after student removal to rid the array of blank elements
	private void transfer() {
		Student[] temp = new Student[capacity];
		temp = this.roster;
		roster = new Student[capacity];;
		for(int i = 0; i < index; i++) {
			if(temp[i].getFirstName() == "" && temp[i].getFirstName() == "" && temp[i].getIDNo() == 0) {
				continue;
			}
			roster[i] = temp[i];
		}
	}
	
	/**
	 * Allows user to access a Roster array element chosen with the use of an index
	 * @param index - integer that indicate element in Roster array
	 * @return returns a Roster array element at a given index.  
	 */
	public Student getStudent(int index) {
		return roster[index];
	}
	
	/**
	 * Allows user to set or modify a Roster array element chosen with the use of an index
	 * @param index - integer that indicate element in Roster array
	 * @param d - Student data that chosen array element will be set to.
	 */
	public void setStudent(int index, Student d) {
		roster[index] = d;
	}
	
	/**
	 * Allows user to access Roster array length 
	 * @return returns Roster array length
	 */
	public int getSize() {
		return roster.length;
	}
}
