package edu.gatech.seclass.prj1;

public class Sentence {
	private String Sentence = null;
	private String[] Words = null;

	public void setSentence(String sentence) {
		this.Sentence = sentence;
		this.Words = sentence.split("\\s+");
	}

	public String getSentence() {
		return this.Sentence;
	}

	public int length(int minLength) {
		if(this.Words == null) {
			this.Words = this.Sentence.split("\\s|\\.");
		}
		
		if(this.Sentence == "" || this.Sentence == null)
			return 0;
		else {
			int len = 0;
			for(int i = 0; i < Words.length; i++) {
				if (Words[i].length() >= minLength){
					len++;
				}
			}
			
			return len;
		}
	}
}
