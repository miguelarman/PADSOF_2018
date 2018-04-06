package junit;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;

import org.junit.*;

import application.App;
import application.offer.*;
import application.users.Host;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import exceptions.AUserIsAlreadyLoggedException;
import exceptions.CouldNotPayHostException;
import exceptions.IncorrectPasswordException;
import exceptions.InvalidOfferStatusException;
import exceptions.NoUserLoggedException;
import exceptions.NotTheOwnerException;
import exceptions.UnexistentUserException;
import exceptions.UserIsBannedException;

public class OfferTest {

	private Host h;
	private Offer o;
	
	@BeforeClass
	public static void removeData() {
		File file = new File("data.obj"); file.delete();
	}
	
	@Before
	public void setUp() {
		h = new Host("Ana Maria", "Garcia Serrano", "swordFish", "9999666633330000", "51999111X");
		House house = new House(4730, "cantblanc", h);
		
		o = new HolidayOffer(LocalDate.now(), 100.0, 10.0, "description", house, LocalDate.now().plusDays(1));
	}
	
	// modifyOffer
	
	@Test
	public void testModifyOffer() {
		
		App app = App.openApp();
		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			fail();
		}
		
		h = (Host)App.getLoggedUser();
		o = new HolidayOffer(LocalDate.now(), 100.0, 10.0, "description", new House(4730, "cantblanc", h), LocalDate.now().plusDays(1));
		
		
		
		try {
			o.modifyOffer(OfferStatus.PENDING_FOR_CHANGES);
			o.modifyOffer(LocalDate.now());
			o.modifyOffer(0.0, 0.0);
			o.modifyOffer("new description");
			o.modifyOffer(OfferStatus.APPROVED);
		} catch (InvalidOfferStatusException e) {
			System.out.println(e.getStatus());
			fail();
		} catch (NotTheOwnerException e) {
			fail();
		}
		app.logout();
	}
	
	@Test
	public void testModifyOfferIncorrect() {
		
		App app = App.openApp();
		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			fail();
		}
		
		
		o.modifyOffer(OfferStatus.PENDING_FOR_CHANGES);
		
		try {
			app = App.openApp();
			app.logout();
			app.login("55535121Z", "lolol");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			e.printStackTrace();
			fail();
		}
		try {
			o.modifyOffer(LocalDate.now());
		} catch (InvalidOfferStatusException e) {
			fail();
		} catch (NotTheOwnerException e) {
			System.out.println("Excepcion esperada");
		}
		
		
		
		try {
			app = App.openApp();
			app.logout();
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			e.printStackTrace();
			fail();
		}
		
		o.modifyOffer(OfferStatus.PAID);
		try {
			o.modifyOffer(0.0, 0.0);
		} catch (InvalidOfferStatusException e) {
			System.out.println("Excepcion esperada");
		} catch (NotTheOwnerException e) {
			fail();
		}
		
		
		try {
			o.modifyOffer("new description");
		} catch (InvalidOfferStatusException e) {
			System.out.println("Excepcion esperada");
		} catch (NotTheOwnerException e) {
			fail();
		}
		
		
		o.modifyOffer(OfferStatus.PENDING_FOR_CHANGES);
		try {
			app = App.openApp();
			app.logout();
			app.login("55535121Z", "lolol");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			e.printStackTrace();
			fail();
		}
		try {
			o.modifyOffer("new description");
		} catch (InvalidOfferStatusException e) {
			fail();
		} catch (NotTheOwnerException e) {
			System.out.println("Excepcion esperada");
		}
		
		
		o.modifyOffer(OfferStatus.APPROVED);
		app.logout();
	}
	
	// payOffer
	@Test
	public void testPayOfferCorrect() {
		App app = App.openApp();
		app.logout();
		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			o.payOffer();
		} catch (InvalidCardNumberException e) {
			e.printStackTrace();
			fail();
		} catch (NoUserLoggedException e) {
			e.printStackTrace();
			fail();
		} catch (CouldNotPayHostException e) {
			e.printStackTrace();
			fail();
		}
		app.logout();
	}
	
	@Test
	public void testPayOffer() {
		App app = App.openApp();
		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			fail();
		}
		
		App.getLoggedUser().changeCreditCard("wrong");
		
		try {
			o.payOffer();
		} catch (InvalidCardNumberException e) {
			System.out.println("Excepcion esperada");
		} catch (NoUserLoggedException e) {
			e.printStackTrace();
			fail();
		} catch (CouldNotPayHostException e) {
			e.printStackTrace();
			fail();
		}
		
		
		app = App.openApp();
		app.logout();
		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			e.printStackTrace();
			fail();
		}
		
		app.logout();
		
		try {
			o.payOffer();
		} catch (InvalidCardNumberException e) {
			e.printStackTrace();
			fail();
		} catch (NoUserLoggedException e) {
			System.out.println("Excepcion esperada");
		} catch (CouldNotPayHostException e) {
			e.printStackTrace();
			fail();
		}
		
		
		app = App.openApp();
		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			fail();
		}
		
		o.getHouse().getHost().changeCreditCard("wrong");
		
		try {
			o.payOffer();
		} catch (InvalidCardNumberException e) {
			e.printStackTrace();
			fail();
		} catch (NoUserLoggedException e) {
			e.printStackTrace();
			fail();
		} catch (CouldNotPayHostException e) {
			System.out.println("Excepcion esperada");
		}
		
		app.logout();
	}
	
	// rateOffer
	
	@Test
	public void testRateOffer() {
		
		App app = App.openApp();
		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			fail();
		}
		
		assertEquals(o.getComments().size(), 0, 0);
		
		try {
			o.rateOffer(0.0);

			assertEquals(o.getOpinions().size(), 1, 0);

			o.rateOffer(5.0);
			assertEquals(o.getOpinions().size(), 2, 0);
			
			o.rateOffer("really nice");
			assertEquals(o.getOpinions().size(), 3, 0);
		} catch (NoUserLoggedException e) {
			e.printStackTrace();
			fail();
		}
		app.logout();
	}
	
	@Test(expected = NoUserLoggedException.class)
	public void testRateOfferIncorrectNumerical() throws NoUserLoggedException {
		
		App app = App.openApp();
		app.logout();
		
		assertEquals(o.getComments().size(), 0, 0);
		
		o.rateOffer(0.0);
	}
	
	@Test(expected = NoUserLoggedException.class)
	public void testRateOfferIncorrecttext() throws NoUserLoggedException {
		
		App app = App.openApp();
		app.logout();
		
		assertEquals(o.getComments().size(), 0, 0);
		
		o.rateOffer("nice house");
	}
	
	// getAvgRating

	@Test
	public void testAvgRating() {
		
		App app = App.openApp();
		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			fail();
		}
		
		
		try {
			o.rateOffer(0.0);
			o.rateOffer(5.0);			
			o.rateOffer("really nice");
			o.rateOffer(1.0);
			o.rateOffer(2.0);
			
			
			assertEquals(o.getAvgRating(), 2.0, 0.1);
		} catch (NoUserLoggedException e) {
			e.printStackTrace();
			fail();
		}
		
		app.logout();
	}
}
