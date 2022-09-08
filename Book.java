package assign02;

/**
 * This class represents a book, in which the ISBN, author, and title cannot
 * change once the book is created.  Note that ISBNs are unique.
 *
 * @author Erin Parker and ??
 * @version September 2, 2020
 */
public class Book {

	private long isbn;

	private String author;

	private String title;

	/**
	 * Creates a book from the given ISBN, author, and title.
	 *
	 * @param isbn
	 * @param author
	 * @param title
	 */
	public Book(long isbn, String author, String title) {
		this.isbn = isbn;
		this.author = author;
		this.title = title;
	}

	/**
	 * Accessor method for the author field.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * Accessor method for the isbn field.
	 *
	 * @return the ISBN
	 */
	public long getIsbn() {
		return this.isbn;
	}

	/**
	 * Accessor method for the title field.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Two books are considered equal if they have the same ISBN, author, and
	 * title.
	 *
	 * @param other - the object begin compared with this book
	 * @return true if other object is a Book type and is equal to this book, false otherwise
	 */
	public boolean equals(Object other) {
		// FILL IN -- do not return false unless appropriate
		if(this.getClass() != other.getClass()){

			return false;

		}
		else{
			if(this.getIsbn() != ((Book) other).getIsbn() || this.getAuthor() != ((Book) other).getAuthor() || this.getTitle() != ((Book) other).getTitle()){
				return false;

			}
			else{
				return true;
			}
		}
	}

	/**
	 * Returns a textual representation of this book.
	 */
	public String toString() {
		return isbn + ", " + author + ", \"" + title + "\"";
	}


	public static void main(String[] args){

//		System.out.println("This is the start of the program...");
//		Book book1 = new Book(123,"Author of 123", "This book is called 123");
//		Book book2 = new Book(456,"Author of 456", "This book is called 456");
//		Book book3 = new Book(123,"Author of 123", "This book is called 123");
//		String notBook = "hi";
//
//
//		System.out.println("Is book1 equal to book 2");
//		System.out.println(book1.equals(book2));
//		System.out.println("Is book1 equal to book 3");
//		System.out.println(book1.equals(book3));
//		System.out.println("Is book1 equal to notBook");
//		System.out.println(book1.equals(notBook));
//
//
//		System.out.println("This is the end of the code...");
//
//
//
	}
}