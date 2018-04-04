package testers;

import application.users.*;

public class TesterRegisteredUser {
	// constructor
	// changeCreditCard
	// toString
	
	public static void main(String[] args) {

		RegisteredUser guest = new Guest("Guest Mario", "Perez", "0123456789", "0284847567268570", "73588362G");
		RegisteredUser host = new Host("Maria de los Hosts", "Sardinez", "ILovePaco", "0284847567268571", "73588362H");
		RegisteredUser multirole = new MultiRoleUser("Multi Jesus", "Tomillo", "romeroBeatsTomillo", "0284847567268572",
				"73588362M");
		RegisteredUser admin = new Admin("Adminostrio", "Admionio", "admin00", "0284847567268573", "73588362A");
		
		System.out.println("We inizialize all the different types of RegisteredUser:");
		System.out.println(guest);
		System.out.println(host);
		System.out.println(multirole);
		System.out.println(admin);
		
		
		System.out.println();
		System.out.println("Modifying the credit card of all of them:");
		guest.changeCreditCard("0000G");
		host.changeCreditCard("0000H");
		multirole.changeCreditCard("0000M");
		admin.changeCreditCard("0000A");
		
		System.out.println("Checking they have been modified correctly:");
		System.out.println(guest);
		System.out.println(host);
		System.out.println(multirole);
		System.out.println(admin);
	}
}
