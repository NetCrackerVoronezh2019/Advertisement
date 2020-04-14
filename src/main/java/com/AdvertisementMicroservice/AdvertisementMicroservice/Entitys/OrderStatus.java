package com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys;

public enum OrderStatus {
	
	
	ACCEPTED(1),
	INPROGRESS(2),
	СOMPLETED(3);

	private final int statusWeight;
	OrderStatus(int statusWeight) {
		this.statusWeight=statusWeight;
	}
	
	public int getStatusWeight()
	{
		return this.statusWeight;
	}
}
