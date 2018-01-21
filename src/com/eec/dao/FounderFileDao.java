package com.eec.dao;

import java.util.List;

import com.eec.entity.FounderFile;
import com.eec.entity.FounderMessage;

public interface FounderFileDao {
	
	/*将图片信息插入到数据库中*/
	public int insertPicToDB(FounderFile founderFile);
	
	public List<FounderFile> getFileByAE(int currentPage,int pageCount,int fileId,String fileName,String fileDes,double filePriceMin,double filePriceMax);
	
	/*获取条件总记录数*/
	public int getCount(int pageCount,int fileId,String fileName,String fileDes,double filePriceMin,double filePriceMax);
	
	/*获取商品详细信息*/
	public FounderFile getFileInfo(int fileid);
	
	/*插入一条留言信息到数据库中*/
	public int insertMsg(FounderMessage fm);
	
	/* 根据id提取数据库中的信息 */
	public List<FounderMessage> getFMById(int fileid);
	
	/*查找currentPage页面的pageCount个记录*/
	public List<FounderMessage> getPageResultByData(int fileid,
			int currentPage, int pageCount);
}
