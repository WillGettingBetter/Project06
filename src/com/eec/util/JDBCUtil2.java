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
	 * �������������Ӷ���
	 * 
	 * @return �������Ӷ���
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
	 * ִ��SQL���
	 * 
	 * @param sql
	 *            ��Ҫ��ѯ��SQL���
	 * @param objs
	 *            �ɱ����
	 * @return ���ص���һ�������
	 */
	public static ResultSet executeQuery(String sql, Object... objs) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ����ע��
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
	 * �����޸�SQL���
	 * 
	 * @param sql
	 *            ��Ҫ��ִ�е�SQL���
	 * @param objs
	 *            ��Ҫע��Ŀɱ����
	 * @return ���ص���һ��������ָʾ��Ӱ�������
	 */
	public static int executeUpdate(String sql, Object... objs) {
		// ��ȡ���ݿ�����
		Connection conn = getConnection();
		if(conn==null){
			return 0;
		}
		PreparedStatement pstmt = null;
		int temp = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			// ����ע��
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
	 * �ر�������Դ
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
