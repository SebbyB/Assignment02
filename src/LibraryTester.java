package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class contains tests for Library.
 *
 * @author Erin Parker and ??
 * @version September 2, 2020
 */
public class LibraryTester {

	private Library emptyLib, smallLib, mediumLib, generalTestlib;

	@BeforeEach
	void setUp() throws Exception {
		emptyLib = new Library();

		smallLib = new Library();
		smallLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		smallLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		smallLib.add(9780446580342L, "David Baldacci", "Simple Genius");
		
		generalTestlib = new Library();
		generalTestlib.add(9781843192701L, "Moyra Caldecott", "The Lily and the Bull");
		generalTestlib.add(9781843190363L, "Emma Lorant", "Cloner");
		generalTestlib.add(9781843190011L, "Moyra Caldecott", "The Eye of Callanish" );
		
		

		mediumLib = new Library();
		mediumLib.addAll("src/assign02/Mushroom_Publishing.txt");
		// FILL IN -- extend this tester to consider a medium-size library
	}

	//----lookup(ISBN) test methods-----
	@Test
	public void testEmptyLookupISBN() {
		assertNull(emptyLib.lookup(978037429279L));
	}
	
	@Test
	public void testMediumFindLookupISBN() {
		mediumLib.checkout( 9781843192701L ,"Josh Schell", 1, 1, 1111);
		assertEquals("Josh Schell", mediumLib.lookup(9781843192701L));
	}
	
	@Test
	public void testSmallLibraryLookupISBN() {
		assertNull(smallLib.lookup(9780330351690L));
	}
	
	
	//-----------------------------

	
	//----lookup(Holder) test methods------
	@Test
	public void testEmptyLookupHolder() {
		ArrayList<LibraryBook> booksCheckedOut = emptyLib.lookup("Jane Doe");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	@Test
	public void testMultipleBookCheckoutLookupHolder() {
		mediumLib.checkout( 9781843192701L,"Josh Schelly", 1, 1, 1111);
		mediumLib.checkout( 9781843190363L,"Josh Schelly", 1, 1, 1111);
		mediumLib.checkout( 9781843190011L,"Josh Schelly", 1, 1, 1111);
		long[] finalISBN = {9781843190011L, 9781843190363L,9781843192701L};
		
		
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("Josh Schelly");
		assertEquals(3, booksCheckedOut.size());
		assertNotNull(booksCheckedOut);
		for(int i = 0; i < booksCheckedOut.size(); i++) {
		assertTrue(booksCheckedOut.get(i).getIsbn() == finalISBN[i]);
		}

	}
	
	@Test
	public void testSmallLibraryLookupHolder() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = smallLib.lookup("Jane Doe");

		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new LibraryBook(9780330351690L, "Jon Krakauer", "Into the Wild"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}
	///----------------------------------------
	
    //-----checkout test methods-----
	@Test
	public void testEmptyCheckout() {
		assertFalse(emptyLib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
	}
	
	@Test
	public void testSmallLibraryCheckout() {
		assertTrue(smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008));
	}
	
	@Test
	public void testAlreadyCheckedOutCheckout() {
		mediumLib.checkout( 9781843192701L ,"Josh Sch", 1, 1, 2008);
		assertFalse(mediumLib.checkout(9781843192701L, "Casey Johns", 1, 1, 2008));
	}
	@Test
	public void testGeneralCheckout() {
		mediumLib.checkout( 9781843190073L ,"Josh Sch", 1, 1, 2008);		
		assertEquals("Josh Sch",mediumLib.lookup(9781843190073L));
	}
	//------------------------------------
	

	//------ checkin(ISBM) test methods-------
	@Test
	public void testEmptyCheckinISBN() {
		assertFalse(emptyLib.checkin(978037429279L));
	}
	
	@Test
	public void testSmallLibraryCheckinISBN() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		assertTrue(smallLib.checkin(9780330351690L));
	}
	
	@Test
	public void testTrueCheckinCheckinISBN() {
		emptyLib.add(9781843192701L, "Moyra Caldecott", "The Lily and the Bull");
		emptyLib.checkout(9781843192701L, "Joe Doe",1, 1, 2008);
		assertTrue(emptyLib.checkin(9781843192701L));
	}
	
	@Test
	public void testAlreadyCheckinCheckinISBN() {
		emptyLib.add(9781843192701L, "Moyra Caldecott", "The Lily and the Bull");
		emptyLib.checkout(9781843192701L, "Joe Doe",1, 1, 2008);
		emptyLib.checkin(9781843192701L);
		assertFalse(emptyLib.checkin(9781843192701L));
	}

	//------------------------------------
	
	//-----------checkin(Holder) test methods -------------
	@Test
	public void testEmptyCheckinHolder() {
		assertFalse(emptyLib.checkin("Jane Doe"));
	}	
	@Test
	public void testMultipleTrulyeCheckinHolder() {
		emptyLib.add(9781843192701L, "Moyra Caldecott", "The Lily and the Bull");
		emptyLib.add(9781843190363L, "Emma Lorant", "Cloner");
		emptyLib.checkout(9781843192701L, "Joe Doe",1, 1, 2008);
		emptyLib.checkout(9781843192701L, "Joe Doe",1, 1, 2008);
		assertTrue(emptyLib.checkin("Joe Doe"));
		assertFalse(emptyLib.checkin("Joe Doe"));
	}
	
	@Test
	public void testSmallLibraryCheckinHolder() {
		assertFalse(smallLib.checkin("Jane Doe"));
	}
	//---------------------------------------}
}