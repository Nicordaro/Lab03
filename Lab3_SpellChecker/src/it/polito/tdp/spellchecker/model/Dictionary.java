package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary implements Comparator<RichWord>{
	
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
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		RichWord toRemove=null;
		List<RichWord> wrongWords = new LinkedList<RichWord>();
		for(String word: inputTextList) {
			wrongWords.add(new RichWord(word));
		}
		List<RichWord> rightWords = new LinkedList<RichWord>();
		
		for(String word: inputTextList) {
			RichWord rich = new RichWord(word);
			for(String dictWord:keywords) {
				if(rich.isCorrect(dictWord)) {
					rightWords.add(rich);
				}
			}
		}
		
		for(RichWord rw: rightWords) {
			for(RichWord ww: wrongWords) {
				if(rw.getWord().compareTo(ww.getWord())==0) {
					//Se c'è match, vuol dire che la parola è corretta, quindi la rimuovo!
					toRemove=ww;
					}
			}
			wrongWords.remove(toRemove);
		}
		return wrongWords;	
	}
	
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		RichWord toRemove=null;
		List<RichWord> wrongWords = new LinkedList<RichWord>();
		for(String word: inputTextList) {
			wrongWords.add(new RichWord(word));
		}
		List<RichWord> rightWords = new LinkedList<RichWord>();
		
		for(String word: inputTextList) {
			RichWord rich = new RichWord(word);
			//DEVO LAVORARE SU QUESTA PARTE
			
			int upperLimit=keywords.size();
			int lowerLimit=0;
			
			int correct=1;
//			int position=0;
			
			while(correct!=0 && upperLimit!=lowerLimit) {
				int index = (upperLimit+lowerLimit)/2;
				if(rich.getWord().compareTo(keywords.get(index))==0) {
					correct=0;
					rightWords.add(rich);
					if(upperLimit==lowerLimit) {
						break;
					}
				} else if(rich.getWord().compareTo(keywords.get(index))<0) {
					upperLimit=index;
					if(upperLimit==lowerLimit) {
						break;
					}
				} else if(rich.getWord().compareTo(keywords.get(index))>0) {
					lowerLimit=index;
					if(upperLimit==lowerLimit) {
						break;
					}
					}
			}
			correct=1;
			
//			for(String dictWord:keywords) {
//				if(rich.isCorrect(dictWord)) {
//					rightWords.add(rich);
//				}
//			}
			
			
			
		}
		
		for(RichWord rw: rightWords) {
			for(RichWord ww: wrongWords) {
				if(rw.getWord().compareTo(ww.getWord())==0) {
					//Se c'è match, vuol dire che la parola è corretta, quindi la rimuovo!
					toRemove=ww;
					}
			}
			wrongWords.remove(toRemove);
		}
		return wrongWords;	
	}

	@Override
	public int compare(RichWord r1, RichWord r2) {
		return r1.getWord().compareTo(r2.getWord());
	}
}
