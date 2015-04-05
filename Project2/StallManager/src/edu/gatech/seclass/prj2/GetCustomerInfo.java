package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.CustomerTableData.CustomerTableInfo;
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
	    		String acct = ((EditText)findViewById(R.id.customerID)).getText().toString();
	    	//	cursor.moveToFirst();
	    		if( cursor.moveToFirst() ) {
	    			do{
	    				String customerID=cursor.getString(cursor.getColumnIndex("acctnum"));
	    				if(customerID.equals("")){
	    					Toast.makeText(getBaseContext(), "Please enter customer ID!", Toast.LENGTH_LONG).show();
	    					return;
	    				}
	    				else if(acct.equals(customerID)){
	    					Cursor c = DB.getInfoByKey(DB, CustomerTableInfo.USER_ID, acct);
	    					String fname=cursor.getString(cursor.getColumnIndex("fname"));
	    					String lname=cursor.getString(cursor.getColumnIndex("lname"));
	    					String zip=cursor.getString(cursor.getColumnIndex("zip"));
	    					String email=cursor.getString(cursor.getColumnIndex("email"));
	    					int isGoldInt = c.getInt(c.getColumnIndex(CustomerTableInfo.GOLD_STATUS));
	    					double total = c.getDouble(c.getColumnIndex(CustomerTableInfo.TOTAL_SPENT));
	    					double discount = c.getDouble(c.getColumnIndex(CustomerTableInfo.DISCOUNT));					

	    					myTextView=(TextView)findViewById(R.id.cName);
	    					myTextView.setText(fname+" "+lname);
	    					myTextView=(TextView)findViewById(R.id.cZip);
	    					myTextView.setText(zip);		
	    					myTextView=(TextView)findViewById(R.id.cEmail);
	    					myTextView.setText(email);	
	    					myTextView=(TextView)findViewById(R.id.cGoldStatus);
	    					if (isGoldInt==1){
	    						myTextView.setText("Yes");	
	    					}
	    					else{
	    						myTextView.setText("No");
	    					}
	    					myTextView=(TextView)findViewById(R.id.cDiscount);
	    					myTextView.setText(""+discount);
	    					myTextView=(TextView)findViewById(R.id.cTotalSpent);
	    					myTextView.setText(""+total);
	    					DB.close();
	    					Toast.makeText(getBaseContext(), "Customer found successfully!", Toast.LENGTH_LONG).show();
	    					return;
	    				}
	    			}while(cursor.moveToNext());
	    			
		    		myTextView=(TextView)findViewById(R.id.cName);
		    		myTextView.setText("");
		    		myTextView=(TextView)findViewById(R.id.cZip);
		    		myTextView.setText("");		
		    		myTextView=(TextView)findViewById(R.id.cEmail);
		    		myTextView.setText("");	
		    		myTextView=(TextView)findViewById(R.id.cGoldStatus);
		    		myTextView.setText("");	
		    		myTextView=(TextView)findViewById(R.id.cDiscount);
		    		myTextView.setText("");
		    		myTextView=(TextView)findViewById(R.id.cTotalSpent);
		    		myTextView.setText("");
		    		DB.close();
		    		Toast.makeText(getBaseContext(), "Customer NOT found!", Toast.LENGTH_LONG).show();
		    		return;
	    		}
	    		else{
	    			Toast.makeText(getBaseContext(), "Empty database!", Toast.LENGTH_LONG).show();
	    		}
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
