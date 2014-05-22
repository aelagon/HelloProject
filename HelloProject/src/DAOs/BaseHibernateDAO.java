package DAOs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseHibernateDAO {

	private SessionFactory sessionFactory;

	public BaseHibernateDAO() {
		sessionFactory = new Configuration()
        .configure() // configures settings from hibernate.cfg.xml
        .buildSessionFactory();
	}
	
	public Session getSession(){
		return sessionFactory.openSession();
	}
}
