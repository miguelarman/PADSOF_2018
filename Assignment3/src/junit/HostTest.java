package junit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import application.offer.House;
import application.users.Host;
import exceptions.HouseAlreadyCreatedException;

public class HostTest {
	
	public static House h1;
	public static House h2;
	public static House h3;
	public static Host host;
	
	@BeforeClass
	public static void setUp() {
		
		File file = new File("data.obj"); file.delete();
		
		
		host = new Host("Host Jesus", "de los hosts", "password", "23871298371", "92837492F");
		
		h1 = new House(28049, "Cantoblanco", host);
		h2 = new House(28050, "Cantoblanco", host);
		h3 = new House(28049, "Cantonegro", host);
		
	}

	@Test
	public void addHouseTest() {
		assertEquals(host.getHouses().size(), 0, 0);
		
		try {
			host.addHouse(h1);
		} catch (HouseAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(host.getHouses().size(), 1, 0);
		
		try {
			host.addHouse(h2);
		} catch (HouseAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(host.getHouses().size(), 2, 0);
		
		try {
			host.addHouse(h3);
		} catch (HouseAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(host.getHouses().size(), 3, 0);
	}
	
	
	@Test(expected = HouseAlreadyCreatedException.class)
	public void addSameTest() throws HouseAlreadyCreatedException {
		host.addHouse(h1);
		host.addHouse(h1);
	}
	
	@After
	public void clean() {
		host = new Host("Host Jesus", "de los hostss", "password", "23871298371", "92837492F");
	}

}
