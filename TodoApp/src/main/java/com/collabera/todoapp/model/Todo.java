package com.collabera.todoapp.model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

public class Todo {
	
	private int id;
	// which should take more than 5 characters
	@Size(min = 5, message = "Description field should contain 5 or more character")
	private String desc;
	private String user;
	@Future(message="Date should be greater than today")
	private Date targetDate;
	private boolean status;
	
	public Todo() {
		super();
	}
	
	public Todo(int id, String desc, String user, Date targetDate, boolean status) {
		super();
		this.id = id;
		this.desc = desc;
		this.user = user;
		this.targetDate = targetDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + id;
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((targetDate == null) ? 0 : targetDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		if (targetDate == null) {
			if (other.targetDate != null)
				return false;
		} else if (!targetDate.equals(other.targetDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", desc=" + desc + ", user=" + user + ", targetDate=" + targetDate + ", status="
				+ status + "]";
	}
	
	
	
}
