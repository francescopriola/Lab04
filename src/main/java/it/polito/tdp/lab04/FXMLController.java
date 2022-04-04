package it.polito.tdp.lab04;

import java.net.URL;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	Map<Integer, Studente> studenti;
	
	public void setModel(Model model) {
    	this.model = model;
    	studenti = this.model.getTuttiStudenti();
    	List<Corso> corsi = this.model.getTuttiICorsi();
        for(Corso c : corsi) 
        	cmbCorso.getItems().add(c);
        cmbCorso.getItems().add(null);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbCorso;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtText;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtText.clear();
    	String m = txtMatricola.getText();
    	
    	if(m.equals("")) {
    		txtText.appendText("Inserisci una matricola!\n");
    		return;
    	}
    	
    	Integer matricola = Integer.parseInt(m);

    	if(!studenti.containsKey(matricola)) {
    		txtText.appendText("Inserisci una matricola valida!\n");
    		return;
    	}
    	
    	Studente s = studenti.get(matricola);
    	List<Corso> corsi = model.getCorsiByStudente(s);
    	Collections.sort(corsi);
    	for(Corso c : corsi)
    		txtText.appendText(c + "\n");

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtText.clear();
    	Corso c = this.cmbCorso.getValue();
    	
    	if(c == null) {
    		txtText.appendText("Selezionare un corso di studi! \n");
    		return;
    	}
    	
    	List<Studente> studenti = this.model.getStudentiIscrittiAlCorso(c);
    	Collections.sort(studenti);
    	for(Studente s : studenti)
    		txtText.appendText(s + "\n");
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	txtText.clear();
    	String m = txtMatricola.getText();
    	
    	if(m.equals("")) {
    		txtText.appendText("Inserisci una matricola!\n");
    		return;
    	}
    	
    	Integer matricola = Integer.parseInt(m);
    	
    	if(!studenti.containsKey(matricola)) {
    		txtText.appendText("Inserisci una matricola valida!\n");
    		return;
    	}

    	Studente s = this.model.getNomeCognomeStudente(matricola);
    	List<Corso> corsi = new ArrayList<Corso>(model.getCorsiByStudente(s));
    	Collections.sort(corsi);
    	Corso c = null;
    	
    	if(cmbCorso.getValue() != null) {
    		c = cmbCorso.getValue();
    		if(corsi.contains(c)) {
    			txtText.appendText("Studente già iscritto a questo corso!\n");
   				return;
    		}
    		else {
    			model.inscriviStudenteACorso(s, c);
    			txtText.appendText("Studente iscritto al corso!\n");
    		}
    	}
    	
    }

    @FXML
    void doNomeCognome(ActionEvent event) {
    	txtText.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	String m = txtMatricola.getText();
    	
    	if(m.equals("")) {
    		txtText.appendText("Inserisci una matricola!\n");
    		return;
    	}
    	
    	Integer matricola = Integer.parseInt(m);
    	
    	if(!studenti.containsKey(matricola)) {
    		txtText.appendText("Inserisci una matricola valida!\n");
    		return;
    	}

    	Studente s = this.model.getNomeCognomeStudente(matricola);
    	txtNome.setText(s.getNome());
   		txtCognome.setText(s.getCognome());
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtCognome.clear();
    	txtNome.clear();
    	txtMatricola.clear();
    	txtText.clear();
    	this.cmbCorso.setValue(null);
    }

    @FXML
    void initialize() {
        assert cmbCorso != null : "fx:id=\"cmbCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtText != null : "fx:id=\"txtText\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
