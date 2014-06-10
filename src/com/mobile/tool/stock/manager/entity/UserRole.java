package com.mobile.tool.stock.manager.entity;

public class UserRole {

	private int roleId;
	private String roleName;
	private String rolePermissions;
	
	public UserRole() {
		
	}
	
	public UserRole(int roleId, String roleName, String rolePermissions) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.rolePermissions = rolePermissions;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRolePermissions() {
		return rolePermissions;
	}
	public void setRolePermissions(String rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", roleName=" + roleName
				+ ", rolePermissions=" + rolePermissions + "]";
	}
	

}
