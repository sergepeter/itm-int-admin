package com.masict.itm.data.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.masict.itm.data.domain.INTDataFlow;
import com.masict.itm.data.domain.INTDataQueue;

public class JpaINTDataQueueDaoImplTest {

	protected static EntityManagerFactory emf;
	protected EntityManager em;
	
	
	@Test
	public void test() {
		emf = Persistence.createEntityManagerFactory("mongo");
		em = emf.createEntityManager();
		
		INTDataQueueDao dao = new JpaINTDataQueueDaoImpl();
		dao.setEntityManager(em);
		
		dao.persist(new INTDataQueue("TestQueue", "OK", new INTDataFlow("TestFLowFromTestQueue", "OK", "soa11g", "none") ,0, 10));
		
		List<INTDataQueue> list = dao.findAll();
		
		assert(list.size()>0);
		
		for (INTDataQueue service: list) {
			
			if (service.getName().contains("TestQueue")) {
				dao.remove(service);
			}
		}
		
		list = dao.findAll();
		assertTrue(list.size()>=0);
			 
		em.close();
	}

}
