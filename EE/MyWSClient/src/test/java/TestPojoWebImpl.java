import junit.framework.TestCase;

import com.tougher.PojoWebImplService;

public class TestPojoWebImpl extends TestCase {
	public void testPojo() {
		PojoWebImplService service = new PojoWebImplService();
		String response = service.getPojoWebImplPort().echo("World");
		assertEquals("Hello World", response);
		System.out.println("response: " + response);
	}
}
