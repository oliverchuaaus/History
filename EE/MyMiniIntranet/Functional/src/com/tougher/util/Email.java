package com.tougher.util;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import org.apache.log4j.Logger;

/**
 * Represents a single email session.
 * <P>
 * Allows text-only email and attachments.
 * <P>
 * It gets the mail server from ProgramAttributes using the key smtpHost.
 * 
 * @author Kristoffer Chua
 * @version 0.1 August 26, 2003
 */
public class Email {
	private Logger log=Logger.getLogger(Email.class);
	private static String mailSMTPHost = ProgramAttributes
			.getProperty("smtpHost");
	private static Session session;
	private javax.mail.Message msg;
	private Multipart mp = new MimeMultipart();

	static {
		Properties p = new Properties();
		p.put("mail.smtp.host", mailSMTPHost);
		session = Session.getInstance(p, null);
	}

	public Email() {
	}

	/**
	 * Sets header fields: FROM, TO SUBJECT
	 * 
	 * @param fromName
	 * @param fromEmail
	 *          cannot be null
	 * @param toName
	 *          should be of equal length as toEmail
	 * @param toEmail
	 * @param subject
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void setHeader(String fromName, String fromEmail, String[] toName,
			String[] toEmail, String subject) throws MessagingException,
			UnsupportedEncodingException {
		InternetAddress[] address = null;
		msg = new MimeMessage(session);

		if (fromName == null) {
			fromName = fromEmail;
		}
		msg.setFrom(new InternetAddress(fromEmail, fromName));

		if (toEmail != null) {
			address = new InternetAddress[toEmail.length];
			for (int i = 0; i < toEmail.length; i++) {
				if (toName == null) {
					address[i] = new InternetAddress(toEmail[i], toEmail[i]);
				} else {
					address[i] = new InternetAddress(toEmail[i], toName[i]);
				}
			}
			msg.setRecipients(javax.mail.Message.RecipientType.TO, address);
		}
		msg.setSubject(subject);
		msg.setSentDate(new java.util.Date(System.currentTimeMillis()));
	}

	/**
	 * Sets the CC and BCC fields. Developer should call setHeader method before
	 * this method
	 * 
	 * @param ccName
	 *          should be of equal length as ccEmail
	 * @param ccEmail
	 *          can be null
	 * @param bccName
	 *          should be of equal length as bccEmail
	 * @param bccEmail
	 *          can be null
	 * @throws MessagingException
	 */
	/*
	 *  
	 */
	public void setCCandBCC(String[] ccName, String[] ccEmail, String[] bccName,
			String[] bccEmail) throws MessagingException,
			UnsupportedEncodingException {
		InternetAddress[] address = null;
		if (ccEmail != null) {
			address = new InternetAddress[ccEmail.length];
			for (int i = 0; i < ccEmail.length; i++) {
				if (ccName == null) {
					address[i] = new InternetAddress(ccEmail[i], ccName[i]);
				} else {
					address[i] = new InternetAddress(ccEmail[i], ccEmail[i]);
				}
			}
			msg.addRecipients(javax.mail.Message.RecipientType.CC, address);
		}
		if (bccEmail != null) {
			address = new InternetAddress[bccEmail.length];
			for (int i = 0; i < bccEmail.length; i++) {
				if (ccName == null) {
					address[i] = new InternetAddress(bccEmail[i], bccName[i]);
				} else {
					address[i] = new InternetAddress(bccEmail[i], bccEmail[i]);
				}
			}
			msg.addRecipients(javax.mail.Message.RecipientType.BCC, address);
		}
	}

	/**
	 * The actual text message in the email. Use java.text.MessageFormat for
	 * templating the message, if there are dynamic parts in the email. Multiple
	 * messages is supported, will be separated by horizontal line.
	 * 
	 * @param messageBody
	 * @throws MessagingException
	 */
	public void addMessageBody(String messageBody) throws MessagingException {
		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setText(messageBody);
		mp.addBodyPart(mbp);
	}

	/**
	 * Add non-text attachments to the email using InputStream.
	 * The disposition is ATTACHMENT, instead of INLINE.
	 * 
	 * @param filename
	 * @throws MessagingException
	 */
	public void addAttachment(String str, String filename) throws MessagingException {
		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setDisposition(Part.ATTACHMENT);
		FileDataSource fds = new FileDataSource(str);
		mbp.setDataHandler(new DataHandler(fds));
		mbp.setFileName(filename);
		mp.addBodyPart(mbp);
	}
	
	
	/**
	 * Add attachments to the email using File.
	 * 
	 * @param filename
	 * @throws MessagingException
	 */
	public void addAttachment(File file) throws MessagingException {
		MimeBodyPart mbp = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(file);
		mbp.setDataHandler(new DataHandler(fds));
		mbp.setFileName(file.getName());
		mp.addBodyPart(mbp);
	}

	/**
	 * Convenience method to add attachments to the email using filename.
	 * 
	 * @param filename
	 * @throws MessagingException
	 */
	public void addAttachment(String filename) throws MessagingException {
		File f = new File(filename);
		this.addAttachment(f);
	}

	/**
	 * Sends the actual message
	 * 
	 * @throws MessagingException
	 */
	public boolean sendMessage() throws MessagingException {
		try {
			msg.setContent(mp);
			Transport.send(this.msg);
			return true;
		} catch (javax.mail.MessagingException e) {
			log.error("Sending unsuccessful.", new MessagingException(e.toString()));
			return false;
			//throw e;
		}
	}
}