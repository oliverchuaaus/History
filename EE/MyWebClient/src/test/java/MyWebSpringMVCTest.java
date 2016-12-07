import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import junit.framework.TestCase;

public class MyWebSpringMVCTest extends TestCase {
	public static final String HOST = "http://localhost:8080/";

	public void test() throws Exception {
		final WebClient webClient = new WebClient();
		final HtmlPage page = (HtmlPage) webClient.getPage(HOST
				+ "MyWebSpringMVC/welcome.htm");
		final String pageAsText = page.asText();
		assertTrue(pageAsText.contains("Message : Spring 3 MVC Hello World"));
	}
}
