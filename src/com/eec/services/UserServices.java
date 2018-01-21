package com.eec.services;

import java.util.List;

import com.eec.entity.FounderUser;

public interface UserServices {

	public List<FounderUser> getAllUser();
	
	public List<FounderUser> getPageResult(int currentPage,int pageCount);
}
