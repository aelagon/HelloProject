package DAOs;

import it.restws.UtenteRest;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Utente;

public class UtenteDAO extends BaseHibernateDAO {
	
	private Logger log = Logger.getLogger(UtenteDAO.class);
	
	public List<Utente> findAll() throws Exception {
		
		Session session;
		try{
			session = getSession();
		} catch( HibernateException e) {
			throw e;
		}
		
		Transaction transaction = null;
		List<Utente> utenti = null;
		
		try{
			transaction = session.beginTransaction();
			Criteria c;
			c = session.createCriteria(Utente.class);
			utenti = (List<Utente>)c.list();
//			Query query = getSession().createQuery("from Utente");
//			utenti = query.list();

		} catch(Exception e) {
			if(transaction != null) transaction.rollback();
			throw e;
		} finally {
			session.close();
		}	
		
		return utenti;		
	}

	public Utente findById(int id) {

		return (Utente) getSession().get(Utente.class, id);
	}

	public boolean save(Utente user) {
		log.debug("Saving: " + user.getNome() + " " + user.getCognome() + " " + user.getId());
		boolean result=true;
		Session session = getSession();
		Transaction transaction = null;				
		try {
			transaction = session.beginTransaction();
			Integer id = (Integer) session.save(user);
			log.debug("Saved: " + id);
			transaction.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
			transaction.rollback();
		}
		return result;
	}

}
