package com.eec.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil2 {

	/**
	 * 加载驱动，连接对象
	 * 
	 * @return 返回连接对象
	 */
	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		InputStream is = JDBCUtil2.class.getClassLoader().getResourceAsStream(
				"db.properties");

		try {
			prop.load(is);
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),
					prop.getProperty("user"), prop.getProperty("psw"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 *            需要查询的SQL语句
	 * @param objs
	 *            可变参数
	 * @return 返回的是一个结果集
	 */
	public static ResultSet executeQuery(String sql, Object... objs) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// 参数注入
			if (objs != null) {
				for (int i = 0; i < objs.length; i++) {
					pstmt.setObject(i + 1, objs[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 负责修改SQL语句
	 * 
	 * @param sql
	 *            需要被执行的SQL语句
	 * @param objs
	 *            需要注入的可变参数
	 * @return 返回的是一个整数，指示受影响的行数
	 */
	public static int executeUpdate(String sql, Object... objs) {
		// 获取数据库连接
		Connection conn = getConnection();
		if(conn==null){
			return 0;
		}
		PreparedStatement pstmt = null;
		int temp = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			// 参数注入
			if (objs != null) {
				for (int i = 0; i < objs.length; i++) {
					pstmt.setObject(i + 1, objs[i]);
				}
			}
			temp = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeRPC(null, pstmt, conn);
		}
		return temp;
	}

	/**
	 * 关闭所有资源
	 * 
	 * @param rs
	 * @param pstmt
	 * @param conn
	 */
	public static void closeRPC(ResultSet rs, Statement pstmt,
			Connection conn) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
