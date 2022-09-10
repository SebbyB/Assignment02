package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a library, which is a collection of library books.
 *
 * @author Erin Parker and Amelia Nelson and Sebastian Barney
 * @version September 2, 2020
 */
public class Library {

	private ArrayList<LibraryBook> library;

	/**
	 * Creates an empty library.
	 * Empty Libraries are array lists of type Library Book;
	 * This initializes the library.
	 */
	public Library() {
		library = new ArrayList<LibraryBook>();
	}

	/**
	 * Adds to the library the book, specified by the given ISBN, author, and
	 * title. Assumes there is no possibility of duplicate library books.
	 *
	 * @param isbn - ISBN of the book to be added
	 * @param author - Author of the book to be added
	 * @param title - Title of the book to be added
	 *
	 * Adds an object to the array list of type LibraryBook.
	 */
	public void add(long isbn, String author, String title) {
		library.add(new LibraryBook(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library. Assumes there is no
	 * possibility of duplicate library books.
	 *
	 * @param list - list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBook> list) {
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
		ArrayList<LibraryBook> toBeAdded = new ArrayList<LibraryBook>();

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

				toBeAdded.add(new LibraryBook(isbn, author, title));

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
	public String lookup(long isbn) {
	
		
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
	public ArrayList<LibraryBook> lookup(String holder) {
		
		ArrayList<LibraryBook> holderLibrary = new ArrayList<LibraryBook>();
		
		for (int i = 0; i < this.library.size(); i++) {
			if(!(this.library.get(i).getHolder() == null)) {
				if(this.library.get(i).getHolder().equals(holder)) {
				holderLibrary.add(this.library.get(i));
				}
			}
		}
		return holderLibrary;

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
	public boolean checkout(long isbn, String holder, int month, int day, int year) {
		
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
		// FILL IN -- do not return false unless appropriate
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
				if(this.library.get(i).getCheckOutStatus()== true) {
					this.library.get(i).checkInLibraryBook();
					return true;
				}
				else {
					continue;
				}
		
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
	public boolean checkin(String holder) {

		for (int i = 0; i < this.library.size(); i++) {
			if(this.library.get(i).getHolder() == holder) {
				if(this.library.get(i).getCheckOutStatus()== true)
					this.library.get(i).checkInLibraryBook();
					return true;
				}
				else {
					return false;
				}
			
		}		
		return false;
	}
}