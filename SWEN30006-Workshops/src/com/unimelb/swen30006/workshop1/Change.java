package com.unimelb.swen30006.workshop1;

public class Change {
	private float oldValue;
	private float newValue;
	private String subjectID;
	
	public Change(float oldValue, float newValue, String subjectID) {
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.subjectID = subjectID;
	}
	
	public void printChange() {
		System.out.format("Change in " + subjectID + ":\n");
	}
	
	public float getOld() {
		return new Float(oldValue);
	}
	
	public float getNew() {
		return new Float(newValue);
	}
}
