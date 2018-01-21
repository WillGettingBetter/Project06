package com.eec.dao;

import java.util.List;

import com.eec.entity.FounderUser;

public interface FounderUserDao {
	// 根据用户名查询记录
	public FounderUser getUserByName(String username);

	// 根据用户对象插入用户,增加用户
	public boolean insertUserByObj(FounderUser user);

	// 查询所有用户信息
	public List<FounderUser> getAllUser();

	// 根据用户id删除用户
	public boolean deleteByUserId(int userid);
	
	//根据id号查找用户
	public boolean getUserById(int userid);
	
	//分页查询用户信息
	public List<FounderUser> getPageResult(int currentPage, int pageCount);
	
	
}
