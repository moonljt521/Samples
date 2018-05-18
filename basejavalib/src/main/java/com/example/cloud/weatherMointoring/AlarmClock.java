package com.example.cloud.weatherMointoring;


import com.example.cloud.api.AlarmClockImp;
import com.example.cloud.api.ClockListener;
import com.example.cloud.api.StationToolkit;

//定义一个时钟调度
public class AlarmClock implements ClockListener {

	StationToolkit stationToolkit;
	
	AlarmClockImp clockImp;
	
	AlarmListener alarmListener;
	
	public AlarmClock() {
		// TODO Auto-generated constructor stub
	}
	
	public AlarmClock(StationToolkit stationToolkit) {
		// TODO Auto-generated constructor stub
		
		
		this.stationToolkit = stationToolkit;
		
		this.clockImp = this.stationToolkit.getAlarmClock();
		
		this.clockImp.register(this);
	}

	@Override
	public void tic() {
		// TODO Auto-generated method stub
		//分发消息
		this.alarmListener.wakeup();
	}
	
	
	
	

	public AlarmListener getAlarmListener() {
		return alarmListener;
	}



	public void setAlarmListener(AlarmListener alarmListener) {
		this.alarmListener = alarmListener;
	}

//
//
//	
//	
//	
//	/**
//	 * 
//	 * @param interval,时间
//	 * @param listener
//	 */
//	public void wakeEvery(int interval,AlarmListener listener) {
//		
//		
//	}
//
//	@Override
//	public void wakeup() {
//		// TODO Auto-generated method stub
//		
//	}
}
