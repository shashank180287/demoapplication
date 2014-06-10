package com.mobile.tool.stock.manager.entity;

public class RecordKeeping {

	private int recordId;
	private String type;
	private String tag;
	private String comment;

	public RecordKeeping() {
	}

	public RecordKeeping(int recordId, String type, String tag, String comment) {
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
	
}
