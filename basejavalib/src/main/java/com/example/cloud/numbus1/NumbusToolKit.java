package com.example.cloud.numbus1;


import com.example.cloud.api.AlarmClockImp;
import com.example.cloud.api.StationToolkit;
import com.example.cloud.api.TemperatureSensorImpl;

public class NumbusToolKit implements StationToolkit {

	@Override
	public TemperatureSensorImpl makeTemperature() {
		// TODO Auto-generated method stub
		
		return new NumbusTempureSensor();
	}
	
	@Override
	public AlarmClockImp getAlarmClock() {
		// TODO Auto-generated method stub
		return new NumbusAlarmClock();
	}

}
