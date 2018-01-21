package com.eec.services;

import java.util.List;

import com.eec.entity.FounderFile;
import com.eec.entity.FounderMessage;

public interface FounderFileServices {
	/* 将图片信息插入到数据库中 */
	public int insertPicToDB(FounderFile founderFile);
	
	/*根据辅助实体类完成对数据的查询工作*/
	public List<FounderFile> getFileByAE(int currentPage,int pageCount,int fileId,String fileName,String fileDes,double filePriceMin,double filePriceMax);
	
	/*获取总页数*/
	public int getTotalPages(int pageCount,int fileId,String fileName,String fileDes,double filePriceMin,double filePriceMax);
	
	/*得到商品详细信息*/
	public FounderFile getFileInfo(int fileid);
	
	/*founderMessage，将留言信息插入到数据库中*/
	public boolean insertMsg(FounderMessage fm);
	
	/*提取数据库中的信息到这里*/
	public List<FounderMessage> getFMById(int fileid);

	/*返回在currentPage页面的pageCount个记录数*/
	public List<FounderMessage> getPageResultByData(int fileid, int currentPage, int pageCount);
	
	/*返回总页数*/
	public int getTotalPages(int fileid,int pageCount);
}
