package com.masict.itm.data.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.masict.itm.data.dao.ITServiceDao;
import com.masict.itm.data.dao.JpaITServiceDaoImpl;
import com.masict.itm.data.domain.ITService;

@Path("/itservices")
public class ITServiceService {
	
	protected static EntityManagerFactory emf;
	protected EntityManager em;
	
	ITServiceDao dao;
	
	public ITServiceService() {
		emf = Persistence.createEntityManagerFactory("mongo");
		em = emf.createEntityManager();
		dao = new JpaITServiceDaoImpl();
		dao.setEntityManager(em);
	}
	
	@GET
	@Path("/")
	@Produces("application/json")
	public List<ITService> findAll( ) {
		return dao.findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ITService getById(@PathParam("id") String id) {	
		return dao.getById(id);
	}
	
}
