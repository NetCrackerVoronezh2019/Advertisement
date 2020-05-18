package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import java.util.List;

public class AttachmentsFromDialog {

	private Integer chatId;
	private List<AmazonModel> allFiles;
	public Integer getChatId() {
		return chatId;
	}
	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}
	public List<AmazonModel> getAllFiles() {
		return allFiles;
	}
	public void setAllFiles(List<AmazonModel> allFiles) {
		this.allFiles = allFiles;
	}
	
	
}
