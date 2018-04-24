package junit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import application.offer.House;
import application.users.Host;
import exceptions.DuplicateCharacteristicException;

@SuppressWarnings("javadoc")
public class HouseTest {

	private static House h;
	
	@BeforeClass
	public static void setUp() {
		
		File file = new File("data.obj"); file.delete();
		
		
		Host host = new Host("Host Jesus", "de los hosts", "password", "23871298371", "92837492F");
		h = new House(280922, "cantowhite", host);
		
		try {
			h.addCharacteristic("repeated", "yes");
		} catch (DuplicateCharacteristicException e) {
			fail();
		}
	}
	
	
	@Test
	public void testAddCharacteristic() {
		assertEquals(h.getCharacteristics().size(), 1, 0);
		
		try {
			h.addCharacteristic("pool", "covered");
		} catch (DuplicateCharacteristicException e) {
			fail();
		}
		assertEquals(h.getCharacteristics().size(), 2, 0);
		
		try {
			h.addCharacteristic("rooms", "three");
		} catch (DuplicateCharacteristicException e) {
			fail();
		}
		assertEquals(h.getCharacteristics().size(), 3, 0);
		
		try {
			h.addCharacteristic("elevator", "yes");
		} catch (DuplicateCharacteristicException e) {
			fail();
		}
		assertEquals(h.getCharacteristics().size(), 4, 0);
	}
	
	@Test(expected = DuplicateCharacteristicException.class)
	public void testException() throws DuplicateCharacteristicException {
		h.addCharacteristic("repeated", "yes");
	}

}
