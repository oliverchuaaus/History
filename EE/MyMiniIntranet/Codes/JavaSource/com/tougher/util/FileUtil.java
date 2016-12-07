package com.tougher.util;

import java.io.File;
import java.io.FileInputStream;

public class FileUtil {

	public static String fileToString(File in) throws Exception {
		FileInputStream fin = new FileInputStream(in);
		StringBuffer sb = new StringBuffer();
		int i = 0;
		byte[] buf = new byte[1024];

		while ((i = fin.read(buf)) != -1) {
			sb.append(new String(buf, 0, i));
		}
		fin.close();
		return sb.toString();
	}

}
