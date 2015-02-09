package com.nagravision.itm.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataFormat=DataFormatType.MAPPED, dataType="it_service")
public class ITService{
	
	@Id
    @GeneratedValue
    @Field(name="_id")
	private String id;
	 
	@Column(unique=true)
	private String name;
	private String status;
	
	private String createdAt;
	private long uptime; 
	private long problem;
	private long downtime;
	private String lastChanged;
	
	public ITService() {
		super();
	
	}

	public ITService(String name, String status) {
		this.name = name;
		this.status = status;
	}

	public ITService(String name, String status, String createdAt, long uptime, long problem, long downtime, String lastChanged) {
		this(name, status);
		this.createdAt = createdAt;
		this.uptime = uptime;
		this.problem = problem;
		this.downtime = downtime;
		this.lastChanged = lastChanged;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getProblem() {
		return problem;
	}

	public void setProblem(long problem) {
		this.problem = problem;
	}

	public long getDowntime() {
		return downtime;
	}

	public void setDowntime(long downtime) {
		this.downtime = downtime;
	}

	public String getLastChanged() {
		return lastChanged;
	}

	public void setLastChanged(String lastChanged) {
		this.lastChanged = lastChanged;
	}

	
	@Override
	public String toString() {
		return "ITService [id=" + id + ", name=" + name + ", status=" + status
				+ ", createdAt=" + createdAt + ", uptime=" + uptime
				+ ", problem=" + problem + ", downtime=" + downtime
				+ ", lastChanged=" + lastChanged + "]";
	}
	
}
