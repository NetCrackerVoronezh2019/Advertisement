package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.AdvertisementSections;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.AdvertisementModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.AdvertisementRepository;

@RestController
@CrossOrigin("http://localhost:4200")
public class AdvertisementController {

	@Autowired
	public AdvertisementRepository ar;
	
	@GetMapping("getSections")
	public List<AdvertisementSections> getSections()
	{
		return Arrays.asList(AdvertisementSections.values());
	}
	
	@PostMapping("student/addadvertisement")
	public void addnewAdvertisement(@RequestBody AdvertisementModel model)
	{
		Advertisement a=new Advertisement();
		a.setAdvertisementName(model.getAdvertisementName());
		a.setBudget("1000");
		a.setSection(AdvertisementSections.valueOf(AdvertisementSections.class, model.getAdvertisementSection()));
		a.setDeadline(model.getDeadlineDate());
		a.setDescription(model.getDescription());
		a.setDateOfPublication(LocalDateTime.now());
		a.setAuthorId(model.getUserId());
		ar.save(a);
		
	}
}
