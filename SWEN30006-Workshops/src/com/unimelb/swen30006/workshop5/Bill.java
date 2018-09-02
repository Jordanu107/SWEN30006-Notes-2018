package com.unimelb.swen30006.workshop5;

import java.util.Date;

public class Bill {
	private double price;
	private Date dateIssued;
	private Date dateDueBy;
	private boolean isOutStanding;
	
	public Bill(double price, Date dateIssued, Date dateDueBy, boolean isOutStanding) {
		this.price = price;
		this.dateIssued = dateIssued;
		this.dateDueBy = dateDueBy;
		this.isOutStanding = isOutStanding;
	}
	
	public boolean getOutStanding() {
		return isOutStanding;
	}
}