package com.eec.services.impl;

import java.util.List;

import com.eec.dao.FounderFileDao;
import com.eec.dao.impl.FounderFileDaoImpl;
import com.eec.entity.FounderFile;
import com.eec.entity.FounderMessage;
import com.eec.services.FounderFileServices;

public class FounderFileServicesImpl implements FounderFileServices {

	/**
	 * 将图片中的信息插入到数据库中
	 */
	public int insertPicToDB(FounderFile founderFile) {
		FounderFileDao dao = new FounderFileDaoImpl();
		int temp = dao.insertPicToDB(founderFile);
		return temp;
	}

	@Override
	public List<FounderFile> getFileByAE(int currentPage, int pageCount,
			int fileId, String fileName, String fileDes, double filePriceMin,
			double filePriceMax) {
		// 依赖数据访问层dao层。
		FounderFileDao dao = new FounderFileDaoImpl();
		List<FounderFile> fileList = dao.getFileByAE(currentPage, pageCount,
				fileId, fileName, fileDes, filePriceMin, filePriceMax);
		return fileList;
	}

	@Override
	public int getTotalPages(int pageCount, int fileId, String fileName,
			String fileDes, double filePriceMin, double filePriceMax) {
		/* 获取总页数 */
		/* 依赖dao层 */
		FounderFileDao dao = new FounderFileDaoImpl();
		int temp = dao.getCount(pageCount, fileId, fileName, fileDes,
				filePriceMin, filePriceMax);
		return temp % pageCount == 0 ? temp / pageCount
				: (temp / pageCount + 1);
	}

	@Override
	public FounderFile getFileInfo(int fileid) {
		/* 进入数据访问层，即dao层 */
		FounderFileDao dao = new FounderFileDaoImpl();
		FounderFile founderFile = dao.getFileInfo(fileid);
		return founderFile;
	}

	@Override
	public boolean insertMsg(FounderMessage fm) {
		/* 找数据访问层 */
		FounderFileDao dao = new FounderFileDaoImpl();
		int temp = dao.insertMsg(fm);
		return temp == 1 ? true : false;
	}

	@Override
	public List<FounderMessage> getFMById(int fileid) {
		/* 根据id提取数据库中的信息 */
		/* 去到dao层 */
		FounderFileDao dao = new FounderFileDaoImpl();
		List<FounderMessage> fmList = dao.getFMById(fileid);
		return fmList;
	}

	@Override
	public List<FounderMessage> getPageResultByData(int fileid,
			int currentPage, int pageCount) {
		/* 去找dao进行查找数据 */
		FounderFileDao dao = new FounderFileDaoImpl();
		List<FounderMessage> fmList = dao.getPageResultByData(fileid,
				currentPage, pageCount);
		return fmList;
	}

	@Override
	public int getTotalPages(int fileid, int pageCount) {
		/* 去找dao层，进行功能实现 */
		FounderFileDao dao = new FounderFileDaoImpl();
		List<FounderMessage> fmList = dao.getFMById(fileid);
		int temp = fmList.size();
		return temp % pageCount == 0 ? temp / pageCount
				: (temp / pageCount + 1);
	}

}
