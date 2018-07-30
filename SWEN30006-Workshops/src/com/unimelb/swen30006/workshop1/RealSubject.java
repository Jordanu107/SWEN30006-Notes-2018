package com.unimelb.swen30006.workshop1;

import java.util.ArrayList;

public class RealSubject extends Subject {
	private String subjectID;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public RealSubject(String subjectID) {
		this.subjectID = subjectID;
	}

	@Override
	public void registerObserver(Observer observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
	}

	@Override
	public void deregsterObserver(Observer observer) {
		// TODO Auto-generated method stub
		observers.remove(observer);
	}

	@Override
	public void notifyAll(Change change, ArrayList<Observer> observers) {
		// TODO Auto-generated method stub
		for (Observer observe : observers) {
			observe.notification(change);
		}
	}

	@Override
	public Change newUpdate(float oldValue, float newValue, String subjectID) {
		
		return new Change(oldValue, newValue, subjectID);
	}

	@Override
	public void updateValue(float newValue) {
		// Value has changed, need to update and make a new Change and tell all subscribers
		if (this.getValue() != newValue) {
			notifyAll(newUpdate(this.getValue(), newValue, subjectID), observers);
			setValue(newValue);
		}
	}
	
	public String getSubject() {
		return new String (this.subjectID);
	}
}
