package entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.WebServiceContext;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ComplexPojoWeb {
	@Resource
	private WebServiceContext ctx;

	@WebMethod
	public Record echo(String string, BigInteger bigInteger,
			BigDecimal bigDecimal, Calendar calendar, Date date, QName qName,
			URI uri, XMLGregorianCalendar xmlGregorianCalendar,
			Duration duration, Object object, UUID uuid) {
		Record record = new Record(string, bigInteger, bigDecimal, calendar,
				date, qName, uri, xmlGregorianCalendar, duration, object, uuid);
		return record;
	}

	@WebMethod
	public Source echoSource(Source source) {
		return source;
	}

	@WebMethod
	public byte[] echoBytes(byte[] bytes) {
		return bytes;
	}
	
	//WebMethods cannot be overloaded
}
