package com.caps.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCDelete {

	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		Statement stmt=null;
		ResultSet rs=null;
		int i;
		
		
		try {
			//Load the driver
			java.sql.Driver div=new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);
			System.out.println("Driver loaded");
			System.out.println("********************************");
			
			
			//get the connection via. driver
			String dbUrl="jdbc:mysql://localhost:3306/caps_mumbai";
			Scanner sc=new Scanner(System.in);
			
			
			System.out.println("Enter the user");
			String user=sc.nextLine();
			System.out.println("Enter the password");
			String password=sc.nextLine();
			conn=DriverManager.getConnection(dbUrl, user, password);
			
			System.out.println("Connection established");
			System.out.println("********************************");
			
			System.out.println("Enter user_id to Delete");
			int userid=Integer.parseInt(sc.nextLine());
			System.out.println("Enter Password");
			String temppass=sc.nextLine();
			
			//get password
			String tempquery="Select password from users_info where user_id="+userid;
			stmt=conn.createStatement();
			rs=stmt.executeQuery(tempquery);
			rs.next();
			String pass=rs.getString("password");
			
			
			
			
			//issue the query via. connectivity
			
			if(pass.equals(temppass)) {
			String query="DELETE FROM users_info where "
												+"user_id="+userid;
			pstmt=conn.prepareStatement(query);
			
			 i=pstmt.executeUpdate();
			//process the result
				if(i>0) {
					System.out.println("User with id "+userid+" deleted");
				}
				else {
					System.out.println("Failed");
				}

			}else {
				System.out.println("Password is wrong");
			}
			
			System.out.println("********************************");
						
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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

