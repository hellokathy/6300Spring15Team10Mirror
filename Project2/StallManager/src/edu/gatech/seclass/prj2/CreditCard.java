package edu.gatech.seclass.prj2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

import android.text.format.DateFormat;

public class CreditCard {
	private String szCardholderName;
	private String szAcctnum;
	private String szSecurityCode;
	private Date dtExpireDate;
	
	public CreditCard(String name, String acct, String code, String expire) throws ParseException {
		this.szCardholderName = name;
		this.szAcctnum = acct;
		this.szSecurityCode = code;
		SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		this.dtExpireDate = format.parse(expire);
	}

}
