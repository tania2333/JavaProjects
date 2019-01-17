package tp4;

import static org.junit.Assert.*;

import org.junit.Test;

public class GateANDTest {

	@Test
	public void testSetOutput() {
		Signal s1=new Signal(false);
		Signal s2=new Signal(false);
		Signal s3=new Signal(false);
		
		s1.addEvent(new Event(3,true));			
		s2.addEvent(new Event(4,true));
		
		Gate p1=new GateAND(s1,s2,s3,5);

			s1.actualise();
			s2.actualise();
			s3.actualise();
			p1.setOutput();
			
		assertEquals(5,p1.output.driver.timeOfNextEvent());		
		//fail("Not yet implemented");
	}

}
