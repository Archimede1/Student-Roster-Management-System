package project3;

/**
 * This class represent a student that will occupy a roster.
 * Each student has data that represents their name (firstname, lastname)
 * and a 6 digit ID number (IDNo);
 * @author Archimede Cornely
 */

public class Student implements Comparable<Student> {
	private String firstName, lastName;
	private int IDNo;
	
	public Student(String fName, String lName, int idno) {
		this.firstName = fName;
		this.lastName = lName;
		this.IDNo = idno;
	}
	
	public Student() {
		this.firstName = "";
		this.lastName = "";
	}

	/**
	 * This method allows different Student variables to be compared to each other according to firstname and lastname
	 * This compareTo method implements alphabetical order.
	 * @param Student other - the Student instance variable that will be compared to
	 */
	@Override
	public int compareTo(Student other) {
		if(firstName.equalsIgnoreCase(other.firstName)) {
			return lastName.compareTo(other.lastName);
		}
		else {
			return firstName.compareTo(other.firstName);
		}
	}
	/**
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		Student student = (Student) other;
		return IDNo == student.IDNo;
	}
	
	@Override
	public String toString() {
		return lastName + " , " + firstName + " " + IDNo ; 
	}
	
	/**
	 * Allows user to access Student firstname 
	 * @return returns Student firstname
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Allows user to access Studest lastname
	 * @return returns Student lastname
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Allows user to access Student ID number
	 * @return returns Student ID number
	 */
	public int getIDNo() {
		return IDNo;
	}
	/**
	 * Allows user to set or modify Student firstname
	 * @param fName - the first name that the Student firstname will be modified to 
	 */
	public void setFirstName(String fName) {
		fName = firstName;
	}
	
	/**
	 * Allows user to set or modify Student lastname
	 * @param lName - the last name that the Student lastname will be modified to
	 */
	public void setLastName(String lName) {
		lName = lastName;
	}
	/**
	 * Allows user to set or modify Student IDNo (id number) 
	 * @param idno - the six digit ID number that the Student IDNo will be modified to
	 */
	public void setIDNo(int idno) {
		idno = IDNo;
	}

}
