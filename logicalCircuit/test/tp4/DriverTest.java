package tp4;

import static org.junit.Assert.*;

import org.junit.Test;

public class DriverTest {

	@Test
	public void testNextValue() {
		Driver d = new Driver();
		//d.addEvent(new Event(3,true));
		boolean b = d.nextValue();
		//boolean c = d.nextValue();
		//fail("Not yet implemented");
		assertEquals(false,b);
	}

}
