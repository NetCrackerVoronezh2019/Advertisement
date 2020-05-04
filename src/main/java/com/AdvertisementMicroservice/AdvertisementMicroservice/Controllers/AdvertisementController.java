package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.*;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Tag;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.*;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.AttachmentRepository;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.*;
import com.AdvertisementMicroservice.AdvertisementMicroservice.searchRepository.AdvertisementElasticRepository;


@RestController
@CrossOrigin("http://localhost:4200")
public class AdvertisementController {

	
	@Autowired
	private  SubjectService subjectService;
	
	
	@Autowired
	private AdvertisementElasticRepository elasticRep;
	
	
	@Autowired 
	private AdvertisementElasticSearchService elasticService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired 
	private TagService tagService;
		
	
	@PostMapping("filterAdvertisements")
	public ResponseEntity<List<Advertisement>> filterAdvertisements(@RequestBody AdvFilters advFilters)
	{
		List<Advertisement> advs=this.elasticService.filter(advFilters);
		
		//List<AdvertisementModel> models=AdvertisementModel.advListToModelList(advs);
		return new ResponseEntity<>(advs,HttpStatus.OK);
		
		
	}
	
	
	/*
	@GetMapping("filter")
	public List<Advertisement> filter()
	{
		return  this.elasticService.filter();
	}
	
	*/
	
	
	@GetMapping("allAdvBabe")
	public List<Advertisement> allAdvBabe()
	{
		List<Advertisement> documents = new ArrayList<>();
        // iterate all documents and add it to list
		System.out.println("ddfdsfsf");
        for (Advertisement doc : this.elasticRep.findAll()) {
        	System.out.println("ddfdsfsf");
            documents.add(doc);
        }
        return documents;
	}
	
	@GetMapping("delete")
	public void fdfgndg()
	{
		this.elasticService.deleteAll();
	}
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
	
	
	@PostMapping("updateAdvertisementInformation")
	public ResponseEntity<?> updateAdvertisementInformation(@RequestBody AdvertisementModel model)
	{
		Advertisement adv=advertisementService.findById(model.getAdvertisementId());
		//adv.setDeadline(model.getDeadline());
		adv.setAdvertisementName(model.getAdvertisementName());
		adv.setBudget(model.getBudget());
		adv.setDescription(model.getDescription());
		adv.setSection(model.getSection());
		advertisementService.save(adv);
		
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	@GetMapping("advertisement/{id}")
	public ResponseEntity<Advertisement> getadvbyid(@PathVariable String id)
	{
		Advertisement adv=advertisementService.findById(Long.parseLong(id));
		
		return new ResponseEntity<>(adv,HttpStatus.OK);
	}
	
	
	@PostMapping("addAdvertisement")
	public ResponseEntity<Boolean> addnewAdvertisement(@RequestBody AdvertisementModel adv) throws IOException
	{
		
		adv.setDateOfPublication(LocalDateTime.now());
		Advertisement advertisement=ModelUtils.AdvertisementModelToEntity(adv);
		advertisement.setStatus(AdvertisementStatus.ACTIVE);
		
		if(adv.getAuthorRole().equals("ROLE_STUDENT"))
			advertisement.setType(AdvertisementType.ORDER);
		else
			advertisement.setType(AdvertisementType.FREELANCE);
		advertisement=advertisementService.save(advertisement);
		
		String[] keys=advertisement.getAttachmentKeys(adv.getAllFiles());
		advertisement=advertisementService.save(advertisement);
		advertisement.setCoverImageKey("advcoverImage_"+advertisement.getAdvertisementId());
		AmazonModel amazonModel=adv.getCoverImage();
		amazonModel.setKey(advertisement.getCoverImageKey());
		advertisement=advertisementService.save(advertisement);
		
		for(Tag tag:adv.getTags())
		{
			
			tag.setAdvertisement(advertisement);
			tagService.save(tag);
			
		}
		
		List<Tag> tags=this.tagService.findAllByAdvId(advertisement.getAdvertisementId());
		
		advertisement.setTags(tags);
		this.elasticService.save(advertisement);
		 
		AmazonModels amazon=new AmazonModels();
		if(keys.length!=0)
		{
			
			for(int i=0;i<keys.length;i++)
			{
				Attachment a=new Attachment();
				a.setKey(keys[i]);
				a.setAdvertisement(advertisement);
				attachmentService.save(a);
				AmazonModel m=adv.getAllFiles().get(i);
				m.setKey(keys[i]);
				amazon.allFiles.add(m);
			}
		}
		amazon.allFiles.add(amazonModel);
		HttpEntity<AmazonModels> requestEntity =new HttpEntity<>(amazon);
		RestTemplate restTemplate = new RestTemplate();
		try 
		{
		   ResponseEntity<Object> res=restTemplate.exchange("http://localhost:1234/uploadAdvertisementFiles",HttpMethod.POST,requestEntity,Object.class);
		}
		catch(Exception ex)
		{
			
		
		 }			
			
		 
		 
		return new ResponseEntity<>(true,HttpStatus.OK);
		
	}
	
		
}
