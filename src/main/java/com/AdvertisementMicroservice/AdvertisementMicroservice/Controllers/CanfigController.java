package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Subject;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Kafka.MicroserviceInfo;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Kafka.Microservices;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.SubjectService;

import java.util.*;

@RestController
public class CanfigController {

	@Autowired
	private SubjectService subjectService;
	
	@Autowired Microservices m;
	
	@GetMapping("getAllSubjects")
	public List<Subject> getallSubjects(){
		return subjectService.getAllSubjects();
	}
	
	
	@GetMapping("getAllInfo")
	public Microservices allmicro()
	{
		return m;
	}
}
