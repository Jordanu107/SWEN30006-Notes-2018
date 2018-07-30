package com.unimelb.swen30006.workshop1;

public class Main {
	public static void main(String args[]) {
		RealSubject subject1 = new RealSubject("Apple");
		RealSubject subject2 = new RealSubject("Banana");
		
		RealObserver observer1 = new RealObserver("Minxian");
		RealObserver observer2 = new RealObserver("Marko");
		
		subject1.registerObserver(observer1);
		subject1.registerObserver(observer2);
		
		subject2.registerObserver(observer1);
		
		subject1.updateValue(1.2f);
		subject1.updateValue(6.1f);
		
		subject2.updateValue(3.14f);
		
	}
}
