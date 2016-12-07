import junit.framework.TestCase;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class MyJQueryTest extends TestCase {
	public static final String HOST = "http://localhost:8080/MyJQuery/";

	public WebClient getWebClient() {
		final WebClient webClient = new WebClient();
		return webClient;
	}

	public void test1() throws Exception {
		final WebClient webClient = getWebClient();
		final HtmlPage page = (HtmlPage) webClient.getPage(HOST + "test1.html");
		final String pageAsText = page.asText();
		assertTrue(pageAsText.contains("This is Hello World by HTML "
				+ "This is Hello World by JQuery"));
	}

	public void test2() throws Exception {
		final WebClient webClient = getWebClient();
		final HtmlPage page = (HtmlPage) webClient.getPage(HOST + "test2.html");
		final String pageAsText = page.asText();
		assertTrue(pageAsText.contains("This is Hello World by HTML "
				+ "This is Hello World by JQuery 1 "
				+ "This is Hello World by JQuery 2 "
				+ "This is Hello World by JavaScript"));
	}
}
