package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Scanner;

public class LibraryGeneric<Type>{

	private ArrayList<LibraryBookGeneric<Type>> library;

	/**
	 * Creates an empty library.
	 * Empty Libraries are array lists of type Type;
	 * This initializes the library.
	 */
	public LibraryGeneric() {
		library = new ArrayList<LibraryBookGeneric<Type>>();
	}

	/**
	 * Adds to the library the book, specified by the given ISBN, author, and
	 * title. Assumes there is no possibility of duplicate library books.
	 *
	 * @param isbn - ISBN of the book to be added
	 * @param author - Author of the book to be added
	 * @param title - Title of the book to be added
	 *
	 * Adds an object to the array list of type Type.
	 */
	public void add(long isbn, String author, String title) {
		library.add(new LibraryBookGeneric(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library. Assumes there is no
	 * possibility of duplicate library books.
	 *
	 * @param list - list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {
		library.addAll(list);
	}

	/**
	 * Adds the the library the books specified by the input file. Assumes the
	 * input files specifies one book per line with ISBN, author, and title
	 * separated by tabs.
	 *
	 * If file does not exist or format is violated, prints an error message and
	 * does not change the library.
	 *
	 * @param filename
	 */

	public void addAll(String filename) {
		ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNum = 1;

			while(fileIn.hasNextLine()) {
				String line = fileIn.nextLine();

				Scanner lineIn = new Scanner(line);
				lineIn.useDelimiter("\\t");

				if(!lineIn.hasNextLong()) {
					lineIn.close();
					throw new ParseException("ISBN", lineNum);
				}
				long isbn = lineIn.nextLong();

				if(!lineIn.hasNext()) {
					lineIn.close();
					throw new ParseException("Author", lineNum);
				}
				String author = lineIn.next();

				if(!lineIn.hasNext()) {
					lineIn.close();
					throw new ParseException("Title", lineNum);
				}
				String title = lineIn.next();

				toBeAdded.add(new LibraryBookGeneric(isbn, author, title));

				lineNum++;
				lineIn.close();
			}
			fileIn.close();
		}
		catch(FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		}
		catch(ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Nothing added to the library.");
			return;
		}

		library.addAll(toBeAdded);
	}

	/**
	 * Returns the holder of the library book with the specified ISBN.
	 *
	 * If no book with the specified ISBN is in the library, returns null.
	 *
	 * @param isbn - ISBN of the book to be looked up
	 *
	 * Loops through the Library Array checking each book
	 *If the isbn of the book is equal to the isbn of the book to be found it will return the holder of the book
	 *Otherwise the book is not a part of the library and returns null
	 */
	public Type lookup(long isbn) {
	
		
		for (int i = 0; i < this.library.size(); i++) {
			if(this.library.get(i).getIsbn() == isbn) {
				return this.library.get(i).getHolder();
			}
		}
		return null;
	}

	/**
	 * Returns the list of library books checked out to the specified holder.
	 *
	 * If the specified holder has no books checked out, returns an empty list.
	 *
	 * @param holder - holder whose checked out books are returned
	 *
	 * Creates an empty library of type LibraryBook
	 * Loops through the Library Array checking each book
	 *If the Holder of the book is equal to the Holder of the book to be found it will add the book the empty library
	 *Otherwise the book is not a part of the library and the loop continues
	 *Returns the empty library with the added books
	 */
	public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {
		
		ArrayList<LibraryBookGeneric<Type>> holderLibrary = new ArrayList<LibraryBookGeneric<Type>>();
		
		for (int i = 0; i < this.library.size(); i++) {
			if(!(this.library.get(i).getHolder() == null)) {
				if(this.library.get(i).getHolder().equals(holder)) {
				holderLibrary.add(this.library.get(i));
				}
			}
		}
		return holderLibrary;
		// FILL IN -- do not return null

	}

	/**
	 * Sets the holder and due date of the library book with the specified ISBN.
	 *
	 * If no book with the specified ISBN is in the library, returns false.
	 *
	 * If the book with the specified ISBN is already checked out, returns
	 * false.
	 *
	 * Otherwise, returns true.
	 *
	 * @param isbn - ISBN of the library book to be checked out
	 * @param holder - new holder of the library book
	 * @param month - month of the new due date of the library book
	 * @param day - day of the new due date of the library book
	 * @param year - year of the new due date of the library book
	 *
	 *
	 *Loops through the Library Array
	 *If the book has the same isbn as the requested book >>
	 * It checks if the book is checked out. If it is the book cannot be checked out and returns false
	 * Otherwise it checks out the book for the requested length of time and designates the requested holder as the holder of the book.
	 *If loop finishes and it cannot find the book within the library then the book isn't in the library and it returns false.
	 */
	public boolean checkout(long isbn, Type holder, int month, int day, int year) {
		
		for (int i = 0; i < this.library.size(); i++) {
			if(this.library.get(i).getIsbn() == isbn ) {
				//If this library book is not checked out, check book out and return true
				if(!this.library.get(i).getCheckOutStatus()) {					
					this.library.get(i).checkOutLibraryBook(holder, month, day, year);
					return true;
					}
				else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Unsets the holder and due date of the library book.
	 *
	 * If no book with the specified ISBN is in the library, returns false.
	 *
	 * If the book with the specified ISBN is already checked in, returns false.
	 *
	 * Otherwise, returns true.
	 *
	 * @param isbn - ISBN of the library book to be checked in
	 *
	 *
	 *Loops through the library array
	 *if the isbn of each book is equal to the requested isbn and the book is checked out it checks in the book and returns true
	 *Otherwise it continues through the loop.
	 *if the loop finishes without finding the book then the book is not a part of the library and it returns false.
	 */
	public boolean checkin(long isbn) {
		
		for (int i = 0; i < this.library.size(); i++) {
			if(this.library.get(i).getIsbn() == isbn) {
				if(this.library.get(i).getCheckOutStatus())
					this.library.get(i).checkInLibraryBook();
					return true;
				}
				else {
					continue;
				}
		
		}		
		return false;

	}

	/**
	 * Unsets the holder and due date for all library books checked out by the
	 * specified holder.
	 *
	 * If no books with the specified holder are in the library, returns false;
	 *
	 * Otherwise, returns true.
	 *
	 * @param holder - holder of the library books to be checked in
	 *
	 *loops through the library
	 *               if the selected book is held by the designated holder it will check in the book and return true.
	 *               if the selected book is not held by the designated holder it will return false.
	 *if there is not a book in the library with the designated holder then it will return false.
	 */
	public boolean checkin(Type holder) {
		// FILL IN -- do not return false unless appropriate
		for (int i = 0; i < this.library.size(); i++) {
			if(this.library.get(i).getHolder() == holder) {
				if(this.library.get(i).getCheckOutStatus())
					this.library.get(i).checkInLibraryBook();
					return true;
				}
				else {
					return false;
				}
			
		}		
		return false;
	}
	
	
	/**
	 * Returns the list of library books, sorted by ISBN (smallest ISBN first).
	 */
	public ArrayList<LibraryBookGeneric<Type>> getInventoryList() {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);

		OrderByIsbn comparator = new OrderByIsbn();

		sort(libraryCopy, comparator);

		return libraryCopy;
	}

	/**
	 *
	 * Returns a list of OverDue Books
	 *
	 * @param month - Month OverDue as int
	 * @param day - Day OverDue as int
	 * @param year -Year OverDue as int
	 * @return A List of Overdue books as a library
	 *
	 * Initializes a new Array List of type LibraryBookGeneric which is empty
	 * loops through the compared library
	 * if the dueDate has a day, month, or year that is before the given month, day, or year it adds it to the empty Library.
	 *
	 * Initializes a new list.
	 * Sorts the List
	 * adds it to overDueLibrary
	 * returns a sorted list of overDueBooks
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year) {
		
		ArrayList<LibraryBookGeneric<Type>> overdueLibrary = new ArrayList<LibraryBookGeneric<Type>>();
		for (int i = 0; i < this.library.size(); i++) {
			if(this.library.get(i).getDueDate().get(Calendar.YEAR) > year ) {
				overdueLibrary.add(this.library.get(i));
				continue;
			}
			if(this.library.get(i).getDueDate().get(Calendar.YEAR) < year) {
				continue;
			}
			if(this.library.get(i).getDueDate().get(Calendar.MONTH) > month) {
				overdueLibrary.add(this.library.get(i));
				continue;
			}
			if(this.library.get(i).getDueDate().get(Calendar.MONTH) < month) {
				continue;
			}
			if(this.library.get(i).getDueDate().get(Calendar.DAY_OF_MONTH)> day) {
				overdueLibrary.add(this.library.get(i));
				continue;
			}
		}		
		OrderByDueDate comparator = new OrderByDueDate();

		sort(overdueLibrary, comparator);
		
		return overdueLibrary;
	}

	/**
	 * Returns the list of library books, sorted by title
	 * @return a list of books sorted by Name
	 *
	 * Initializes a new Array List of Type LibraryBookGeneric which is empty
	 *Loops through compared Library and adds each item to the empty list
	 * Compares book title to and sorts by alphabetical order
	 */


	public ArrayList<LibraryBookGeneric<Type>> getOrderedByTitle() {
		
		ArrayList<LibraryBookGeneric<Type>> sortedNameLibrary = new ArrayList<LibraryBookGeneric<Type>>();
		for (int i = 0; i < this.library.size(); i++) {
		sortedNameLibrary.add(this.library.get(i));
		}
		
		OrderByLibraryBookTitle comparator = new OrderByLibraryBookTitle();
		
		sort(sortedNameLibrary, comparator);
		
		

		return sortedNameLibrary;
	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList. 
	 * 
	 * 1. Finds the smallest item in the list. 
	 * 2. Swaps the smallest item with the first item in the list. 
	 * 3. Reconsiders the list be the remaining unsorted portion (second item to Nth item) and 
	 *    repeats steps 1, 2, and 3.
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
		for(int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for(j = i + 1, minIndex = i; j < list.size(); j++)
				if(c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			ListType temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the ISBN.
	 */
	protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {

		/**
		 * Returns a negative value if lhs is smaller than rhs. 
		 * Returns a positive value if lhs is larger than rhs. 
		 * Returns 0 if lhs and rhs are equal.
		 */
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			return lhs.getIsbn() > rhs.getIsbn() ? 1 : (lhs.getIsbn() < rhs.getIsbn() ? -1 : 0);
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the due
	 * date.
	 */
	protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {
		
		/**
		 * Returns a negative value if lhs OLDER/ more overdue. 
		 * Returns a positive value if lhs NEWER/ less overdue . 
		 * Returns 0 if lhs and rhs are checked out same day.
		 */
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			if(lhs.getDueDate().get(Calendar.YEAR) > rhs.getDueDate().get(Calendar.YEAR)) {
				return 1;
			  } 
			if (lhs.getDueDate().get(Calendar.YEAR) < rhs.getDueDate().get(Calendar.YEAR)){
				return -1;
			}
			if(lhs.getDueDate().get(Calendar.MONTH) > rhs.getDueDate().get(Calendar.MONTH)) {
				return 1;
			  }
			if(lhs.getDueDate().get(Calendar.MONTH) < rhs.getDueDate().get(Calendar.MONTH)) {
				return -1;
			  }
			if(lhs.getDueDate().get(Calendar.DAY_OF_MONTH) > rhs.getDueDate().get(Calendar.DAY_OF_MONTH)) {
				return 1;
			  }
			if(lhs.getDueDate().get(Calendar.DAY_OF_MONTH) < rhs.getDueDate().get(Calendar.DAY_OF_MONTH)) {
				return -1;
			  }
			else {
				return 0;
			}
		
		//	return lhs.getDueDate() > rhs.getDueDate() ? 1 : (lhs.getDueDate() < rhs.getDueDate() ? -1 : 0);
		// FILL IN
	}
}
		
		protected class OrderByLibraryBookTitle implements Comparator<LibraryBookGeneric<Type>> {
			/**
			 * Returns a negative value if lhs beginning/smaller 
			 * Returns a positive value if lhs later/larger less overdue . 
			 * Returns 0 if lhs and rhs are the same word
			 */
			
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			

			
			String LHS = lhs.getTitle();
			String RHS = rhs.getTitle();
			
			int smallerBookSize;
			if(LHS.length() < RHS.length()|| LHS.length() == RHS.length()) {
				smallerBookSize = LHS.length();
			}
			else{
				smallerBookSize = RHS.length();
			}
			for(int i = 0; i < smallerBookSize ;i++) {
				if(LHS.charAt(i) < RHS.charAt(i)) {
					return -1;
				}
				if(LHS.charAt(i) > RHS.charAt(i)) {
					return 1;
				}
			}
			if(LHS.length() < RHS.length()) {
				return -1;
			}
			if(RHS.length() < LHS.length()) {
				return 1;
			}
			return 0;
			
		}
}}
