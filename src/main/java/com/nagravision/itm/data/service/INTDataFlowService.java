package com.nagravision.itm.data.service;

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

import com.nagravision.itm.data.dao.INTDataFlowDao;
import com.nagravision.itm.data.dao.JpaINTDataFlowDaoImpl;
import com.nagravision.itm.data.domain.INTDataFlow;
import com.nagravision.itm.data.utils.DataInitUtils;

@Path("/dataflows")
public class INTDataFlowService {
	
	protected static EntityManagerFactory emf;
	protected EntityManager em;
	
	INTDataFlowDao dao;
	
	public INTDataFlowService() {
		emf = Persistence.createEntityManagerFactory("mongo");
		em = emf.createEntityManager();
		dao = new JpaINTDataFlowDaoImpl();
		dao.setEntityManager(em);
	}
	
	@GET
	@Path("/")
	@Produces("application/json")
	public List<INTDataFlow> findAll( ) {
		return dao.findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public INTDataFlow getById(@PathParam("id") String id) {	
		return dao.getById(id);
	}
	
	@POST
	@Path("/")
	@Produces("application/json")
	public Response create(INTDataFlow entity) {	
		dao.persist(entity);
		return Response.ok("<status>success</status>").build();
	}
	
	@PUT
	@Path("/")
	@Produces("application/json")
	public Response save(INTDataFlow entity) {	
		dao.persist(entity);
		return Response.ok("<status>success</status>").build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces("application/json")
	public Response delete(@PathParam("id") String id) {	
		INTDataFlow entity = dao.getById(id);
		dao.remove(entity); 
		return Response.ok("<status>success</status>").build();
	}
	
	@GET
	@Path("/init")
	@Produces("application/json")
	public Response init( ) {
		DataInitUtils utils = new DataInitUtils();
		utils.initData();
		return Response.ok("<status>success</status>").build();
	}
	
}
