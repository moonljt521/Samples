package com.example.cloud.observer;

public interface Observable {
	
	
	public void addObserver(Observer observer);
	
	public void notifyObservers(double val);
}
