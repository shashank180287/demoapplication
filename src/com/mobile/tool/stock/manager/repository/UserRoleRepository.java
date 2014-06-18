package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.UserRole;

public class UserRoleRepository {

	private static List<UserRole> userRoles;
	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getMySQLJdbcTemplate();
	
	public static List<UserRole> getUserRoles() {
		if(userRoles!=null)
			return userRoles;
		
		String query = "SELECT * FROM USER_ROLE ORDER BY role_id desc";
		userRoles = new ArrayList<UserRole>();
		ResultSet userRolesInDb = null;
		try{
			userRolesInDb = jdbcTemplate.executeQuery(query);
			while(userRolesInDb.next()){
				userRoles.add(new UserRole(userRolesInDb.getInt("role_id"), userRolesInDb.getString("role_name"), userRolesInDb.getString("role_permissions")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(userRolesInDb!=null)
				try {
					userRolesInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return userRoles;
	}
	
	public static UserRole getUserRolesByRoleId(int roleId) {
		if(userRoles==null)
			getUserRoles();
		
		for (UserRole userRole : userRoles) {
			if(userRole.getRoleId()==roleId)
				return userRole;
		}
		return null;
	}
	
	public static UserRole getUserRolesByRoleName(String roleName) {
		if(userRoles==null)
			getUserRoles();
		
		for (UserRole userRole : userRoles) {
			if(userRole.getRoleName().equalsIgnoreCase(roleName))
				return userRole;
		}
		return null;
	}
}
