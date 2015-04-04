package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.MainActivity;
import edu.gatech.seclass.prj2.CustomerTableData.CustomerTableInfo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomer extends Activity {
	Button Submit;
	EditText Acct;
	Context ctx = this;
	DatabaseOperations DB;
	String firstName="", lastName="", zip="", email="", customerID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_customer);
		Acct = (EditText) findViewById(R.id.customerID);
		DB = new DatabaseOperations(ctx);
		Cursor cr = DB.getLargestAcctnum(DB);	
		
		if( (cr.moveToLast()) ) {
			int dex = cr.getColumnIndex(CustomerTableInfo.USER_ID);
			String largestAcct = cr.getString(dex);
			int intLA = Integer.parseInt(largestAcct);
			Acct.setText(Integer.toString(intLA + 1));
		}
		else {
			Acct.setText("1");
		}
	}
	
	public void submitNewCustomerPressed(View view){
		//Get the information the user entered and create a new customer
		firstName = ((EditText)findViewById(R.id.firstNameText)).getText().toString();
		lastName = ((EditText)findViewById(R.id.lastNameText)).getText().toString();
		zip = ((EditText)findViewById(R.id.zipCodeText)).getText().toString();
		email = ((EditText)findViewById(R.id.emailText)).getText().toString();
		customerID = ((EditText)findViewById(R.id.customerID)).getText().toString();
		
		if( firstName.equals("") || lastName.equals("") ) {
			Toast.makeText(getBaseContext(), "Please enter FULL NAME!", Toast.LENGTH_LONG).show();
		}
		else if( zip.equals("") || (zip.length() != 5 && zip.length() != 9 && zip.length() != 10) ) {
			Toast.makeText(getBaseContext(), "Please enter full ZIP!", Toast.LENGTH_LONG).show();
		}
		else if( email.equals("") || !email.contains("@") ) {
			Toast.makeText(getBaseContext(), "Please enter EMAIL!", Toast.LENGTH_LONG).show();
		}
		else{
			Cursor c = DB.getInfoByKey(DB, CustomerTableInfo.USER_ID, customerID);
			if( !(c.moveToFirst()) ) {
				DB.EnterCustomerInfo(DB, firstName, lastName, zip, email, customerID);
				DB.close();
				Toast.makeText(getBaseContext(), "Customer added successfully!", Toast.LENGTH_LONG).show();
				//Switch back to the main view
				Intent launchactivity= new Intent(AddCustomer.this, MainActivity.class);   
				startActivity(launchactivity);  
			}
			else {
				// Show popup but do not switch back to main screen
				Toast.makeText(getBaseContext(), "User ID already exists, please choose another!", Toast.LENGTH_LONG).show();
			}
		}
	}
	
	public void addCustomerReturnPressed(View view){		
		//Switch back to the main view
		Intent launchactivity= new Intent(AddCustomer.this, MainActivity.class);   
		startActivity(launchactivity);       
	}
}

