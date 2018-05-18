package com.example.cloud.numbus1;


import com.example.cloud.api.AlarmClockImp;
import com.example.cloud.api.ClockListener;

//具体的clock实现
public class NumbusAlarmClock implements AlarmClockImp {

	ClockListener lisenter;
	
	@Override
	public void register(ClockListener listener) {
		// TODO Auto-generated method stub
		this.lisenter = listener;
	}
	
	//定时器的实现
	public void scheduler() {
		
		this.lisenter.tic();
	}

}
