package parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser extends DefaultHandler {

	public void startElement(String uri, String localName, String qName,
			Attributes attr) throws SAXException {
		System.out.println("element: " + qName);
		for (int i = 0; i < attr.getLength(); i++) {
			System.out.println("\tattribute: " + attr.getQName(i));
			System.out.println("\tattributeValue: " + attr.getValue(i));
		}
	}

}
