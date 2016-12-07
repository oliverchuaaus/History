package xslt;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTTransformer {
	public void transform(StreamSource xml, StreamSource xsl,
			StreamResult result) throws TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer(xsl);
		t.transform(xml, result);
	}
}