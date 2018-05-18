package com.example.cloud.dao;

import java.io.Serializable;

public class HiloDataImp  implements HiloData, Serializable{

	double low;
	long lowTime;
	double high;
	long highTime;
	String type;
	
		
//	public HiloDataImp(StationToolkit st,String type,Date date,double init,long time) {
//		// TODO Auto-generated constructor stub
//	}
//	
	@Override
	public boolean currentReading() {
		
		return true;
	}
	@Override
	public void newDay(double initial,long time) {
		
	}
	
	

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public long getLowTime() {
		return lowTime;
	}

	public void setLowTime(long lowTime) {
		this.lowTime = lowTime;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public long getHighTime() {
		return highTime;
	}

	public void setHighTime(long highTime) {
		this.highTime = highTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
