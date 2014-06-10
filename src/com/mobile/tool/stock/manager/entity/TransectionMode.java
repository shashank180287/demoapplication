package com.mobile.tool.stock.manager.entity;

public class TransectionMode {

	private int channelId;
	private String channelName;
	private String channelDescription;
	
	public TransectionMode() {
	}
	
	public TransectionMode(int channelId, String channelName,
			String channelDescription) {
		super();
		this.channelId = channelId;
		this.channelName = channelName;
		this.channelDescription = channelDescription;
	}

	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelDescription() {
		return channelDescription;
	}
	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
	}
	@Override
	public String toString() {
		return "TransectionMode [channelId=" + channelId + ", channelName="
				+ channelName + ", channelDescription=" + channelDescription
				+ "]";
	}
 
}
