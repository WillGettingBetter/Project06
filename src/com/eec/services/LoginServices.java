package com.eec.services;

import com.eec.entity.FounderUser;

public interface LoginServices {
	
	public FounderUser judgeLoginState(FounderUser user);
	
	public boolean registerByUser(FounderUser user);
}
