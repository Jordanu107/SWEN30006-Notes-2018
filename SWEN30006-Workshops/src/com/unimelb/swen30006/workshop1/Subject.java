package com.unimelb.swen30006.workshop1;

import java.util.ArrayList;

public abstract class Subject {
	private float currentValue = 0;
	
	public abstract void registerObserver(Observer observer);
	public abstract void deregsterObserver(Observer observer);
	public abstract void notifyAll(Change change, ArrayList<Observer> observers);
	public abstract Change newUpdate(float oldValue, float newValue, String subjectID);
	public abstract void updateValue(float newValue);
	
	public float getValue() {
		return new Float(this.currentValue);
	}
	
	public void setValue(float newValue) {
		currentValue = newValue;
	}
}
