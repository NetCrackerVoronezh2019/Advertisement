package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.AdvertisementStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.AdvertisementRepository;

@Service
public class AdvertisementService {

	@Autowired
	private AdvertisementRepository advRep;
	
	
	public Boolean advertisemensIsExists(Long id)
	{
		return advRep.existsById(id);
	}
	
	
	public List<Advertisement> getAdvertisementsByAuthorId(Long id)
	{
		return advRep.findByAuthorId(id);
	}
	

	public List<Advertisement> getAdvertisementsByTeacherId(Long id)
	{
		return advRep.findByTeacherId(id);
	}
	
	public List<Advertisement> getAdvByAuthorIdAndStatus(Long id,AdvertisementStatus status)
	{
		List<Advertisement> advs=advRep.findByAuthorId(id);
		return advs.stream().filter(adv->adv.getStatus()==status).collect(Collectors.toList());
		
	}
	
}
