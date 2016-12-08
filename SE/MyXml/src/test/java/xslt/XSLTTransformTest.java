package xslt;

import java.io.File;

import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

public class XSLTTransformTest  {
	private static final String RESOURCE = ".output";

	@Test
	public void testXSLTTransformer() throws TransformerException {
		StreamSource xml = new StreamSource(new File(
				"src/test/resources/Sample.xml"));
		StreamSource xsl = new StreamSource(new File(
				"src/test/resources/Sample.xsl"));
		
		File output = new File(RESOURCE);
		output.mkdirs();
		
		StreamResult result = new StreamResult(new File(
				RESOURCE + "/SampleOutput.xml"));

		XSLTTransformer transformer = new XSLTTransformer();
		transformer.transform(xml, xsl, result);
	}
}
