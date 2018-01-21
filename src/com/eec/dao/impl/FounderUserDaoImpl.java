package com.eec.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eec.dao.FounderUserDao;
import com.eec.entity.FounderUser;
import com.eec.util.JDBCUtil2;

public class FounderUserDaoImpl implements FounderUserDao {

	// 根据用户名查询记录
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

	// 根据用户对象插入用户,增加用户
	public boolean insertUserByObj(FounderUser user) {
		String sql = " insert into founderuser (user_id, user_name, user_sex, "
				+ "user_tel, user_birth, user_psw) values (?,?,?,?,?,?) ";
		Object[] objs = { user.getUserid(), user.getUsername(),
				user.getUsersex(), user.getUsertel(), user.getUserbirth(),
				user.getUserpsw() };
		int temp = JDBCUtil2.executeUpdate(sql, objs);
		return temp > 0 ? true : false;
	}

	// 查询所有用户信息
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

	// 根据用户id删除用户
	public boolean deleteByUserId(int userid) {
		String sql = "delete from founderuser where user_id=? ";
		int temp = JDBCUtil2.executeUpdate(sql, userid);
		return temp > 0 ? true : false;
	}

	// 根据id查找用户
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
   
	//分页
	// 根据currentPage(当前页数)，pageCount(页记录数)来查找出对应的founderuser集合
	@Override
	public List<FounderUser> getPageResult(int currentPage, int pageCount) {
		int maxCount=pageCount*currentPage;
		int minCount=(currentPage-1)*pageCount;
		//分页的SQL语句
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
