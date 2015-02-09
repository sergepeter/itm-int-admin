package com.masict.itm.data.domain;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class ITServiceTest {

	protected static EntityManagerFactory emf;
	protected EntityManager em;

	@Test
	public void test() {
		
		ITService service = new ITService("P2E", "OK");
		
		emf = Persistence.createEntityManagerFactory("mongo");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(service);
		em.getTransaction().commit();
		
		assertNotNull(service.getId());
		
		em.getTransaction().begin();
		em.remove(service);
		em.getTransaction().commit();
		
		em.close();
		
	}

}
