package com.example.stallmanager;

import edu.gatech.seclass.prj2.DatabaseOperations;
import edu.gatech.seclass.prj2.Manager;
import edu.gatech.seclass.prj2.CustomerTableData.CustomerTableInfo;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditCustomer extends Activity {
	Button Submit;
	public static String firstName, lastName, zip, email, customerID, origID;
	Context ctx = this;
	EditText FNAME, LNAME, ZIP, EMAIL, UID = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_customer);
		
		EditText FNAME = (EditText)findViewById(R.id.firstNameTextEdit);
		EditText LNAME = (EditText)findViewById(R.id.lastNameTextEdit);
		EditText ZIP = (EditText)findViewById(R.id.zipCodeTextEdit);
		EditText EMAIL = (EditText)findViewById(R.id.emailTextEdit);
		EditText UID = (EditText)findViewById(R.id.customerIDEdit);
		customerID = origID;
		
		// Get customer info from DB and write to screen
		FNAME.setText(firstName);
		LNAME.setText(lastName);
		ZIP.setText(zip);
		EMAIL.setText(email);
		UID.setText(customerID);

		Submit = (Button) findViewById(R.id.editCustomerButton);
		Submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Get the information the user entered and create a new customer
				firstName = ((EditText)findViewById(R.id.firstNameTextEdit)).getText().toString();
				lastName = ((EditText)findViewById(R.id.lastNameTextEdit)).getText().toString();
				zip = ((EditText)findViewById(R.id.zipCodeTextEdit)).getText().toString();
				email = ((EditText)findViewById(R.id.emailTextEdit)).getText().toString();
				customerID = ((EditText)findViewById(R.id.customerIDEdit)).getText().toString();
				
				DatabaseOperations DB = new DatabaseOperations(ctx);
				//DB.deleteCustomer(DB, origID);
				DB.EditCustomerInfo(DB, firstName, lastName, zip, email, customerID);
				Toast.makeText(getBaseContext(), "Customer updated successfully", Toast.LENGTH_LONG).show();
				
				setContentView(R.layout.activity_main);
				Intent launchactivity= new Intent(EditCustomer.this, MainActivity.class);   
				startActivity(launchactivity);
			}
		});
	}

	/*public void submitEditCustomerPressed(View view){
		setContentView(R.layout.activity_main);
		Intent launchactivity= new Intent(EditCustomer.this, MainActivity.class);   
		startActivity(launchactivity);
	}*/
}
