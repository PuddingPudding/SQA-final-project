import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PrototypeTest {
	static Prototype p1 , p2 , p3;
	
	@BeforeClass
	public static void beforeClassTest() throws Exception
	{
		System.out.println("Test Start");
		p1 = new Prototype(15);
		p2 = new Prototype(25);
		p3 = new Prototype(35);
	}
	
	@Test
	public void test() throws Exception {		
		assertEquals("15 25 35 " , Prototype.getAllLocation() );
	}

	@Test
	public void testA() throws Exception {				
		assertEquals(true , p1.checkDeliver(20 , 100));
		assertEquals(true , p1.checkDeliver(60 , 600));
		assertEquals(false , p1.checkDeliver(45 , 490));
	}
	
	@Test
	public void testB() throws Exception {				
		assertEquals(75 , p1.orderMeal("ab"));
		assertEquals(140 , p1.orderMeal("ace"));
		assertEquals(195 , p1.orderMeal("abcdef"));
		assertEquals(195 , p1.orderMeal("abcdefxx"));
	}
}
