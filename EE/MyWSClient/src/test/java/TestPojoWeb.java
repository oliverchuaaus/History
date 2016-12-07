import junit.framework.TestCase;

import com.tougher.PojoWebService;

public class TestPojoWeb extends TestCase {
	public void testPojo() {
		PojoWebService service = new PojoWebService();
		String response = service.getPojoWebPort().echo("World");
		assertEquals("Hello World", response);
		System.out.println("response: " + response);
	}
}
