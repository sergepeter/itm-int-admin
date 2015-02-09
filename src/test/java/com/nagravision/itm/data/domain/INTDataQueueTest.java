package com.nagravision.itm.data.domain;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class INTDataQueueTest {

	protected static EntityManagerFactory emf;
	protected EntityManager em;
	
	@Test
	public void test() {

		ITService service = new ITService("P2E_Test", "OK");
		INTDataFlow dataFlow = new INTDataFlow("PrecixOrders_Test", "OK", "soa11g", "orderRoute");
		dataFlow.setItService(service);

		INTDataQueue dataQueue = new INTDataQueue("OrderQueue_Test", "OK", dataFlow, 0, 10);
		
		emf = Persistence.createEntityManagerFactory("mongo");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(service);
		em.persist(dataFlow);
		em.persist(dataQueue);
		em.flush();
		
		em.getTransaction().commit();
		
		assertNotNull(service.getId());
		assertNotNull(dataFlow.getId());
		
		em.getTransaction().begin();
		em.remove(dataQueue);
		em.remove(dataFlow);
		em.remove(service);
		
		em.flush();

		em.getTransaction().commit();

		em.close();
		
	}

}
