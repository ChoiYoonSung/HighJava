package kr.or.ddit.rmi.vo;

import java.io.Serializable;

/**
 * 파일 전송용 VO
 */
public class FileInfoVO implements Serializable{
	private String fileName;
	private byte[] fileData;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
	
}
