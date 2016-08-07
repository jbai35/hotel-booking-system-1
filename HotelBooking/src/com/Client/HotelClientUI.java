package com.Client;

import java.io.*;

import com.Constants.HotelConstants;

public class HotelClientUI {

  protected BufferedReader console;
  protected HotelClientHOPP clientHOPP;
  
  String userID = null;
  String psw = null;

  public static void main(String[] args) {

  if (args.length != 1) {
      System.err.println("Usage: Client address");
      System.exit(1);
  }
      HotelClientUI ui = new HotelClientUI(args[0]);
      ui.loop();
  }

  public HotelClientUI(String server) {

    clientHOPP = null;
    try {
      clientHOPP = new HotelClientHOPP(server);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    console = new BufferedReader(new InputStreamReader(System.in));
  }

  public void loop() {
    while (true) {
      String line = null;
      try {
        System.out.print("************Welcome to use Online Hotel Booking System************\n" + 
        		         "****                                                          ****\n" + 
                         "**** Please enter corresponding number for your request:      ****\n" + 
                         "**** 1: LogIn                                                 ****\n" +
                         "**** 2: Register                                              ****\n" +
                         "**** 3: Quit                                                  ****\n" +
                         "******************************************************************\n");
        System.out.print("\n");
        line = console.readLine();
        System.out.print("\n");
      } catch (IOException e) {
        clientHOPP.quit();
        e.printStackTrace();
        System.exit(1);
      }

      if (line.equalsIgnoreCase("1")) {
        logIn();
      } else if (line.equalsIgnoreCase("2")) {
        register();
      } else if (line.equalsIgnoreCase("3")) {
        clientHOPP.quit();
        System.exit(0);
      } else {
    	System.out.print("******************************************************************\n" );
        System.out.print("****                   Unrecognised command!                  ****\n" );
        System.out.print("******************************************************************\n" );
        System.out.print("\n");
      }
    }
  }

  /**
   * Given that the string starts with the prefix, get rid of the prefix and any
   * whitespace
   */
  public String losePrefix(String str, String prefix) {
    int index = prefix.length();
    String ret = str.substring(index).trim();
    return ret;
  }

  public void logIn() {
	  while (true) {

	      try {
	        System.out.print("****************************** LogIn *****************************\n" + 
	        		         "****                                                          ****\n" + 
	                         "**** Please enter your userID:                                ****\n" );
	        userID = console.readLine();
	        System.out.print("******************************************************************\n" + 
   		                     "****                                                          ****\n" + 
                             "**** Please enter your password:                              ****\n" );
            psw = console.readLine();
            System.out.print("******************************************************************\n" );
            System.out.print("\n");
	      } catch (IOException e) {
	        clientHOPP.quit();
	        e.printStackTrace();
	        System.exit(1);
	      }
	      if (userID.equals("") || userID.equals(" ") || psw.equals("") || psw.equals(" ")) {
	    	    System.out.print("******************************************************************\n" );
		    	System.out.print("**** The userID or password can't be empty, please try again! ****\n" );
		    	System.out.print("******************************************************************\n" );
		    	System.out.print("\n");
		    	loop();
	      } else if (clientHOPP.logIn(userID, psw)) {
	    	System.out.print("******************************************************************\n" );
	        System.out.print("************************** LogIn Success! ************************\n" );
	        System.out.print("******************************************************************\n" );
	        System.out.print("\n");
	        homepage();
	        }  else {
	        System.out.print("******************************************************************\n" );
	        System.out.print("********************* LogIn Failed! Try Again! *******************\n" );
	        System.out.print("******************************************************************\n" );
	        System.out.print("\n");
	        loop();
	        }
	  }
  }
  
  public void  register() {
	  while (true) {
	      String userID = null;
	      String psw1 = null;
	      String psw2 = null;
	      try {
	        System.out.print("**************************** Register ****************************\n" + 
	        		         "****                                                          ****\n" + 
	                         "**** Please set your userID:                                  ****\n" );
	        userID = console.readLine();
	        System.out.print("******************************************************************\n" + 
   		                     "****                                                          ****\n" + 
                             "**** Please set your password:                                ****\n" );
            psw1 = console.readLine();
            System.out.print("******************************************************************\n" + 
	                         "****                                                          ****\n" + 
                             "**** Please confirm your password:                            ****\n" );
            psw2 = console.readLine();
            System.out.print("******************************************************************\n" );
            System.out.print("\n");
	      } catch (IOException e) {
	        clientHOPP.quit();
	        e.printStackTrace();
	        System.exit(1);
	      }
	      if (userID.equals("") || userID.equals(" ") || psw1.equals("") || psw1.equals(" ")) {
	    	System.out.print("******************************************************************\n" );
	    	System.out.print("**** The userID or password can't be empty, please try again! ****\n" );
	    	System.out.print("******************************************************************\n" );
	    	System.out.print("\n");
	    	register();
	      }
	      else if (!psw1.equals(psw2)) {
	    	System.out.print("******************************************************************\n" );
	    	System.out.print("****    The two passwords are not same, please try again!     ****\n" );
	    	System.out.print("******************************************************************\n" );
	    	System.out.print("\n");
	    	register();
	      }
	      if (clientHOPP.register(userID, psw1)) {
	    	System.out.print("******************************************************************\n" );
	        System.out.print("************************ Register Success! ***********************\n" );
	        System.out.print("******************************************************************\n" );
	        System.out.print("\n");
	        loop();
	        }  else {
	        System.out.print("******************************************************************\n" );
	        System.out.print("******************* Register Failed! Try Again! ******************\n" );
	        System.out.print("******************************************************************\n" );
	        System.out.print("\n");
	        loop();
	        }
	  }
  }
  
  public void homepage() {
	  while (true) {
	      String line = null;
	      try {
	        System.out.print("******************************************************************\n" +
	                         "************Welcome to use Online Hotel Booking System************\n" + 
	        		         "****                                                          ****\n" + 
	                         "**** Please enter corresponding number for your request:      ****\n" + 
	                         "**** 1: Query                                                 ****\n" +
	                         "**** 2: Booking Hotel                                         ****\n" +
	                         "**** 3: OrderManagement                                       ****\n" +
	                         "**** 4: LogOut                                                ****\n" +
	                         "******************************************************************\n");
	        System.out.print("\n");
	        line = console.readLine();
	        System.out.print("\n");
	      } catch (IOException e) {
	        clientHOPP.quit();
	        e.printStackTrace();
	        System.exit(1);
	      }

	      if (line.equalsIgnoreCase("1")) {
	        query();
	      } else if (line.equalsIgnoreCase("2")) {
	        book();
	      } else if (line.equalsIgnoreCase("3")) {
		    manage();
		  } else if (line.equalsIgnoreCase("4")) {
	        loop();
	      } else {
	    	System.out.print("******************************************************************\n" );
	        System.out.print("****                   Unrecognised command!                  ****\n" +
	        		         "******************************************************************\n");
	        System.out.print("\n");
	        homepage();
	      }
	    }
  }

  //list all available cities
  public void query() {
	  String str = clientHOPP.query();
	  String[] strstr = str.split(" ");
      if (str.equals(HotelConstants.ERROR)){
    	  System.out.print("******************************************************************\n" );
    	  System.out.print("****                     404 NOT FOUND!                       ****\n" );
    	  System.out.print("******************************************************************\n" );
    	  System.out.print("\n");
    	  homepage();
      } else {
    	  System.out.print("******************************************************************\n" );
	      System.out.print("************Welcome to use Online Hotel Booking System************\n" + 
			               "****                                                          ****\n" + 
	                       "**** This is the list of cities which are currently serviced, ****\n" + 
	                       "**** Please enter corresponding number for more information!  ****\n" );
	      for (int i = strstr.length - 1; i >= 0; i--) {
		    System.out.println("**** " + strstr[i]);
		  } 
	      System.out.print("******************************************************************\n" );
	      System.out.print("\n");
		  queryhotel();
	  }

  }
  
  //list all available hotels in selected city
  public void queryhotel (){
	  String line = null;
	  try {
		line = console.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  if (line.equals("0001") || line.equals("0002") || line.equals("0003")){
		  String respond = clientHOPP.queryhotel(line);
		  String[] respond1 = respond.split(":");
		  String cityname = respond1[0];
		  String respond2 = respond1[1];
		  String[] hotel = respond2.split(" ");
		      System.out.print("\n");
		      System.out.print("******************************************************************\n" );
			  System.out.print("************Welcome to use Online Hotel Booking System************\n" + 
		                       "****                                                          ****\n" + 
		                       "**** This is the list of hotels located in " + cityname + "          ****\n" +
		                       "|** HotelID  HotelName SingleRoom DoubleRoom   VIP             **|\n");
		  for (int i = hotel.length - 1; i >= 0; i--) {
			    System.out.println(hotel[i]);
			  } 
		      System.out.print("******************************************************************\n" );
		      System.out.print("****** [Enter 1 to Book] **************** [Enter 2 to Back] ******\n" );
		      System.out.print("******************************************************************\n" );
		      System.out.print("\n");
		  String line2 = null;
		  try {
			line2 = console.readLine();
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
	      if (line2.equalsIgnoreCase("1")) {
	    	    System.out.print("\n");
		        book();
		      } else if (line2.equalsIgnoreCase("2")) {
		    	System.out.print("\n");
		    	homepage();
		      } else {
		      System.out.print("\n");
		      System.out.print("******************************************************************\n" );
		      System.out.print("****                   Unrecognised command!                  ****\n" +
	     		               "******************************************************************\n");
		      System.out.print("\n");
	          homepage();
		      }
		  }
	  else {
		      System.out.print("\n");
		      System.out.print("******************************************************************\n" );
		      System.out.print("****               Wrong cityID, please try again!            ****\n" +
	                           "******************************************************************\n");
		      System.out.print("\n");
		      query();
	  }
  } 
  
  //check vacancy and make order
  public void book(){
	  String line = null;
	  String line2 = null;
	  while (true) {
		  System.out.print("******************************************************************\n" );
	      System.out.print("************Welcome to use Online Hotel Booking System************\n" + 
			               "****                                                          ****\n" + 
	                       "**** Please enter request to make your booking                ****\n" +
	                       "**** Format:                                                  ****\n" +
	                       "**** \"hotelid,roomtype(A:SingleRoom; B:DoubleRoom; C:VIP)\"    ****\n" +
	                       "******************************************************************\n" +
	                       "**** Enter \"query\" to query the hotel information.            ****\n" );
	      System.out.print("******************************************************************\n" );
	      System.out.print("\n");
	      try {
	  		line = console.readLine();
		  	} catch (IOException e) {
		  		// TODO Auto-generated catch block
		  		e.printStackTrace();
		  	}
	      String[] line1validate = line.split(",");
	      if (line.equals("query")){
	    	  System.out.print("\n");
	    	  query();
	      } else if(line1validate.length != 2){
	    	  System.out.print("\n");
	    	  System.out.print("******************************************************************\n" );
	    	  System.out.print("****      The format of information you entered is wrong!     ****\n" +
                               "******************************************************************\n");
	    	  System.out.print("\n");
              book();
	      } else {
		      String roomid = clientHOPP.book(line);
		      if (roomid.equals(HotelConstants.ERROR)){
		    	  System.out.print("\n");
		    	  System.out.print("******************************************************************\n" );
			      System.out.print("****        Guest room is full, please choose again!          ****\n" );
			      System.out.print("******************************************************************\n" );
			      System.out.print("\n");
			      book();
		      } else {
		    	  System.out.print("\n");
		    	  System.out.print("******************************************************************\n" );
			      System.out.print("**** The hotel room you are requesting now is available!      ****\n" +
			    		           "**** Please enter your information to complete the order.     ****\n" +
			    		           "**** Format:                                                  ****\n" +
			                       "**** \"username,indate,outdate,phone,card\"                     ****\n");
			      System.out.print("******************************************************************\n" );
			      System.out.print("\n");
		      try {
			  		line2 = console.readLine();
				  } catch (IOException e) {
				  		// TODO Auto-generated catch block
				  		e.printStackTrace();
				  }
		      
		      String[] line2validate = line2.split(",");
		      if (line2validate.length != 5) {
		    	  System.out.print("\n");
		    	  System.out.print("******************************************************************\n" );
		    	  System.out.print("****      The format of information you entered is wrong!     ****\n" +
                                   "******************************************************************\n");
		    	  System.out.print("\n");
                  book();
		      } else {
			      String hotelid = "";
			      String[] strstr = line.split(",");
			      hotelid = strstr[0];
			      String request = userID + "," + hotelid + "," + roomid + "," + line2;
			      String respond = clientHOPP.order(request);
			      if (respond.equals(HotelConstants.SUCCEEDED)){
			    	  System.out.print("\n");
			    	  System.out.print("******************************************************************\n" );
				      System.out.print("****          You have booked hotel room successfully!        ****\n" +
				    		           "******************************************************************\n" +
				    		           "****  [Enter 1 to Manage] ***************** [Enter 2 to Back] ****\n" );
				      System.out.print("******************************************************************\n" );
				      System.out.print("\n");
				      String line3 = null;
					  try {
						line3 = console.readLine();
					  } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					  }
					  if (line3.equalsIgnoreCase("1")) {
						  System.out.print("\n");
					      manage();
					  } else if (line3.equalsIgnoreCase("2")) {
						  System.out.print("\n");
					      homepage();
					  } else {
						  System.out.print("\n");
						  System.out.print("******************************************************************\n" );
						  System.out.print("****                   Unrecognised command!                  ****\n" +
		     		                       "******************************************************************\n");
						  System.out.print("\n");
					      homepage();
						  
					  }
			      } else {
			    	  System.out.print("\n");
			    	  System.out.print("******************************************************************\n" );
			    	  System.out.print("****                   ERROR! Please try again!               ****\n" +
			                           "******************************************************************\n");
			    	  System.out.print("\n");
				      book();
			    	  
			      } 
			  } 
	          }
		      }
	 }
  }
  
  //list all orders of current user
  public void manage(){
	  String str = clientHOPP.manage(userID);
	  String[] strstr = str.split(" ");
	  
	  String orderid = null;

	  if (str.equals(HotelConstants.ERROR)){
		  System.out.print("\n");
		  System.out.print("******************************************************************\n" );
    	  System.out.print("****                     404 NOT FOUND!                       ****\n" );
    	  System.out.print("******************************************************************\n" );
    	  System.out.print("\n");
    	  homepage();
      } else {
    	  System.out.print("\n");
    	  System.out.print("******************************************************************\n" );
	      System.out.print("************Welcome to use Online Hotel Booking System************\n" + 
			               "****                                                          ****\n" + 
	                       "**** This is the list of your order information               ****\n" + 
	                       "**** Enter corresponding orderid to delete order;             ****\n" );
	      System.out.print("******************************************************************\n" );
	      System.out.print("\n");
	      System.out.print("|       orderid       |username|cityname|hotelname|roomid|indate|outdate|phone|card|\n" );
	      for (int i = strstr.length - 1; i >= 0; i--) {
		    System.out.println(strstr[i]);
		  } 
	      System.out.print("\n");
	      System.out.print("******************************************************************\n" );
	      System.out.print("**** Enter \"back\" to back to the homepage.                    ****\n");
	      System.out.print("******************************************************************\n" );
	      System.out.print("\n");
	      try {
				orderid = console.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      if (orderid.equals("back")){
	    	  System.out.print("\n");
	    	  homepage();
	      }
	      else {
	  	      delete(orderid);
	      }
	  }
  }
  
  //delete order
  public void delete(String orderid){
	  String respond = clientHOPP.delete(orderid);
	  if (respond.equals(HotelConstants.ERROR)){
		  System.out.print("\n");
		  System.out.print("******************************************************************\n" );
		  System.out.print("****                       DELETE FAILD!                      ****\n" );
		  System.out.print("******************************************************************\n" );
    	  manage();
	  } else {
		  System.out.print("\n");
		  System.out.print("******************************************************************\n" );
		  System.out.print("****                      DELETE SUCCESS!                     ****\n" );
		  System.out.print("******************************************************************\n" );
    	  manage();
      }
  }
} 

