package com.DAO;

import java.sql.*;
import java.util.Date;

import com.Constants.HotelConstants;

public class DBManage {
	
	protected DBConnect dbconn = new DBConnect();
	
	//login
	public boolean logIn(String str) throws Exception {
		String[] strstr = str.split(" ");
		String userID = strstr[0];
		String psw = strstr[1];
		if (validate(userID, psw)) {
			return true;
		} else {
			return false;
		}
	}

	//check userID and password
	public boolean validate (String userID, String psw)  {
		Connection conn = null;
		try {
			conn = dbconn.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return false;
		}
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("select * from user where userid=? and psw=?");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			return false;
		}
			{
			try {
				pstmt.setString(1, userID);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				return false;
			}
			try {
				pstmt.setString(2, psw);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				return false;
			}
			// 如果查询的ResultSet里有超过一条的记录，则登录成功
			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					return true;
				}
			}
		    catch (Exception e) {
		    	return false;
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return false;
		}
	}
	
	//register new user
	public boolean register (String str) throws Exception {
		String[] strstr = str.split(" ");
		String userID = strstr[0];
		String psw = strstr[1];
		if (create(userID, psw)) {
			return true;
		} else {
			return false;
		}
	}
	
	//create new user account
	public boolean create (String userID, String psw)  {
		String sql = "insert into user values ('" + userID + "','" + psw + "')";
		Connection conn = null;
		try {
			conn = dbconn.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	    return true;
	}
	
	//query all available cities
	public String query () throws Exception{
		String str = "";
		String sql = "select cityid, cityname from city";
		Connection conn = dbconn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql 
				, ResultSet.TYPE_SCROLL_INSENSITIVE);
			ResultSet rs = pstmt.executeQuery();
		{
			rs.last();
			int rowCount = rs.getRow();
			for (int i = rowCount; i > 0 ; i-- )
			{
				rs.absolute(i);
				str = str + rs.getString(1) + ":" + rs.getString(2) + " ";
			}
		}
		pstmt.close();
		return str;
	} 
	
	//query all hotel information located in selected city
	public String queryhotel (String line) throws Exception{
		String respond = "";
		String cityname = "";
		String sql = "select hotelid,hotelname,priceA,priceB,priceC from hotel where cityid ='" + line + "'";
		String sql2 = "select cityname from city where cityid ='" + line + "'";
		
		Connection conn = dbconn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql 
				, ResultSet.TYPE_SCROLL_INSENSITIVE);
		ResultSet rs = pstmt.executeQuery();
			rs.last();
			int rowCount = rs.getRow();
			for (int i = rowCount; i > 0 ; i-- )
			{
				rs.absolute(i);
				respond = respond + "|****" + rs.getString(1) + "**|**" + rs.getString(2) + "**|**" 
				+ rs.getString(3) + "**|**" + rs.getString(4) + "**|**" + rs.getString(5) + "****|" + " ";
			}
			pstmt.close();
			
		Connection conn2 = dbconn.getConnection();	
		Statement stmt2 = conn2.createStatement();
		ResultSet rs2 = stmt2.executeQuery(sql2);
		rs2.absolute(1);
		cityname = rs2.getString(1);
		stmt2.close();
		
		return cityname + ":" + respond;
	} 
	
	//check vacancy, return roomid
	public String book (String str) throws Exception{
		String[] strstr = str.split(",");
		String hotelid = strstr[0];
		String type = strstr[1];
		String roomid = "";
		
		String sql = "select roomid from room where hotelid ='" + hotelid + "'and type='" + type + "'and state=1";
		Connection conn = dbconn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql 
				, ResultSet.TYPE_SCROLL_INSENSITIVE);
		ResultSet rs = pstmt.executeQuery();
		{
		if (rs.next()) {
			rs.absolute(1);
			roomid = rs.getString(1);
		}
		else {
			roomid = HotelConstants.ERROR;
		}
		}
		pstmt.close();
		return roomid;
	}
	
	//make oeder
	public boolean order (String str) throws Exception{
		String[] strstr = str.split(",");
		String userid = strstr[0];
		String hotelid = strstr[1];
		String roomid = strstr[2];
		String username = strstr[3];
		String indate = strstr[4];
		String outdate = strstr[5];
		String phone = strstr[6];
		String card = strstr[7];
		Date d = new Date();
		String time = String.valueOf(d.getTime());
		String orderid = userid + time ;
		String cityid = "";
		String hotelname = "";
		String cityname = "";
		
		String sql = "select cityid,hotelname from hotel where hotelid ='" + hotelid + "'";
		Connection conn = dbconn.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		{
		rs.absolute(1);
		cityid = rs.getString(1);
		hotelname = rs.getString(2);
	    }
		stmt.close();
		
		String sql2 = "select cityname from city where cityid ='" + cityid + "'";
		Connection conn2 = dbconn.getConnection();
		Statement stmt2 = conn2.createStatement();
		ResultSet rs2 = stmt2.executeQuery(sql2);	
		{
			while(rs2.next()){
			rs2.absolute(1);
			cityname = rs2.getString(1);
			}
		}
		stmt2.close();
		
		String sql3 = "insert into `order` values ('" + orderid + "','" + userid + "','" + username + "','" + cityid + "','" + cityname 
				+ "','" + hotelid+ "','" + hotelname+ "','" + roomid + "','" + indate + "','" + outdate+ "','" + phone + "','" + card +"')";
		Connection conn3 = dbconn.getConnection();
		Statement stmt3 = conn3.createStatement();
			{
				stmt3.executeUpdate(sql3);
			}	
		stmt3.close();
		
		String sql4 = "update room set state = 0 where roomid='" + roomid + "'and hotelid = '" + hotelid +"'";
		Connection conn4 = dbconn.getConnection();
		Statement stmt4 = conn4.createStatement();
			{
				stmt4.executeUpdate(sql4);
			}	
		stmt4.close();
			
		return true;
	}
	
	//list all order information of current user
	public String manage (String str) throws Exception{
		String userid = str; 
		String respond = "";
		String sql = "select orderid,username,cityname,hotelname,roomid,indate,outdate,phone,card from `order` where userid='" + userid +"'";
		Connection conn = dbconn.getConnection();
			// 使用Connection来创建一个PreparedStatement对象
			// 传入控制结果集可滚动，可更新的参数。
		PreparedStatement pstmt = conn.prepareStatement(sql 
			, ResultSet.TYPE_SCROLL_INSENSITIVE);
		ResultSet rs = pstmt.executeQuery();
	    {
			rs.last();
			int rowCount = rs.getRow();
			for (int i = rowCount; i > 0 ; i-- )
			{
				rs.absolute(i);
				respond = respond + "|" + rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3) 
						+ "|" + rs.getString(4) + "|" + rs.getString(5) + "|" + rs.getString(6) 
						+ "|" + rs.getString(7) + "|" + rs.getString(8) + "|" + rs.getString(9) + "|" + " ";
			}
	    }
	    pstmt.close();
		return respond;
	} 
	
	//delete order
	public boolean delete (String str) throws Exception{
		String orderid = str; 
		String roomid = "";
		String hotelid = "";
		
		String sql1 = "select roomid,hotelid from `order` where orderid = '" + orderid +"'";
		Connection conn = dbconn.getConnection();
				// 使用Connection来创建一个PreparedStatement对象
				// 传入控制结果集可滚动，可更新的参数。
		PreparedStatement pstmt = conn.prepareStatement(sql1 
			, ResultSet.TYPE_SCROLL_INSENSITIVE);
		ResultSet rs = pstmt.executeQuery();
		{
		if (rs.next()) {
			rs.absolute(1);
			roomid = rs.getString(1);
			hotelid = rs.getString(2);
		}
		pstmt.close();
		
		String sql2 = "update room set state = 1 where roomid ='" + roomid + "'and hotelid = '" + hotelid +"'";
		Connection conn2 = dbconn.getConnection();
		Statement stmt2 = conn2.createStatement();
		{
			stmt2.executeUpdate(sql2);
		}	
		stmt2.close();
		
		String sql3 = "delete from `order` where orderid='" + orderid +"'";
		Connection conn3 = dbconn.getConnection();
		Statement stmt3 = conn3.createStatement();
		{
			stmt3.executeUpdate(sql3);
		}
		stmt3.close();
		
		return true;
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
	  
	//get port number of the hotel server when check vacancy
	public int getbookPort(String str) throws Exception {
		// TODO Auto-generated method stub
		String str1 = losePrefix(str, HotelConstants.BOOK);
		String[] strstr = str1.split(",");
		String hotelid = strstr[0];
		int port = 0;
		String sql = "select port from hotel where hotelid = '" + hotelid + "'";
		Connection conn = dbconn.getConnection();
		// 使用Connection来创建一个PreparedStatement对象
		// 传入控制结果集可滚动，可更新的参数。
        PreparedStatement pstmt = conn.prepareStatement(sql 
	             , ResultSet.TYPE_SCROLL_INSENSITIVE);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			rs.absolute(1);
			port = rs.getInt(1);
			pstmt.close();
			return port;
		} else {
			return -1;
		}	
	}
	
	//get port number of the hotel server when make order
	public int getorderPort(String str) throws Exception {
		// TODO Auto-generated method stub
		String str1 = losePrefix(str, HotelConstants.ORDER);
		String[] strstr = str1.split(",");
		String hotelid = strstr[1];
		int port = 0;
		String sql = "select port from hotel where hotelid = '" + hotelid + "'";
		Connection conn = dbconn.getConnection();
		// 使用Connection来创建一个PreparedStatement对象
		// 传入控制结果集可滚动，可更新的参数。
        PreparedStatement pstmt = conn.prepareStatement(sql 
	             , ResultSet.TYPE_SCROLL_INSENSITIVE);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			rs.absolute(1);
			port = rs.getInt(1);
			pstmt.close();
			return port;
		} else {
			return -1;
		}	
	}

	//get port number of the hotel server when delete order
	public int getdeletePort(String str) throws Exception {
		// TODO Auto-generated method stub
		String str1 = losePrefix(str, HotelConstants.DELETE);
		String orderid = str1;
		String hotelid = "";
		int port = 0;
		String sql = "select hotelid from `order` where orderid = '" + orderid + "'";
		Connection conn = dbconn.getConnection();
		// 使用Connection来创建一个PreparedStatement对象
		// 传入控制结果集可滚动，可更新的参数。
        PreparedStatement pstmt = conn.prepareStatement(sql 
	             , ResultSet.TYPE_SCROLL_INSENSITIVE);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			rs.absolute(1);
			hotelid = rs.getString(1);
		}
		pstmt.close();
		
		
		String sql2 = "select port from hotel where hotelid = '" + hotelid + "'";
		Connection conn2 = dbconn.getConnection();
		// 使用Connection来创建一个PreparedStatement对象
		// 传入控制结果集可滚动，可更新的参数。
        PreparedStatement pstmt2 = conn2.prepareStatement(sql2 
	             , ResultSet.TYPE_SCROLL_INSENSITIVE);
		ResultSet rs2 = pstmt2.executeQuery();
		
		if (rs2.next()) {
			rs2.absolute(1);
			port = rs2.getInt(1);
			pstmt.close();
			return port;
		} else {
			return -1;
		}	
   }
}
