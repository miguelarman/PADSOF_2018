package testers;

import application.offer.House;
import application.users.Host;
import application.users.MultiRoleUser;
import exceptions.HouseException;

public class TesterHouse {

	public static void main(String[] args) {
		Host user1 = new Host("Mario","Garcia", "ImTheBest", "2322432", "25242423Z");
		House house1 = new House(28039, "Turuel", user1);
		
		try {
			house1.addCharacteristic("Pool", "1");
			house1.addCharacteristic("Garden", "Amazing");
			house1.addCharacteristic("Pool", "OMG");
			house1.addCharacteristic("Garden", "Beautiful");
			house1.addCharacteristic("Garden", "Beautiful");
		} catch (HouseException e) {
			e.printStackTrace();
		}
		System.out.println(house1);
		MultiRoleUser user2 = new MultiRoleUser("Mario","Garcia", "ImTheBest", "2322432", "25242423Z");
		House house2 = new House(28039, "Turuel", user2);
		try {
			house1.addCharacteristic("Pool", "1");
			house1.addCharacteristic("Garden", "Amazing");
			house1.addCharacteristic("Pool", "OMG");
			house1.addCharacteristic("Garden", "Beautiful");
			house1.addCharacteristic("Garden", "Beautiful");
		} catch (HouseException e) {
			e.printStackTrace();
		}
		System.out.println(house2);
	}

}
