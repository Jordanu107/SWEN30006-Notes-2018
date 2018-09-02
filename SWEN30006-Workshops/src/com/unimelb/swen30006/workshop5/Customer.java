package com.unimelb.swen30006.workshop5;

import java.util.ArrayList;

public class Customer {
	private String fullName;
	private String customerAddress;
	private ArrayList<CustomerAccount> customerAccounts;
	
	public Customer(String fullName, String customerAddress) {
		this.fullName = fullName;
		this.customerAddress = customerAddress;
		customerAccounts = new ArrayList<CustomerAccount>();
	}
	
	public void addAccount(CustomerAccount customerAccount) {
		customerAccounts.add(customerAccount);
	}
	
	public void complain(CustomerAccount customerAccount, String type, String message) {
		Complaint newComplaint = new Complaint(type, message);
		customerAccount.addComplaint(newComplaint);
	}
	
	public void payBill(CustomerAccount customerAccount) {
		customerAccount.payAllBills();
	}
	
	public ArrayList<CustomerAccount> getAccounts() {
		return customerAccounts;
	}
}
