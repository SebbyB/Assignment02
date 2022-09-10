package assign02;

/**
 * This class represents a book, in which the ISBN, author, and title cannot
 * change once the book is created.  Note that ISBNs are unique.
 *
 * @author Erin Parker and Amelia Neilson and Sebastian Barney
 * @version September 2, 2020
 */
public class Book {

	private long isbn;

	private String author;

	private String title;

	/**
	 * Creates a book from the given ISBN, author, and title.
	 *
	 * @param isbn - the ISBN
	 * @param author - the Author
	 * @param title - the Title
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
	 *
	 *  First checks if the object is of the class book.
	 *  If it isn't it returns false.
	 *  Otherwise it checks if the Isbn and the author and the title of other, are equal to the book it is comparing to.
	 *  If the ISBN or the Author or the Title are different it returns false.
	 *  Otherwise it Returns true.
	 *
	 */
	public boolean equals(Object other) {
		if(!(other instanceof Book)){
			return false;
		}
		else{
			Book B = (Book)other;
			if(this.getIsbn() != B.getIsbn() || !this.getAuthor().equals(B.getAuthor()) || !this.getTitle().equals(B.getTitle())){
				return false;
			}
			else{
				return true;
			}
		}
	}


	}
