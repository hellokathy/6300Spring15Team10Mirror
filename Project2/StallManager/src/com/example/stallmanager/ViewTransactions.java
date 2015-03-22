package com.example.stallmanager;

import edu.gatech.seclass.prj2.DatabaseOperations;
import edu.gatech.seclass.prj2.TransactionTableData.TransactionTableInfo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ViewTransactions extends Activity {
	private DatabaseOperations DB;
	//private SimpleCursorAdapter SCA;
	Context ctx = this;
	public static String acct;
	String SelectedID = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_transactions);

		DB = new DatabaseOperations(ctx);
		DB.open();
		displayListView();
	}

	private void displayListView() {
		Cursor cursor = DB.getTransactionInfo(DB);
		String[] col = new String[] {
				TransactionTableInfo.DATE,
				TransactionTableInfo.AMOUNT
		};

		int[] to = new int[] {
				R.id.date,
				R.id.amount,
		};

		SimpleCursorAdapter SCA = null;
		try{
				SCA = new SimpleCursorAdapter(getBaseContext(), R.layout.transaction_layout, cursor, col, to, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ListView lv = (ListView)findViewById(R.id.transactionList);
		lv.setAdapter(SCA);
		
	}
}
