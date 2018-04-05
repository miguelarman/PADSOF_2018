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
		
		a.logout();
		
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
		} 
		
		//Checking if the loggedUser has been changed successfully
		System.out.println("====================");
		System.out.println("Logged user:\n" + App.getLoggedUser());
		System.out.println("====================\n");
		
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
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		//Checking if the characteristic was added correctly
		System.out.println("====================");
		System.out.println(house1);
		System.out.println("====================\n");
		
		try {
			house1.addCharacteristic("SwimmingPool", "Amazing"); //We try to add the same characteristic again
		} catch (DuplicateCharacteristicException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		//Checking if the characteristic was not added
		System.out.println("====================");
		System.out.println(house1);
		System.out.println("====================\n");
		
		try {
			a.addHouse(house1); //We add the house to the system
		} catch (InvalidRolException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		//Checking if the house has been added correctly
		System.out.println("====================");
		System.out.println("Logged user:\n" + App.getLoggedUser());
		System.out.println("====================\n");
		
		//TODO logout y esas cosas
		
		try {
			a.createLivingOffer(LocalDate.of(2018, 12, 30), 200.34, 100.01, "Perfect", house1, 2);
		} catch (InvalidRolException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (NotTheOwnerException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		System.out.println("====================");
		System.out.println(a);
		System.out.println("====================\n");
	}
}
