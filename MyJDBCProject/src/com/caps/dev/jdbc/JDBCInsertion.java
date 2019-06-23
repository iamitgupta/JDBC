package com.caps.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCInsertion {

	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
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
			
			//issue the query via. connectivity
			String query="INSERT INTO users_info values "
												+"(?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			
			System.out.println("Enter user_id");
			int userid=Integer.parseInt(sc.nextLine());
			System.out.println("Enter username");
			String username=sc.nextLine();
			System.out.println("Enter email");
			String email=sc.nextLine();
			System.out.println("Enter password");
			String passwd=sc.nextLine();
			
			pstmt.setInt(1, userid);
			pstmt.setString(2, username);
			pstmt.setString(3, email);
			pstmt.setString(4, passwd);
			
			int i=pstmt.executeUpdate();
			
			System.out.println("********************************");
			//process the result
			if(i>0) {
				System.out.println(i+" Data Inserted");
			}
			else {
				System.out.println("Insertion failed");
			}
			
			
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
			
			
		}
	}

}
