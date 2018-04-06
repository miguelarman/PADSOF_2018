package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import application.App;
import application.dates.ModifiableDate;
import application.offer.House;
import application.users.*;
import exceptions.*;

public class AppTest {

	@Test
	public void testOpenApp() {
		App app = App.openApp();
		assertNotNull(app);
	}

	@Test
	public void testLoginCorrect() {
		App app = App.openApp();

		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			fail();
		}

		assertNotNull(app);
	}

	@Test
	public void testLoginIncorrect() {
		App app = App.openApp();

		try {
			app.login("51999111X", "incorrect");
		} catch (UserIsBannedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (IncorrectPasswordException e) {

		} catch (UnexistentUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (AUserIsAlreadyLoggedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

		try {
			app.logout();
			app.login("51999111X", "swordFish");
			RegisteredUser userToBan = App.getLoggedUser();
			
			app.logout();
			app = App.openApp();
			app.login("X1130055", "secret");
			app.banUser(userToBan);

		} catch (UserIsBannedException e) {

		} catch (IncorrectPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (UnexistentUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (AUserIsAlreadyLoggedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

		app = App.openApp();
		app.logout();

		try {
			app.login("nouserwiththis", "swordFish");
		} catch (UserIsBannedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (IncorrectPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (UnexistentUserException e) {

		} catch (AUserIsAlreadyLoggedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (IncorrectPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (UnexistentUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (AUserIsAlreadyLoggedException e) {
			
		}

	}
	
	
	// deleteExpiredOffers
	
	@Test
	public void testDeleteExpiredOffers() {
		App app = App.openApp();
		
		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		Host host = (Host)App.getLoggedUser();
		
		House house = new House(28049, "Cantoblanco", host);
		try {
			app.createHolidayOffer(ModifiableDate.getModifiableDate().plusDays(3), 0.0, 0.0, "description", house, ModifiableDate.getModifiableDate().plusDays(18));
		} catch (InvalidRolException | NoUserLoggedException | InvalidDateException | NotTheOwnerException
				| OfferAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		int before = app.getOffers().size();
		app.logout();
		app.closeApp();
		
		
		
		ModifiableDate.plusDays(100);
		
		app = App.openApp();
		assertEquals(app.getOffers().size(), before - 1, 0);
		app.logout();
		app.closeApp();
	}
	
	
	
	// deleteExpiredReservations

	@Test
	public void testDeleteExpiredReservations() {
		App app = App.openApp();
		app.logout();
		
		try {
			app.login("51999111X", "swordFish");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		Host host = (Host)App.getLoggedUser();
		
		House house = new House(28049, "Cantoblanco", host);
		
		try {
			app.createHolidayOffer(ModifiableDate.getModifiableDate().plusDays(3), 0.0, 0.0, "description", house, ModifiableDate.getModifiableDate().plusDays(18));
		} catch (InvalidRolException | NoUserLoggedException | InvalidDateException | NotTheOwnerException
				| OfferAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		app.logout();
		app.closeApp();
		
		app = App.openApp();
		
		try {
			app.login("55555111Z", "ItIsNoTKnOwN");
		} catch (UserIsBannedException | IncorrectPasswordException | UnexistentUserException
				| AUserIsAlreadyLoggedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail();
		}
		
		try {
			app.addReservation(app.getOffers().get(0));
		} catch (InvalidRolException | RestrictedUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		int before = 0;
		
		for (RegisteredUser u : app.getAuthorizedUsers()) {
			before += u.getReservations().size();
		}
		
		app.logout();
		app.closeApp();
		
		ModifiableDate.plusDays(100);
		
		app = App.openApp();
		
		int after = 0;

		for (RegisteredUser u : app.getAuthorizedUsers()) {
			after += u.getReservations().size();
		}
		
		assertEquals(after, before - 1, 0);
	}
	
	
	
	
	// deleteExpiredPendingOffers (suggestChanges) (approveOffer) (requestRevision)
	
	

}
