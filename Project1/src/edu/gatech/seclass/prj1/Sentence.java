package edu.gatech.seclass.prj1;

public class Sentence {
	private String Sentence = null;
	private String[] Words = null;

	public void setSentence(String sentence) {
		this.Sentence = sentence;
		this.Words = sentence.split(" ");
	}

	public String getSentence() {
		return this.Sentence;
	}

	public int length() {
		if(this.Words == null) {
			this.Words = this.Sentence.split(" ");
		}
		
		if(this.Sentence == "" || this.Sentence == null)
			return 0;
		else
			return this.Words.length;
	}
}
