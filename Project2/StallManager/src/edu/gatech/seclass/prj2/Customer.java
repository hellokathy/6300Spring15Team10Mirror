package edu.gatech.seclass.prj2;

public class Customer {

	private String firstName;
	private String lastName;
	private String zip;
	private String email;
	private boolean isGold = false;
	private double AmtSpentCalYr = 0;
	private double cumulativeDiscount = 0;
	private String customerID;
	
	public Customer(String firstName, String lastName, String zip, String email, String customerID){
		this.firstName = firstName;
		this.lastName = lastName;
		this.zip = zip;
		this.email = email;
		this.customerID = customerID;
		
	}
	
}