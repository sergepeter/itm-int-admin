package com.masict.itm.data.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.masict.itm.data.dao.INTDataQueueDao;
import com.masict.itm.data.dao.JpaINTDataQueueDaoImpl;
import com.masict.itm.data.domain.INTDataQueue;

@Path("/dataqueues")
public class INTDataQueueService {
	
	protected static EntityManagerFactory emf;
	protected EntityManager em;
	
	INTDataQueueDao dao;
	
	public INTDataQueueService() {
		emf = Persistence.createEntityManagerFactory("mongo");
		em = emf.createEntityManager();
		dao = new JpaINTDataQueueDaoImpl();
		dao.setEntityManager(em);
	}
	
	@GET
	@Path("/")
	@Produces("application/json")
	public List<INTDataQueue> findAll( ) {
		return dao.findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public INTDataQueue getById(@PathParam("id") String id) {	
		return dao.getById(id);
	}
	
	@POST
	@Path("/")
	@Produces("application/json")
	public Response create(INTDataQueue entity) {	
		dao.persist(entity);
		return Response.ok("<status>success</status>").build();
	}
	
	@PUT
	@Path("/")
	@Produces("application/json")
	public Response save(INTDataQueue entity) {	
		dao.persist(entity);
		return Response.ok("<status>success</status>").build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public Response delete(@PathParam("id") String id) {	
		INTDataQueue entity = dao.getById(id);
		dao.remove(entity); 
		return Response.ok("<status>success</status>").build();
	
	}
	
}
