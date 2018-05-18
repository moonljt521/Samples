package com.example.cloud.dao;

import java.io.Serializable;
import java.util.List;

public interface PersistenImp {

	void store(String name, Serializable obj);
	
	Object retrieve(String name);
	
	List directory(String regExp);
}
