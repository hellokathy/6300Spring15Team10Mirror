package com.example.stallmanager;

import edu.gatech.seclass.prj2.CustomerTableData.TableInfo;
import edu.gatech.seclass.prj2.DatabaseOperations;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SelectcustomerActivity extends Activity {
	private DatabaseOperations DB;
	//private SimpleCursorAdapter SCA;
	Context ctx = this;
	String SelectedID = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectcustomer);

		DB = new DatabaseOperations(ctx);
		DB.open();
		displayListView();
	}

	private void displayListView() {
		Cursor cursor = DB.getInfo(DB);
		String[] col = new String[] {
				TableInfo.USER_ID,
				TableInfo.FIRST_NAME,
				TableInfo.LAST_NAME,
				TableInfo.ZIP,
				TableInfo.EMAIL
		};

		int[] to = new int[] {
				R.id.uid,
				R.id.fname,
				R.id.lname,
				R.id.zip,
				R.id.mail
		};

		SimpleCursorAdapter SCA = null;
		try{
				SCA = new SimpleCursorAdapter(getBaseContext(), R.layout.customer_layout, cursor, col, to, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ListView lv = (ListView)findViewById(R.id.listView1);
		lv.setAdapter(SCA);
		/*lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> lv, View v, int pos, long id) {
				Cursor cr = (Cursor)lv.getItemAtPosition(pos);
				SelectedID = cr.getString(cr.getColumnIndexOrThrow("acct"));
			}
		});*/
	}
}
