package testers;

import application.offer.House;
import application.users.Host;
import application.users.RegisteredUser;

public class TesterHouse {

	public static void main(String[] args) {
		Host user = new Host("Mario","Mingo", "marioPesado", "2322432", "25242423Z");
		House house = new House(28039, "madriz", user);

	}

}
