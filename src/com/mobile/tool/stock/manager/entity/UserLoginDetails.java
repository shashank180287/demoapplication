package com.mobile.tool.stock.manager.entity;


public class UserLoginDetails {

	private String username;
	private String password;
	private UserRole userRole;
	
	public UserLoginDetails() {
	}

	public UserLoginDetails(String username, String password, UserRole userRole) {
		super();
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public String toString() {
		return "UserLoginDetails [username=" + username + ", password="
				+ password + ", userRole=" + userRole + "]";
	}
}
