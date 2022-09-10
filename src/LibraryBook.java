package assign02;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LibraryBook extends Book {

    private String bookHolder;
    private GregorianCalendar dueDate;
    
//    checkOutStatus = true if the book is checked out
    private boolean checkOutStatus = false;

    /**
     *
     * @param isbn - Isbn of the book
     * @param author - Author of the book
     * @param title - Title of the book
     * Creates a LibraryBook that inherits the properties of the superclass Book.
     */
    public LibraryBook(long isbn, String author, String title){
        super(isbn, author, title);

    }

    /**
     *
     * @return bookHolder - returns the value of the bookHolder Variable that it checks against.
     * Ex. LibraryBook has a holder named Jeffrey Eggleton
     *  LibraryBook.getHolder() returns Jeffrey Eggleton
     */
    public String getHolder() {
    	return this.bookHolder;
    }


    /**
     * @return dueDate - returns the value of the dueDate Variable that it checks against.
     *  Ex. LibraryBook has a dueDate of 1/1/2020
     *  LibraryBook.getDueDate() returns 1/1/2020
     */
    public GregorianCalendar getDueDate() {    	
    	return this.dueDate;
    }


    /**
     * @return checkOutStatus - returns the value of the checkOutStatus Variable that it checks against.
     *  Ex. LibraryBook has a checkOutStatus of False
     *  LibraryBook.checkOutStatus() returns False
     */
    public boolean getCheckOutStatus() {    	
    	return this.checkOutStatus;
    }

    /**
     *
     * @param holder -Holder of the LibraryBook
     * @param month -Month of requested Checkout
     * @param day -Day of requested Checkout
     * @param year -Year of requested Checkout
     *
     * Sets LibraryBook's Variable dueDate to the GregorianCalendar Date of month, day, year.
     * Sets LibraryBook's Variable bookHolder to the designated holder.
     * Sets LibraryBook's Variable checkOutStatus to true.
     *
     *  Ex. LibraryBook is not checked out so it has a null holder and dueDate and a checkOutStatus of False.
     *             Ethan Eggleton is the Designated Holder and requests 4/19/2023 as a checkout Date for LibraryBook
     *             LibraryBook.checkOutLibraryBook("Ethan Eggleton", 4, 19, 2023) changes LibraryBook's dueDate to 4/19/2023, the Holder to Ethan Eggleton, and the checkOutStatus to true.
     */
    public void checkOutLibraryBook(String holder, int month, int day, int year){
    	this.dueDate = new GregorianCalendar(year, month, day);
    	this.bookHolder = holder;
    	this.checkOutStatus = true;
    	
    }

    /**
     * Checks in the LibraryBook by changing the variable values for bookHolder and dueDate to null and checkOutStatus to false
     * Ex.
     * LibraryBook's dueDate is 4/19/2023, the Holder is Ethan Eggleton, and the checkOutStatus is true.
     * LibraryBook.checkInLibraryBook() makes LibraryBook have a holder of null and a dueDate of Null. The Book is not checked out.
     */
    public void checkInLibraryBook() {
    	this.bookHolder = null;
    	this.dueDate = null;
    	this.checkOutStatus = false;
    }    
}

