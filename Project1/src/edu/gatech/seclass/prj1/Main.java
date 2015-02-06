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
	private static String delimiters = "";
	private static int minLength = 0;
	
	public static void main(String[] args) {
		//Grab command line args
		for(int i = 0; i < args.length; i++) {
			if(args[i].equalsIgnoreCase("-D"))
				delimiters = args[i + 1];
			else if (args[i].equalsIgnoreCase("-L")) {
				minLength = Integer.parseInt(args[i + 1]);
			}
		}	
		
		// Get file, set file and parameters to asl, and calculate average
		File file = new File(args[0]);
		asl.setFile(file); 
		asl.setMinWordLength(minLength);
		asl.setSentenceDelimiters(delimiters);
		AvgLen = asl.computeAverageSentenceLength();
		
		System.out.println("The average length of your sentences is " + AvgLen + " words.");
	}
}
