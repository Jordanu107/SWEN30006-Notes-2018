package com.unimelb.swen30006.workshop1;

public class RealObserver extends Observer {
	private String observerName;
	
	public RealObserver(String observerName) {
		this.observerName = observerName;
	}
	
	@Override
	public void notification(Change change) {
		change.printChange();
		System.out.format("OLD: " + change.getOld() + "\nNEW: " + change.getNew() + "\n" + observerName + " has been notified!\n--------\n");
	}

}
