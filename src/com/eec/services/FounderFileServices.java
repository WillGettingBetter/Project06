package com.eec.services;

import java.util.List;

import com.eec.entity.FounderFile;
import com.eec.entity.FounderMessage;

public interface FounderFileServices {
	/* ��ͼƬ��Ϣ���뵽���ݿ��� */
	public int insertPicToDB(FounderFile founderFile);
	
	/*���ݸ���ʵ������ɶ����ݵĲ�ѯ����*/
	public List<FounderFile> getFileByAE(int currentPage,int pageCount,int fileId,String fileName,String fileDes,double filePriceMin,double filePriceMax);
	
	/*��ȡ��ҳ��*/
	public int getTotalPages(int pageCount,int fileId,String fileName,String fileDes,double filePriceMin,double filePriceMax);
	
	/*�õ���Ʒ��ϸ��Ϣ*/
	public FounderFile getFileInfo(int fileid);
	
	/*founderMessage����������Ϣ���뵽���ݿ���*/
	public boolean insertMsg(FounderMessage fm);
	
	/*��ȡ���ݿ��е���Ϣ������*/
	public List<FounderMessage> getFMById(int fileid);

	/*������currentPageҳ���pageCount����¼��*/
	public List<FounderMessage> getPageResultByData(int fileid, int currentPage, int pageCount);
	
	/*������ҳ��*/
	public int getTotalPages(int fileid,int pageCount);
}
