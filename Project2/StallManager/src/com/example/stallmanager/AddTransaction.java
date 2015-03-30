package com.example.stallmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.gatech.seclass.prj2.DatabaseOperations;
import edu.gatech.seclass.services.CreditCardService;
import edu.gatech.seclass.services.PaymentService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTransaction extends Activity {
	
	Button Submit;
	EditText DateText;
	String date, amount;
	String[] CardInfo;
	public static String acct;
	Context ctx = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		
		
		Submit = (Button) findViewById(R.id.addTransactionSubmitButton);
		DateText = (EditText) findViewById(R.id.dateText);
		DateText.setText(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
		
		Submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Get dummy card info from service
				String CCInfo = CreditCardService.getCardInfo();
				if(CCInfo.equalsIgnoreCase("ERROR")) {
					Toast.makeText(ctx, "Credit Card Reading error, please retry card...", Toast.LENGTH_LONG).show();
				}
				else {
					CardInfo = CCInfo.split("#");
					
					// Get the information the user entered and create a new customer
					date = ((EditText)findViewById(R.id.dateText)).getText().toString();
					amount = ((EditText)findViewById(R.id.amountText)).getText().toString();
					double amt = Double.parseDouble(amount);
					
					// Process payment
					boolean paymentSuccess = PaymentService.processPayment(CardInfo[0], CardInfo[1], CardInfo[2], CardInfo[3], CardInfo[4], amt);
					if(paymentSuccess) {
						// Add to DB
						try {
							DatabaseOperations DB = new DatabaseOperations(ctx);
							DB.EnterTransactionInfo(DB, amount, date, acct);
							Toast.makeText(ctx, "Payment Successful", Toast.LENGTH_LONG).show();
							DB.close();
							Intent launchactivity = new Intent(ctx, MainActivity.class);
							startActivity(launchactivity);
							finish();
						}
						catch (SQLException se) {
							Toast.makeText(ctx, "Processing Error, please try again.", Toast.LENGTH_LONG).show();
						}
					}
					else {
						Toast.makeText(ctx, "Processing Error, please try again.", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
	}
}
