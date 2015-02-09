package com.nagravision.itm.data.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.nagravision.itm.data.dao.INTDataFlowDao;
import com.nagravision.itm.data.dao.INTDataQueueDao;
import com.nagravision.itm.data.dao.ITServiceDao;
import com.nagravision.itm.data.dao.JpaINTDataFlowDaoImpl;
import com.nagravision.itm.data.dao.JpaINTDataQueueDaoImpl;
import com.nagravision.itm.data.dao.JpaITServiceDaoImpl;
import com.nagravision.itm.data.domain.INTDataFlow;
import com.nagravision.itm.data.domain.INTDataQueue;
import com.nagravision.itm.data.domain.ITService;

public class DataInitUtils {

	protected static EntityManagerFactory emf;
	protected EntityManager em;

	ITServiceDao daoITService;
	INTDataFlowDao daoDataFlow;
	INTDataQueueDao daoDataQueue;

	public DataInitUtils() {
		emf = Persistence.createEntityManagerFactory("mongo");
		em = emf.createEntityManager();
		daoITService = new JpaITServiceDaoImpl();
		daoITService.setEntityManager(em);
		daoDataFlow = new JpaINTDataFlowDaoImpl();
		daoDataFlow.setEntityManager(em);
		daoDataQueue = new JpaINTDataQueueDaoImpl();
		daoDataQueue.setEntityManager(em);

	}

	public void initData() {

		
		// init data
		List<ITService> services = daoITService.findAll();
		
		for (ITService service : services) {
			daoITService.remove(service);
			
		}
		List<INTDataFlow> dataFlows = daoDataFlow.findAll();

		for (INTDataFlow dataFlow : dataFlows) {
			daoDataFlow.remove(dataFlow);
			
		}
		List<INTDataQueue> dataQueues = daoDataQueue.findAll();

		for (INTDataQueue dataQueue : dataQueues) {
			daoDataQueue.remove(dataQueue);
			
		}
		
		ITService service = new ITService("A2E", "OK");
		
		daoITService.persist(service);
		
		INTDataFlow dataFlow = new INTDataFlow("Persons", "OK", "SOA10g",
				"PersonsRoute");
		dataFlow.setItService(service);
		daoDataFlow.persist(dataFlow);

		INTDataQueue dataQueue = new INTDataQueue("PersonQueue", "OK", dataFlow, 0, 0);
		daoDataQueue.persist(dataQueue);

		
		dataFlow = new INTDataFlow("Assignment", "OK", "SOA10g",
				"AssignmentRoute");
		dataFlow.setItService(service);
		daoDataFlow.persist(dataFlow);

		dataQueue = new INTDataQueue("AssignementQueue", "OK", dataFlow, 0, 0);
		daoDataQueue.persist(dataQueue);

		
		service = new ITService("Bixi2HR", "OK");
		daoITService.persist(service);

		dataFlow = new INTDataFlow("Clocking", "OK", "Fuse", "Bixi2HRRoute");
		dataFlow.setItService(service);
		daoDataFlow.persist(dataFlow);

		service = new ITService("PIBO", "OK");
		daoITService.persist(service);

		dataFlow = new INTDataFlow("EPMMessages", "OK", "SOA11g",
				"AllMsEpmRoute");
		dataFlow.setItService(service);
		daoDataFlow.persist(dataFlow);

		service = new ITService("Precix2Ebs", "OK");
		daoITService.persist(service);
		
		dataFlow = new INTDataFlow("PrecixOrders", "OK", "SOA11g",
				"PrecixOrdersRoute");
		dataFlow.setItService(service);
		daoDataFlow.persist(dataFlow);

		dataQueue = new INTDataQueue("SalesOrderLineQueue", "OK", dataFlow, 0, 0);
		daoDataQueue.persist(dataQueue);

		
		dataFlow = new INTDataFlow("PrecixInstances", "OK", "SOA11g",
				"InstancesRoute");
		dataFlow.setItService(service);
		daoDataFlow.persist(dataFlow);
		
		dataQueue = new INTDataQueue("ItemInstance", "OK", dataFlow, 0, 0);
		daoDataQueue.persist(dataQueue);
		
		

	}

	
}
