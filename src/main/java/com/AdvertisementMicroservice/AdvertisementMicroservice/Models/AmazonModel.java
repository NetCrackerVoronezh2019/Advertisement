package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

public class AmazonModel {
	
	private String key;
	private String content;
	private String bucket;
	
	public AmazonModel(String key,String content)
	{
		this.key=key;
		this.content=content;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBucket() {
		return bucket;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	
	
}
