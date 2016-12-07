package entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class Record {
	private String string;
	private BigInteger bigInteger;
	private BigDecimal bigDecimal;
	private Calendar calendar;
	private Date date;
	private QName qName;
	private URI uri;
	private XMLGregorianCalendar xmlGregorianCalendar;
	private Duration duration;
	private Object object;
	private UUID uuid;

	public Record() {
	}

	public Record(String string, BigInteger bigInteger, BigDecimal bigDecimal,
			Calendar calendar, Date date, QName qName, URI uri,
			XMLGregorianCalendar xmlGregorianCalendar, Duration duration,
			Object object, UUID uuid) {
		super();
		this.string = string;
		this.bigInteger = bigInteger;
		this.bigDecimal = bigDecimal;
		this.calendar = calendar;
		this.date = date;
		this.qName = qName;
		this.uri = uri;
		this.xmlGregorianCalendar = xmlGregorianCalendar;
		this.duration = duration;
		this.object = object;
		this.uuid = uuid;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public BigInteger getBigInteger() {
		return bigInteger;
	}

	public void setBigInteger(BigInteger bigInteger) {
		this.bigInteger = bigInteger;
	}

	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}

	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public QName getqName() {
		return qName;
	}

	public void setqName(QName qName) {
		this.qName = qName;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public XMLGregorianCalendar getXmlGregorianCalendar() {
		return xmlGregorianCalendar;
	}

	public void setXmlGregorianCalendar(
			XMLGregorianCalendar xmlGregorianCalendar) {
		this.xmlGregorianCalendar = xmlGregorianCalendar;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

}
