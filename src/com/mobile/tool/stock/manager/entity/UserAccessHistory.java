package com.mobile.tool.stock.manager.entity;

import java.sql.Date;

public class UserAccessHistory {

	private int accessId;
	private UserLoginDetails user;
	private Date startTime;
	private Date endTime;
	
	public UserAccessHistory() {
	}

	public UserAccessHistory(int accessId, UserLoginDetails user,
			Date startTime, Date endTime) {
		super();
		this.accessId = accessId;
		this.user = user;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getAccessId() {
		return accessId;
	}
	public void setAccessId(int accessId) {
		this.accessId = accessId;
	}
	public UserLoginDetails getUser() {
		return user;
	}
	public void setUser(UserLoginDetails user) {
		this.user = user;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return "UserAccessHistory [accessId=" + accessId + ", user=" + user
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}
