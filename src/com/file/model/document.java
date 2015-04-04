package com.file.model;

import java.util.Date;
public class document {

	private String directory;
	private String username;
	private String filename;
	private Date time;
    private String  url;

	public String getDirectory() {
		return directory;
	}
	public void setDirectoryname(String directory) {
		this.directory = directory;
	}

	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

}
