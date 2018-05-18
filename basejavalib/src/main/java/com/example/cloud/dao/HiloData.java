package com.example.cloud.dao;

public interface HiloData {

	public boolean currentReading();

	public void newDay(double initial, long time);
}
