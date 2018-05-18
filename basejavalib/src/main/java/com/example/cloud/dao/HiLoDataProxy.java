package com.example.cloud.dao;

//使用代理把持久化部分，和业务部分分离开
public class HiLoDataProxy implements HiloData{

	public HiloDataImp imp;
	transient String storeageKey;
	
	transient PersistenImp persistenImp;

	@Override
	public boolean currentReading() {
		// TODO Auto-generated method stub
		boolean change;
		change = imp.currentReading();
		
		if(change) {
			store();
		}
		
		return change;
	}

	@Override
	public void newDay(double initial, long time) {
		// TODO Auto-generated method stub
		store();
		imp.newDay(initial, time);
		store();
	}
	
	
	public void store() {
		
	}
	

}
