package com.example.news;

import org.springframework.web.multipart.MultipartFile;

public class News {
	private int aid;
	private String title;
	private String img;
	private String date;
	private String content;
	
	private MultipartFile imgFile;  // front에서 파일 전송받기 위해 추가
	
	public MultipartFile getImgFile() {
		return imgFile;
	}
	
	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}

	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	
	
}
