package com.example.stallmanager;

import edu.gatech.seclass.prj2.DatabaseOperations;
import edu.gatech.seclass.prj2.Manager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
	public static String firstName, lastName, zip, email, customerID;
	Context ctx = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_customer);
		
		//Fill in the text fields with the current information for the customer being edited
		((EditText)findViewById(R.id.firstNameText)).setText(firstName);
		((EditText)findViewById(R.id.lastNameText)).setText(lastName);
		((EditText)findViewById(R.id.zipCodeText)).setText(zip);
		((EditText)findViewById(R.id.emailText)).setText(email);

		Submit = (Button) findViewById(R.id.submitEditCustomerButton);

		Log.d("Customer ID Edit: ", String.valueOf(customerID));
		Log.d("Edit: ", "Waiting for click");
		Submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("Edit: ", "Click received");
				//Get the information the user entered and create a new customer
				firstName = ((EditText)findViewById(R.id.firstNameText)).getText().toString();
				lastName = ((EditText)findViewById(R.id.lastNameText)).getText().toString();
				zip = ((EditText)findViewById(R.id.zipCodeText)).getText().toString();
				email = ((EditText)findViewById(R.id.emailText)).getText().toString();
				DatabaseOperations DB = new DatabaseOperations(ctx);
				DB.EditInfo(DB, firstName, lastName, zip, email, customerID);
				Toast.makeText(getBaseContext(), "Customer edited successfully", Toast.LENGTH_LONG).show();
				finish();
			}
		});
	}

	public void submitEditCustomerPressed(View view){
		setContentView(R.layout.activity_main);
		Intent launchactivity= new Intent(EditCustomer.this, MainActivity.class);   
		startActivity(launchactivity);       
	}
}
