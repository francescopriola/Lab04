package it.polito.tdp.lab04.model;

import java.util.Objects;

import it.polito.tdp.lab04.model.Studente;

public class Studente implements Comparable<Studente>{
	
	private Integer matricola;
	private String cognome;
	private String nome;
	private String cds;
	
	public Studente(Integer matricola, String cognome, String nome, String cds) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cds = cds;
	}

	public Integer getMatricola() {
		return matricola;
	}

	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCds() {
		return cds;
	}

	public void setCds(String cds) {
		this.cds = cds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricola);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		return Objects.equals(matricola, other.matricola);
	}

	@Override
	public String toString() {
		return matricola + ", " + cognome + ", " + nome + ", " + cds;
	}

	@Override
	public int compareTo(Studente o) {
		
		return this.getMatricola() - o.getMatricola();
	}

}
