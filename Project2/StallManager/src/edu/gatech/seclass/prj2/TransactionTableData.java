package edu.gatech.seclass.prj2;

import android.provider.BaseColumns;

public class TransactionTableData {
	public TransactionTableData() { }
	
	public static class TransactionTableInfo implements BaseColumns {
		public static final String DATE = "date";
		public static final String AMOUNT = "amount";
		public static final String USER_ID = "acctnum";
		public static final String GOLD_STATUS = "isGold";
		public static final String DISCOUNT_USED = "discountUsed";
		public static final String DATABASE_NAME = "StallManagerTest422015-4.db";
		public static final String TABLE_NAME = "TransactionTable";
	}

}