package testers;

import application.app.App;
import exceptions.IncorrectPasswordException;
import exceptions.UnexistentUserException;
import exceptions.UserIsBannedException;

public class Demo {

	public static void main(String[] args) {
		App a = App.openApp();
		
		//Checking if the system is opened correctly the first time
		System.out.println("====================");
		System.out.println(a);
		System.out.println("====================");
		
		a.logout();
		
		//Checking if the system is opened correctly when it has already created
		a = App.openApp();
		
		System.out.println("====================");
		System.out.println(a);
		System.out.println("====================");
		
		
		try {
			a.login("12345678X", "jejexd"); //Trying to login with an incorrect id
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (UnexistentUserException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		try {
			a.login("51999111X", "asdfg");
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (UnexistentUserException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} //Trying to login with an incorrect password
		
		try {
			a.login("51999111X", "swordFish");
			System.out.println("Login successful with NIF " + App.getLoggedUser().getNIF());
		} catch (UserIsBannedException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (IncorrectPasswordException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (UnexistentUserException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} //Login with an authorized user (host)
		
		System.out.println("====================");
		System.out.println(a);
		System.out.println("====================");
	}
}
