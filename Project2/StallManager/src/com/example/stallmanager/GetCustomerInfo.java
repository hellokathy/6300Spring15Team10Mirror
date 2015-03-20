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

public class GetCustomerInfo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_customer_info);
	}
	
	public void submitGetCustomerInfoPressed(View view){	
		String customerID = ((EditText)findViewById(R.id.customerID)).getText().toString();
		Manager.getInstance().getCustomerInfo(customerID);
		//Switch back to the main view
		setContentView(R.layout.activity_main);
		Intent launchactivity= new Intent(GetCustomerInfo.this, MainActivity.class);   
		startActivity(launchactivity);       
	}
}
