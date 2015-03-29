package com.example.stallmanager;

import edu.gatech.seclass.prj2.DatabaseOperations;
import edu.gatech.seclass.services.CreditCardService;
import edu.gatech.seclass.services.PaymentService;
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
		
	}
	
	public void submitNewTransactionPressed(View view){
		//Get the information the user entered and create a new transaction
		date = ((EditText)findViewById(R.id.dateText)).getText().toString();
		amount = ((EditText)findViewById(R.id.amountText)).getText().toString();
		
		String scanCard = CreditCardService.getCardInfo();
		if(scanCard == "Error"){
			Toast.makeText(getBaseContext(), "Card scan failed, try again", Toast.LENGTH_LONG).show();
		}
		else{
	    String [] toProcess = scanCard.split("#");
	    boolean processSuccessful = PaymentService.processPayment(toProcess[0], toProcess[1], toProcess[2], toProcess[3], toProcess[4], Double.valueOf(amount));
	    if(processSuccessful){
	    //Credit card was processed successfully
	    	
	    //TODO: This is probably the point where we should figure out if the customer making a purchase
	    //		is eligible for any discounts
		DatabaseOperations DB = new DatabaseOperations(ctx);
		DB.EnterTransactionInfo(DB, amount, date, acct);
		DB.close();
		Toast.makeText(getBaseContext(), "Transaction added successfully", Toast.LENGTH_LONG).show();
		Intent launchactivity = new Intent(ctx, MainActivity.class);
		startActivity(launchactivity);
		finish();
	    }
	    else{
	    //Payment processing failed
		Toast.makeText(getBaseContext(), "Payment processing failed, try again", Toast.LENGTH_LONG).show();
	    }
		}

	}
}
