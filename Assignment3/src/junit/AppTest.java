package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import application.App;
import application.dates.ModifiableDate;
import application.offer.House;
import application.users.Host;
import application.users.RegisteredUser;
import exceptions.AUserIsAlreadyLoggedException;
import exceptions.IncorrectPasswordException;
import exceptions.InvalidDateException;
import exceptions.InvalidRolException;
import exceptions.NoUserLoggedException;
import exceptions.NotTheOwnerException;
import exceptions.OfferAlreadyCreatedException;
import exceptions.UnexistentUserException;
import exceptions.UserIsBannedException;

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
			app.login("51999111X", "swordFish");
			RegisteredUser userToBan = App.getLoggedUser();

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
		} catch (InvalidRolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

		app = App.openApp();

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
		app.logout();
		
		int before = app.getOffers().size();
		
		ModifiableDate.plusDays(100);
		
		app = App.openApp();
		assertEquals(app.getOffers().size(), before - 1, 0);
	}
	
	// deleteExpiredReservations
	// deleteExpiredPendingOffers (suggestChanges) (approveOffer) (requestRevision)
	
	

}
