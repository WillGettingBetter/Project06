package com.eec.services.impl;

import java.util.List;

import com.eec.dao.FounderFileDao;
import com.eec.dao.impl.FounderFileDaoImpl;
import com.eec.entity.FounderFile;
import com.eec.entity.FounderMessage;
import com.eec.services.FounderFileServices;

public class FounderFileServicesImpl implements FounderFileServices {

	/**
	 * ��ͼƬ�е���Ϣ���뵽���ݿ���
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
		// �������ݷ��ʲ�dao�㡣
		FounderFileDao dao = new FounderFileDaoImpl();
		List<FounderFile> fileList = dao.getFileByAE(currentPage, pageCount,
				fileId, fileName, fileDes, filePriceMin, filePriceMax);
		return fileList;
	}

	@Override
	public int getTotalPages(int pageCount, int fileId, String fileName,
			String fileDes, double filePriceMin, double filePriceMax) {
		/* ��ȡ��ҳ�� */
		/* ����dao�� */
		FounderFileDao dao = new FounderFileDaoImpl();
		int temp = dao.getCount(pageCount, fileId, fileName, fileDes,
				filePriceMin, filePriceMax);
		return temp % pageCount == 0 ? temp / pageCount
				: (temp / pageCount + 1);
	}

	@Override
	public FounderFile getFileInfo(int fileid) {
		/* �������ݷ��ʲ㣬��dao�� */
		FounderFileDao dao = new FounderFileDaoImpl();
		FounderFile founderFile = dao.getFileInfo(fileid);
		return founderFile;
	}

	@Override
	public boolean insertMsg(FounderMessage fm) {
		/* �����ݷ��ʲ� */
		FounderFileDao dao = new FounderFileDaoImpl();
		int temp = dao.insertMsg(fm);
		return temp == 1 ? true : false;
	}

	@Override
	public List<FounderMessage> getFMById(int fileid) {
		/* ����id��ȡ���ݿ��е���Ϣ */
		/* ȥ��dao�� */
		FounderFileDao dao = new FounderFileDaoImpl();
		List<FounderMessage> fmList = dao.getFMById(fileid);
		return fmList;
	}

	@Override
	public List<FounderMessage> getPageResultByData(int fileid,
			int currentPage, int pageCount) {
		/* ȥ��dao���в������� */
		FounderFileDao dao = new FounderFileDaoImpl();
		List<FounderMessage> fmList = dao.getPageResultByData(fileid,
				currentPage, pageCount);
		return fmList;
	}

	@Override
	public int getTotalPages(int fileid, int pageCount) {
		/* ȥ��dao�㣬���й���ʵ�� */
		FounderFileDao dao = new FounderFileDaoImpl();
		List<FounderMessage> fmList = dao.getFMById(fileid);
		int temp = fmList.size();
		return temp % pageCount == 0 ? temp / pageCount
				: (temp / pageCount + 1);
	}

}
