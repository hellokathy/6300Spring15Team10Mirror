package edu.gatech.seclass.prj2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GetCustomerInfo extends Activity {
	Button Submit;
	private TextView myTextView;
	Context ctx = this;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_customer_info);
		
	    Submit = (Button) findViewById(R.id.submitGetCustomerInfoButton);
		
		Submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DatabaseOperations DB = new DatabaseOperations(ctx);
				Cursor cursor=DB.getCustomerInfo(DB);
				String inquiryID = ((EditText)findViewById(R.id.customerID)).getText().toString();
				cursor.moveToFirst();
					do{
					String customerID=cursor.getString(cursor.getColumnIndex("acctnum"));
					if(inquiryID.equals(customerID)){
						String fname=cursor.getString(cursor.getColumnIndex("fname"));
						String lname=cursor.getString(cursor.getColumnIndex("lname"));
						String zip=cursor.getString(cursor.getColumnIndex("zip"));
						String email=cursor.getString(cursor.getColumnIndex("email"));
						
						myTextView=(TextView)findViewById(R.id.cName);
						myTextView.setText("Name: "+fname+" "+lname);
						myTextView=(TextView)findViewById(R.id.cZip);
						myTextView.setText("Zip: "+zip);		
						myTextView=(TextView)findViewById(R.id.cEmail);
 						myTextView.setText("Email: "+email);	
 						DB.close();
 						Toast.makeText(getBaseContext(), "Customer found successfully!", Toast.LENGTH_LONG).show();
 						return;
					}
					else{
						myTextView=(TextView)findViewById(R.id.cName);
						myTextView.setText("Name: ");
						myTextView=(TextView)findViewById(R.id.cZip);
						myTextView.setText("Zip: ");		
						myTextView=(TextView)findViewById(R.id.cEmail);
 						myTextView.setText("Email: ");	
					}
				}while(cursor.moveToNext());
					DB.close();
					Toast.makeText(getBaseContext(), "Customer NOT found!", Toast.LENGTH_LONG).show();
			}
		});
	}	     
	
	public void returnGetCustomerInfo(View view){		
		//Switch back to the main view
		//setContentView(R.layout.activity_main);
		Intent launchactivity= new Intent(GetCustomerInfo.this, MainActivity.class);   
		startActivity(launchactivity);       
	}
}
