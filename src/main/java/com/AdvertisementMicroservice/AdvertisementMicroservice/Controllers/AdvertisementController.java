package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Subject;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.AdvertisementRepository;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.SubjectService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


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
	public ResponseEntity<String> addnewAdvertisement(@RequestParam String advertisement,@RequestParam("file") MultipartFile _file) throws IOException
	{
		//JSON to Java Class
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Advertisement adv = mapper.readValue(advertisement,Advertisement.class);
		adv.setDateOfPublication(LocalDateTime.now());
		adv=ar.save(adv);
		adv.setImageKey("adv_"+adv.getAdvertisementId()+"N_1");
		ar.save(adv);
		
		// create http request
		byte[] somebyteArray=_file.getBytes();
		  HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
	        ContentDisposition contentDisposition = ContentDisposition
	                .builder("form-data")
	                .name("file")
	                .filename("file")
	                .build();
	        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
	        HttpEntity<byte[]> fileEntity = new HttpEntity<>(somebyteArray, fileMap);
	        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	        body.add("file", fileEntity);
	        body.add("key", adv.getImageKey());
	        HttpEntity<MultiValueMap<String, Object>> requestEntity =new HttpEntity<>(body, headers);
		    RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> res=restTemplate.exchange("http://localhost:9999/uploadfile",HttpMethod.POST,requestEntity,String.class);
			return res;
	}
}
