package junit;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.offer.House;
import application.offer.LivingOffer;
import application.offer.Offer;
import application.users.Host;
import exceptions.CouldNotPayHostException;

@SuppressWarnings("javadoc")
public class LivingOfferTest {

	private Offer o;
	private Host h;
	
	@BeforeClass
	public static void removeData() {
		File file = new File("data.obj"); file.delete();
	}
	
	
	@Before
	public void setUp() {
		h = new Host("Host Jesus", "de los hosts", "password", "2387129837134534", "92837492F");
		House house = new House("4730", "cantblanc", h);
		
		o = new LivingOffer(LocalDate.now(), 100.0, 10.0, "description", house, 6);
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
