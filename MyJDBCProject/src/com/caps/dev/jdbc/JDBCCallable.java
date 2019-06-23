package com.caps.dev.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCCallable {
	public static void main(String[] args) {

		Connection conn=null;
		ResultSet rs=null;
		CallableStatement cstmt=null;


		try {
			//Load the driver
			java.sql.Driver div=new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);
			System.out.println("Driver loaded...");

			//Get DB connectivity via driver
			String url="jdbc:mysql://127.0.0.1:3306/caps_mumbai?user=root&password=root";
			conn=DriverManager.getConnection(url);
			System.out.println("Connection established..");
			
			//Issue the query
			
			String query="CALL SP1()";
			cstmt=conn.prepareCall(query);
			boolean res=cstmt.execute();
			if(res) {
				rs=cstmt.getResultSet();
				//process the results
				while(rs.next()) {
					int userid=rs.getInt("user_id");
					String username=rs.getString("username");
					String email=rs.getString("email");

					System.out.println(userid);
					System.out.println(username);
					System.out.println(email);

					System.out.println("***************************");
				}
			}else {
				int i=cstmt.getUpdateCount();
				//process the result
				if(i>0) {
					System.out.println("Query ok");
				}
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(cstmt!=null) {
				try {
					cstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
