package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtperiodo;

    @FXML
    private TextField txtcorso;

    @FXML
    private Button btnCorsiPerPeriodo;

    @FXML
    private Button btnSrudenti;

    @FXML
    private Button btnNumeroStudenti;

    @FXML
    private Button btnDivisioneStudenti;

    @FXML
    private TextArea txtrisultato;

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	txtrisultato.clear();
    	String pdString=txtperiodo.getText();
    	Integer pd;
    	try {
    	pd=Integer.parseInt(pdString);
    	} catch(NumberFormatException e) {
    		txtrisultato.setText("Devi inserire un numero(1 o 2)");
    		return;
    	}
    	if(!pd.equals(1) && !pd.equals(2)) {
    		txtrisultato.setText("Devi inserire un numero(1 o 2)");
    		return;
    	}
    	List <Corso> corsi=this.model.getCorsiByPeriodo(pd);
    	for(Corso c:corsi) {
    		txtrisultato.appendText(c.toString()+"\n");
    	}

    }

    @FXML
    void numeroStudenti(ActionEvent event) {
    	txtrisultato.clear();
    	String pdString=txtperiodo.getText();
    	Integer pd;
    	try {
    	pd=Integer.parseInt(pdString);
    	} catch(NumberFormatException e) {
    		txtrisultato.setText("Devi inserire un numero(1 o 2)");
    		return;
    	}
    	if(!pd.equals(1) && !pd.equals(2)) {
    		txtrisultato.setText("Devi inserire un numero(1 o 2)");
    		return;
    	}
    	Map <Corso,Integer> statistiche=this.model.getIscrittiByPeriodo(pd);
    	for(Corso c: statistiche.keySet()) {
    		txtrisultato.appendText(c.getNome()+ "  "+ statistiche.get(c)+"\n");
    	}

    }

    @FXML
    void stampaDivisione(ActionEvent event) {
    	//OUTPUT dato un corso, ci aspettiamo una divisione del genere
    	//informatica 12
    	//gestionali 28
    	txtrisultato.clear();
    	String codins=txtcorso.getText();
    	if(model.esistecorso(new Corso(codins,null,null,null))==false) {
    		txtrisultato.appendText("il corso non esiste\n");
    		return;
    	}
    	Map<String,Integer> statistiche=this.model.getDivisioneCDS(new Corso(codins,null,null,null));
    	for(String cds: statistiche.keySet()) {
    		txtrisultato.appendText(cds+" "+ statistiche.get(cds).toString()+"\n");
    	}
    	
    }

    @FXML
    void stampaStudenti(ActionEvent event) {
    	txtrisultato.clear();
    	String codins=txtcorso.getText();
    	if(model.esistecorso(new Corso(codins,null,null,null))==false) {
    		txtrisultato.appendText("il corso non esiste\n");
    		return;
    	}
    	//potremmo controllare se il codice esiste
    	List <Studente> studenti= this.model.getStudentiByCorso(new Corso(codins,null,null,null));
    	if(studenti.size()==0) {
    		txtrisultato.appendText("il corso non ha studenti iscritti\n");
    		return;
    	}
    	for(Studente s:studenti) {
    		txtrisultato.appendText(s.toString()+"\n");
    	}

    }

    @FXML
    void initialize() {
        assert txtperiodo != null : "fx:id=\"txtperiodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtcorso != null : "fx:id=\"txtcorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSrudenti != null : "fx:id=\"btnSrudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtrisultato != null : "fx:id=\"txtrisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
}
