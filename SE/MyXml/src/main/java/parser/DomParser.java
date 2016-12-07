package parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParser {

	public void parse(String xmlFile) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new File(xmlFile));

		NodeList children = doc.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node rootNode = (Node) children.item(i);

			// This check is needed because some children include text elements.
			if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("element: " + rootNode.getNodeName());
				// This will get all text element of this and all its children's
				// text elements
				System.out.println("elementText: " + rootNode.getTextContent());

				NodeList rootChildren = rootNode.getChildNodes();
				for (int j = 0; j < rootChildren.getLength(); j++) {
					Node node = (Node) rootChildren.item(j);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						System.out.println("element: " + node.getNodeName());
						// We will get resolved text even if source xml has
						// CDATA markup
						System.out.println("elementText: "
								+ node.getTextContent());
						NamedNodeMap map = node.getAttributes();
						for (int k = 0; k < map.getLength(); k++) {
							Node attributeNode = map.item(k);
							System.out.println("\tattribute: "
									+ attributeNode.getNodeName());
							System.out.println("\tattributeValue: "
									+ attributeNode.getNodeValue());
						}
					}
				}
			}
		}
	}
}
