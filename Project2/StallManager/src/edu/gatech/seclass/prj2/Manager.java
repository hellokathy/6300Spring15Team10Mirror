package edu.gatech.seclass.prj2;

import java.util.HashMap;

//Singleton
public class Manager {
	private static HashMap<String, Customer> customers = new HashMap<String, Customer>();
	
	private static Manager instance = null;
	private Manager() {
		//Defeats instantiation
	}
	public static Manager getInstance() {
		if(instance == null){
			instance = new Manager();
		}
		return instance;
	}
	
	//Consider having this return if the addition was a success or a failure
	public void addCustomer(String firstName, String lastName, String zip, String email, String customerID){
		/*
			//Use full name + num for the key where num=number of customers with the given first name and last name
			String key = ID;
	 		int num = 1;
			while(customers.containsKey(key + String.valueOf(num))){
				num++;
			}
			key = key + String.valueOf(num);
		*/
		//Customer newCustomer = new Customer(firstName, lastName, zip, email, customerID);
		//customers.put(customerID, newCustomer);
	}
	
	public void editCustomer(String firstName, String lastName, String zip, String email, String customerID){
		Customer newCustomer = new Customer(firstName, lastName, zip, email, customerID);
		
		customers.put(customerID, newCustomer);
	}
	
	public Customer getCustomerInfo(String customerID){
		
		Customer oldCustomer=customers.get(customerID);
		return oldCustomer;
	}
}
