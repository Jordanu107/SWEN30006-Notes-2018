/**
 * @author jordanung
 * @contact jordanu@student.unimelb.edu.au
 * @version 31/07/18
 */

package com.unimelb.swen30006.workshop1;

public class Main {
	public static void main(String args[]) {
		
		// Create subjects
		RealSubject subject1 = new RealSubject("Apple");
		RealSubject subject2 = new RealSubject("Banana");
		
		// Create objects
		RealObserver observer1 = new RealObserver("Minxian");
		RealObserver observer2 = new RealObserver("Marko");
		
		// Register observers
		subject1.registerObserver(observer1);
		subject1.registerObserver(observer2);
		subject2.registerObserver(observer1);
		
		// Update value of Subjects
		subject1.updateValue(1.2f);
		subject1.updateValue(6.1f);
		subject2.updateValue(3.14f);
		
	}
}
