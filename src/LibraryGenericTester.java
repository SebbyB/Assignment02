package assign02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for LibraryGeneric.
 *
 * @author Erin Parker and ??
 * @version September 2, 2020
 */
public class LibraryGenericTester {

	private LibraryGeneric<String> nameLib;  // library that uses names to identify patrons (holders)
	private LibraryGeneric<PhoneNumber> phoneLib;  // library that uses phone numbers to identify patrons
	private LibraryGeneric smallLib; // general library

	@BeforeEach
	void setUp() throws Exception {
		nameLib = new LibraryGeneric<String>();
		nameLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		nameLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		nameLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		phoneLib = new LibraryGeneric<PhoneNumber>();
		phoneLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		phoneLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		phoneLib.add(9780446580342L, "David Baldacci", "Simple Genius");
		
		smallLib = new LibraryGeneric();
		smallLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		smallLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		smallLib.add(9780446580342L, "David Baldacci", "Simple Genius");
		
	}
	//-----------String as Holder Tests------------
	@Test
	public void testNameLibCheckout() {
		String patron = "Jane Doe";
		assertTrue(nameLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(nameLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}
	

	@Test
	public void testNameLibLookup() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = nameLib.lookup(patron);

		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testNameLibCheckin() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(nameLib.checkin(patron));
	}
	//--------------------------------------
	
	
	//-------- PhoneNumber as Holder Tests -------
	@Test
	public void testPhoneLibCheckout() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertTrue(phoneLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(phoneLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testPhoneLibLookup() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut = phoneLib.lookup(patron);

		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testPhoneLibCheckin() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		assertTrue(phoneLib.checkin(patron));
	}
	//------------------------------------------
	
	
	//--------- OverdueList Tests String----------------
	@Test
	public void testDifferingYearsOverdueList() {

		smallLib.checkout(9780374292799L, "Lucy Loo", 12, 18, 2003);
		smallLib.checkout(9780330351690L, "Abraham Abbot", 8, 21, 2001);
		smallLib.checkout(9780446580342L, "Bastion Labbel", 16, 8, 1998);
		

		ArrayList<LibraryBookGeneric<String>> finalArrayList = smallLib.getOverdueList(1, 1, 1990);
		
		assertTrue(finalArrayList.size() == 3);
		assertTrue(finalArrayList.get(0).getHolder().equals("Bastion Labbel"));
		assertTrue(finalArrayList.get(1).getHolder().equals("Abraham Abbot"));
		assertTrue(finalArrayList.get(2).getHolder().equals("Lucy Loo"));

	}
	
	@Test
	public void testDifferingMonthsOverdueList() {

		smallLib.checkout(9780374292799L, "Lucy Loo", 12, 18, 2001);
		smallLib.checkout(9780330351690L, "Abraham Abbot", 8, 21, 2001);
		smallLib.checkout(9780446580342L, "Bastion Labbel", 9, 8, 2001);
		

		ArrayList<LibraryBookGeneric<String>> finalArrayList = smallLib.getOverdueList(1, 1, 1990);
		
		assertTrue(finalArrayList.size() == 3);
		assertTrue(finalArrayList.get(0).getHolder().equals("Abraham Abbot"));
		assertTrue(finalArrayList.get(1).getHolder().equals("Bastion Labbel"));
		assertTrue(finalArrayList.get(2).getHolder().equals("Lucy Loo"));

	}
	
	@Test
	public void testDifferingDaysOverdueList() {

		smallLib.checkout(9780374292799L, "Lucy Loo", 8, 2, 2001);
		smallLib.checkout(9780330351690L, "Abraham Abbot", 8, 21, 2001);
		smallLib.checkout(9780446580342L, "Bastion Labbel", 8, 8, 2001);
		

		ArrayList<LibraryBookGeneric<String>> finalArrayList = smallLib.getOverdueList(1, 1, 1990);
		
		assertTrue(finalArrayList.size() == 3);
		assertTrue(finalArrayList.get(0).getHolder().equals("Lucy Loo"));
		assertTrue(finalArrayList.get(1).getHolder().equals("Bastion Labbel"));
		assertTrue(finalArrayList.get(2).getHolder().equals("Abraham Abbot"));

	}
	
	@Test
	public void testNoBooksOverdueOverdueList() {

		smallLib.checkout(9780374292799L, "Lucy Loo", 12, 18, 2003);
		smallLib.checkout(9780330351690L, "Abraham Abbot", 8, 21, 2001);
		smallLib.checkout(9780446580342L, "Bastion Labbel", 16, 8, 1998);
		

		ArrayList<LibraryBookGeneric<String>> finalArrayList = smallLib.getOverdueList(1, 1, 2200);
		ArrayList<LibraryBookGeneric<String>> CompareArrayList = new ArrayList<LibraryBookGeneric<String>>();
		
		assertTrue(finalArrayList.size() == 0);
		assertTrue(finalArrayList.equals(CompareArrayList));

	}
	
	@Test
	public void testSomeBooksOverdueOverdueList() {

		smallLib.checkout(9780374292799L, "Lucy Loo", 1, 1, 2001);
		smallLib.checkout(9780330351690L, "Abraham Abbot", 2, 1, 2000);
		smallLib.checkout(9780446580342L, "Bastion Labbel", 16, 8, 1998);
		

		ArrayList<LibraryBookGeneric<String>> finalArrayList = smallLib.getOverdueList(1, 1, 2000);
		ArrayList<LibraryBookGeneric<String>> CompareArrayList = new ArrayList<LibraryBookGeneric<String>>();
		
		assertEquals(2,finalArrayList.size());
		assertTrue(finalArrayList.get(0).getHolder().equals("Abraham Abbot"));
	}
	

	//--------- OverdueList Tests PhoneNumber----------------
		@Test
		public void testPhoneDifferingYearsOverdueList() {
			PhoneNumber patron1 = new PhoneNumber("801.555.1234");
			PhoneNumber patron2 = new PhoneNumber("801.666.1234");
			PhoneNumber patron3 = new PhoneNumber("801.555.1222");
			smallLib.checkout(9780374292799L, patron1, 12, 18, 2003);
			smallLib.checkout(9780330351690L, patron2, 8, 21, 2001);
			smallLib.checkout(9780446580342L, patron3, 16, 8, 1998);
			

			ArrayList<LibraryBookGeneric<PhoneNumber>> finalArrayList = smallLib.getOverdueList(1, 1, 1990);
			
			assertTrue(finalArrayList.size() == 3);
			assertTrue(finalArrayList.get(0).getHolder().equals(patron3));
			assertTrue(finalArrayList.get(1).getHolder().equals(patron2));
			assertTrue(finalArrayList.get(2).getHolder().equals(patron1));

		}
		
		@Test
		public void testPhoneDifferingMonthsOverdueList() {
			PhoneNumber patron1 = new PhoneNumber("801.555.1234");
			PhoneNumber patron2 = new PhoneNumber("801.666.1234");
			PhoneNumber patron3 = new PhoneNumber("801.555.1222");

			smallLib.checkout(9780374292799L, patron1, 12, 18, 2001);
			smallLib.checkout(9780330351690L, patron2, 8, 21, 2001);
			smallLib.checkout(9780446580342L, patron3 , 9, 8, 2001);
			

			ArrayList<LibraryBookGeneric<PhoneNumber>> finalArrayList = smallLib.getOverdueList(1, 1, 1990);
			
			assertTrue(finalArrayList.size() == 3);
			assertTrue(finalArrayList.get(0).getHolder().equals(patron2));
			assertTrue(finalArrayList.get(1).getHolder().equals(patron3));
			assertTrue(finalArrayList.get(2).getHolder().equals(patron1));

		}
		
		@Test
		public void testPhoneDifferingDaysOverdueList() {

			PhoneNumber patron1 = new PhoneNumber("801.555.1234");
			PhoneNumber patron2 = new PhoneNumber("801.666.1234");
			PhoneNumber patron3 = new PhoneNumber("801.555.1222");
			
			smallLib.checkout(9780374292799L, patron1 , 8, 2, 2001);
			smallLib.checkout(9780330351690L, patron2 , 8, 21, 2001);
			smallLib.checkout(9780446580342L, patron3, 8, 8, 2001);
			

			ArrayList<LibraryBookGeneric<PhoneNumber>> finalArrayList = smallLib.getOverdueList(1, 1, 1990);
			
			assertTrue(finalArrayList.size() == 3);
			assertTrue(finalArrayList.get(0).getHolder().equals(patron1));
			assertTrue(finalArrayList.get(1).getHolder().equals(patron3));
			assertTrue(finalArrayList.get(2).getHolder().equals(patron2));

		}
		
		@Test
		public void testPhoneNoBooksOverdueOverdueList() {
			PhoneNumber patron1 = new PhoneNumber("801.555.1234");
			PhoneNumber patron2 = new PhoneNumber("801.666.1234");
			PhoneNumber patron3 = new PhoneNumber("801.555.1222");
			

			smallLib.checkout(9780374292799L, patron1, 12, 18, 2003);
			smallLib.checkout(9780330351690L, patron2, 8, 21, 2001);
			smallLib.checkout(9780446580342L, patron3, 16, 8, 1998);
			

			ArrayList<LibraryBookGeneric<PhoneNumber>> finalArrayList = smallLib.getOverdueList(1, 1, 2200);
			ArrayList<LibraryBookGeneric<PhoneNumber>> CompareArrayList = new ArrayList<LibraryBookGeneric<PhoneNumber>>();
			
			assertTrue(finalArrayList.size() == 0);
			assertTrue(finalArrayList.equals(CompareArrayList));

		}
		
		@Test
		public void testPhoneSomeBooksOverdueOverdueList() {
			
			PhoneNumber patron1 = new PhoneNumber("801.555.1234");
			PhoneNumber patron2 = new PhoneNumber("801.666.1234");
			PhoneNumber patron3 = new PhoneNumber("801.555.1222");
			

			smallLib.checkout(9780374292799L, patron1, 1, 1, 2001);
			smallLib.checkout(9780330351690L, patron2, 2, 1, 2000);
			smallLib.checkout(9780446580342L, patron3, 16, 8, 1998);
			

			ArrayList<LibraryBookGeneric<PhoneNumber>> finalArrayList = smallLib.getOverdueList(1, 1, 2000);
			ArrayList<LibraryBookGeneric<PhoneNumber>> CompareArrayList = new ArrayList<LibraryBookGeneric<PhoneNumber>>();
			
			assertEquals(2,finalArrayList.size());
			assertTrue(finalArrayList.get(0).getHolder().equals(patron2));
		}
	
	//---------------------------------------------------
	
	//--------- getOrderedByTitle String Tests----------------

	@Test
	public void testNameGeneralTitleSort() {
	

		ArrayList<LibraryBookGeneric<String>> finalArrayList = smallLib.getOrderedByTitle();
		
		assertTrue(finalArrayList.size() == 3);
		
		assertEquals(finalArrayList.get(0).getTitle(), "Into the Wild");
		assertEquals(finalArrayList.get(1).getTitle(), "Simple Genius");
		assertEquals(finalArrayList.get(2).getTitle(), "The World is Flat");

	}
	
	@Test
	public void testNameAlreadyOrderedNameSort() {
				

		ArrayList<LibraryBookGeneric<String>> finalArrayList = smallLib.getOrderedByTitle();
		
		assertTrue(finalArrayList.size() == 3);		
		assertEquals(finalArrayList.get(0).getTitle(), "Into the Wild");
		assertEquals(finalArrayList.get(1).getTitle(), "Simple Genius");
		assertEquals(finalArrayList.get(2).getTitle(), "The World is Flat");
	}
	@Test
	public void testPhoneGeneralOrderedNameSort() {
		
	 //-----------------------------------------
		
		//--------- getOrderedByTitle PhoneNumber Tests----------------

		ArrayList<LibraryBookGeneric<PhoneNumber>> finalArrayList = phoneLib.getOrderedByTitle();
		ArrayList<LibraryBookGeneric<PhoneNumber>> CompareArrayList = new ArrayList<LibraryBookGeneric<PhoneNumber>>();
		
		assertTrue(finalArrayList.size() == 3);		
		assertEquals(finalArrayList.get(0).getTitle(), "Into the Wild");
		assertEquals(finalArrayList.get(1).getTitle(), "Simple Genius");
		assertEquals(finalArrayList.get(2).getTitle(), "The World is Flat");
	}
	
	@Test
	public void testPhoneAlreadyOrderedNameSort() {

		ArrayList<LibraryBookGeneric<PhoneNumber>> finalArrayList = phoneLib.getOrderedByTitle();
		ArrayList<LibraryBookGeneric<PhoneNumber>> CompareArrayList = new ArrayList<LibraryBookGeneric<PhoneNumber>>();
		
		assertTrue(finalArrayList.size() == 3);		
		assertEquals(finalArrayList.get(0).getTitle(), "Into the Wild");
		assertEquals(finalArrayList.get(1).getTitle(), "Simple Genius");
		assertEquals(finalArrayList.get(2).getTitle(), "The World is Flat");
	}
	 //--------------------------------------------------------------
	
	
}
