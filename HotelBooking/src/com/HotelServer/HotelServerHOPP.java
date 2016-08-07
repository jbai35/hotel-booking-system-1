package com.HotelServer;

import com.DAO.*;

public class HotelServerHOPP {
	
	DBManage dbmanage = new DBManage();
	
	public String book (String str) throws Exception{
		return dbmanage.book(str);
	}
	
	public boolean order (String str) throws Exception{
		return dbmanage.order(str);
	}
	
	public boolean delete (String str) throws Exception{
		return dbmanage.delete(str);
	} 
}
