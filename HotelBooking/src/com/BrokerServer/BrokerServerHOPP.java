package com.BrokerServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

import com.Constants.HotelConstants;
import com.DAO.*;

public class BrokerServerHOPP {
	
	protected Socket mySocket;
	protected BufferedReader reader1;
	protected PrintStream writer1;

	public String book (String str) throws Exception{
		  InetAddress address = InetAddress.getByName("localhost");
		  mySocket = null;
		  InputStream inStream = null;
		  OutputStream outStream = null;
		
		  int port = getbookPort(str);
		  if (port == -1) {
			  return HotelConstants.ERROR;
		  } else {
			  mySocket = new Socket(address, port);
			  inStream = mySocket.getInputStream();
			  outStream = mySocket.getOutputStream();
			
			  reader1 = new BufferedReader(new InputStreamReader(inStream));
			  writer1 = new PrintStream(outStream);
			  
			  writer1.print(str + HotelConstants.CR_LF);
			  System.out.println("Request to Hotel Server: " + str + HotelConstants.CR_LF);
			  
			  String respond = "";
			  while (true) {
			      try {
			        respond = reader1.readLine();
			        System.out.println("Response from Hotel Server: " + respond);
			        writer1.close();  
			        reader1.close();  
			        mySocket.close();  
			  	    return respond;
			      } catch (IOException e) {
			    	System.out.println(e);
			        return HotelConstants.ERROR;
			      }
			  }
		 }  
	  }
	
	
	private int getbookPort(String str) throws Exception {
		// TODO Auto-generated method stub
		return dbmanage.getbookPort(str);
	}


	public String order (String str) throws Exception{
		  InetAddress address = InetAddress.getByName("localhost");
		  mySocket = null;
		  InputStream inStream = null;
		  OutputStream outStream = null;
		
		  int port = getorderPort(str);
		  if (port == -1) {
			  return HotelConstants.ERROR;
		  } else {
			  mySocket = new Socket(address, port);
			  inStream = mySocket.getInputStream();
			  outStream = mySocket.getOutputStream();
			
			  reader1 = new BufferedReader(new InputStreamReader(inStream));
			  writer1 = new PrintStream(outStream);
			  
			  writer1.print(str + HotelConstants.CR_LF);
			  System.out.println("Request to Hotel Server: " + str + HotelConstants.CR_LF);
			  
			  String respond = "";
			  while (true) {
			      try {
			        respond = reader1.readLine();
			        System.out.println("Response from Hotel Server: " + respond);
			        writer1.close();  
			        reader1.close();  
			        mySocket.close();  
			  	    return respond;
			      } catch (IOException e) {
			    	System.out.println(e);
			        return HotelConstants.ERROR;
			      }
			  }
		  }
	  }
	    
	private int getorderPort(String str) throws Exception {
		// TODO Auto-generated method stub
		return dbmanage.getorderPort(str);
	}

	public String delete (String orderid) throws Exception{
		  InetAddress address = InetAddress.getByName("localhost");
		  mySocket = null;
		  InputStream inStream = null;
		  OutputStream outStream = null;
		
		  int port = getdeletePort(orderid);
		  if (port == -1) {
			  return HotelConstants.ERROR;
		  } else {
			  mySocket = new Socket(address, port);
			  inStream = mySocket.getInputStream();
			  outStream = mySocket.getOutputStream();
			
			  reader1 = new BufferedReader(new InputStreamReader(inStream));
			  writer1 = new PrintStream(outStream);
			  
			  writer1.print(orderid + HotelConstants.CR_LF);
			  System.out.println("Request to Hotel Server: " + orderid + HotelConstants.CR_LF);
			  
			  
			  String respond = "";
			  while (true) {
			      try {
			        respond = reader1.readLine();
			        System.out.println("Response from Hotel Server: " + respond);
			        writer1.close();  
			        reader1.close();  
			        mySocket.close();  
			  	    return respond;
			      } catch (IOException e) {
			        return HotelConstants.ERROR;
			      }
			  }
		  }
	}
	
	private int getdeletePort(String orderid) throws Exception {
		// TODO Auto-generated method stub
		return dbmanage.getdeletePort(orderid);
	}

	
	
	DBManage dbmanage = new DBManage();
	
	public boolean logIn(String str) throws Exception {
		return dbmanage.logIn(str);
	}

	public boolean register (String str) throws Exception {
		return dbmanage.register(str);
	}
	
	public String query () throws Exception{
		return dbmanage.query();
	} 
	
	public String queryhotel (String line) throws Exception{
		return dbmanage.queryhotel(line);
	} 
	
	public String manage (String str) throws Exception{
		return dbmanage.manage(str);
	} 
}
