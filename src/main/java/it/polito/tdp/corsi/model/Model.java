package it.polito.tdp.corsi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	public List<Corso> getCorsiByPeriodo(Integer pd){
		CorsoDAO dao=new CorsoDAO();
		return dao.getCorsiByPeriodo(pd);
	}
	
	public Map<Corso,Integer> getIscrittiByPeriodo(Integer pd){
		CorsoDAO dao=new CorsoDAO();
		return dao.getIscrittiByPeriodo(pd);
	}
	
	public List<Studente> getStudentiByCorso(Corso corso){
		CorsoDAO dao=new CorsoDAO();
		return dao.getStudentiByCorso(corso);
	}
	
	public boolean esistecorso(Corso c) {
		CorsoDAO dao=new CorsoDAO();
		return dao.esistecorso(c);
	}
	
	public Map<String,Integer> getDivisioneCDS(Corso c) {
		CorsoDAO dao=new CorsoDAO();
		/*List<Studente> studenti=dao.getStudentiByCorso(c);
		for(Studente s:studenti) {
			if(s.getCDS() != null && !s.getCDS().equals("")) {
			if(statistiche.containsKey(s.getCDS())) {
				statistiche.put(s.getCDS(),statistiche.get(s.getCDS())+1);
			}
			else {
				statistiche.put(s.getCDS(),1);
			}
		}
		}
		return statistiche;*/
		return dao.getDivisione(c);
	}

}
