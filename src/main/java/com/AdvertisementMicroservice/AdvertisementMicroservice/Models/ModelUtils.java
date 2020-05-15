package com.AdvertisementMicroservice.AdvertisementMicroservice.Models;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.*;


public class ModelUtils {

	
	public static Advertisement AdvertisementModelToEntity(AdvertisementModel model,String section)
	{
		Advertisement adv=new Advertisement();
		adv.setAdvertisementName(model.getAdvertisementName());
		adv.setAuthorId(model.getAuthorId());
		adv.setDescription(model.getDescription());
		adv.setSection(section);
		adv.setBudget(model.getBudget());
		adv.setDeadline(model.getDeadline());
		adv.setDateOfPublication(model.getDateOfPublication());		
		return adv;
	}
}
