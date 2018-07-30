package com.unimelb.swen30006.workshop1;

public abstract class Observer {
	public abstract void notification(Change change); // notify is a method from Object and cannot be overriden
}
