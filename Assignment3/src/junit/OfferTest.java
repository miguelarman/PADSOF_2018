package junit;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.*;

import application.App;
import application.offer.*;
import application.users.Host;
import exceptions.AUserIsAlreadyLoggedException;
import exceptions.IncorrectPasswordException;
import exceptions.InvalidOfferStatusException;
import exceptions.NotTheOwnerException;
import exceptions.UnexistentUserException;
import exceptions.UserIsBannedException;

public class OfferTest {

	private Host h;
	private Offer o;
	
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
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
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
			app.login("55535121Z", "lolol");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
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
	}
	
	// payOffer
	
	// rateOffer
	
	// getAvgRating

}
