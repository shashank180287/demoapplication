package com.mobile.tool.stock.manager.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.tool.stock.manager.entity.TransectionMode;

public class TransectionModeRepository {

	private static JdbcTemplate jdbcTemplate = JdbcTemplate.getMySQLJdbcTemplate();
	
	public static TransectionMode getTransectionModeByModeId(int channelId) {
		String query = "SELECT * FROM TRANSECTION_MODE WHERE channel_id="+channelId;
		List<TransectionMode> transectionModes = getTransectionModeByQuery(query);
		return transectionModes.size()>0?transectionModes.get(0):null;
	}

	public static List<TransectionMode> getTransectionModeByQuery(String query) {
		List<TransectionMode> transectionModes = new ArrayList<>();
		ResultSet transectionModeInDb = null;
		try{
			transectionModeInDb = jdbcTemplate.executeQuery(query);
			while(transectionModeInDb.next()){
				transectionModes.add(new TransectionMode(transectionModeInDb.getInt("channel_id"), transectionModeInDb.getString("channel_name"), 
						transectionModeInDb.getString("channel_description")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(transectionModeInDb!=null)
				try {
					transectionModeInDb.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return transectionModes;
	}
	
	public static List<TransectionMode> getAllTransectionModes() {
		return getTransectionModeByQuery( "SELECT * FROM TRANSECTION_MODE ORDER BY channel_id");
	}
}
