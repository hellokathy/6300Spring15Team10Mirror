package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.CustomerTableData.CustomerTableInfo;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SelectcustomerActivity extends Activity {
	private DatabaseOperations DB;
	private SimpleCursorAdapter SCA = null;
	Context ctx = this;
	String SelectedFN = "";
	String SelectedLN = "";
	String SelectedZIP = "";
	String SelectedEML = "";
	String SelectedID = "";
	String SelectedDISCOUNT = "";
	int ifClick=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectcustomer);

		DB = new DatabaseOperations(ctx);
		DB.open();
		displayListView();
	}
	
	public void DeletePressed(View view) {
		if (ifClick==1){
			AlertDialog.Builder bldr = new AlertDialog.Builder(ctx);
			bldr.setTitle("Confirm");
			bldr.setMessage("Do you really want to delete customer?");
			bldr.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					DB.deleteCustomer(DB, SelectedID);
					displayListView();
					dialog.dismiss();
				}
			});
			bldr.setNegativeButton("Keep", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			AlertDialog dialog = bldr.create();
			dialog.show();
			ifClick=0;
		}
		else{
			Toast.makeText(getBaseContext(), "Please choose a customer!", Toast.LENGTH_LONG).show();
		}
	}
	
	public void EditPressed(View view) {
		if (ifClick==1){
			Intent launchactivity = new Intent(ctx, EditCustomer.class);
			EditCustomer.firstName = SelectedFN;
			EditCustomer.lastName = SelectedLN;
			EditCustomer.zip = SelectedZIP;
			EditCustomer.email = SelectedEML;
			EditCustomer.origID = SelectedID;
			startActivity(launchactivity);
		}
		else{
			Toast.makeText(getBaseContext(), "Please choose a customer!", Toast.LENGTH_LONG).show();
		}
	}
	
	public void ReturnPressed(View view){		
		//Switch back to the main view
		Intent launchactivity= new Intent(SelectcustomerActivity.this, MainActivity.class);   
		startActivity(launchactivity);       
	}
	
	private void displayListView() {
		Cursor cursor = DB.getCustomerInfo(DB);
		String[] col = new String[] {
				CustomerTableInfo.USER_ID,
				CustomerTableInfo.FIRST_NAME,
				CustomerTableInfo.LAST_NAME,
				CustomerTableInfo.ZIP,
				CustomerTableInfo.EMAIL
		};

		int[] to = new int[] {
				R.id.uid,
				R.id.fname,
				R.id.lname,
				R.id.zip,
				R.id.mail
		};

		try{
			SCA = new SimpleCursorAdapter(getBaseContext(), R.layout.customer_layout, cursor, col, to, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ListView lv = (ListView)findViewById(R.id.CustList);
		lv.setAdapter(SCA);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> lv, View v, int pos, long id) {
				//Log.d("Click received: ", "Click received");
				Cursor cr = (Cursor)lv.getItemAtPosition(pos);
				SelectedFN = cr.getString(cr.getColumnIndexOrThrow(CustomerTableInfo.FIRST_NAME));
				SelectedLN = cr.getString(cr.getColumnIndexOrThrow(CustomerTableInfo.LAST_NAME));
				SelectedZIP = cr.getString(cr.getColumnIndexOrThrow(CustomerTableInfo.ZIP));
				SelectedEML = cr.getString(cr.getColumnIndexOrThrow(CustomerTableInfo.EMAIL));
				SelectedID = cr.getString(cr.getColumnIndexOrThrow(CustomerTableInfo.USER_ID));
				SelectedDISCOUNT = cr.getString(cr.getColumnIndexOrThrow(CustomerTableInfo.DISCOUNT));
				ifClick=1;
			}
		});
		
		SCA.getFilter().filter("");
		EditText filter = (EditText) findViewById(R.id.Search);
		filter.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				SCA.getFilter().filter(s.toString());
			}
			@Override				
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			@Override
			public void afterTextChanged(Editable s) { }
		});
		
		
		SCA.setFilterQueryProvider(new FilterQueryProvider() {
			@Override
			public Cursor runQuery(CharSequence constraint) {
				return DB.getInfoByKey(DB, CustomerTableInfo.LAST_NAME, constraint.toString());
			}
		});
	}
}
