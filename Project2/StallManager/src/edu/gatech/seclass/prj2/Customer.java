package edu.gatech.seclass.prj2;

import java.util.ArrayList;

public class Customer {

	private String szFName;
	private String szLName;
	private String szZip;
	private String szEmail;
	private String szCustomerID;
	private boolean bIsGold = false;
	private double dblAmtSpentCalYr = 0;
	private double dblCumulativeDiscount = 0;
	private ArrayList<Transaction> aTransactions = null;
	private ArrayList<CreditCard> aCreditCards = null;
	
	public Customer(String firstName, String lastName, String zip, String email, String customerID){
		this.szFName = firstName;
		this.szLName = lastName;
		this.szZip = zip;
		this.szEmail = email;
		this.szCustomerID = customerID;
	}
	
	public void addTransaction(Transaction trans) {
		if(this.aTransactions == null)
			this.aTransactions = new ArrayList<Transaction>();
		
		this.aTransactions.add(trans);
	}
	
	public void addCard(CreditCard card) {
		if(this.aCreditCards == null)
			this.aCreditCards = new ArrayList<CreditCard>();
		
		this.aCreditCards.add(card);
	}
	
	public void setFName(String firstname) {
		this.szFName = firstname;
	}
	
	public void setLName(String lastname) {
		this.szLName = lastname;
	}
	
	public void setZip(String zip) {
		this.szZip = zip;
	}
	
	public void setEmail(String email) {
		this.szEmail = email;
	}
	
	public void setCumDiscount(double amt) {
		this.dblCumulativeDiscount = amt;
	}
	
}
