package com.example.stallmanager;

import edu.gatech.seclass.prj2.Customer;
import edu.gatech.seclass.prj2.Manager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GetCustomerInfo extends Activity {

	private TextView myTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_customer_info);
	}
	
	public void submitGetCustomerInfoPressed(View view){	
		String customerID = ((EditText)findViewById(R.id.customerID)).getText().toString();
		
		Customer oldCustomer=Manager.getInstance().getCustomerInfo(customerID);
		myTextView=(TextView)findViewById(R.id.cName);
		myTextView.setText("Name: "+oldCustomer.getFName()+""+oldCustomer.getLName());
		myTextView=(TextView)findViewById(R.id.cZip);
		myTextView.setText("Zip: "+oldCustomer.getZip());		
		myTextView=(TextView)findViewById(R.id.cEmail);
		myTextView.setText("Email: "+oldCustomer.getEmail());		
		     
	}
	
	public void returnGetCustomerInfo(View view){		
		//Switch back to the main view
		setContentView(R.layout.activity_main);
		Intent launchactivity= new Intent(GetCustomerInfo.this, MainActivity.class);   
		startActivity(launchactivity);       
	}
}
