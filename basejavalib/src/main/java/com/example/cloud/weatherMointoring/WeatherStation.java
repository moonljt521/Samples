package com.example.cloud.weatherMointoring;


import com.example.cloud.api.StationToolkit;
import com.example.cloud.observer.Observer;

public class WeatherStation {

	TemperatureSensor ts;

	public WeatherStation(String tkNmae) throws Exception {
		// TODO Auto-generated constructor stub
		
		Class class1 = Class.forName("StationToolkit");
		StationToolkit st = (StationToolkit) class1.newInstance();
		AlarmClock aClock = new AlarmClock(st);
//		
		this.ts = new TemperatureSensor(st,aClock);
//		
	}
	
	public void addTempObserver(Observer o) {
		
		this.ts.addObserver(o);
	}
}
