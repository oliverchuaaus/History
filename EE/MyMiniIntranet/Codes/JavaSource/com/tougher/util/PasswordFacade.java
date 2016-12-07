/*
 * Created on Jun 13, 2004
 */
package com.tougher.util;

import java.io.*;
import javax.crypto.*;
import org.apache.log4j.Logger;
import java.security.Key;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.security.SecureRandom;

/**
 * @author Kristoffer Chua Tougher Software
 */
public class PasswordFacade {
	private static Logger log = Logger.getLogger(PasswordFacade.class);

	private static final String FILENAME = "secretKey.ser";

	private static final String ALGORITHM = "DESede";

	private static Key key;

	private static Cipher cipher;

	private static BASE64Decoder decoder = new BASE64Decoder();

	private static BASE64Encoder encoder = new BASE64Encoder();

	private static SecureRandom sr = new SecureRandom();

	private static int MAX_LENGTH = 8;

	private static StringBuffer password = new StringBuffer();

	static {
		loadCipher();
	}

	/**
	 * Loads Cipher.
	 * 
	 * @throws Exception
	 */
	private static void loadCipher() {
		try {
			cipher = Cipher.getInstance(ALGORITHM);
		} catch (Exception e) {
			log.error(null, e);
		}
	}

	/**
	 * Loads secretKey.ser file from the root directory of the current drive.
	 * 
	 * @throws Exception
	 */
	private static void loadSecretKeyFromRootClasspath() {
		try {
			File f = FileLoader.getResource(FILENAME);
			log.debug("key file: "+f);
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			key = (Key) in.readObject();
			in.close();
		} catch (Exception e) {
			log.error(null, e);
		}
	}


	public static String encrypt(String unencryptedPassword) throws Exception {
		if (key == null) {
			loadSecretKeyFromRootClasspath();
		}
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] ciphertext = cipher.doFinal(unencryptedPassword.getBytes());
		String encoded = encoder.encode(ciphertext);
		return encoded;
	}

	/*
	 * To be used for development and emergencies only. Should never be used on
	 * production. To check if entered password is correct, encrypt entered
	 * password then use the encrypted String in the WHERE clause.
	 */
	public static String decrypt(String encryptedPassword) throws Exception {
		if (key == null) {
			loadSecretKeyFromRootClasspath();
		}
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] ciphertext = decoder.decodeBuffer(encryptedPassword);
		byte[] decoded = cipher.doFinal(ciphertext);
		return new String(decoded);
	}

	public static void generateKeyFile() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
		Key key = kg.generateKey();
		File f = new File("/" + FILENAME);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(key);
		oos.close();
	}

	public static String generateRandomPassword() {
		password.setLength(0);
		boolean validCharacter = false;
		for (int i = 0; i < MAX_LENGTH; i++) {
			validCharacter = false;
			while (!validCharacter) {
				//alphanumeric range 49-122(122-49=73) characters including
				// some
				// unwanted characters like symbols, etc.
				int j = sr.nextInt() % 73 + 49;
				char ch = (char) j;
				if (Character.isLetterOrDigit(ch)
						&& "Oo0LlIi1".indexOf(ch) == -1) {
					validCharacter = true;
					password.append(ch);
				} else {
					//ignore
				}
			}
		}
		return password.toString();
	}
}