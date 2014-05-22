package it.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.Utente;
import DAOs.UtenteDAO;

public class testListaUtenti extends TestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLista() {
		UtenteDAO utenteDAO = new UtenteDAO();
		List<Utente> l = new ArrayList<Utente>();
		try {
			l = utenteDAO.findAll();
			for (Utente utente : l) {
				System.out.println(utente.getNome());
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception during test!");
		}		
		assertEquals(2, l.size());
	}

}
