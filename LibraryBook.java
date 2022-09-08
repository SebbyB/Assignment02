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
//    private int daysCheckedOut;

//    public boolean CheckOutStatus(){
//        if(this.checkOutStatus == false) {
//
//            this.bookHolder = null;
//            this.dueDate = null;
//            return false;
//        }
//        else{
//            return true;
//        }
//    }
//        public GregorianCalendar getDueDate(){
//        int year = this.dueDate.getYear();
//        int month = this.dueDate.getHours();
//        int day = this.dueDate.getDay();
//        GregorianCalendar dueDate = new GregorianCalendar(year,month,day);
//        return dueDate;
//    }


    public LibraryBook(long isbn, String author, String title){
        super(isbn, author, title);

//        GregorianCalendar checkOutDate = new GregorianCalendar();
//        dueDate = new Date(checkOutDate.getDueDate());
//
    }
    
    public String getHolder() {
    	return this.bookHolder;
    }
    
    public GregorianCalendar getDueDate() {    	
    	return this.dueDate;
    }
    
    public boolean getCheckOutStatus() {    	
    	return this.checkOutStatus;
    }
    
    public void checkOutLibraryBook(String holder, int month, int day, int year){
    	this.dueDate = new GregorianCalendar(year, month, day);
    	this.bookHolder = holder;
    	this.checkOutStatus = true;
    	
    }
     
    public void checkInLibraryBook() {
    	this.bookHolder = null;
    	this.dueDate = null;
    }
    
//    public void CheckOutLibraryBook(LibraryBook libraryBook, String fullName, int daysCheckedOut){
//
//
//        String bookName = libraryBook.getTitle();
//        String bookAuthor = libraryBook.getAuthor();
//        GregorianCalendar cal = new GregorianCalendar();
//
//        if(libraryBook.CheckOutStatus() == true){
//            System.out.println(bookName +" by " + bookAuthor + " was checked out by: \n" + libraryBook.bookHolder + " on " + libraryBook.checkOutDate + "...\n");
//            System.out.println("It should be checked back in by " + libraryBook.dueDate);
//        }
//        else {
//            cal.add(Calendar.DAY_OF_MONTH, daysCheckedOut);
//            libraryBook.dueDate = cal.getTime();
//            libraryBook.bookHolder = fullName;
//            this.checkOutStatus = true;
//            System.out.println("The book " + bookName + " by " + bookAuthor + " is now checked out to: \n" + libraryBook.bookHolder);
//            System.out.println("It is due on " + libraryBook.dueDate + "...");
//
//
//        }
//
//
//    
//    public boolean CheckOutStatus(){
//        if(this.checkOutStatus == false) {
//
//            this.bookHolder = null;
//            this.dueDate = null;
//            return false;
//        }
//        else{
//            return true;
//        }
//    
//    
//    public boolean checkOut() 
//
//    public String getHolder(){
//        if(this.checkOutStatus == false) {
//            bookHolder = null;
//            dueDate = null;
//            return null;
//        }
//        else{
//            return this.bookHolder;
//        }
//    }
//

public static void main(String[] args){





}
}

