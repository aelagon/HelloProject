package beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entities.Utente;
import DAOs.UtenteDAO;

public class UtenteBean {

	private UtenteDAO utenteDAO;
	private Logger log;
	public UtenteBean(){
		utenteDAO = new UtenteDAO();
		log = Logger.getLogger(UtenteBean.class.getName());
	}
	
	public List<Utente> getUtenti(){
		List<Utente> utenti = new ArrayList<Utente>();
		log.debug("Gestisco richiesta");
		try {
			utenti = utenteDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("Ritorno risultato");
		return utenti;
	}
	
}
