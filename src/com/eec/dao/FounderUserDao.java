package com.eec.dao;

import java.util.List;

import com.eec.entity.FounderUser;

public interface FounderUserDao {
	// �����û�����ѯ��¼
	public FounderUser getUserByName(String username);

	// �����û���������û�,�����û�
	public boolean insertUserByObj(FounderUser user);

	// ��ѯ�����û���Ϣ
	public List<FounderUser> getAllUser();

	// �����û�idɾ���û�
	public boolean deleteByUserId(int userid);
	
	//����id�Ų����û�
	public boolean getUserById(int userid);
	
	//��ҳ��ѯ�û���Ϣ
	public List<FounderUser> getPageResult(int currentPage, int pageCount);
	
	
}
