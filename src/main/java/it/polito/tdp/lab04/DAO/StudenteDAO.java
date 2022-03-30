package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.model.*;

public class StudenteDAO {
	
	public Map<Integer, Studente> getTuttiStudenti() {

		final String sql = "SELECT * FROM studente";

		Map<Integer, Studente> studenti = new HashMap<Integer, Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Integer matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");

				Studente s = new Studente(matricola, cognome, nome, cds);
				studenti.put(s.getMatricola(), s);
			}

			rs.close();
			st.close();
			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public Studente getNomeCognomeStudente(int matricola) {

		final String sql = "Select * "
				+ "From studente s "
				+ "Where s.`matricola` = ?";

		Studente s = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("cds"));
			}

			rs.close();
			st.close();
			conn.close();
			
			return s;
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<Corso> getCorsiByStudente(Studente studente) {
		
		String sql = "Select c.`codins`, c.`nome`, c.`crediti`, c.`pd` "
				+ "From corso c, iscrizione i "
				+ "Where c.`codins` = i.`codins` AND i.`matricola` = ?";
		//Quando copio la query da Sequel Pro ricordati di togliere gli \n a tutte le righe 
		//e aggiungere uno spazio in tutte (tranne l'ultima) in modo da non avere le parole attaccate
		
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			
			st.close();
			rs.close();
			conn.close();
			return result;	
		}catch(SQLException e) {
			System.err.println("Errore nel DAO!");
			e.printStackTrace();
			return null;
		}
	}

}
