package edu.gatech.seclass.prj1;

import java.io.*;

public class Main {
	private static AvgSentenceLength asl = new AvgSentenceLength();
	private static int AvgLen = 0;
	private static String delimiters = "";
	private static int minLength = 3;
	
	public static void main(String[] args) {
		//Grab command line args
		for(int i = 0; i < args.length; i++) {
			if(args[i].equalsIgnoreCase("-D"))
				delimiters = args[i + 1];
			else if (args[i].equalsIgnoreCase("-L")) {
				try {
					minLength = Integer.parseInt(args[i + 1]);
				}
				catch(NumberFormatException nfe) {
					System.out.println("The value provided for '-l' was not an integer.  Please re-run with a valid whole number");
					return;
				}
			}
		}	
		
		// Get file, set file and parameters to asl, and calculate average
		File file = new File(args[0]);
		int rtn = asl.setFile(file);
		if(rtn == -1)
			return;
		
		asl.setMinWordLength(minLength);
		asl.setSentenceDelimiters(delimiters);
		AvgLen = asl.computeAverageSentenceLength();
		
		if(AvgLen != -1) {
			System.out.println("The average length of your sentences is " + AvgLen + " words.");
		}
	}
}
