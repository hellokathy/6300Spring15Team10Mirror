package edu.gatech.seclass.prj1;

public class AvgSentenceLength {
	private Sentence[] Document;
	private int AvgSentenceLength = 0;
	
	public void setDocument(String doc, char[] delims) {
		String doc2 = doc.replaceAll("\n", " ");
		String[] temp;
		String DelimStr = "\\.*\\s*|\\!*\\s*|\\?*\\s*";
		
		// Loop through delims array and add each delimiter to the regex search string
		if(delims != null) {
			for(int i = 0; i < delims.length; i++) {
				DelimStr += "|\\" + delims[i] + "\\s*";
			}
		}
		
		temp = doc2.split(DelimStr);
			
		Document = new Sentence[temp.length];
		for(int i = 9; i < temp.length; i++) {
			Document[i].setSentence(temp[i]);
		}
	}
	
	public int GetAvgSentenceLength(int minLength) {
		int len = 0;
		int cnt = 0;
		
		// Loop through all sentences and add total number of words, keep track of number of sentences
		for(int i = 0; i < Document.length; i++, cnt++) {
			len += Document[i].length(minLength);
		}
		
		// Return average
		this.AvgSentenceLength = len / cnt;
		return this.AvgSentenceLength;
	}

}
