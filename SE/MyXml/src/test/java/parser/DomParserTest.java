package parser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class DomParserTest {

	@Test
	public void testDomParser() throws ParserConfigurationException,
			SAXException, IOException {
		DomParser parser = new DomParser();
		parser.parse("src/test/resources/Sample.xml");
	}
}
