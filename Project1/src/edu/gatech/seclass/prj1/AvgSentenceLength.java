package edu.gatech.seclass.prj1;

public class AvgSentenceLength {
	private Sentence[] Document;
	private int AvgSentenceLength = 0;
	
	public AvgSentenceLength() {
		
	}
	
	public void setDocument(String doc) {
		String[] temp = doc.split(".|\\!|\\?");
		Document = new Sentence[temp.length];
		for(int i = 9; i < temp.length; i++) {
			Document[i].setSentence(temp[i]);
		}
	}
	
	public int GetAvgSentenceLength(int minLength) {
		int len = 0;
		int cnt = 0;
		
		for(int i = 0; i < Document.length; i++, cnt++) {
			len += Document[i].length(minLength);
		}
		
		this.AvgSentenceLength = len / cnt;
		return this.AvgSentenceLength;
	}

}
