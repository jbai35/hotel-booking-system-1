package com.HotelServer;

import java.io.*;
import java.net.*;

import com.Constants.*;

public class HotelServer {

public static void main(String[] args) {
	if (args.length != 2) {
	   System.err.println("Usage: Client address");
	   System.exit(1);
	}
	
    ServerSocket s = null;
    System.out.print("\n");
    
    if(args[0].equals("Melbourne") && args[1].equals("Hilton")){
	    try {
	      s = new ServerSocket(18892);
	    } catch (IOException e) {
	      e.printStackTrace();
	      System.exit(1);
	    }
	    System.out.println("Melbourne Hilton Server is Running...");
    } else if (args[0].equals("Sydney") && args[1].equals("Chevron")){
    	try {
    	  s = new ServerSocket(18893);
    	} catch (IOException e) {
    	  e.printStackTrace();
    	  System.exit(1);
    	}
    	System.out.println("Sydney Chevron Server is Running...");
    } else if (args[0].equals("Perth") && args[1].equals("Regent")){
    	try {
  	      s = new ServerSocket(18894);
  	    } catch (IOException e) {
  	      e.printStackTrace();
  	      System.exit(1);
  	    }
    	System.out.println("Perth Regent Server is Running...");
    } else if (args[0].equals("Melbourne") && args[1].equals("Chevron")){
    	try {
  	      s = new ServerSocket(18895);
  	    } catch (IOException e) {
  	      e.printStackTrace();
  	      System.exit(1);
  	    }
    	System.out.println("Melbourne Chevron Server is Running...");
    } else if (args[0].equals("Melbourne") && args[1].equals("Regent")){
    	try {
  	      s = new ServerSocket(18896);
  	    } catch (IOException e) {
  	      e.printStackTrace();
  	      System.exit(1);
  	    }
    	System.out.println("Melbourne Regent Server is Running...");
    }
    while (true) {
        Socket incoming = null;
        try {
          incoming = s.accept();
        } catch (IOException e) {
          System.out.println(e);
          continue;
        }
        new SocketHandler(incoming).start();
      }
  }
}

class SocketHandler extends Thread {

  Socket incoming;
  HotelServerHOPP fileServer = new HotelServerHOPP();

  BufferedReader reader;
  PrintStream writer;

  SocketHandler(Socket incoming) {
    this.incoming = incoming;
  }

  public void run() {
    try {
      writer = new PrintStream(incoming.getOutputStream());
      reader = new BufferedReader(new InputStreamReader(
          incoming.getInputStream()));
      while (true) {
        String line = reader.readLine();
        if (line == null) {
          break;
        }
        System.out.println("Received request: " + line);

        if (line.startsWith(HotelConstants.BOOK)) {
            try {
    			book(losePrefix(line, HotelConstants.BOOK));
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else if (line.startsWith(HotelConstants.ORDER)) {
            try {
    			order(losePrefix(line, HotelConstants.ORDER));
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else if (line.startsWith(HotelConstants.DELETE)) {
            try {
    			delete(losePrefix(line, HotelConstants.DELETE));
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else if (line.startsWith(HotelConstants.QUIT)) {
          break;
        } else {
          writer.print(HotelConstants.ERROR
              + HotelConstants.CR_LF);
        }

      }
      incoming.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Given that the string starts with the prefix, get rid of the prefix and any
   * following whitespace
   */
  public String losePrefix(String str, String prefix) {
    int index = prefix.length();
    String ret = str.substring(index).trim();
    return ret;
  }
  
  public void book (String str) throws Exception {
	  String respond = fileServer.book(str);
	  writer.print(respond + HotelConstants.CR_LF);
	  System.out.println("Server Respond: " + respond + HotelConstants.CR_LF);
  }
  
  public void order (String str) throws Exception {
	  if (fileServer.order(str)){
	      writer.print(HotelConstants.SUCCEEDED + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + HotelConstants.SUCCEEDED + HotelConstants.CR_LF);
	  } else {
	      writer.print(HotelConstants.ERROR + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + HotelConstants.ERROR + HotelConstants.CR_LF);
	  }
  }
  
  public void delete (String str) throws Exception {
	  if (fileServer.delete(str)) {
	      writer.print(HotelConstants.SUCCEEDED + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + HotelConstants.SUCCEEDED + HotelConstants.CR_LF);
	  } else {
		  writer.print(HotelConstants.ERROR + HotelConstants.CR_LF);
		  System.out.println("Server Respond: " + HotelConstants.ERROR + HotelConstants.CR_LF);
	  }
  }
}
