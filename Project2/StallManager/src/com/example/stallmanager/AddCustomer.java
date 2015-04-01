package com.example.stallmanager;

import com.example.stallmanager.MainActivity;

import edu.gatech.seclass.prj2.CustomerTableData.CustomerTableInfo;
import edu.gatech.seclass.prj2.DatabaseOperations;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomer extends Activity {
	Button Submit;
	EditText Acct;
	String firstName, lastName, zip, email, customerID;
	Context ctx = this;
	DatabaseOperations DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_customer);
		Acct = (EditText) findViewById(R.id.customerID);
		Submit = (Button) findViewById(R.id.submitNewCustomerButton);

		DB = new DatabaseOperations(ctx);
		int cnt = DB.count(DB, CustomerTableInfo.TABLE_NAME);
		if ( cnt > 0 ) {
			Cursor cr = DB.getLargestAcctnum(DB);
			if( !(cr.moveToLast()) ) {
				int dex = cr.getColumnIndex(CustomerTableInfo.USER_ID);
				String largestAcct = cr.getString(dex);
				int intLA = Integer.parseInt(largestAcct);
				Acct.setText(Integer.toString(intLA + 1));
			}
			else {
				Acct.setText("1");
			}
		}
		else {
			Acct.setText("1");
		}

		Submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				//Get the information the user entered and create a new customer
				firstName = ((EditText)findViewById(R.id.firstNameText)).getText().toString();
				lastName = ((EditText)findViewById(R.id.lastNameText)).getText().toString();
				zip = ((EditText)findViewById(R.id.zipCodeText)).getText().toString();
				email = ((EditText)findViewById(R.id.emailText)).getText().toString();
				customerID = ((EditText)findViewById(R.id.customerID)).getText().toString();
				
				Cursor c = DB.getInfoByKey(DB, CustomerTableInfo.USER_ID, customerID);
				if( !(c.moveToFirst()) ) {
					DB.EnterCustomerInfo(DB, firstName, lastName, zip, email, customerID);
					DB.close();
					Toast.makeText(getBaseContext(), "Customer added successfully", Toast.LENGTH_LONG).show();
					finish();
				}
				else {
					Toast.makeText(getBaseContext(), "User ID already exists, please choose another", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	public void submitNewCustomerPressed(View view){
		//Switch back to the main view
		setContentView(R.layout.activity_main);
		Intent launchactivity= new Intent(AddCustomer.this, MainActivity.class);                             
		startActivity(launchactivity);
	}
}
