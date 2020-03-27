package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import java.util.List;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.AdvertisementType;
public class AdvFilters {

	private List<SubjectModel> subjects;
	private Integer minPrice;
	private Integer maxPrice;
	private String searchRow;
	private AdvertisementType type;
	
	
	
	
	public AdvertisementType getType() {
		return type;
	}
	public void setType(AdvertisementType type) {
		this.type = type;
	}
	public Integer getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		if(minPrice==null)
			this.minPrice=Integer.MIN_VALUE;
		else 
			this.minPrice=minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		if(maxPrice==null)
			this.maxPrice=Integer.MAX_VALUE;
		else
			this.maxPrice=maxPrice;
	}

	public List<SubjectModel> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectModel> subjects) {
		this.subjects = subjects;
	}

	public String getSearchRow() {
		return searchRow;
	}

	public void setSearchRow(String searchRow) {
		if(searchRow==null)
			this.searchRow="";
		else
			this.searchRow=searchRow;
	}
	
	
	
}
