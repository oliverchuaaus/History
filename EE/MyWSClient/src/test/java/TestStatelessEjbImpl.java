import junit.framework.TestCase;

import com.tougher.StatelessEjbImplService;

public class TestStatelessEjbImpl extends TestCase {
	public void testPojo() {
		StatelessEjbImplService service = new StatelessEjbImplService();
		String response = service.getStatelessEjbImplPort().echo("World");
		assertEquals("Hello World", response);
		System.out.println("response: " + response);
	}
}
