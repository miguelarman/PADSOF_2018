package junit;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import application.offer.*;
import application.users.*;
import exceptions.HouseAlreadyCreatedException;

@SuppressWarnings("javadoc")
public class MultiRoleUserTest {

	public static MultiRoleUser user;
	public static Reservation r;
	private static House h1;
	private static House h2;
	private static House h3;
	
	@BeforeClass
	public static void createMultiRole() {
		
		File file = new File("data.obj"); file.delete();
		
		user = new MultiRoleUser("Multi Jesus", "de los multis", "password", "23871298371", "92837492F");
		
		Host host = new Host("Host Jesus", "de los hosts", "password", "23871298371", "92837492F");
		House h = new House("28049", "Cantoblanco", host);
		
		Offer o = new LivingOffer(LocalDate.now(), 0.0, 0.0, "description", h, 3);
		
		r = new Reservation(host, o);
		
		h1 = new House("28049", "Cantoblanco", host);
		h2 = new House("28050", "Cantoblanco", host);
		h3 = new House("28049", "Cantonegro", host);
	}
	
	
	@Test
	public void testAddReservation() {
		
		assertEquals(user.getReservedOffers().size(), 0, 0);
		
		user.addReservation(r);
		
		assertEquals(user.getReservedOffers().size(), 1, 0);
		
	}
	
	@Test
	public void testDeleteReservation() {
		user.addReservation(r);
		
		user.deleteReservation(r);
		
		assertEquals(user.getReservedOffers().size(), 0, 0);
	}
	
	@Test
	public void addHouseTest() {
		assertEquals(user.getHouses().size(), 0, 0);
		
		try {
			user.addHouse(h1);
		} catch (HouseAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(user.getHouses().size(), 1, 0);
		
		try {
			user.addHouse(h2);
		} catch (HouseAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(user.getHouses().size(), 2, 0);
		
		try {
			user.addHouse(h3);
		} catch (HouseAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(user.getHouses().size(), 3, 0);
	}
	
	@Test(expected = HouseAlreadyCreatedException.class)
	public void addSameTest() throws HouseAlreadyCreatedException {
		user.addHouse(h1);
	}

}
