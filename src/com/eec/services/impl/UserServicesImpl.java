package com.eec.services.impl;

import java.util.List;

import com.eec.dao.FounderUserDao;
import com.eec.dao.impl.FounderUserDaoImpl;
import com.eec.entity.FounderUser;
import com.eec.services.UserServices;

public class UserServicesImpl implements UserServices {

	public List<FounderUser> getAllUser() {
		FounderUserDao dao = new FounderUserDaoImpl();
		List<FounderUser> userlist = dao.getAllUser();
		return userlist;
	}

	@Override
	public List<FounderUser> getPageResult(int currentPage, int pageCount) {
		FounderUserDao dao = new FounderUserDaoImpl();
		List<FounderUser> userlist=dao.getPageResult(currentPage, pageCount);
		return userlist;
	}

}
