package com.example.cloud.main;


import com.example.cloud.ui.MonitioningScreen;
import com.example.cloud.weatherMointoring.WeatherStation;

public class Main {

	public static void main(String[] args) throws Exception {
		
		
//		AlarmClock aClock = new AlarmClock();
		
		
//		TemperatureSensor tSensor = new TemperatureSensor(aClock, new NumbusTempureSensor());
		//使用factoy，只需要替换工厂就可以了，不需要在这里处理传感器的逻辑
//		AlarmClock aClock = new AlarmClock();
//		NumbusToolKit tKit = new NumbusToolKit();
//		
//		TemperatureSensor tSensor = new TemperatureSensor(tKit, aClock);
		
		//使用factory 创建相应的alarmclock
		
//		StationToolkit st = new NumbusToolKit();
//		
//		AlarmClock aClock = new AlarmClock(st);
//		
//		TemperatureSensor temperatureSensor = new TemperatureSensor(st,aClock);
		
		
		// 动态创建一个工厂
//		Class class1 = Class.forName("StationToolkit");
//		StationToolkit st = (StationToolkit) class1.newInstance();
//		AlarmClock aClock = new AlarmClock(st);
////		
//		TemperatureSensor temperatureSensor = new TemperatureSensor(st,aClock);
//		
//		
		
		MonitioningScreen uiScreen = new MonitioningScreen();
		
		
		WeatherStation station = new WeatherStation("StationToolkit");
		
		station.addTempObserver(uiScreen);
		
	}
}
