package edu.gatech.seclass.prj2;

import android.provider.BaseColumns;

public class CustomerTableData {
	public CustomerTableData() { }
	
	public static class CustomerTableInfo implements BaseColumns {
		public static final String FIRST_NAME = "fname";
		public static final String LAST_NAME = "lname";
		public static final String ZIP = "zip";
		public static final String EMAIL = "email";
		public static final String USER_ID = "acctnum";
		public static final String GOLD_STATUS = "isGold";
		public static final String TOTAL_SPENT = "totalspent";
		public static final String DISCOUNT = "discount";
		public static final String DATABASE_NAME = "StallManagerTest422015-4.db";
		public static final String TABLE_NAME = "CustomerTable";
	}

}
