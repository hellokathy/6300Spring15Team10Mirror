package com.example.stallmanager;

import edu.gatech.seclass.prj2.DatabaseOperations;
import edu.gatech.seclass.prj2.Manager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditCustomer extends Activity {
	Button Submit;
	String firstName, lastName, zip, email, customerID;
	Context ctx = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_customer);
		
		//Get the information the user entered and create a new customer
		firstName = ((EditText)findViewById(R.id.firstNameText)).getText().toString();
		lastName = ((EditText)findViewById(R.id.lastNameText)).getText().toString();
		zip = ((EditText)findViewById(R.id.zipCodeText)).getText().toString();
		email = ((EditText)findViewById(R.id.emailText)).getText().toString();
		customerID = ((EditText)findViewById(R.id.customerID)).getText().toString();
		Submit = (Button) findViewById(R.id.submitNewCustomerButton);

		Submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DatabaseOperations DB = new DatabaseOperations(ctx);
				DB.EnterInfo(DB, firstName, lastName, zip, email, customerID);
				Toast.makeText(getBaseContext(), "Customer added successfully", Toast.LENGTH_LONG).show();
				finish();
			}
		});
	}

	public void submitEditCustomerPressed(View view){
		//Get the information the user entered and create a new customer
		String firstName = ((EditText)findViewById(R.id.firstNameText)).getText().toString();
		String lastName = ((EditText)findViewById(R.id.lastNameText)).getText().toString();
		String zip = ((EditText)findViewById(R.id.zipCodeText)).getText().toString();
		String email = ((EditText)findViewById(R.id.emailText)).getText().toString();	
		String customerID = ((EditText)findViewById(R.id.customerID)).getText().toString();
		Manager.getInstance().editCustomer(firstName, lastName, zip, email,customerID);
		//Switch back to the main view
		//Should we bother adding a confirmation to the user that the customer was added successfully?
		setContentView(R.layout.activity_main);
		Intent launchactivity= new Intent(EditCustomer.this, MainActivity.class);   
		startActivity(launchactivity);       
	}
}
