/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SpellCheckerController {
	
	Dictionary dictionary;
	
	public void setDictionary(Dictionary d) {
		this.dictionary=d;
		boxLang.getItems().addAll("Italian", "English");
	}

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxLang"
    private ChoiceBox<String> boxLang;// Value injected by FXMLLoader

    @FXML // fx:id="txtInitial"
    private TextField txtInitial; // Value injected by FXMLLoader

    @FXML // fx:id="btnCheck"
    private Button btnCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtWrong"
    private TextArea txtWrong; // Value injected by FXMLLoader

    @FXML // fx:id="errCounter"
    private Label errCounter; // Value injected by FXMLLoader

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="timeCounter"
    private Label timeCounter; // Value injected by FXMLLoader

    @FXML
    void doCheck(ActionEvent event) {
    
    	String inputString ="";
    	String lang="";
    	int numErr=0;
    	if(txtInitial.getText().length()==0) {
    		txtWrong.setText("Inserisci una frase.");
    	}
    	else {
    inputString=	txtInitial.getText();
  //inputString.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]\\", "");
    	String sArray[]=inputString.split(" ");
    	LinkedList<String> inputTextList = new LinkedList<String>();
    	for(int i=0; i<sArray.length; i++) {
    		inputTextList.add(sArray[i]);
    	}
    lang=boxLang.getSelectionModel().getSelectedItem();
    dictionary.loadDictionary(lang);
    LinkedList<RichWord> wrongWords = new LinkedList<RichWord>(dictionary.spellCheckText(inputTextList));
    	
    for(RichWord w:wrongWords) {
    	numErr++;
    	txtWrong.appendText(w.getWord()+" ");
    }
    errCounter.setText("The text contains"+numErr+" errors");
    	}
    }

    @FXML
    void doClear(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxLang != null : "fx:id=\"boxLang\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInitial != null : "fx:id=\"txtInitial\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWrong != null : "fx:id=\"txtWrong\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert errCounter != null : "fx:id=\"errCounter\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert timeCounter != null : "fx:id=\"timeCounter\" was not injected: check your FXML file 'SpellChecker.fxml'.";
    }
}
