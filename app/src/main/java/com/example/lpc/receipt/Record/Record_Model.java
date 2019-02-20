package com.example.lpc.receipt.Record;

public class Record_Model{
	String RecordName;
	long DateLong;
	String Type;
	String TotalPrice;
	String Exchange;
	String PayMethod;
	String Remarks;

	public Record_Model(String recordName, long dateLong, String type, String totalPrice, String exchange, String payMethod, String remarks)
	{
		RecordName = recordName;
		DateLong = dateLong;
		Type = type;
		TotalPrice = totalPrice;
		Exchange = exchange;
		PayMethod = payMethod;
		Remarks = remarks;
	}

	public void setRecordName(String recordName)
	{
		RecordName = recordName;
	}

	public String getRecordName()
	{
		return RecordName;
	}

	public void setDateLong(long dateLong)
	{
		DateLong = dateLong;
	}

	public long getDateLong()
	{
		return DateLong;
	}

	public void setType(String type)
	{
		Type = type;
	}

	public String getType()
	{
		return Type;
	}

	public void setTotalPrice(String totalPrice)
	{
		TotalPrice = totalPrice;
	}

	public String getTotalPrice()
	{
		return TotalPrice;
	}

	public void setExchange(String exchange)
	{
		Exchange = exchange;
	}

	public String getExchange()
	{
		return Exchange;
	}

	public void setPayMethod(String payMethod)
	{
		PayMethod = payMethod;
	}

	public String getPayMethod()
	{
		return PayMethod;
	}

	public void setRemarks(String remarks)
	{
		Remarks = remarks;
	}

	public String getRemarks()
	{
		return Remarks;
	}
	
}
