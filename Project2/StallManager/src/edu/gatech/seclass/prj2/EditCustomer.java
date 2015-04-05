package edu.gatech.seclass.prj2;

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

	public void ReturnPressed(View view){		
		//Switch back to the main view
		Intent launchactivity= new Intent(EditCustomer.this, SelectcustomerActivity.class);   
		startActivity(launchactivity);       
	}
	
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
				DatabaseOperations DB = new DatabaseOperations(ctx); 
				
				// Get the information the user entered and create a new customer
				firstName = ((EditText)findViewById(R.id.firstNameTextEdit)).getText().toString();
				lastName = ((EditText)findViewById(R.id.lastNameTextEdit)).getText().toString();
				zip = ((EditText)findViewById(R.id.zipCodeTextEdit)).getText().toString();
				email = ((EditText)findViewById(R.id.emailTextEdit)).getText().toString();
				String newCustomerID = ((EditText)findViewById(R.id.customerIDEdit)).getText().toString();

				
				if( firstName.equals("") || lastName.equals("") ) {
					Toast.makeText(getBaseContext(), "Please enter FULL NAME!", Toast.LENGTH_LONG).show();
				}
				else if( zip.equals("") || (zip.length() != 5 && zip.length() != 9 && zip.length() != 10) ) {
					Toast.makeText(getBaseContext(), "Please enter FULL ZIP!", Toast.LENGTH_LONG).show();
				}
				else if( email.equals("") || !email.contains("@") ) {
					Toast.makeText(getBaseContext(), "Please enter CORRECT EMAIL!", Toast.LENGTH_LONG).show();
				} 
//				else if(!customerID.equals(newCustomerID)){
//					Toast.makeText(getBaseContext(), "CUSTOMER ID is not allowed to be changed", Toast.LENGTH_LONG).show();
//				}
				else{
					Cursor c = DB.getInfoByKey(DB, CustomerTableInfo.USER_ID, newCustomerID);
	 				if( !(c.moveToFirst()) || (customerID.equals(newCustomerID)) ) {
						DB.EditCustomerInfo(DB, firstName, lastName, zip, email, origID, newCustomerID);
	 					Toast.makeText(getBaseContext(), "Customer updated successfully", Toast.LENGTH_LONG).show();
						Intent launchactivity = new Intent(ctx, SelectcustomerActivity.class);
						startActivity(launchactivity);
	 					finish();
	 				}
	 				else {
						Toast.makeText(getBaseContext(), "User ID already exists, please choose another", Toast.LENGTH_LONG).show();
					}
				}
				return;
			}
		});
	}
}
