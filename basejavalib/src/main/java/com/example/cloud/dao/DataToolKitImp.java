package com.example.cloud.dao;

public class DataToolKitImp implements DataTookKit{

	@Override
	public HiloData getTempHiloData() {
		// TODO Auto-generated method stub
		
		HiLoDataProxy proxy = new HiLoDataProxy();
		
		proxy.imp = new HiloDataImp();
		
		return proxy;
	}

}
