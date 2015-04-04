package edu.gatech.seclass.prj2;

import java.text.SimpleDateFormat;
import java.util.Date;
import edu.gatech.seclass.prj2.CustomerTableData.CustomerTableInfo;
import edu.gatech.seclass.services.CreditCardService;
import edu.gatech.seclass.services.EmailService;
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
import android.widget.TextView;
import android.widget.Toast;

public class AddTransaction extends Activity {
	
	Button Submit;
	EditText DateText;
	TextView DiscountText;
	String date, amount;
	String[] CardInfo;
	public static String acct;
	public static String email;
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
		DiscountText = (TextView) findViewById(R.id.discountBalance);
		
		DatabaseOperations DB = new DatabaseOperations(ctx);
		Cursor c = DB.getInfoByKey(DB, CustomerTableInfo.USER_ID, acct);
		int ddex = c.getColumnIndex(CustomerTableInfo.DISCOUNT);
		double discount = c.getDouble(ddex);
		DiscountText.setText(String.valueOf(discount));

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
							Cursor c = DB.getInfoByKey(DB, CustomerTableInfo.USER_ID, acct);
							int gdex = c.getColumnIndex(CustomerTableInfo.GOLD_STATUS);
							int tdex = c.getColumnIndex(CustomerTableInfo.TOTAL_SPENT);
							int ddex = c.getColumnIndex(CustomerTableInfo.DISCOUNT);
							int isGoldInt = c.getInt(gdex);
							double total = c.getDouble(tdex);
							double discount = c.getDouble(ddex);
							double discUsed = 0;
							boolean isGold = isGoldInt != 0; 
							double goldApplied = 0;
							
							//Apply gold discount
							if( isGold ) {
								goldApplied = amt*.05;
								amt *= 0.95;
							}
							
							//Apply existing discount
							if(discount > 0 && amt <= discount){
								//Customer has enough discount to pay for the full transaction
								discount = discount - amt;
								discUsed = amt;
								amt = 0;
							}
							else{
								//Discount can be fully used
								amt = amt - discount;
								discUsed = discount;
								discount = 0;
							}

							amount = Double.toString(amt);


							// Process payment
							boolean paymentSuccess = PaymentService.processPayment(CardInfo[0], CardInfo[1], CardInfo[2], CardInfo[3], CardInfo[4], amt);
							if(paymentSuccess) {
								// Add to DB
								DB.EnterTransactionInfo(DB, amount, date, acct, goldApplied, discUsed);
								Toast.makeText(ctx, "Payment Successful", Toast.LENGTH_LONG).show();

								total += amt;

								if(amt >= 100){
									//Customer spent at least $100, so they get a $10 discount next time
									boolean mailSent = EmailService.sendEmail(email, "Discount received!", "Congratulations! You will" + 
											"receive a $10 credit which will be automatically applied to your next purchase.");

									if( !(mailSent) ) {
										mailSent = EmailService.sendEmail(email, "Discount received!", "Congratulations! You will" + 
												"receive a $10 credit which will be automatically applied to your next purchase.");
									}

									if( !(mailSent) ) {
										mailSent = EmailService.sendEmail(email, "Discount received!", "Congratulations! You will" + 
												"receive a $10 credit which will be automatically applied to your next purchase.");
									}
									else {
										Toast.makeText(ctx, "Email could not be sent...", Toast.LENGTH_LONG).show();
									}

									discount += 10;
								}

								if( (total) >= 1000.0 ) {
									if(!isGold){
										//Customer just transitioned to gold 
										boolean mailSent = EmailService.sendEmail(email, "Gold status reached!", "Congratulations! You will" + 
												"receive a 5% discount on all future purchases.");

										if( !(mailSent) ) {
											mailSent = EmailService.sendEmail(email, "Gold status reached!", "Congratulations! You will" + 
													"receive a 5% discount on all future purchases.");
										}

										if( !(mailSent) ) {
											mailSent = EmailService.sendEmail(email, "Gold status reached!", "Congratulations! You will" + 
													"receive a 5% discount on all future purchases.");
										}
										else {
											Toast.makeText(ctx, "Email could not be sent...", Toast.LENGTH_LONG).show();
										}
									}
									isGold = true;
									isGoldInt = 1;
								}

								DB.EditCustomerInfo(DB, acct, total, isGoldInt, discount);
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
