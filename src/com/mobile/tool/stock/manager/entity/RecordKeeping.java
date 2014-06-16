package com.mobile.tool.stock.manager.entity;

import java.util.ArrayList;
import java.util.List;

public class RecordKeeping {

	private Integer recordId;
	private String type;
	private String tag;
	private String comment;
	public static String[] tableColumnNames = new String[] {"Tag", "Comment", "Attached"};
	
	public RecordKeeping() {
	}

	public RecordKeeping(Integer recordId, String type, String tag, String comment) {
		super();
		this.recordId = recordId;
		this.type = type;
		this.tag = tag;
		this.comment = comment;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RecordKeeping [recordId=" + recordId + ", type=" + type
				+ ", tag=" + tag + ", comment=" + comment + "]";
	}
	
	public static String[][] getTableModel(List<RecordKeeping> recordKeepings) {
		List<String[]> tableModel = new ArrayList<>();
		for (RecordKeeping recordKeeping : recordKeepings) {
			String tag = recordKeeping.getTag();
			String[] record = new String[]{recordKeeping.getType(), recordKeeping.getComment(), (tag!=null && tag.contains("."))?tag.substring(0, tag.lastIndexOf(".")):""};
			tableModel.add(record);
		}
		 String[][] tableModelArray = new String[tableModel.size()][];
		 tableModel.toArray(tableModelArray);
		return tableModelArray;
	}
}
