package testers;

import application.app.App;
import application.offer.House;
import exceptions.*;


public class Demo {

	public static void main(String[] args) {
		App a = App.openApp();
		
		//Checking if the system is opened correctly the first time
		System.out.println("====================");
		System.out.println(a);
		System.out.println("====================\n");
		
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
			e.printStackTrace();
		}
		//Checking if the characteristic was added correctly
		System.out.println("====================");
		System.out.println(house1);
		System.out.println("====================\n");
		
		try {
			house1.addCharacteristic("SwimmingPool", "Amazing"); //We try to add the same characteristic again
		} catch (DuplicateCharacteristicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Checking if the characteristic was not added
		System.out.println("====================");
		System.out.println(house1);
		System.out.println("====================\n");
		//Checking if the house has been created correctly
		System.out.println("====================");
		System.out.println("Logged user:\n" + App.getLoggedUser());
		System.out.println("====================\n");
		
	}
}
