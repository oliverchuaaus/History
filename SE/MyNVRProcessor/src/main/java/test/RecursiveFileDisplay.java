package test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;

public class RecursiveFileDisplay {

	public static void main(String[] args) {
		/*
		File input = new File("src/test/resources/input"); // input directory
		File output = new File("src/test/resources/output"); // output directory
		*/
		File input = new File("h:/NVR"); // input directory
		File output = new File("h:/output"); // output directory
		
		displayDirectoryContents(input, output);
	}

	public static void displayDirectoryContents(File input, File output) {
		try {
			File[] files = input.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					displayDirectoryContents(file, output);
				} else {
					String name = file.getName();
					String channel = name.substring(4, 7);
					String dateString = name.substring(13, 21);
					DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
					LocalDate localDate = LocalDate.parse(dateString, readFormatter);
					DateTimeFormatter writeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					File destDir = new File(output, writeFormatter.format(localDate) + "/" + channel);
					FileUtils.moveFileToDirectory(file, destDir, true);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}