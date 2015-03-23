package com.example.stallmanager;

import edu.gatech.seclass.prj2.DatabaseOperations;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTransaction extends Activity {
	
	Button Submit;
	String date, amount;
	public static String acct;
	Context ctx = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		
		
		Submit = (Button) findViewById(R.id.addTransactionSubmitButton);
		
		Submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//Get the information the user entered and create a new customer
				date = ((EditText)findViewById(R.id.dateText)).getText().toString();
				amount = ((EditText)findViewById(R.id.amountText)).getText().toString();
				
				DatabaseOperations DB = new DatabaseOperations(ctx);
				DB.EnterTransactionInfo(DB, amount, date, acct);
				DB.close();
				Toast.makeText(getBaseContext(), "Transaction added successfully", Toast.LENGTH_LONG).show();
				Intent launchactivity = new Intent(ctx, MainActivity.class);
				startActivity(launchactivity);
				finish();
			}
		});
	}
}
