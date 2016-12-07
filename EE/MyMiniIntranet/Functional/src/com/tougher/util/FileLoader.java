package com.tougher.util;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class FileLoader extends HttpServlet{
	private static Logger log = Logger.getLogger(FileLoader.class);
	private static ServletContext context;

	public void init(ServletConfig config) throws ServletException {
		FileLoader.context=config.getServletContext();
	}
	
	private static URL getURL(String filename) throws Exception {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		URL u = cl.getResource(filename);
		if (u == null) {
			log.debug("context:" + context);
			u = context.getResource("/WEB-INF/classes/" + filename);
			log.debug("/WEB-INF/classes/" + filename);
			log.debug("url:" + u);
		}
		return u;
	}
	
	public static File getResource(String filename) throws Exception{
		URL u = getURL(filename);
		String fileString = u.getFile();
		fileString = URLDecoder.decode(fileString, "UTF-8");
		return new File(fileString);
	}


}
