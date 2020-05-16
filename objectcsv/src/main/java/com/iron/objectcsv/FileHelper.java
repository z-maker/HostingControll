/**
 *  @author Rajat Sahu 
 *  https://github.com/Rjtsahu/object-csv-java
 *  ObjectCsv Java Library : Handy library to map CSV document to java model. 
 * */
package com.iron.objectcsv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Helper class to read text file as string or rows of string.
 */
class FileHelper {

	private static final String NEW_LINE_CHAR = "\n";

	/**
	 * Reads .csv file from file system.
	 * 
	 * @param filePath file location it can be absolute or relative.
	 * @return lines in file as string.
	 */
	public static List<String> readFileByLine(String filePath)
			throws IOException {
		FileReader reader = new FileReader(getFile(filePath));
		BufferedReader bufferedReader = new BufferedReader(reader);
		List<String> linesInFile = new ArrayList<>();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			linesInFile.add(line);
		}

		bufferedReader.close();
		return linesInFile;
	}

	/**
	 * Reads .csv file from file system.
	 * 
	 * @param filePath file location it can be absolute or relative.
	 * @return file as string.
	 */
	public static String readFileAsString(String filePath)
			throws IOException {
		List<String> linesInFile = readFileByLine(filePath);
		StringBuilder buffer = new StringBuilder();
		for (String line : linesInFile) {
			buffer.append(line).append(NEW_LINE_CHAR);
		}
		return buffer.toString();
	}

	/**
	 * Return file object.
	 * 
	 * @param filePath file location it can be absolute or relative.
	 * @return fileObject if file exists else throws FileNotFoundException.
	 */
	private static File getFile(String filePath) {
		return new File(filePath);
	}

}
