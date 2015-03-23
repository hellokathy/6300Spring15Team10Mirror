package com.example.stallmanager;

import com.example.stallmanager.SelecttransActivity.views;

import edu.gatech.seclass.prj2.Manager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	Context ctx = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void newCustomerPressed(View view){
		Intent launchactivity = new Intent(ctx, AddCustomer.class);
		startActivity(launchactivity);
	}
	
	public void editCustomerPressed(View view){
		Intent launchactivity = new Intent(ctx, SelectcustomerActivity.class);
		startActivity(launchactivity);
	}
	public void addTransactionPressed(View view){
		SelecttransActivity.previousView = views.ADD_TRANSACTION;
		Intent launchactivity = new Intent(ctx, AddTransaction.class);
		startActivity(launchactivity);
	}
	
	public void getCustomerInfoPressed(View view){
		Intent launchactivity = new Intent(ctx, GetCustomerInfo.class);
		startActivity(launchactivity);
	}
	public void listOfCustomersPressed(View view){
		Intent launchactivity = new Intent(ctx, ListOfCustomers.class);
		startActivity(launchactivity);
	}
	public void viewTransactionsPressed(View view){
		SelecttransActivity.previousView = views.VIEW_TRANSACTIONS;
		Intent launchactivity = new Intent(ctx, ViewTransactions.class);
		startActivity(launchactivity);
	}
}
