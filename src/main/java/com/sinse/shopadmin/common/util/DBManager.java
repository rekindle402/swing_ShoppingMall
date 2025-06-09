package com.sinse.shopadmin.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sinse.shopadmin.common.config.Config;

// 아무도 직접 인스턴스를 생성하지 못하게 생성장의 접근제한을 막아버린다.. (Singleton)
public class DBManager {
	
	private static DBManager instance; // Digest
	
	private Connection con; 	// 인스터가 죽으면 안되므로 멤버변수로 뺀다.
	
	private DBManager() {
		try {
			
			// 1) 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2) DB접속
			con = DriverManager.getConnection(Config.url, Config.user, Config.pass);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DBManager getInstance() {
		//만일 인스턴스가 존재하지 않으면 이 메서드에서 인스턴스를 생성해줌
		if(instance==null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConnetion() {
		return con;
	}

	//DML
	public void release(PreparedStatement pstmt) { 
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//SELECT
	public void release(PreparedStatement pstmt, ResultSet rs) { 
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs) { 
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
}
