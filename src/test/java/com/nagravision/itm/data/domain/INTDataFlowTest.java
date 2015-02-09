package com.nagravision.itm.data.domain;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class INTDataFlowTest {

	protected static EntityManagerFactory emf;
	protected EntityManager em;

	@Test
	public void test() {

		ITService service = new ITService("P2E_Test", "OK");
		INTDataFlow dataFlow = new INTDataFlow("PrecixOrders_Test", "OK", "soa11g", "orderRoute");
		dataFlow.setItService(service);
		
		emf = Persistence.createEntityManagerFactory("mongo");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(service);
		em.persist(dataFlow);
		em.flush();
		
		em.getTransaction().commit();
		
		assertNotNull(service.getId());
		assertNotNull(dataFlow.getId());
		
		em.getTransaction().begin();
		
		em.remove(service);
		em.remove(dataFlow);
		em.flush();
		
		em.getTransaction().commit();
		
		em.close();
	}
}
