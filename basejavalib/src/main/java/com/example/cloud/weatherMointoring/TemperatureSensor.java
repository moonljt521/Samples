package com.example.cloud.weatherMointoring;


import com.example.cloud.api.StationToolkit;
import com.example.cloud.api.TemperatureSensorImpl;
import com.example.cloud.observer.Observable;
import com.example.cloud.observer.Observer;

//温度传感器
//写一个模版
//可以使用桥接模式，把具体的sensor实现（read）给抽离出来
public  class TemperatureSensor implements Observable {

	//添加一个工厂
	StationToolkit factory;
	
	Observer tempObserver;
	
	AlarmClock alarmClock;
	
	double lastReading;
	
	TemperatureSensorImpl sensorImpl;
	
	public TemperatureSensor(StationToolkit factory,AlarmClock clock) {
		// TODO Auto-generated constructor stub
	
		this.factory = factory;
		setAlarmClock(alarmClock);

		sensorImpl = this.factory.makeTemperature();
	}
	
	public TemperatureSensor(AlarmClock alarmClock,TemperatureSensorImpl sensorImpl) {
		// TODO Auto-generated constructor stub
		
		this.sensorImpl = sensorImpl;
		
		setAlarmClock(alarmClock);
		
	}



	public void setAlarmClock(AlarmClock alarmClock) {
		this.alarmClock = alarmClock;

		this.alarmClock.setAlarmListener(new AlarmListener() {
			
			@Override
			public void wakeup() {
				// TODO Auto-generated method stub
				TemperatureSensor.this.wakeup();
			}
		});
	}




	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		this.tempObserver = observer;
	}

	@Override
	public void notifyObservers(double val) {
		// TODO Auto-generated method stub
		
		this.tempObserver.update(val);
	}

	//唤醒
//	@Override
	public void wakeup() {
		// TODO Auto-generated method stub
		check();
	}
	
	
	public void check() {
		double val = read();
		
		if(val != lastReading) {
			
			lastReading = val;
			
			setChanged();
			notifyObservers(val);
		}
	}
	
	public void setChanged() {
		
		
	}
	
	
	//这个方法可以写成抽象方法
	public double read() {
		
		
		return sensorImpl.read();
		
	}

}
