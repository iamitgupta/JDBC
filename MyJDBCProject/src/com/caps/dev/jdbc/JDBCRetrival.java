/*write a program using JDBC to find the details of the users on
  the basis of their 'user_ids'
*/

package com.caps.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCRetrival {

	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			//Load the driver
			java.sql.Driver div=new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);
			System.out.println("Driver loaded...");

			//Get DB connectivity via driver
			String url="jdbc:mysql://127.0.0.1:3306/caps_mumbai?user=root&password=root";
			conn=DriverManager.getConnection(url);
			System.out.println("Connection established..");
			
//			Scanner sc=new Scanner(System.in);
//			System.out.println("Enter user_id");
//			int userid=Integer.parseInt(sc.nextLine());
			int userid;

			//Issue SQL queries via connectivity
			String query="Select * from users_info";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			System.out.println("Query issued..");
			System.out.println("*********************************");

			//process the results
			while(rs.next()) {
				userid=rs.getInt("user_id");
				String username=rs.getString("username");
				String email=rs.getString("email");

				System.out.println(userid);
				System.out.println(username);
				System.out.println(email);

				System.out.println("***************************");
			}


		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			//close all the JDBC objects
			if(conn!=null) {
				try {
					conn.clearWarnings();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

}