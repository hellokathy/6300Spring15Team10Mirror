package edu.gatech.seclass.prj1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AvgSentenceLength {
    Sentence[] Document;
    private int AvgSentenceLength = 0;
    int minLength = 3;
    char [] charDelimeters;
    File inputFile;
    
    public void setDocument(String doc) {
        
        doc = doc.replaceAll("\n", " ");
        String[] temp;
        String DelimStr = "\\.+\\s*|\\!+\\s*|\\?+\\s*";
        
        // Loop through delims array and add each delimiter to the regex search string
        if(charDelimeters != null) {
            for(int i = 0; i < charDelimeters.length; i++) {
                DelimStr += "|\\" + charDelimeters[i] + "\\s*";
            }
        }
        
        temp = doc.split(DelimStr);
        
        Document = new Sentence[temp.length];
        
        for(int i = 0; i < temp.length; i++) {
            Document[i] = new Sentence();
            Document[i].setSentence(temp[i]);
        }
    }
    
    public int computeAverageSentenceLength() {
        
        String inputDoc = GetDocumentContents(inputFile.getAbsolutePath());
        
        setDocument(inputDoc);
        int len = 0;
        int cnt = 0;
        
        for(int i = 0; i < Document.length; i++, cnt++) {
            len += Document[i].length(minLength);
        }
        
        this.AvgSentenceLength = len / cnt;
        return this.AvgSentenceLength;
    }
    
    public void setMinWordLength(int length) {
        if (length>=0){
            this.minLength = length;
        }
        else{
            this.minLength=0;
        	System.out.println("The given value for '-l' was less than zero.  A value of zero will be used instead.");
        }
        
    }
    
    public void setSentenceDelimiters(String delims){
        this.charDelimeters = delims.toCharArray();
    }
    
    public void setFile(File file){
    	try {
    		this.inputFile = file;
    	}
    	catch (NullPointerException npe) {
    		System.out.println("The provided file does not exist.  Please be sure the file path and name are correct.");
    		System.exit(0);
    	}
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
            
            try {
                if(inFile != null)
                    inFile.close();
            }
            catch (IOException ioe2) {
                
            }
            
            System.exit(0);
        }

        try {
            if(inFile != null)
                inFile.close();
        }
        catch (IOException ioe) {
            
        }
        
        return outString;
        
    }
    
}
