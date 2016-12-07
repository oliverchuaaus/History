package parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.SAXException;

public class SaxParserTest {

	@Test
	public void testSaxParser() throws SAXException, IOException,
			ParserConfigurationException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		SaxParser saxParser = new SaxParser();
		sp.parse(new File("src/test/resources/Sample.xml"), saxParser);
	}
}
