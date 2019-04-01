package com.example.lpc.receipt.Public;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Change_Date
{
	private long ms; 
	 
	private String formateDateMask;
	
	private Calendar getDate_Calendar = Calendar.getInstance();

	public Change_Date(){} 
	
	public String parseToDateString(Long ms, String dateformat_mask){

		String DateStringRest = "";

		getDate_Calendar.setTimeInMillis(ms);

		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(dateformat_mask);

		DateStringRest = mSimpleDateFormat.format(getDate_Calendar.getTime());

		return DateStringRest;
	}
	
	
	
	/*
	 * 負責計算日差
	 */
	public int getDate_Diff(long TargetDate, long StartDate){
		
		// 計算開始日期與本日之間日差 定義為當前顯示頁面 Current_Position
    	// one_day_ms 為計算一日毫秒，用作計算日差, 必須定義為 long
     	long one_day_ms = 24 * 60 * 60 * 1000;
		
		int day_diff = 0;
		
		day_diff = (int) ((TargetDate - StartDate) / (one_day_ms));
		
		return day_diff;
	}
	
}
