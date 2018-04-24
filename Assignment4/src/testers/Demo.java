package testers;

import application.offer.House;
import application.offer.Offer;
import application.offer.OfferStatus;
import application.offer.OfferType;
import application.opinion.Comment;
import application.users.MultiRoleUser;

import java.time.LocalDate;

import application.App;
import application.dates.ModifiableDate;
import exceptions.*;


@SuppressWarnings("javadoc")
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
			System.out.println(e);
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
			System.out.println(e);
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
			System.out.println(e);
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
			System.out.println(e);
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
			System.out.println(e);
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
			System.out.println(e);
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
		
		try {
			a.suggestChanges(a.getOffers().get(0), "Cheaper plz");
		} catch (InvalidOfferStatusException e) {
			System.out.println(e);
		} catch (InvalidRolException e) {
			System.out.println(e);
		}
		
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
			System.out.println(e);
		} catch (InvalidRolException e) {
			System.out.println(e);
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
			System.out.println(e);
		} catch (RestrictedUserException e) {
			System.out.println(e);
		}
		
		//We check that the reservation has added to the user's list
		System.out.println("====================");
		System.out.println(App.getLoggedUser().getReservedOffers());
		System.out.println("====================\n");
		
		//10 days have passed
		ModifiableDate.plusDays(10);
		
		//We try to pay the offer
		a.payReservation(App.getLoggedUser().getReservedOffers().get(0));
		
		//We check that the reservation has been deleted from the user's list
		System.out.println("====================");
		System.out.println(App.getLoggedUser().getReservedOffers());
		System.out.println("====================\n");
		
		//We try to book that offer again
		try {
			a.addReservation(a.getOffers().get(0));
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (RestrictedUserException e) {
			System.out.println(e);
		}
		
		//We check the reservation list
		System.out.println("====================");
		System.out.println(App.getLoggedUser().getReservedOffers());
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
		
		try {
			house2.addCharacteristic("Toilet", "1"); //We add a characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		try {
			house2.addCharacteristic("Garden", "WOW"); //We another characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		
		//Checking if the house was created successfully
		System.out.println("====================");
		System.out.println(house2);
		System.out.println("====================\n");
		
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
			System.out.println(e);
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
			System.out.println(e);
		} catch (InvalidRolException e) {
			System.out.println(e);
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
			System.out.println(e);
		} catch (RestrictedUserException e) {
			System.out.println(e);
		}
		
		//We check that the reservation has added to the user's list
		System.out.println("====================");
		System.out.println(App.getLoggedUser());
		System.out.println("====================\n");
		
		MultiRoleUser m = (MultiRoleUser)App.getLoggedUser();
		
		System.out.println("====================");
		System.out.println(m.getReservedOffers());
		System.out.println("====================\n");
		//We try to pay the offer (invalid creditcard)
		
		a.payReservation(m.getReservedOffers().get(0));
		
		//Check if the user lists have been updated
		System.out.println("====================");
		System.out.println(a.getBannedUsers());
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
		try {
			a.changeCreditCard("1111222233334444", a.getBannedUsers().get(0));
		} catch (InvalidRolException e) {
			System.out.println(e);
		}
		
		//Check if the user lists have been updated
		System.out.println("====================");
		System.out.println(a.getBannedUsers());
		System.out.println("====================\n");
		System.out.println("====================");
		System.out.println(a.getAuthorizedUsers());
		System.out.println("====================\n");
		
		a.logout();
		
		try {
			a.login("12345678Z", "hehehe"); //Login with an authorized user (host)
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
		
		House house3 = null;
		try {
			house3 = a.createHouse(22337, "Teruel"); //We create a house
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		
		try {
			house3.addCharacteristic("Toilet", "1"); //We add a characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		try {
			house3.addCharacteristic("Garden", "WOW"); //We another characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		try {
			a.addHouse(house3); //We add the house to the system
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		}		
		//We create a new offer
		try {
			a.createLivingOffer(LocalDate.of(2018, 10, 2), 200000.0, 32.04, "Even worst than Palencia", house3, 10);
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (InvalidDateException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		} catch (OfferAlreadyCreatedException e) {
			System.out.println(e);
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
		try {
			a.approveOffer(a.getOffers().get(2));
		} catch (OfferIsPendingForChangesExceptions e) {
			System.out.println(e);
		} catch (InvalidRolException e) {
			System.out.println(e);
		}
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
		
		//We book the new offer with the host having an invalid creditcard number
		try {
			a.payOffer(a.getOffers().get(2));
		} catch (RestrictedUserException e) {
			System.out.println(e);
		}
		
		//Checking if the debt was added
		System.out.println("====================");
		System.out.println(a.getToPay());
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
		
		try {
			a.changeCreditCard("1111222244445555", a.getBadCCard().get(0));
		} catch (InvalidRolException e) {
			System.out.println(e);
		}
		
		a.logout();
		a.closeApp();
		a = App.openApp();
		
		//Checking if the debt was paid
		System.out.println("====================");
		System.out.println(a.getToPay());
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
		//We comment and rate an offer
		try {
			a.getOffers().get(2).rateOffer("Awful");
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		//Rate the offer
		try {
			a.getOffers().get(2).rateOffer(1.0);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		//Check the avg rating
		System.out.println("====================");
		System.out.println(a.getOffers().get(2).getAvgRating());
		System.out.println("====================\n");
		
		//Check the comments
		System.out.println("====================");
		System.out.println(a.getOffers().get(2).getComments());
		System.out.println("====================\n");
		
		a.logout();
		
		try {
			a.login("12345678Z", "hehehe"); //Login with an authorized user (host)
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
		
		Comment c = (Comment)a.getOffers().get(2).getComments().get(0);
		//Reply the comment
		try {
			c.addReply("Nah");
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		
		//Rate the comment
		try {
			c.rateComment(2.0);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		
		//Rate the offer		
		try {
			a.getOffers().get(2).rateOffer(4.0);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		
		//Check the avg rating
		System.out.println("====================");
		System.out.println(a.getOffers().get(2).getAvgRating());
		System.out.println("====================\n");
		
		//Check the comments
		System.out.println("====================");
		System.out.println(a.getOffers().get(2).getComments());
		System.out.println("====================\n");
		
		//Now we are going to add some offers to try the searches
		House house4 = null;
		try {
			house4 = a.createHouse(34337, "ValdeMingoMez"); //We create a house
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		
		try {
			house4.addCharacteristic("Toilet", "1"); //We add a characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		try {
			house4.addCharacteristic("Garden", "WOW"); //We another characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		try {
			a.addHouse(house4); //We add the house to the system
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		}		
		//We create a new offer
		try {
			a.createLivingOffer(LocalDate.of(2018, 7, 15), 100.34, 21.04, "Pfff", house4, 1);
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (InvalidDateException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		} catch (OfferAlreadyCreatedException e) {
			System.out.println(e);
		}
		
		House house5 = null;
		try {
			house5 = a.createHouse(12337, "Valencia"); //We create a house
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		
		try {
			house5.addCharacteristic("Toilet", "1"); //We add a characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		try {
			house5.addCharacteristic("Garden", "WOW"); //We another characteristic to the created house
		} catch (DuplicateCharacteristicException e) {
			System.out.println(e);
		}
		try {
			a.addHouse(house5); //We add the house to the system
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		}		
		//We create a new offer
		try {
			a.createHolidayOffer(LocalDate.of(2018, 10, 2), 160.89, 32.04, "PFFFF", house5, LocalDate.of(2019, 1, 1));
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		} catch (InvalidDateException e) {
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			System.out.println(e);
		} catch (OfferAlreadyCreatedException e) {
			System.out.println(e);
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
		
		//We approve all offers
		for(Offer o : a.getOffers()) {
			if(o.getStatus().equals(OfferStatus.PENDING_FOR_APPROVAL)) {
				o.modifyOffer(OfferStatus.APPROVED);
			}
		}
		
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
		//We book an offer
		
		try {
			a.addReservation(a.getOffers().get(3));
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (RestrictedUserException e) {
			System.out.println(e);
		}
		//We comment and rate an offer
		try {
			a.getOffers().get(4).rateOffer("Ok");
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		//Rate the offer
		try {
			a.getOffers().get(4).rateOffer(3.0);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		
		a.logout();
		//Try searches with no user logged
		
		//Search by zip code
		System.out.println("====================");
		System.out.println(a.searchZipCode(12337));
		System.out.println("====================\n");
		
		//Search by dates
		System.out.println("====================");
		System.out.println(a.searchStartingDate(LocalDate.of(2018, 11, 1), LocalDate.of(2018, 12, 1)));
		System.out.println("====================\n");
		
		//Search by type
		System.out.println("====================");
		System.out.println(a.searchOfferType(OfferType.HOLIDAY));
		System.out.println("====================\n");
		
		//Search booked offers
		System.out.println("====================");
		try {
			System.out.println(a.searchBooked());
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		System.out.println("====================\n");
		//The result is the same for searchPaid and searchAvgRating
		
		//Now we log in and we search with the advanced searches
		try {
			a.login("12345678Z", "hehehe"); //Login with an authorized user (host)
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
		
		//Search booked offers
		System.out.println("====================");
		try {
			System.out.println(a.searchBooked());
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		//Search paid offers
		System.out.println("====================");
		try {
			System.out.println(a.searchPaid());
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		//Search by average rating
		System.out.println("====================");
		try {
			System.out.println(a.searchAvgRating(1.0));
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
	}
}
