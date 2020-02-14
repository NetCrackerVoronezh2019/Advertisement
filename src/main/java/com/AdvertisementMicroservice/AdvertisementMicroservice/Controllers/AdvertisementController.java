package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.AdvertisementSections;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Subject;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.AdvertisementModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.AdvertisementRepository;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.SubjectRepository;

@RestController
@CrossOrigin("http://localhost:4200")
public class AdvertisementController {

	@Autowired
	private  AdvertisementRepository ar;
	
	@Autowired
	private  SubjectRepository rep;
	
	@GetMapping("subj")
	public List<Subject> getAllSubjects()
	{
		return rep.findAll();
	}
	
	@GetMapping("getSections")
	public ResponseEntity<List<AdvertisementSections>> getSections()
	{
		List<AdvertisementSections> sections=Arrays.asList(AdvertisementSections.values());
		return new ResponseEntity<>(sections,HttpStatus.OK);
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
	public ResponseEntity<?> addnewAdvertisement(@RequestBody Advertisement a)
	{
		a.setDateOfPublication(LocalDateTime.now());
		ar.save(a);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
