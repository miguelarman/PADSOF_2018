package junit;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import application.offer.*;
import application.users.*;

public class GuestTest {
	
	private static Reservation r;
	public static Guest g;
	
	@BeforeClass
	public static void setUp() {
		
		File file = new File("data.obj"); file.delete();
				
		Host host = new Host("Host Jesus", "de los hosts", "password", "23871298371", "92837492F");
		House h = new House(28049, "Cantoblanco", host);
		
		Offer o = new LivingOffer(LocalDate.now(), 0.0, 0.0, "description", h, 3);
		
		r = new Reservation(host, o);
		
		g = new Guest("Guest Jesus", "de los guests", "passasdword", "238713458371", "00000000D");
	}
	
	
	@Test
	public void testAddReservation() {
		
		assertEquals(g.getReservedOffers().size(), 0, 0);
		
		g.addReservation(r);
		
		assertEquals(g.getReservedOffers().size(), 1, 0);
		
	}
	
	@Test
	public void testDeleteReservation() {
		g.addReservation(r);
		
		g.deleteReservation(r);
		
		assertEquals(g.getReservedOffers().size(), 0, 0);
	}

}
