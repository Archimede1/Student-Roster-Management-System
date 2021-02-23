package project3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Roster Management System:
 * This overall roster management system exemplifies the differences between using various data structures
 * when carrying out certain tasks. In this case we used a BST and a sorted array to implement a Roster System.
 * For this case  using the BST implemention was more advantageous since features like insert/delete/search were relatively more 
 * efficient having a average case of Θ(log(n)) as opposed to the array average case of insert/delete/search, which was Θ(n). Also the tree implementation allowed for easier
 * access to different parts of the roster, for example this helped with deletion: any element could easily be deleted with no need for shifting element
 * to different or new indexes (since BST do not have indexes), as well as no need to account for blank or null Student elements (by creating new arrays). 
 * With the addition/insert feature, the BST capacity was not fixed, so more Student elements could be added without need
 * for a waitlist queue for the element that could not fit into the list. In conclusion, the BST implementation of the roster would be a more viable option use in the future.
 * Date: 8/10/20
 * @author Archimede Cornely
 */

public class Driver {
	private static Roster roster = new Roster();
	private static Map<Integer, Student> studentlist = new TreeMap<Integer, Student>();
	
	public static void main (String args[]) {	
		menu_1(roster, studentlist);
	}
	/**
	 * This is the main menu that allows user to modify and edit the unofficial roster, represented by a BST
	 * @param roster - Roster instance variable that will contain the official roster after the unofficial one has been finalized
	 * @param studentlist - the unofficial roster as a BST
	 */
	public static void menu_1(Roster roster, Map<Integer, Student> studentlist) {
		int userInput = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("**Student Roster || Class of 2020** \n\n"
				+ "Enter '1' Load Roster From File \n"
				+ "Enter '2' Add Student \n"
				+ "Enter '3' Remove Student \n"
				+ "Enter '4' Search Student By ID \n"
				+ "Enter '5' Search Student By Name \n"
				+ "Enter '6' Save Roster \n"
				+ "Enter '7' Exit \n");
		userInput = input.nextInt();
		switch(userInput) {
		 	default:
		 	/**
		 	 * This Case when activated, calls the readFromFile, which read a Roster from a text file
		 	 * and inserts all its elements into a BST. This BST represents an unofficial roster within this system.
		 	 * This BST is implemented with a TreeMap. 
		 	 */
		 	case 1:{
		 		readFromFile("students", studentlist);
				System.out.println("**Roster Has Been Loaded** \n");
				break;
		 	}
		 	/**
		 	 * |Time Complexity for BST Add/Put/Insert - Average Case: Θ(log(n)), Worst Case: O(n)|
		 	 * This Case add a new Student element to the BST. It allows the user to manually enter in the data
		 	 * that will be represented in the new Student Element. This Case uses the BST method "put" to add new Student Element within the tree.
		 	 */
		 	case 2:{
		 		String firstname, lastname;
				int Id;
				System.out.println("Enter Student First Name");
				firstname = input.next();
				System.out.println("Enter Student Last Name");
				lastname = input.next();
				System.out.println("Enter Student ID");
				Id = input.nextInt();
				if(Integer.toString(Id).length() != 6) {
				System.out.println("Invalid ID Number \n");
				break;
				}
				Student newStudent = new Student(firstname, lastname, Id);
				System.out.println(newStudent);
				studentlist.put(newStudent.getIDNo(), newStudent);
				break;
		 	}
		 	/**
		 	 * |Time Complexity for BST Remove/Delete - Average Case: Θ(log(n)), Worst Case: O(n)|
		 	 * This Case removes a Student element from the BST by using Student ID as an identifier. 
		 	 * This ID number is indicated by the user and used to find the Student. 
		 	 * This Case uses the BST "remove" to remove the indicated Student. 
		 	 */
		 	case 3:{
		 		int ID; 
				System.out.println("Enter Student ID");
				ID = input.nextInt();
				if(Integer.toString(ID).length() != 6) {
					System.out.println("Invalid ID Number \n");
					break;
				}
				if(!studentlist.containsKey(ID)) {
					System.out.println("Student Could Not Be Found \n");
					break;
				}
				else {
					System.out.println("Student: " + studentlist.remove(ID) + " has been removed. \n");
					break;
				}
		 	}
		 	/**
		 	 * |Time Complexity for BST Search - Average Case: Θ(log(n)), Worst Case: O(n)|
		 	 * This Case searches for and returns a Student element from the BST by using Student ID as an identifier. 
		 	 * This ID number is indicated by the user and used to find the Student.
		 	 * This Case uses the BST "get" method to search for the indicated Student.
		 	 */
		 	case 4:{
		 		int ID; 
				System.out.println("Enter Student ID");
				ID = input.nextInt();
				if(Integer.toString(ID).length() != 6) {
					System.out.println("Invalid ID Number \n");
					break;
				}
				if(!studentlist.containsKey(ID)) {
					System.out.println("Student Could Not Be Found \n");
					break;
				}
				else {
					System.out.println(studentlist.get(ID) + "\n");
					break;
				}
		 	}
		 	/**
		 	 * |Time Complexity for BST Search By Iteration - Average Case: Θ(n), Worst Case: O(n)|
		 	 * This Case searches for and returns a Student element from the BST by using Student firstname and lastname as identifiers. 
		 	 * These names are indicated by the user and used to find the Student.
		 	 * This Case searches for the Student element by way of iterations
		 	 */
		 	case 5:{
		 		String firstname, lastname;
		 		System.out.println("Enter Student First Name");
				firstname = input.next();
				System.out.println("Enter Student Last Name");
				lastname = input.next();
				boolean flag = false;
				for(Map.Entry<Integer, Student> entry : studentlist.entrySet()) {
					if(firstname == entry.getValue().getFirstName() && lastname == entry.getValue().getLastName()) {
						System.out.println(entry.getValue());
						flag = true;
					}
				}
				if(flag == false) {
					System.out.println("Student(s) Not Found \n");
				}
				break;
		 	}
		 	/**
		 	 * This Case allows user to finalize the BST implemented Roster. After roster has been confirmed for finalization
		 	 * all elements within the BST will be added into a Roster array.
		 	 */
		 	case 6:{
		 		char choice = 0;
				System.out.println("Would You Like to Edit the Roster Before Finalizing \n"
						+ "Choose 'Y' for YES and 'N' for NO \n\n"
						+ "**Unofficial Roster** \n\n"
						+ studentlist + "\n");
				choice = input.next().charAt(0);
				if(choice == 'Y' || choice == 'y') {
					break;
				}
				if(choice == 'N' || choice == 'n') {
					for(int i = 0; i < studentlist.size(); i++) {
						roster.addStudent(studentlist.remove(((TreeMap<Integer, Student>) studentlist).lastKey()));
					}
					System.out.println("**Finalized Roster** \n\n"
							+ roster + "\n");
					System.out.println("Roster Has Been Saved");
					menu_2(roster);
				}
		 	}
		 	/**
		 	 * Terminates the Roster system.
		 	 */
		 	case 7:{
		 		System.out.println("Goodbye!");
				System.exit(0);
		 	}
		}
		System.out.println("Press Enter To Continue to Main Menu");
		input.nextLine();
		menu_1(roster, studentlist);
	}
	/**
	 * This is the menu that will allow the user to modify and edit the official roster, represented by a sort array.
	 * @param roster - the class that contains the now official, sorted Roster array
	 */
	public static void menu_2(Roster roster) {
		int userInput = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("**Final Student Roster || Class of 2020** \n\n"
				+ "Enter '1' Add Student \n"
				+ "Enter '2' Remove Student \n"
				+ "Enter '3' Search Student By ID \n"
				+ "Enter '4' Search Student By Name \n"
				+ "Enter '5' Save to File \n"
				+ "Enter '6' Exit \n");
		userInput = input.nextInt();
		switch(userInput) {
			default:
			/**
			 * |Time Complexity for Array Add/Insert- Average Case: Θ(n), Worst Case: O(n)|
			 * Case uses "addStudent" method in Roster class to add a new Student element into the sorted Roster array.
			 */
			case 1:{
				String firstname, lastname;
				int Id;
				System.out.println("Enter Student First Name");
				firstname = input.next();
				System.out.println("Enter Student Last Name");
				lastname = input.next();
				System.out.println("Enter Student ID");
				Id = input.nextInt();
				if(Integer.toString(Id).length() != 6) {
					System.out.println("Invalid ID Number \n");
					break;
				}
				Student newStudent = new Student(firstname, lastname, Id);
				System.out.println(newStudent);
				roster.addStudent(newStudent);
				break;
			}
			/**
			 * |Time Complexity for Array Remove/Delete - Average Case: Θ(n), Worst Case: O(n)|
			 * Case uses "removeStudent" method in Roster class to remove a Student element from sorted Roster Array.
			 */
			case 2:{
				int ID; 
				System.out.println("Enter Student ID");
				ID = input.nextInt();
				if(Integer.toString(ID).length() != 6) {
					System.out.println("Invalid ID Number \n");
					break;
				}
				roster.removeStudent(ID);
				break;
			}
			/**
			 * |Time Complexity for Array Search - Average Case: Θ(n), Worst Case: O(n)|
			 * Case uses "searchByID" method in Roster class to search for a Student element in the sorted Roster Array by ID.
			 */
			case 3:{
				int ID; 
				System.out.println("Enter Student ID");
				ID = input.nextInt();
				if(Integer.toString(ID).length() != 6) {
					System.out.println("Invalid ID Number \n");
					break;
				}
				System.out.println(roster.searchByID(ID));
				break;
			}
			/**
			 * |Time Complexity for Array Search - Average Case: Θ(n), Worst Case: O(n)|
			 * Case uses "searchByName" method in Roster class to search for a Student element in the sorted Roster Array by Student firstname and lastname.
			 */
			case 4:{
				String firstname, lastname;
		 		System.out.println("Enter Student First Name");
				firstname = input.next();
				System.out.println("Enter Student Last Name");
				lastname = input.next();
				System.out.println(roster.searchByName(firstname, lastname));
				break;
			}
			/**
			 * Case allows user to finalize official Roster. Once confirmed for finalization,
			 * the Roster will be written to a text file. If the Roster Waitlist is populated
			 * all the elements within the Waitlist will be written to a separate text file.
			 */
			case 5:{
				char choice = 0;
				System.out.println("Would You Like to Edit the Roster Before Finalizing \n"
						+ "Choose 'Y' for YES and 'N' for NO " );
				choice = input.next().charAt(0);
				if(choice == 'Y' || choice == 'y') {
					break;
				}
				if(choice == 'N' || choice == 'n') {
					saveFRtoFile(roster);
					if(roster.waitlist.size() != 0) {
						saveWLtoFile(roster);
					}
					System.out.println("Roster Has Been Saved To File");
					break;
				}
			}
			/**
			 * Terminates Roster system.
			 */
			case 6:{
				System.out.println("Goodboye!");
				System.exit(0);
			}
		}
		System.out.println("Press Enter To Continue to Main Menu");
		input.nextLine();
		menu_2(roster);
	}
	/**
	 * Writes Finalized Roster to a separate text file
	 * @param o - Roster that will be used to save to file
	 */
	public static void saveFRtoFile(Roster o) { //saves file roster to file
		try {
			File rosterFile = new File("finalroster.txt");
			FileWriter rfw = new FileWriter(rosterFile);
			PrintWriter rfpw = new PrintWriter(rfw);
			rfpw.println(o);
			rfpw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes Roster Waitlist to a separate text file
	 * @param o - Roster that will be used to save to file
	 */
	public static void saveWLtoFile(Roster o) { //saves waitlist to file
		try {
			File waitlistFile = new File("studentwaitlist.txt");
			FileWriter wfw = new FileWriter(waitlistFile);
			PrintWriter wfpw = new PrintWriter(wfw);
			wfpw.println(o.printWaitlist());
			wfpw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Allows a data from text file to be read as Student elements into the Roster system. 
	 * All Student elements will be populated into a Binary Search Tree.
	 * @param filename - the name of the text file
	 * @param roster - the BST that the Student elements will be added to. Implemented with a Map data structures in
	 */
	public static void readFromFile(String filename, Map<Integer, Student> roster) {
		String[] data = new String[20];
		TextFileInput text = new TextFileInput(filename);
		int count = 0;
		String readline = text.readLine();
		while(readline != null) {
			StringTokenizer timeVal = new StringTokenizer(readline, " ");
			if(timeVal.countTokens() == 3) {
				data[count] = readline;
				count++;
			}
			else {
				System.out.println(readline);
			}
			readline = text.readLine();
		}
		text.close();
		for(int i = 0; i < count; i++) {
			String[] str = new String[3];
			int count2 = 0;
			StringTokenizer strings = new StringTokenizer(data[i], " ");
			while(strings.hasMoreTokens()) {	
				str[count2] = strings.nextToken();
				count2++;
			}
			Student newestStudent = new Student(str[0], str[1], Integer.parseInt(str[2]));
			boolean isDuplicate = false;
			if(roster.containsValue(newestStudent)) {
				isDuplicate = true;
			}
			if(isDuplicate) {
				System.out.println("Invalid ID \nStudent: " + newestStudent + "\nError: ID has already been assigned");
			}
			else {
				roster.put(newestStudent.getIDNo(), newestStudent);
			}
		}
	}
}