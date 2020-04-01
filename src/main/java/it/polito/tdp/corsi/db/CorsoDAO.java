package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class CorsoDAO {
	
	public boolean esistecorso(Corso c) {
		String sql ="SELECT * FROM corso WHERE codins= ?";
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1,c.getCodins());
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				conn.close();
				return true;
			}
			else {
				conn.close();
				return false;
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	public List<Corso> getCorsiByPeriodo(Integer pd){
		String sql ="SELECT * FROM corso WHERE pd = ?";
		List <Corso> result=new ArrayList<>();
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1,pd);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Corso c=new Corso(rs.getString("codins"),rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	
	public Map<Corso,Integer> getIscrittiByPeriodo(Integer pd){
		String sql ="SELECT c.codins,c.nome,c.crediti,c.pd,COUNT(*) as tot FROM corso as c,iscrizione i WHERE c.codins=i.codins and c.pd= ? GROUP BY c.codins,c.nome,c.crediti,c.pd";
		 Map<Corso, Integer> result=new HashMap<Corso,Integer>();
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1,pd);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Corso c=new Corso(rs.getString("codins"),rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				Integer n=rs.getInt("tot");
				result.put(c,n);
			}
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	
	public List<Studente> getStudentiByCorso(Corso corso){
		String sql ="SELECT studente.matricola,studente.cognome,studente.nome,studente.CDS"
				+ " FROM studente,iscrizione"
				+ " WHERE studente.matricola=iscrizione.matricola AND iscrizione.codins=?";
		List <Studente> result=new ArrayList<>();
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1,corso.getCodins());
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				Studente c=new Studente(rs.getInt("matricola"),rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				result.add(c);
			}
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public Map<String,Integer> getDivisione(Corso c){
		String sql ="SELECT studente.CDS, COUNT(*) AS tot"+
				" FROM studente,iscrizione" +
				" WHERE studente.matricola=iscrizione.matricola AND studente.CDS <> \"\"  AND iscrizione.codins=? " +
				"GROUP BY studente.cds";
		 Map<String,Integer> result=new HashMap<String,Integer>();
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1,c.getCodins());
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				String cds=rs.getString("cds");
				Integer n=rs.getInt("tot");
				result.put(cds,n);
			}
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	
	

}
