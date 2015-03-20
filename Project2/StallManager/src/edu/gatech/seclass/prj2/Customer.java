package edu.gatech.seclass.prj2;

import java.util.ArrayList;

import android.R;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import edu.gatech.seclass.prj2.Manager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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
	
	public String getFName() {
		return this.szFName;
	}
	
	public void setLName(String lastname) {
		this.szLName = lastname;
	}
	
	public String getLName() {
		return this.szLName;
	}
	
	public void setZip(String zip) {
		this.szZip = zip;
	}
	
	public String getZip() {
		return this.szZip;
	}
	
	public void setEmail(String email) {
		this.szEmail = email;
	}
	
	public String getEmail() {
		return this.szEmail;
	}
	
	public String getCustomerID() {
		return this.szCustomerID;
	}
	
	public void setCumDiscount(double amt) {
		this.dblCumulativeDiscount = amt;
	}
	
}
