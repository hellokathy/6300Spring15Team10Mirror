package com.example.stallmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.gatech.seclass.prj2.Customer;
import edu.gatech.seclass.prj2.CustomerTableData.CustomerTableInfo;
import edu.gatech.seclass.prj2.DatabaseOperations;
import edu.gatech.seclass.services.CreditCardService;
import edu.gatech.seclass.services.PaymentService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

		//Get the information the user entered and create a new transaction
		date = ((EditText)findViewById(R.id.dateText)).getText().toString();
		amount = ((EditText)findViewById(R.id.amountText)).getText().toString();

		Submit = (Button) findViewById(R.id.addTransactionSubmitButton);
		DateText = (EditText) findViewById(R.id.dateText);
		DateText.setText(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));

		Submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Get the information the user entered and create a new customer
				date = ((EditText)findViewById(R.id.dateText)).getText().toString();
				amount = ((EditText)findViewById(R.id.amountText)).getText().toString();

				if( amount.length() > 0 ) {
					double amt = Double.parseDouble(amount);
					amt *= 100;
					Math.round(amt);
					amt /= 100;

					// Get dummy card info from service
					String CCInfo = CreditCardService.getCardInfo();
					if(CCInfo.equalsIgnoreCase("ERROR")) {
						Toast.makeText(getBaseContext(), "Card scan failed, try again", Toast.LENGTH_LONG).show();
					}
					else {
						CardInfo = CCInfo.split("#");
						try {
							// This is probably the point where we should figure out if the customer making a purchase
							// is eligible for any discounts
							DatabaseOperations DB = new DatabaseOperations(ctx);
							Cursor c = DB.getInfoByKey(CustomerTableInfo.USER_ID, acct);
							int isGoldInt = c.getInt(5);
							double total = c.getDouble(6);
							boolean isGold = isGoldInt != 0;
							
							if( isGold ) {
								amt *= 0.95;
								amount = Double.toString(amt);
							}

							// Process payment
							boolean paymentSuccess = PaymentService.processPayment(CardInfo[0], CardInfo[1], CardInfo[2], CardInfo[3], CardInfo[4], amt);
							if(paymentSuccess) {
								// Add to DB
								DB.EnterTransactionInfo(DB, amount, date, acct);
								Toast.makeText(ctx, "Payment Successful", Toast.LENGTH_LONG).show();
								
								total += amt;
								
								if( (total) > 1000.0 ) {
									isGold = true;
									isGoldInt = 1;
								}
								
								DB.EditCustomerInfo(DB, acct, total, isGoldInt);
								DB.close();
								
								Intent launchactivity = new Intent(ctx, MainActivity.class);
								startActivity(launchactivity);
								finish();
							}
							else {
								Toast.makeText(ctx, "Processing Error, please try again.", Toast.LENGTH_LONG).show();
							}
						}
						catch (SQLException se) {
							Toast.makeText(ctx, "Processing Error, please try again.", Toast.LENGTH_LONG).show();
						}
					}
				}
				else {
					Toast.makeText(getBaseContext(), "Please enter a dollar amount", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
