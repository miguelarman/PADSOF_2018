package junit;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import application.offer.HolidayOffer;
import application.offer.House;
import application.offer.Offer;
import application.users.Host;
import exceptions.CouldNotPayHostException;

public class HolidayOfferTest {

	private Offer o;
	private Host h;
	
	@Before
	public void setUp() {
		h = new Host("Host Jesus", "de los hosts", "password", "2387129837134534", "92837492F");
		House house = new House(4730, "cantblanc", h);
		
		o = new HolidayOffer(LocalDate.now(), 100.0, 10.0, "description", house, LocalDate.now().plusDays(1));
	}
	
	@Test
	public void testPayHostCorrect() {
		try {
			o.payHost();
		} catch (CouldNotPayHostException e) {
			fail();
		}
	}
	
	@Test(expected = CouldNotPayHostException.class)
	public void testPayHostIncorrect() throws CouldNotPayHostException {
		
		h.changeCreditCard("wrong");
		
		o.payHost();
	}

}
