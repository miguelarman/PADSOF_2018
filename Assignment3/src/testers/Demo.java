package testers;

import application.App;
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
		
		try {
			a.addHouse(28032, "OMG"); //Adding a house with a host
		} catch (InvalidRolException e) {
			System.out.println(e);
		} catch (NoUserLoggedException e) {
			System.out.println(e);
		}
		//Checking if the house has been created correctly
		System.out.println("====================");
		System.out.println("Logged user:\n" + App.getLoggedUser());
		System.out.println("====================\n");
		
	}
}
