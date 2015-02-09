package com.nagravision.itm.data.domain;

import java.util.Date;

import javax.persistence.CascadeType;
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
@NoSql(dataFormat = DataFormatType.MAPPED, dataType = "int_data_queue")
public class INTDataQueue {

	@Id
	@GeneratedValue
	@Field(name = "_id")
	private String id;

	private String name;
	private String description;

	private String status;
	private String techno;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	@ManyToOne( cascade = CascadeType.PERSIST)
	@JoinField(name = "int_data_flow")
	private INTDataFlow intDataFLow;

	private long nbWaitingMessage;
	private long nbWaitingMessageError;
	
	public INTDataQueue() {
		super();
	}

	@Override
	public String toString() {
		return "INTDataQueue [id=" + id + ", name=" + name + ", status="
				+ status + ", nbWaitingMessage=" + nbWaitingMessage
				+ ", nbWaitingMessageError=" + nbWaitingMessageError + "]";
	}


	public INTDataQueue(String name, String status, INTDataFlow intDataFLow,
			long nbWaitingMessage, long nbWaitingMessageError) {
		super();
		this.name = name;
		this.status = status;
		this.intDataFLow = intDataFLow;
		this.nbWaitingMessage = nbWaitingMessage;
		this.nbWaitingMessageError = nbWaitingMessageError;		
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

	public String getTechno() {
		return techno;
	}

	public void setTechno(String techno) {
		this.techno = techno;
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

	public INTDataFlow getIntDataFLow() {
		return intDataFLow;
	}

	public void setIntDataFLow(INTDataFlow intDataFLow) {
		this.intDataFLow = intDataFLow;
	}

	public long getNbWaitingMessage() {
		return nbWaitingMessage;
	}

	public void setNbWaitingMessage(long nbWaitingMessage) {
		this.nbWaitingMessage = nbWaitingMessage;
	}

	public long getNbWaitingMessageError() {
		return nbWaitingMessageError;
	}

	public void setNbWaitingMessageError(long nbWaitingMessageError) {
		this.nbWaitingMessageError = nbWaitingMessageError;
	}


	
}
