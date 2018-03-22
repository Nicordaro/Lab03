package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	private List<String> keywords = new LinkedList<String>();

	public void loadDictionary(String language) {
	try {
		FileReader fr = new FileReader("rsc/"+language+".txt");
		BufferedReader br = new BufferedReader(fr);
		String word;
		while ((word = br.readLine()) != null) {
			// Aggiungere parola alla struttura dati
			keywords.add(word);
		}
		br.close();
		} catch (IOException e){
		System.out.println("Errore nella lettura del file");
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		RichWord toRemove=null;
		List<RichWord> wrongWords = new LinkedList<RichWord>();
		for(String s: inputTextList) {
			wrongWords.add(new RichWord(s));
		}
		List<RichWord> allWords = new LinkedList<RichWord>();
		
		for(String word: inputTextList) {
			RichWord rich = new RichWord(word);
			for(String dictWord:keywords) {
				if(rich.isCorrect(dictWord)) {
					allWords.add(rich);
				}
			}
		}
		
		for(RichWord rw: allWords) {
			for(RichWord w: wrongWords) {
				if(rw.getWord().compareTo(w.getWord())==0) {
					toRemove=w;
					}
			}
			wrongWords.remove(toRemove);
		}
		return wrongWords;
		
		
	}
}
