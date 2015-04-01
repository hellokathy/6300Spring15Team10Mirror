package com.example.stallmanager;

import edu.gatech.seclass.prj2.CustomerTableData.CustomerTableInfo;
import edu.gatech.seclass.prj2.DatabaseOperations;
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
import android.widget.TextView;
import android.widget.Toast;

public class SelectCustomerForTransactionActivity extends Activity {
	private DatabaseOperations DB;
	private SimpleCursorAdapter SCA = null;
	
	//Rather than have a separate XML for Add and View transactions, we simply keep track of which
	//button brought us to the transaction list
	public enum transactionViews{ADD_TRANSACTION, VIEW_TRANSACTIONS};
	public static transactionViews prevView;
	
	Context ctx = this;
	String SelectedFN = "";
	String SelectedLN = "";
	String SelectedZIP = "";
	String SelectedEML = "";
	String SelectedID = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectcustomerfortransaction);
		
		TextView title = (TextView)findViewById(R.id.transactionLabel);
		
		if(prevView == transactionViews.ADD_TRANSACTION){
			title.setText("Choose the customer making a purchase:");
		}
		else if(prevView == transactionViews.VIEW_TRANSACTIONS){
			title.setText("Choose a customer to view their transactions:");
		}

		DB = new DatabaseOperations(ctx);
		DB.open();
		displayListView();
	}
	
	public void selectPressed(View view) {
		if(prevView == transactionViews.ADD_TRANSACTION){
			AddTransaction.acct = SelectedID;
			Intent launchactivity = new Intent(ctx, AddTransaction.class);
			startActivity(launchactivity);
		}
		else if (prevView == transactionViews.VIEW_TRANSACTIONS){
			ViewTransactions.acct = SelectedID;
			Intent launchactivity = new Intent(ctx, ViewTransactions.class);
			startActivity(launchactivity);
		}
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
			}
		});
		
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
