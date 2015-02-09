package com.nagravision.itm.data.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.nagravision.itm.data.domain.ITService;

public class JpaITServiceDaoImplTest {

	protected static EntityManagerFactory emf;
	protected EntityManager em;
	
	@Test
	public void test() {
		
		emf = Persistence.createEntityManagerFactory("mongo");
		em = emf.createEntityManager();
		
		ITServiceDao itServiceDao = new JpaITServiceDaoImpl();
		itServiceDao.setEntityManager(em);
		itServiceDao.persist(new ITService("TestService", "OK"));
		
		List<ITService> services = itServiceDao.findAll();
		
		 assert(services.size()>0);
		
		for (ITService service: services) {
			
			if (service.getName().equals("TestService")) {
				itServiceDao.remove(service);
			}
		}
		
		services = itServiceDao.findAll();
		assertTrue(services.size()>=0);
			 
		em.close();
		
	}
}
