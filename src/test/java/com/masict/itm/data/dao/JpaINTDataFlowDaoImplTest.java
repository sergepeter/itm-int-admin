package com.masict.itm.data.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.Test;



import com.masict.itm.data.domain.INTDataFlow;

public class JpaINTDataFlowDaoImplTest {

	protected static EntityManagerFactory emf;
	protected EntityManager entityManager;
	
	
	@Test
	public void test() {
		emf = Persistence.createEntityManagerFactory("mongo");
		entityManager = emf.createEntityManager();
		
		INTDataFlowDao dao = new JpaINTDataFlowDaoImpl();
		dao.setEntityManager(entityManager);
		
		dao.persist(new INTDataFlow("TestFLow", "OK", "soa11g", "none"));
		
		List<INTDataFlow> list = dao.findAll();
		
		assert(list.size()>0);
		
		for (INTDataFlow service: list) {
			
			if (service.getName().contains("TestFLow")) {
				dao.remove(service);
			}
		}
		
		list = dao.findAll();
		assertTrue(list.size()>=0);
			 
		entityManager.close();
	}

}
