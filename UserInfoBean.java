package com.mobile.tool.demo.ui.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;

@ManagedBean
@SessionScoped
public class UserInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName; 
	private String streetAddress;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
}
