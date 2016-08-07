package com.BrokerServer;

import java.io.*;
import java.net.*;

import com.Constants.*;

public class BrokerServer {
  //public static InetAddress address = null;
public static void main(String argv[]) {
    ServerSocket s = null;
    try {
      s = new ServerSocket(HotelConstants.BROKERPORT);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
    System.out.print("\n");
    System.out.println("Broker Server is Running...");
    System.out.print("\n");
    while (true) {
      Socket incoming = null;
      try {
        incoming = s.accept();
      } catch (IOException e) {
        System.out.println(e);
        continue;
      }
     // address = incoming.getInetAddress();
      new SocketHandler(incoming).start();
    }
  }
}

class SocketHandler extends Thread {

  Socket incoming;
  BrokerServerHOPP fileServer = new BrokerServerHOPP();

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

        if (line.startsWith(HotelConstants.LOGIN)) {
          try {
			logIn(losePrefix(line, HotelConstants.LOGIN));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        } else if (line.startsWith(HotelConstants.REGISTER)) {
          try {
    		register(losePrefix(line, HotelConstants.REGISTER));
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        } else if (line.startsWith(HotelConstants.QUERY)) {
          try {
			query();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        } else if (line.startsWith(HotelConstants.HOTEL)) {
            try {
    			queryhotel(losePrefix(line, HotelConstants.HOTEL));
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else if (line.startsWith(HotelConstants.BOOK)) {
        	try {
    			book(line);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else if (line.startsWith(HotelConstants.ORDER)) {
            try {
    			order(line);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else if (line.startsWith(HotelConstants.MANAGE)) {
            try {
    			manage(losePrefix(line, HotelConstants.MANAGE));
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else if (line.startsWith(HotelConstants.DELETE)) {
            try {
    			delete(line);
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
      //e.printStackTrace();
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
  
  public void logIn (String str) throws Exception {
	  if (fileServer.logIn(str)) {
	      writer.print(HotelConstants.SUCCEEDED + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + HotelConstants.SUCCEEDED + HotelConstants.CR_LF);
	    } else {
	      writer.print(HotelConstants.ERROR + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + HotelConstants.ERROR + HotelConstants.CR_LF);
	    }
  }

  public void register (String str) throws Exception {
	  if (fileServer.register(str)) {
	      writer.print(HotelConstants.SUCCEEDED + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + HotelConstants.SUCCEEDED + HotelConstants.CR_LF);
	    } else {
	      writer.print(HotelConstants.ERROR + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + HotelConstants.ERROR + HotelConstants.CR_LF);
	    }
  }
  
  public void query () throws Exception {
	  String str = fileServer.query();
	      writer.print(str + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + str + HotelConstants.CR_LF);
  }
  
  public void queryhotel (String line) throws Exception {
	  String respond = fileServer.queryhotel(line);
	      writer.print(respond + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + respond + HotelConstants.CR_LF);
  }
  
  public void book (String str) throws Exception {
	  String respond = fileServer.book(str);
	      writer.print(respond + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + respond + HotelConstants.CR_LF);
  }
  
  public void order (String str) throws Exception {
	  String respond = fileServer.order(str);
	      writer.print(respond + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + respond + HotelConstants.CR_LF);
  }
  
  public void manage (String str) throws Exception {
	  String respond = fileServer.manage(str);
	      writer.print(respond + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + respond + HotelConstants.CR_LF);
  }
  
  public void delete (String str) throws Exception {
	  String respond = fileServer.delete(str);
	      writer.print(respond + HotelConstants.CR_LF);
	      System.out.println("Server Respond: " + respond + HotelConstants.CR_LF);
  }
}