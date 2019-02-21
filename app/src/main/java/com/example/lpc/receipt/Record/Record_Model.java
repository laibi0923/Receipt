package com.example.lpc.receipt.Record;
import java.util.*;

public class Record_Model{
	private String RecordName;
	private long CreateTime;
	private String Type;
	private String TotalPrice;
	private String Exchange;
	private String PayMethod;
	private String Remarks;
	private ArrayList<Record_Item_Model> zItem;

	public Record_Model(String recordName, long createTime, String type, String totalPrice, String exchange, String payMethod, String remarks, ArrayList<Record_Item_Model> zItem)
	{
		RecordName = recordName;
		CreateTime = createTime;
		Type = type;
		TotalPrice = totalPrice;
		Exchange = exchange;
		PayMethod = payMethod;
		Remarks = remarks;
		this.zItem = zItem;
	}

	
	
	
	public Record_Model()
	{}

	public void setZItem(ArrayList<Record_Item_Model> zItem)
	{
		this.zItem = zItem;
	}

	public ArrayList<Record_Item_Model> getZItem()
	{
		return zItem;
	}

	

	

	public String getRecordName() {
		return RecordName;
	}

	public void setRecordName(String recordName) {
		RecordName = recordName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}

	public String getExchange() {
		return Exchange;
	}

	public void setExchange(String exchange) {
		Exchange = exchange;
	}

	public String getPayMethod() {
		return PayMethod;
	}

	public void setPayMethod(String payMethod) {
		PayMethod = payMethod;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
}
