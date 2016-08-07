package com.Client;

import java.io.*;
import java.net.*;

import com.Constants.*;

public class HotelClientHOPP {

  protected Socket mySocket;
  protected BufferedReader reader;
  protected PrintStream writer;

  public HotelClientHOPP(String server) throws UnknownHostException,
      IOException {

    InetAddress address = InetAddress.getByName(server);
    mySocket = null;
    InputStream inStream = null;
    OutputStream outStream = null;

    mySocket = new Socket(address, HotelConstants.BROKERPORT);
    inStream = mySocket.getInputStream();
    outStream = mySocket.getOutputStream();

    reader = new BufferedReader(new InputStreamReader(inStream));
    writer = new PrintStream(outStream);
  }

  public void quit() {
    try {
      writer.print(HotelConstants.QUIT
          + HotelConstants.CR_LF);
      reader.close();
      writer.close();
      mySocket.close();
    } catch (Exception e) {
      // ignore
    }
  }
  
  public boolean logIn(String userID, String psw){
	  writer.print(HotelConstants.LOGIN + " " + userID + " " + psw
		        + HotelConstants.CR_LF);
	  String response = null;
	    try {
	      response = reader.readLine();
	    } catch (IOException e) {
	      return false;
	    }
	    if (response.startsWith(HotelConstants.SUCCEEDED)) {
	      return true;
	    } else {
	      return false;
	    }
  }

  public boolean register(String userID, String psw){
	  writer.print(HotelConstants.REGISTER + " " + userID + " " + psw
		        + HotelConstants.CR_LF);
	  String response = null;
	    try {
	      response = reader.readLine();
	    } catch (IOException e) {
	      return false;
	    }
	    if (response.startsWith(HotelConstants.SUCCEEDED)) {
	      return true;
	    } else {
	      return false;
	    }
  }
  
  public String query (){
	  writer.print(HotelConstants.QUERY
		        + HotelConstants.CR_LF);
	  String str = "";
	  while (true) {
	      try {
	        str = reader.readLine();
	  	  return str;
	      } catch (IOException e) {
	        return HotelConstants.ERROR;
	      }
	  }
  }
  
  public String queryhotel (String line){
	  writer.print(HotelConstants.HOTEL + " " + line
		        + HotelConstants.CR_LF);
	  String respond = "";
	  while (true) {
	      try {
	        respond = reader.readLine();
	  	  return respond;
	      } catch (IOException e) {
	        return HotelConstants.ERROR;
	      }
	  }
  }
  
  public String book (String str){
	  writer.print(HotelConstants.BOOK + " " + str
		        + HotelConstants.CR_LF);
	  String respond = "";
	  while (true) {
	      try {
	        respond = reader.readLine();
	  	  return respond;
	      } catch (IOException e) {
	        return HotelConstants.ERROR;
	      }
	  }
  }
  
  public String order (String str){
	  writer.print(HotelConstants.ORDER + " " + str
		        + HotelConstants.CR_LF);
	  String respond = "";
	  while (true) {
	      try {
	        respond = reader.readLine();
	  	  return respond;
	      } catch (IOException e) {
	        return HotelConstants.ERROR;
	      }
	  }
  }
  
  public String manage (String str){
	  writer.print(HotelConstants.MANAGE + " " + str
		        + HotelConstants.CR_LF);
	  String respond = "";
	  while (true) {
	      try {
	        respond = reader.readLine();
	  	  return respond;
	      } catch (IOException e) {
	        return HotelConstants.ERROR;
	      }
	  }
  }
  
  public String delete (String orderid){
	  writer.print(HotelConstants.DELETE + " " + orderid
		        + HotelConstants.CR_LF);
	  String respond = "";
	  while (true) {
	      try {
	        respond = reader.readLine();
	  	  return respond;
	      } catch (IOException e) {
	        return HotelConstants.ERROR;
	      }
	  }
  }
}

