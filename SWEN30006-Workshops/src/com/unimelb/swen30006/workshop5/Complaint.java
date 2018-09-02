package com.unimelb.swen30006.workshop5;

public class Complaint {
	private String type;
	private String message;

	public Complaint(String type, String message) {
		this.type = type;
		this.message = message;
	}
	
	public String getType() {
		return type;
	}
}
