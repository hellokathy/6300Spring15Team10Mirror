/*
 * A pretty rough sketch of the program without really taking into account
 * any of the command line arguments.  Still needs duplicate functions
 * for setDocument() that uses command line args and test cases.  I also
 * haven't really added try/catch blocks yet.
 * 
 * -Ken
 */

package edu.gatech.seclass.prj1;

import java.io.*;

public class Main {
	private static AvgSentenceLength asl = new AvgSentenceLength();
	private static int AvgLen = 0;
	private static char[] delimiters;
	private static int minLength = 3;
	
	public static void main(String[] args) {
		// Grab command line args
		for(int i = 0; i < args.length; i++) {
			if(args[i].equalsIgnoreCase("-D"))
				delimiters = args[i + 1].toCharArray();
			else if (args[i].equalsIgnoreCase("-L")) {
				minLength = Integer.parseInt(args[i + 1]);
			}
		}
		
		// Get document, set to asl, and calculate average
		String Doc = GetDocumentContents(args[0]);
		asl.setDocument(Doc, delimiters); // update to include with delimiters
		AvgLen = asl.GetAvgSentenceLength(minLength);
		
		System.out.println("The average length of your sentences is " + AvgLen);
	}
	
	private static String GetDocumentContents(String input) {
		FileInputStream inFile = null;
		int cnt = 0;
		char letter;
		String outString = "";
		
		try {
			inFile = new FileInputStream(input);
			while((cnt = inFile.read()) != -1) {
				letter = (char)cnt;
				outString += letter;
			}
		}
		catch(IOException ioe) {
			System.out.println("There was an error reading the file.  Please be sure to include the correct file path.");
		}
		finally {
			try {
				if(inFile != null)
					inFile.close();
			}
			catch (IOException ioe) {
				
			}
		}
		
		return outString;
		
	}

}
