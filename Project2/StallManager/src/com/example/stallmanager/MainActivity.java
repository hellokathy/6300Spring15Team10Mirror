package com.example.stallmanager;

import edu.gatech.seclass.prj2.Manager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void newCustomerPressed(View view){
		//setContentView(R.layout.activity_add_customer);

		Intent launchactivity= new Intent(MainActivity.this, AddCustomer.class);                             
		startActivity(launchactivity);                          
	}
	
	public void editCustomerPressed(View view){
		//setContentView(R.layout.activity_edit_customer);
        	
		Intent launchactivity= new Intent(MainActivity.this, EditCustomer.class); 
		
		startActivity(launchactivity);                          
	}
}
