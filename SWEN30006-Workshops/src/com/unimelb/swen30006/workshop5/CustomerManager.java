package com.unimelb.swen30006.workshop5;

import java.util.Date;

import com.unimelb.swen30006.workshop5.CustomerAccount.CustomerState;

public class CustomerManager {
	public static void main(String args[]) {
		// Create new customer and customer account associated with them
		Customer customer1 = new Customer("Jordan Ung", "123 Fake St");
		CustomerAccount account1 = new CustomerAccount();
		customer1.addAccount(account1);
		System.out.println("+-----------------------");
		
		// Add a product to their customer account
		Product phoneDeal = new Product("Optus Student Deal");
		grantApproval(account1, phoneDeal);
		System.out.println("-+----------------------");
		
		// Optus network going down as per usual... time to call the customer service
		customer1.complain(account1, "Service Issue", "Optus get your shit together");
		System.out.println("--+---------------------");
		
		// They finally fixed the problem, hooray!
		account1.removeComplaint("Service Issue");
		System.out.println("---+--------------------");
		
		// Woops... forgot to pay the phone bill
		addBill(account1, 99.95, new Date(10000000), new Date());
		addBill(account1, 99.95, new Date(10000), new Date(1000202));
		System.out.println("----+-------------------");
		
		// Should pay my bills
		account1.payAllBills();
		System.out.println("-----+------------------");
		
		// Maybe I'm too broke for a plan
		account1.removeAllProducts();
		System.out.println("------+-----------------");
		
		
	}
	
	public static void grantApproval(CustomerAccount customerAccount, Product product) {
		customerAccount.getProducts().add(product);
		customerAccount.setState(CustomerState.ACTIVE);
	}
	
	public static void addBill(CustomerAccount customerAccount, double price, Date date1, Date date2) {
		if (date2.before(new Date())) {
			customerAccount.getBills().add(new Bill(price, date1, date2, true));
		} else {
			customerAccount.getBills().add(new Bill(price, date1, date2, false));
		}
		customerAccount.checkForDebt();
	}
	
}
