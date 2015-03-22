package edu.gatech.seclass.prj2;

import android.provider.BaseColumns;

public class TransactionTableData {
	public TransactionTableData() { }
	
	public static abstract class TransactionTableInfo implements BaseColumns {
		public static final String DATE = "date";
		public static final String AMOUNT = "amount";
		public static final String USER_ID = "acctnum";
		public static final String DATABASE_NAME = "StallManagerTest2.db";
		public static final String TABLE_NAME = "TransactionTable";
	}

}
