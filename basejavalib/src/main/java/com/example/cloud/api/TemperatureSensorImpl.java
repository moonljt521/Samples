package com.example.cloud.api;

//可以使用桥接模式，把具体的sensor实现（read）给抽离出来
//可以不用关注很多实现，让业务更加紧凑-srp
public interface TemperatureSensorImpl {

	
	public double read();
}
