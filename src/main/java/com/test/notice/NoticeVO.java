package com.test.notice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class NoticeVO {
	private String ntcNo;
	private String title;
	private String writer;
	private String memo;
	private String ntcDate;
	private List<MultipartFile> uploadfile;

	public String getNtcNo() {
		return ntcNo;
	}
	public void setNtcNo(String ntcNo) {
		this.ntcNo = ntcNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getNtcDate() {
		return ntcDate;
	}
	public void setNtcDate(String ntcDate) {
		this.ntcDate = ntcDate;
	}
	public List<MultipartFile> getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(List<MultipartFile> uploadfile) {
		this.uploadfile = uploadfile;
	}

	@Override
	public String toString() {
		return "NoticeVO [ntcNo=" + ntcNo + ", title=" + title + ", writer=" + writer + ", memo=" + memo + ", ntcDate="
				+ ntcDate + ", uploadfile=" + uploadfile + "]";
	}
}

