package edu.gatech.seclass.prj2;

import android.provider.BaseColumns;

public class CustomerTableData {
	public CustomerTableData() { }
	
	public static abstract class TableInfo implements BaseColumns {
		public static final String FIRST_NAME = "fname";
		public static final String LAST_NAME = "lname";
		public static final String ZIP = "zip";
		public static final String EMAIL = "email";
		public static final String USER_ID = "accctnum";
		public static final String DATABASE_NAME = "Customers";
		public static final String TABLE_NAME = "CustomerTable";
	}

}
