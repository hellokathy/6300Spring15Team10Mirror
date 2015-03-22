package com.example.stallmanager;

import edu.gatech.seclass.prj2.DatabaseOperations;
import edu.gatech.seclass.prj2.Manager;
import edu.gatech.seclass.prj2.CustomerTableData.TableInfo;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
	EditText FNAME, LNAME, ZIP, EMAIL, UID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_customer);
		DatabaseOperations DB = new DatabaseOperations(ctx);
		
		FNAME = (EditText)findViewById(R.id.firstNameText);
		LNAME = (EditText)findViewById(R.id.lastNameText);
		ZIP = (EditText)findViewById(R.id.zipCodeText);
		EMAIL = (EditText)findViewById(R.id.emailText);
		UID = (EditText)findViewById(R.id.customerID);
		
		Bundle b = getIntent().getExtras();
		String ID = "";
		if(b != null) {
			ID = b.getString("KEY");
		}
		
		// Get customer info from DB and write to screen
		/*Cursor c = DB.getInfoByKey(TableInfo.USER_ID, ID);
		c.moveToFirst();
		FNAME.setText(c.getString(1));
		LNAME.setText(c.getString(2));
		ZIP.setText(c.getString(3));
		EMAIL.setText(c.getString(4));
		UID.setText(c.getString(0));*/

		Submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// Get the information the user entered and create a new customer
				firstName = FNAME.getText().toString();
				lastName = LNAME.getText().toString();
				zip = ZIP.getText().toString();
				email = EMAIL.getText().toString();
				customerID = UID.getText().toString();
				Submit = (Button) findViewById(R.id.submitNewCustomerButton);
				
				DatabaseOperations DB = new DatabaseOperations(ctx);
				DB.deleteCustomer(DB, customerID);
				DB.EnterInfo(DB, firstName, lastName, zip, email, customerID);
				Toast.makeText(getBaseContext(), "Customer updated successfully", Toast.LENGTH_LONG).show();
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
