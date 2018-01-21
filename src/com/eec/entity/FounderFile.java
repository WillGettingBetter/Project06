package com.eec.entity;

public class FounderFile {
	private int fileid;
	private String fileName;
	private String fileDes;
	private String filePic;
	private double filePrice;

	public FounderFile() {
		super();
	}

	@Override
	public String toString() {
		return "FounderFile [fileid=" + fileid + ",fileName=" + fileName + ", fileDes=" + fileDes
				+ ", filePic=" + filePic + ", filePrice=" + filePrice + "]";
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

	public String getFilePic() {
		return filePic;
	}

	public void setFilePic(String filePic) {
		this.filePic = filePic;
	}

	public double getFilePrice() {
		return filePrice;
	}

	public void setFilePrice(double filePrice) {
		this.filePrice = filePrice;
	}

	public int getFileid() {
		return fileid;
	}

	public void setFileid(int fileid) {
		this.fileid = fileid;
	}

}
