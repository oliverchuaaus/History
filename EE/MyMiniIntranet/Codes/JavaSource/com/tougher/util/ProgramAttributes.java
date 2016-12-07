package com.tougher.util;

import java.util.*;
import java.io.*;
import org.apache.log4j.Logger;
/**
* Used to retrieve application-wide property values like database connection string.
* <P>
* It reads from a property file, a text file with key value pairs written as
* key=value, separated by new lines. Comments are prepended by #.
* <P> 
* By default, it looks for a property file named defaultProperties.ini on the
* root directory of the current drive. This was done so that it is platform
* independent. The root directory was also chosen over using classpath 
* to specify the location of the property file because it is unambiguous and
* is the least common denominator in most systems. Note that the classpath 
* can have many entries, so what would happen if there is a property file in 
* multiple classpath entries?
* <P>
* For the deployment of web applications, however, using the default behavior
* is inappropriate. The property file should be part of the war, specifically
* under the WEB-INF folder. The property is loaded by calling the 
* loadPropertiesFromServlet() method. The parameter for InputStream is from
* getServletContext().getResourceAsStream("/WEB-INF/defaultProperties.ini");
* <P>
* If this class cannot find the property file, it will log the error, but 
* will not exit the main thread using System.exit.
* 
* @author  Kristoffer Chua
* @version 0.1 August 26, 2003
*/
public class ProgramAttributes {

	private static final String filename= "miniintranet.properties";
	private static Logger log=Logger.getLogger(ProgramAttributes.class);
	private static Properties defaultProps;

	/**
	 * Call this method to load properties from the root classpath.
	 * To be used for all applications, local IDE, swing or webapps.
	 * @param filename fully qualified filename. e.g. (C:/props/default.ini)
	 */
	public static void loadPropertiesFromRootClasspath() throws Exception {
		File file= null;
		try {
			file= FileLoader.getResource(filename);
			FileInputStream in= new FileInputStream(file);
			defaultProps.load(in);
			in.close();

		} catch (java.io.FileNotFoundException e) {
			throw new Exception(
				"The file " + file.getAbsolutePath() + " was not found.");
		}
	}		
	
	/**
	 * Trims extra spaces in front and behind each property key and value.
	 * This is needed because spaces are inadvertently added during the editing
	 * of the property file but is not readily visible to the person editing the file.
	 */
	private static void trimProperties() {
		//trim extra spaces
		Enumeration keys= defaultProps.keys();
		while (keys.hasMoreElements()) {
			String key= (String)keys.nextElement();
			String value= defaultProps.getProperty(key);
			defaultProps.put(key.trim(), value.trim());
		}
	}
	
	private static void loadProperties(){
		try {
			defaultProps=new Properties();
		    loadPropertiesFromRootClasspath();
			trimProperties();		    
		} catch (Exception e) {
			log.error("Critical Error: Cannot load Program Attributes: " + e);
		}
	}

	/**
	 * Call to retrieve application-wide property values using key. 
	 * Note that key is case-sensitive
	 * @param key
	 * @return Value of property or null if there is no property associated with given key.
	 */
	public static String getProperty(String key) {
		if (defaultProps==null){
			loadProperties();
		}
		return defaultProps.getProperty(key);
	}

	/**
	 * This is for debugging purposes. This provides all key=value pairs
	 * for examination
	 * @return Clone of the properties, so that it cannot be used to change the
	 * properties currently loaded in memory.
	 */
	public static Properties getProperties() {
		if (defaultProps==null){
			loadProperties();
		}		
		return (Properties)defaultProps.clone();
	}

}