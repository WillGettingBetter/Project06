package com.eec.dao;

import java.util.List;

import com.eec.entity.FounderFile;
import com.eec.entity.FounderMessage;

public interface FounderFileDao {
	
	/*��ͼƬ��Ϣ���뵽���ݿ���*/
	public int insertPicToDB(FounderFile founderFile);
	
	public List<FounderFile> getFileByAE(int currentPage,int pageCount,int fileId,String fileName,String fileDes,double filePriceMin,double filePriceMax);
	
	/*��ȡ�����ܼ�¼��*/
	public int getCount(int pageCount,int fileId,String fileName,String fileDes,double filePriceMin,double filePriceMax);
	
	/*��ȡ��Ʒ��ϸ��Ϣ*/
	public FounderFile getFileInfo(int fileid);
	
	/*����һ��������Ϣ�����ݿ���*/
	public int insertMsg(FounderMessage fm);
	
	/* ����id��ȡ���ݿ��е���Ϣ */
	public List<FounderMessage> getFMById(int fileid);
	
	/*����currentPageҳ���pageCount����¼*/
	public List<FounderMessage> getPageResultByData(int fileid,
			int currentPage, int pageCount);
}
