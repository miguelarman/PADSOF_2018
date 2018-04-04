package testers;

import application.app.App;
import exceptions.IncorrectPasswordException;
import exceptions.UnexistentUserException;
import exceptions.UserIsBannedException;

public class Demo {

	public static void main(String[] args) {
		App a = App.openApp();
		//Checking if the system is opened correctly the first time
		System.out.println(a); 
		a.logout();
		//Checking if the system is opened correctly when it has already created
		a = App.openApp();
		System.out.println(a);
		try {
			a.login("12345678X", "jejexd"); //Trying to login with an incorrect id
			a.login("51999111", "asdfg"); //Trying to login with an incorrect password
			a.login("51999111X", "swordFish"); //Login with an authorized user (host)
		} catch (UserIsBannedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnexistentUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a);

	}

}
