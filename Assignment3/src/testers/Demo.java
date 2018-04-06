package testers;

import application.offer.House;

import java.time.LocalDate;

import application.App;
import application.dates.ModifiableDate;
import exceptions.*;


public class Demo {

	public static void main(String[] args) {
		App a = App.openApp();
		
		//Checking if the system is opened correctly the first time
		System.out.println("====================");
		System.out.println(a);
		System.out.println("====================\n");
		ModifiableDate.setToday();
		
		a.closeApp();
		
		//Checking if the system is opened correctly when it has already created
		a = App.openApp();
		
		System.out.println("====================");
		System.out.println(a);
		System.out.println("====================\n");
		
		
		try {
			a.login("12345678X", "jejexd"); //Trying to login with an incorrect id
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		//Checking that no one has logged in yet
		System.out.println("====================");
		System.out.println("Logged user:\n" + App.getLoggedUser());
		System.out.println("====================\n");
		
		try {
			a.login("51999111X", "asdfg"); //Trying to login with an incorrect password
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		//Checking that no one has logged in yet
		System.out.println("====================");
		System.out.println("Logged user:\n" + App.getLoggedUser());
		System.out.println("====================\n");
		
		try {
			a.login("51999111X", "swordFish"); //Login with an authorized user (host)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		//Checking if the loggedUser has been changed successfully
		System.out.println("====================");
		System.out.println("Logged user:\n" + App.getLoggedUser());
		System.out.println("====================\n");
		
		//Trying to log in before logging out
		try {
			a.login("51999111X", "asdfg"); 
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		a.logout(); //Logging out
		
		//Checking if there is no user logged
		System.out.println("====================");
		System.out.println("Logged user:\n" + App.getLoggedUser());
		System.out.println("====================\n");
		
		//Login with the same user
		try {
			a.login("51999111X", "swordFish"); //Login with an authorized user (host)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		House house1 = null;
		try {
			house1 = a.createHouse(29087, "Madrid"); //We create a house
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		
		//Checking if the house was created successfully
		System.out.println("====================");
		System.out.println(house1);
		System.out.println("====================\n");
		
		try {
			house1.addCharacteristic("SwimmingPool", "Amazing"); //We add a characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		//Checking if the characteristic was added correctly
		System.out.println("====================");
		System.out.println(house1);
		System.out.println("====================\n");
		
		try {
			house1.addCharacteristic("SwimmingPool", "Amazing"); //We try to add the same characteristic again
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		//Checking if the characteristic was not added
		System.out.println("====================");
		System.out.println(house1);
		System.out.println("====================\n");
		
		try {
			house1.addCharacteristic("Garden", "OMG"); //We another characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		
		//Checking if the characteristic was added correctly
		System.out.println("====================");
		System.out.println(house1);
		System.out.println("====================\n");
		
		try {
			a.addHouse(house1); //We add the house to the system
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		}
		//Checking if the house has been added correctly
		System.out.println("====================");
		System.out.println("Logged user:\n" + App.getLoggedUser());
		System.out.println("====================\n");
		
		a.logout(); //Logging out
		
		//We try to create a living offer with no user logged
		try {
			a.createLivingOffer(LocalDate.of(2018, 12, 30), 200.34, 100.01, "Perfect", house1, 2);
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (InvalidDateException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		} catch (OfferAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Checking that the offer was not added
		System.out.println("====================");
		System.out.println(a.getOffers());
		System.out.println("====================\n");
		
		try {
			a.login("55555111Z", "ItIsNoTKnOwN"); //Login with an authorized user (guest)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		//We try to create a living offer with a guest
		try {
			a.createLivingOffer(LocalDate.of(2018, 12, 30), 200.34, 100.01, "Perfect", house1, 2);
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (InvalidDateException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		} catch (OfferAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Checking that the offer was not added
		System.out.println("====================");
		System.out.println(a.getOffers());
		System.out.println("====================\n");
		
		a.logout(); //Logging out
		
		//Login with the owner of house1
		try {
			a.login("51999111X", "swordFish"); //Login with an authorized user (host)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		//Adding the living offer correctly		
		try {
			a.createLivingOffer(LocalDate.of(2018, 12, 30), 200.34, 100.01, "Perfect", house1, 2);
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (InvalidDateException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		} catch (OfferAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Checking that the offer was added
		System.out.println("====================");
		System.out.println(a.getOffers());
		System.out.println("====================\n");
		
		//Adding a already expired offer		
		try {
			a.createLivingOffer(LocalDate.of(1998, 12, 30), 200.34, 100.01, "Perfect", house1, 2);
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (InvalidDateException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		} catch (OfferAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Checking that the offer was not added
		System.out.println("====================");
		System.out.println(a.getOffers());
		System.out.println("====================\n");
		
		a.logout();
		
		try {
			a.login("55535121Z", "lolol"); //Login with an authorized user (host)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		//We try to create an living offer with a host that does not own the house
		try {
			a.createLivingOffer(LocalDate.of(2018, 12, 30), 200.34, 100.01, "Perfect", house1, 2);
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (InvalidDateException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		} catch (OfferAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		//Checking that the offer was not added
		System.out.println("====================");
		System.out.println(a.getOffers());
		System.out.println("====================\n");
		
		a.logout(); //Logging out
		
		try {
			a.login("51999111X", "swordFish"); //Login with an authorized user (host)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		//We add a holiday offer
		try {
			a.createHolidayOffer(LocalDate.of(2018, 12, 30), 200.34, 100.01, "Perfect", house1, LocalDate.of(2018, 11, 30));
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (InvalidDateException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		} catch (OfferAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Checking that the offer was added
		System.out.println("====================");
		System.out.println(a.getOffers());
		System.out.println("====================\n");
		
		a.logout(); //Logging out
		
		try {
			a.login("51999111X", "swordFish"); //Login with an authorized user (host)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		//The offer is waiting to be approved. We try to approve it as a host. The result is the same when you are a guest or multirole.
		try {
			a.approveOffer(a.getOffers().get(0));
		} catch (OfferIsPendingForChangesExceptions e) {
			System.out.println(e);
		} catch (InvalidRolException e) {
			System.out.println(e);

		}
		
		//Checking the offer status
		System.out.println("====================");
		System.out.println(a.getOffers().get(0).getStatus());
		System.out.println("====================\n");
		
		a.logout();
		
		try {
			a.login("X1130055", "secret"); //Login with an authorized user (admin)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		a.suggestChanges(a.getOffers().get(0), "Cheaper plz");
		
		System.out.println("====================");
		System.out.println(a.getOffers().get(0).getStatus());
		System.out.println("====================\n");
		
		//We try to modify the offer as the admin
		try {
			a.modifyOffer(50.32, 20.10, a.getOffers().get(0));
		} catch (TimeIsUpException e) {
			System.out.println(e);
		}
		
		//Checking if the offer was modified
		System.out.println("====================");
		System.out.println(a.getOffers().get(0));
		System.out.println("====================\n");
		
		a.logout();
		
		//We simulate that 5 days have passed
		ModifiableDate.plusDays(5);
		
		//Log in with the house owner
		try {
			a.login("51999111X", "swordFish"); //Login with an authorized user (host)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		//We try to modify the offer as the owner
		try {
			a.modifyOffer(50.32, 20.10, a.getOffers().get(0));
		} catch (TimeIsUpException e) {
			System.out.println(e);

		}
		//Checking if the offer was modified
		System.out.println("====================");
		System.out.println(a.getOffers().get(0));
		System.out.println("====================\n");
		
		a.logout();
		a.closeApp();
		//The offer should be eliminated
		a = App.openApp();
		//Checking if the offer was eliminated
		System.out.println("====================");
		System.out.println(a.getOffers());
		System.out.println("====================\n");
		
		try {
			a.login("X1130055", "secret"); //Login with an authorized user (admin)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		//Approve the other offer
		try {
			a.approveOffer(a.getOffers().get(0));
		} catch (OfferIsPendingForChangesExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Checking the status
		System.out.println("====================");
		System.out.println(a.getOffers().get(0).getStatus());
		System.out.println("====================\n");
		
		a.logout();
		
		try {
			a.login("55555111Z", "ItIsNoTKnOwN"); //Login with an authorized user (guest)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		//We book the other created offer
		try {
			a.addReservation(a.getOffers().get(0));
		} catch (InvalidRolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RestrictedUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//We check that the reservation has added to the user's list
		System.out.println("====================");
		System.out.println(App.getLoggedUser().getReservations());
		System.out.println("====================\n");
		
		//10 days have passed
		ModifiableDate.plusDays(10);
		
		//We try to pay the offer
		a.payReservation(App.getLoggedUser().getReservations().get(0));
		
		//We check that the reservation has been deleted from the user's list
		System.out.println("====================");
		System.out.println(App.getLoggedUser().getReservations());
		System.out.println("====================\n");
		
		//We try to book that offer again
		try {
			a.addReservation(a.getOffers().get(0));
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (RestrictedUserException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		//We check the reservation list
		System.out.println("====================");
		System.out.println(App.getLoggedUser().getReservations());
		System.out.println("====================\n");
		
		//We try to pay that offer
		try {
			a.payOffer(a.getOffers().get(0));
		} catch (RestrictedUserException e) {
			System.out.println(e);
		}
		
		//We check the offer status
		System.out.println("====================");
		System.out.println(a.getOffers().get(0).getStatus());
		System.out.println("====================\n");
		
		a.logout();
		
		try {
			a.login("54444111D", "forgetme"); //Login with an authorized user (multirole)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		//We create a house and an offer as a multirole user
		House house2 = null;
		try {
			house2 = a.createHouse(22437, "Palencia"); //We create a house
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		
		//Checking if the house was created successfully
		System.out.println("====================");
		System.out.println(house1);
		System.out.println("====================\n");
		
		try {
			house2.addCharacteristic("Toilet", "1"); //We add a characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		try {
			house1.addCharacteristic("Garden", "WOW"); //We another characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
			
		try {
			a.addHouse(house2); //We add the house to the system
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		}
		//Checking if the house has been added correctly
		System.out.println("====================");
		System.out.println("Logged user:\n" + App.getLoggedUser());
		System.out.println("====================\n");
		
		//We create a new offer
		try {
			a.createLivingOffer(LocalDate.of(2018, 11, 23), 230.67, 56.87, "Palencia best village ever", house2, 6);
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (InvalidDateException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		} catch (OfferAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		a.logout();
		
		try {
			a.login("X1130055", "secret"); //Login with an authorized user (admin)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		//Approve the other offer
		try {
			a.approveOffer(a.getOffers().get(1));
		} catch (OfferIsPendingForChangesExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.logout();
		
		try {
			a.login("2334322D", "jesusRules"); //Login with an authorized user (multirole)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		
		System.out.println(a.getOffers().get(1));
		//We book the new offer
		try {
			a.addReservation(a.getOffers().get(1));
		} catch (InvalidRolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RestrictedUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//We check that the reservation has added to the user's list
		System.out.println("====================");
		System.out.println(App.getLoggedUser().getReservations());
		System.out.println("====================\n");
			
		//We try to pay the offer (invalid creditcard)
		a.payReservation(App.getLoggedUser().getReservations().get(0));
		
		//Check if the user lists have been updated
		System.out.println("====================");
		System.out.println(a.getBannedUsers());
		System.out.println(a.getAuthorizedUsers());
		System.out.println("====================\n");
		
		try {
			a.login("X1130055", "secret"); //Login with an authorized user (admin)
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			System.out.println(e);
		} catch (UnexistentUserException e) {
			System.out.println(e);
		} catch (AUserIsAlreadyLoggedException e) {
			System.out.println(e);
		}
		try {
			a.changeCreditCard("1111222233334444", a.getBannedUsers().get(0));
		} catch (InvalidRolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Check if the user lists have been updated
		System.out.println("====================");
		System.out.println(a.getBannedUsers());
		System.out.println(a.getAuthorizedUsers());
		System.out.println("====================\n");
	}
}
