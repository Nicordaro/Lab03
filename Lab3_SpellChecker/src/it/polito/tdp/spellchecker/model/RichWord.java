package it.polito.tdp.spellchecker.model;

public class RichWord {

	private String word="";
	private boolean correct=false;
	
	public RichWord(String word) {
		super();
		this.word = word;
	}

	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}

	public boolean isCorrect(String wordDictionary) {
		if(this.word.compareTo(wordDictionary)==0)
			correct=true;
		return correct;
	}
	
}
