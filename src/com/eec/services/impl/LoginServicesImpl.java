package com.eec.services.impl;

import com.eec.dao.FounderUserDao;
import com.eec.dao.impl.FounderUserDaoImpl;
import com.eec.entity.FounderUser;
import com.eec.services.LoginServices;
import com.eec.util.MD5Util;

public class LoginServicesImpl implements LoginServices {

	public FounderUser judgeLoginState(FounderUser user) {
		
		FounderUserDao dao = new FounderUserDaoImpl();
		FounderUser result_User = dao.getUserByName(user.getUsername());
		if (result_User != null&&result_User.getUserpsw().equals(MD5Util.MD5(user.getUserpsw()))) {
			return result_User;
		}
		return null;
	}

	
	public boolean registerByUser(FounderUser user) {
		FounderUserDao dao = new FounderUserDaoImpl();
		boolean bool = dao.insertUserByObj(user);
		if(bool){
			return true;
		}
		return false;
	}

}
