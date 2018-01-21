package com.eec.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eec.dao.FounderUserDao;
import com.eec.entity.FounderUser;
import com.eec.util.JDBCUtil2;

public class FounderUserDaoImpl implements FounderUserDao {

	// �����û�����ѯ��¼
	public FounderUser getUserByName(String username) {
		String sql = " select * from founderuser where user_name=? ";
		ResultSet rs = JDBCUtil2.executeQuery(sql, username);
		FounderUser user = null;
		try {
			while (rs.next()) {
				user = new FounderUser(rs.getInt(1), rs.getString(2),
						rs.getString(6), rs.getString(3), rs.getString(4),
						rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil2.closeRPC(rs, rs.getStatement(), rs.getStatement()
						.getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	// �����û���������û�,�����û�
	public boolean insertUserByObj(FounderUser user) {
		String sql = " insert into founderuser (user_id, user_name, user_sex, "
				+ "user_tel, user_birth, user_psw) values (?,?,?,?,?,?) ";
		Object[] objs = { user.getUserid(), user.getUsername(),
				user.getUsersex(), user.getUsertel(), user.getUserbirth(),
				user.getUserpsw() };
		int temp = JDBCUtil2.executeUpdate(sql, objs);
		return temp > 0 ? true : false;
	}

	// ��ѯ�����û���Ϣ
	public List<FounderUser> getAllUser() {
		String sql = "select * from founderuser ";
		ResultSet rs = JDBCUtil2.executeQuery(sql);
		FounderUser user = null;
		List<FounderUser> list = new ArrayList<FounderUser>();
		try {
			while (rs.next()) {
				user = new FounderUser(rs.getInt(1), rs.getString(2),
						rs.getString(6), rs.getString(3), rs.getString(4),
						rs.getDate(5));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil2.closeRPC(rs, rs.getStatement(), rs.getStatement()
						.getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// �����û�idɾ���û�
	public boolean deleteByUserId(int userid) {
		String sql = "delete from founderuser where user_id=? ";
		int temp = JDBCUtil2.executeUpdate(sql, userid);
		return temp > 0 ? true : false;
	}

	// ����id�����û�
	public boolean getUserById(int userid) {
		String sql = " select * from founderuser where user_id=? ";
		ResultSet rs = JDBCUtil2.executeQuery(sql, userid);
		try {
			if (rs.next() == true) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
   
	//��ҳ
	// ����currentPage(��ǰҳ��)��pageCount(ҳ��¼��)�����ҳ���Ӧ��founderuser����
	@Override
	public List<FounderUser> getPageResult(int currentPage, int pageCount) {
		int maxCount=pageCount*currentPage;
		int minCount=(currentPage-1)*pageCount;
		//��ҳ��SQL���
		String sql = "select user_id,user_name,user_sex,user_tel,user_birth,"
				+ "user_psw from(select * from(select user_id,user_name,user_sex,"
				+ "user_tel,user_birth,user_psw,rownum bianhao from founderuser "
				+ "where rownum<=? ) aa where bianhao>?) bb";
		Object[] objs={maxCount,minCount};
		ResultSet rs = JDBCUtil2.executeQuery(sql,objs);
		FounderUser user = null;
		List<FounderUser> list = new ArrayList<FounderUser>();
		try {
			while (rs.next()) {
				user = new FounderUser(rs.getInt(1), rs.getString(2),
						rs.getString(6), rs.getString(3), rs.getString(4),
						rs.getDate(5));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtil2.closeRPC(rs, rs.getStatement(), rs.getStatement()
						.getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
