package com.masict.itm.data.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.JoinField;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataFormat = DataFormatType.MAPPED, dataType = "int_data_flow")
public class INTDataFlow {

	@Id
	@GeneratedValue
	@Field(name = "_id")
	private String id;

	private String name;
	private String description;

	private String status;

	private String techno;

	private String soaPartition;

	private String processId;

	private long onErrorsMessagesToday;
	private long treatedMessagesToday;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@ManyToOne
	@JoinField(name = "int_service_id")
	private ITService itService;
	
	private int sortNumber;
	private boolean show;

	@Override
	public String toString() {
		return "INTDataFlow [id=" + id + ", name=" + name + ", status="
				+ status + ", creationDate=" + creationDate + ", updateDate="
				+ updateDate + "]";
	}

	public INTDataFlow() {
		super();
	}

	public INTDataFlow(String name, String status, String techno,
			String processId) {
		super();
		this.name = name;
		this.status = status;
		this.techno = techno;
		this.processId = processId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSoaPartition() {
		return soaPartition;
	}

	public void setSoaPartition(String soaPartition) {
		this.soaPartition = soaPartition;
	}


	public long getOnErrorsMessagesToday() {
		return onErrorsMessagesToday;
	}

	public void setOnErrorsMessagesToday(long onErrorsMessagesToday) {
		this.onErrorsMessagesToday = onErrorsMessagesToday;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public ITService getItService() {
		return itService;
	}

	public void setItService(ITService itService) {
		this.itService = itService;
	}

	public String getTechno() {
		return techno;
	}

	public void setTechno(String techno) {
		this.techno = techno;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	
	public long getTreatedMessagesToday() {
		return treatedMessagesToday;
	}

	public void setTreatedMessagesToday(long treatedMessagesToday) {
		this.treatedMessagesToday = treatedMessagesToday;
	}

}
