package it.polito.tdp.lab04.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.DAO.*;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return corsoDao.getTuttiICorsi();
	}
	
	public  Map<Integer, Studente> getTuttiStudenti(){
		return studenteDao.getTuttiStudenti();
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return corsoDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiByStudente(Studente studente) {
		return studenteDao.getCorsiByStudente(studente);
	}
	
	public Studente getNomeCognomeStudente(int matricola) {
		return studenteDao.getNomeCognomeStudente(matricola);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return corsoDao.inscriviStudenteACorso(studente, corso);
	}

}
