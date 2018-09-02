package com.unimelb.swen30006.workshop5;

import java.util.ArrayList;

import com.unimelb.swen30006.workshop5.CustomerAccount.CustomerState;

public class CustomerAccount {
	public enum CustomerState {ACTIVE, PENDING, INACTIVE, DEFAULT, PRIORITY, CLOSED, SECONDCHANCE, ADMIN, HEALTHYDEBT, BADDEBT, COLLECTIONS, COLLECTED, WRITTENOFF}
	private CustomerState ACCOUNT_STATE;
	private ArrayList<Complaint> complaints;
	private ArrayList<Bill> bills;
	private ArrayList<Product> products;
	
	public CustomerAccount() {
		this.ACCOUNT_STATE = CustomerState.PENDING;
		System.out.println("Account is in Pending state");
		
		products = new ArrayList<>();
		bills = new ArrayList<>();
		complaints = new ArrayList<>();
	}
	
	public void addComplaint(Complaint newComplaint) {
		complaints.add(newComplaint);
		
		ACCOUNT_STATE = CustomerState.PRIORITY;
		System.out.println("Account is in Priority state");
	}
	
	public void removeComplaint(String type) {
		Complaint toRemove = null;
		for (Complaint complaint : complaints) {
			if (type.equals(complaint.getType())) {
				toRemove = complaint;
			}
		}
		complaints.remove(toRemove);
		
		if (complaints.size() == 0) {
			ACCOUNT_STATE = CustomerState.ACTIVE;
			System.out.println("Account is in Active state");
		}
	}
	public void payAllBills() {
		bills.clear();
		if (ACCOUNT_STATE != CustomerState.ACTIVE) {
			ACCOUNT_STATE = CustomerState.ACTIVE;
			System.out.println("Account is in Active state");
		}
	}
	
	public Product getProduct(String name) {
		for (Product product : products) {
			if (product.getProductType().equals(name)) {
				return product;
			}
		}
		return null;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public ArrayList<Bill> getBills() {
		return bills;
	}
	
	public void setState(CustomerState ACCOUNT_STATE) {
		this.ACCOUNT_STATE = ACCOUNT_STATE;
		System.out.println("Account is in Active State");
	}
	
	public void checkForDebt() {
		for (Bill bill : bills) {
			if (bill.getOutStanding() == true) {
				ACCOUNT_STATE = CustomerState.DEFAULT;
				System.out.println("Account is in Default");
			}
		}
	}
	
	public void removeAllProducts() {
		products.clear();
		if (bills.size() == 0 && products.size() == 0) {
			ACCOUNT_STATE = CustomerState.INACTIVE;
			System.out.println("Account is Inactive");
		}
	}
}
