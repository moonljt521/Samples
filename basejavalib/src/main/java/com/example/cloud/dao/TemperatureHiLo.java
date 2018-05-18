package com.example.cloud.dao;

public class TemperatureHiLo {

	
	HiloData data;
	
	public TemperatureHiLo(DataTookKit kit) {
		// TODO Auto-generated constructor stub
		
		data = kit.getTempHiloData();
	}
}
