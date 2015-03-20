package com.example.stallmanager;

import edu.gatech.seclass.prj2.Manager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditCustomer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_customer);
	}
	
	public void submitEditCustomerPressed(View view){
		//Get the information the user entered and create a new customer
		String firstName = ((EditText)findViewById(R.id.firstNameText)).getText().toString();
		String lastName = ((EditText)findViewById(R.id.lastNameText)).getText().toString();
		String zip = ((EditText)findViewById(R.id.zipCodeText)).getText().toString();
		String email = ((EditText)findViewById(R.id.emailText)).getText().toString();
		Manager.getInstance().editCustomer(firstName, lastName, zip, email);
		//Switch back to the main view
		//Should we bother adding a confirmation to the user that the customer was added successfully?
		setContentView(R.layout.activity_main);
		Intent launchactivity= new Intent(EditCustomer.this, MainActivity.class);   
		startActivity(launchactivity);       
	}
}
