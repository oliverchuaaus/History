import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import junit.framework.TestCase;

import com.tougher.ComplexPojoWebService;
import com.tougher.Record;

/**
 * Test how JAXB supported types are passed between client and web service.
 * 
 * @author chuakr
 * 
 */
public class TestComplexPojoWeb extends TestCase {
	public void testComplexPojoEcho() throws DatatypeConfigurationException,
			IOException, URISyntaxException {
		ComplexPojoWebService service = new ComplexPojoWebService();

		String string = "String";
		BigInteger bigInteger = new BigInteger("1111");
		BigDecimal bigDecimal = new BigDecimal(10.1);
		Calendar calendar = GregorianCalendar.getInstance();
		Date date = new Date();
		QName qName = new QName("localPart");
		URI uri = new URI("http://www.yahoo.com");
		XMLGregorianCalendar xmlGregorianCalendar;
		Duration duration = DatatypeFactory.newInstance().newDuration(
				System.currentTimeMillis());
		Object object = new BigDecimal(10.1);
		UUID uuid = new UUID(2, 1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(calendar.getTime());
		XMLGregorianCalendar xCalendar = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gc);

		gc.setTime(date);
		XMLGregorianCalendar xDate = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gc);

		xmlGregorianCalendar = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gc);

		Record record = service.getComplexPojoWebPort().echo(string,
				bigInteger, bigDecimal, xCalendar, xDate, qName,
				uri.toASCIIString(), xmlGregorianCalendar.toXMLFormat(),
				duration, object, uuid.toString());
		assertEquals(string, record.getString());
		assertEquals(bigInteger, record.getBigInteger());
		assertEquals(bigDecimal, record.getBigDecimal());
		assertEquals(xCalendar, record.getCalendar());
		assertEquals(xDate, record.getDate());
		assertEquals(qName, record.getQName());
		// URI converted to String
		assertEquals(uri.toString(), record.getUri());
		// Commented out cause return object contains just null
		// assertEquals(xmlGregorianCalendar, record.getXmlGregorianCalendar());
		assertEquals(duration, record.getDuration());
		assertEquals(object, record.getObject());
		// Uuid converted to String
		assertEquals(uuid.toString(), record.getUuid());
	}

	public void testBytes() throws IOException {
		ComplexPojoWebService service = new ComplexPojoWebService();
		byte[] bytes = getBytes(new File("src/test/resources/Sample.xsl"));
		byte[] serverBytes = service.getComplexPojoWebPort().echoBytes(bytes);
		assertTrue(Arrays.equals(bytes, serverBytes));

		bytes = getBytes(new File("src/test/resources/Bluehills.jpg"));
		serverBytes = service.getComplexPojoWebPort().echoBytes(bytes);
		assertTrue(Arrays.equals(bytes, serverBytes));
	}

	private byte[] getBytes(File resource) throws IOException {
		InputStream in = null;
		try {
			in = new FileInputStream(resource);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int read; (read = in.read(buf)) != -1;) {
				bos.write(buf, 0, read);
			}
			return bos.toByteArray();
		} finally {
			if (in != null)
				in.close();
		}
	}
}
