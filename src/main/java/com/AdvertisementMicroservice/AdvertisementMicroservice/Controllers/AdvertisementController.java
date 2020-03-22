package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.AdvertisementStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Subject;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.AdvertisementModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.AmazonModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.ModelUtils;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.AdvertisementRepository;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@CrossOrigin("http://localhost:4200")
public class AdvertisementController {

	
	@Autowired
	private  SubjectService subjectService;
	
	@Autowired
	private AdvertisementService advertisementService;
		
	@GetMapping("allSubjects")
	public List<Subject> getAllSubjects()
	{
		return subjectService.getAllSubjects();
	}
	
	@GetMapping("allAdvertisements")
	public ResponseEntity<List<Advertisement>> getAllAdvertisements()
	{
		List<Advertisement> advs=advertisementService.findAll();
		return new ResponseEntity<>(advs,HttpStatus.OK);
		
	}	
	
	@GetMapping("advertisement/{id}")
	public ResponseEntity<Advertisement> getadvbyid(@PathVariable String id)
	{
		Advertisement adv=advertisementService.findById(Long.parseLong(id));
		return new ResponseEntity<>(adv,HttpStatus.OK);
	}
	
	@PostMapping("addAdvertisement")
	public ResponseEntity<String> addnewAdvertisement(@RequestBody AdvertisementModel adv) throws IOException
	{
		
		adv.setDateOfPublication(LocalDateTime.now());
		Advertisement advertisement=ModelUtils.AdvertisementModelToEntity(adv);
		advertisement.setStatus(AdvertisementStatus.ACTIVE);
		advertisement=advertisementService.save(advertisement);
		advertisement.setImageKey("adv_"+advertisement.getAdvertisementId()+"N_1");
		advertisementService.save(advertisement);
		
		AmazonModel model=new AmazonModel();
		model.setContent(adv.getContent());
		model.setKey(advertisement.getImageKey());
		
	    HttpEntity<AmazonModel> requestEntity =new HttpEntity<>(model);
		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> res=restTemplate.exchange("http://localhost:1234/uploadFile",HttpMethod.POST,requestEntity,String.class);
		return res;
		
	}
	

	
}
