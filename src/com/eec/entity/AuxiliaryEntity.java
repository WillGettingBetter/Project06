package com.eec.entity;

/**
 * �ļ���ѯ�ķ���ʵ����
 * 
 * @author SJF18
 * 
 */
public class AuxiliaryEntity {
	private int currentPage; // ��ǰҳ��
	private int pageCount;// ҳ��¼��
	private int fileId;// �ļ����
	private String fileName;// �ļ�����
	private String fileDes;// �ļ�����
	private double filePriceMin;// ��ͼ�
	private double filePriceMax;// ��߼�

	public AuxiliaryEntity() {
		super();
	}

	@Override
	public String toString() {
		return "AuxiliaryEntity [currentPage=" + currentPage + ", pageCount="
				+ pageCount + ", fileId=" + fileId + ", fileName=" + fileName
				+ ", fileDes=" + fileDes + ", filePriceMin=" + filePriceMin
				+ ", filePriceMax=" + filePriceMax + "]";
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDes() {
		return fileDes;
	}

	public void setFileDes(String fileDes) {
		this.fileDes = fileDes;
	}

	public double getFilePriceMin() {
		return filePriceMin;
	}

	public void setFilePriceMin(double filePriceMin) {
		this.filePriceMin = filePriceMin;
	}

	public double getFilePriceMax() {
		return filePriceMax;
	}

	public void setFilePriceMax(double filePriceMax) {
		this.filePriceMax = filePriceMax;
	}

}
