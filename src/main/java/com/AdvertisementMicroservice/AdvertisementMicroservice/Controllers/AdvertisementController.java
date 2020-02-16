package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Subject;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.AdvertisementModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.AdvertisementRepository;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.SubjectRepository;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.SubjectService;

@RestController
@CrossOrigin("http://localhost:4200")
public class AdvertisementController {

	@Autowired
	private  AdvertisementRepository ar;
	
	@Autowired
	private  SubjectService subjectService;
	
	@GetMapping("allsubjects")
	public List<Subject> getAllSubjects()
	{
		return subjectService.getAllSubjects();
	}
	
	
	@GetMapping("student/alladvertisements")
	public ResponseEntity<List<Advertisement>> getAllAdvertisements()
	{
		List<Advertisement> advs=(List<Advertisement>)ar.findAll();
		return new ResponseEntity<>(advs,HttpStatus.OK);
		
	}
	
	
	@GetMapping("student/advertisement/{id}")
	public ResponseEntity<Advertisement> getadvbyid(@PathVariable String id)
	{
		Advertisement adv=ar.findById(Long.parseLong(id)).get();
		return new ResponseEntity<>(adv,HttpStatus.OK);
	}
	
	@PostMapping("student/addadvertisement")
	public ResponseEntity<?> addnewAdvertisement(@RequestBody Advertisement adv)
	{
		adv.setDateOfPublication(LocalDateTime.now());
		if(adv.getImageUrl()==null)
		{
			String subject=adv.getSection();
			Subject _subject=subjectService.getByName(subject);
			adv.setImageUrl(_subject.getUrl());
		}
		ar.save(adv);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
