package junit;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.*;

import application.App;
import application.dates.ModifiableDate;
import application.offer.*;
import application.users.*;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import exceptions.*;

public class ReservationTest {
	
	private Reservation r;
	private static Offer o;
	private RegisteredUser user;
	
	@BeforeClass
	public static void setUp() {
		Host h = new Host("Host Jesus", "de los hosts", "password", "2387129837134534", "92837492F");
		House house = new House(4730, "cantblanc", h);
		
		o = new HolidayOffer(LocalDate.now(), 100.0, 10.0, "description", house, LocalDate.now().plusDays(1));
	}
	
	
	public void createReservationWithGuest() {
		user = new Guest("name", "surname", "psswd", "ccard", "NIF");
		r = new Reservation(user, o);
	}
	
	public void createReservationWithHost() {
		user = new Host("name", "surname", "psswd", "ccard", "NIF");
		r = new Reservation(user, o);
	}
	
	public void createReservationWithMulti() {
		user = new MultiRoleUser("name", "surname", "psswd", "ccard", "NIF");
		r = new Reservation(user, o);
	}
	
	@Test
	public void testCancelReservation() {
		this.createReservationWithGuest();
		r.cancelReservation();
		
		assertNull(r.getBookedOffer());
		assertNull(r.getClient());
		assertNull(r.getBookingDate());
		
		r = null;
		
		this.createReservationWithMulti();
		r.cancelReservation();
		
		assertNull(r.getBookedOffer());
		assertNull(r.getClient());
		assertNull(r.getBookingDate());
	}
	
	@Test(expected = InvalidCardNumberException.class)
	public void testPayReservationInvalidCreditCard() throws InvalidCardNumberException {
		
		user = new Guest("Jose Luis", "Perez Lopez", "ItIsNoTKnOwN", "7777222288885555", "55555111Z");

		App app = App.openApp();
		try {
			app.login("55555111Z", "ItIsNoTKnOwN");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e1) {
			fail();
		}
		
		user = App.getLoggedUser();
		
		user.changeCreditCard("123412341234123");
		
		o.getHouse().getHost().changeCreditCard("2387129837134534");
		
		r = new Reservation(user, o);
		
		try {
			r.payReservation();
		} catch (InvalidCardNumberException e) {
			throw e;
		} catch (NotTheReserverException e) {
			fail();
		} catch (CouldNotPayHostException e) {
			fail();
		} catch (TimeIsUpException e) {
			fail();
		}
	}
	
	@Test(expected = NotTheReserverException.class)
	public void testPayReservationNotTheReserver() throws NotTheReserverException {

		user = new Guest("Otro", "Otro", "Otro", "7777222288885555", "Otro");

		App app = App.openApp();
		try {
			app.login("55555111Z", "ItIsNoTKnOwN");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e1) {
			fail();
		}
		
		r = new Reservation(user, o);
		
		o.getHouse().getHost().changeCreditCard("2387129837134534");
		
		try {
			r.payReservation();
		} catch (InvalidCardNumberException e) {
			fail();
		} catch (NotTheReserverException e) {
			throw e;
		} catch (CouldNotPayHostException e) {
			fail();
		} catch (TimeIsUpException e) {
			fail();
		}
	}
	
	@Test(expected = CouldNotPayHostException.class)
	public void testPayReservationCouldNotPayHost() throws CouldNotPayHostException {
		
		user = new Guest("Jose Luis", "Perez Lopez", "ItIsNoTKnOwN", "7777222288885555", "55555111Z");

		App app = App.openApp();
		try {
			app.login("55555111Z", "ItIsNoTKnOwN");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e1) {
			fail();
		}
		
		user = (Guest) App.getLoggedUser();
		
		r = new Reservation(user, o);
		
		o.getHouse().getHost().changeCreditCard("wrong");
		
		try {
			r.payReservation();
		} catch (InvalidCardNumberException e) {
			fail();
		} catch (NotTheReserverException e) {
			fail();
		} catch (CouldNotPayHostException e) {
			throw e;
		} catch (TimeIsUpException e) {
			fail();
		}
	}
	
	@Test(expected = TimeIsUpException.class)
	public void testPayReservationTimeIsUp() throws TimeIsUpException {
		
		user = new Guest("Jose Luis", "Perez Lopez", "ItIsNoTKnOwN", "7777222288885555", "55555111Z");

		App app = App.openApp();
		try {
			app.login("55555111Z", "ItIsNoTKnOwN");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e1) {
			fail();
		}
		
		
		r = new Reservation(user, o);
		
		o.getHouse().getHost().changeCreditCard("2387129837134534");
		
		ModifiableDate.plusDays(10);
		
		try {
			r.payReservation();
		} catch (InvalidCardNumberException e) {
			fail();
		} catch (NotTheReserverException e) {
			fail();
		} catch (CouldNotPayHostException e) {
			fail();
		} catch (TimeIsUpException e) {
			throw e;
		}
	}

}
