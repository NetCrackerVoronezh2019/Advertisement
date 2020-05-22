package com.AdvertisementMicroservice.AdvertisementMicroservice.Controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.*;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Kafka.Microservices;
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
	private NotificationService notifService;
	
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
	
	@Autowired
	private Microservices microservices;
		
	
	@PostMapping("filterAdvertisements")
	public ResponseEntity<List<Advertisement>> filterAdvertisements(@RequestBody AdvFilters advFilters)
	{
		List<Advertisement> advs=this.elasticService.filter(advFilters);

		return new ResponseEntity<>(advs,HttpStatus.OK);
	}
	
	
	@PostMapping("addNewSubject")
	public ResponseEntity<Subject> addNewSubject(@RequestBody Subject subject)
	{
		subject=subjectService.save(subject);
		
		return new ResponseEntity<>(subject,HttpStatus.OK);
	}
	
	
	@GetMapping("allAdvBabe")
	public List<Advertisement> allAdvBabe()
	{
		List<Advertisement> documents = new ArrayList<>();
    
		
        for (Advertisement doc : this.elasticRep.findAll()) {
        	System.out.println("ddfdsfsf");
            documents.add(doc);
        }
        return documents;
	}
	
	@GetMapping("remove")
	public void delete()
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
		if(advs!=null)
		{
			advs=advs.stream()
				.filter(adv->adv.getStatus()==AdvertisementStatus.ACTIVE)
				.collect(Collectors.toList());
		}
	
		System.out.println(advs.size());
		return new ResponseEntity<>(advs,HttpStatus.OK);
		
	}
	
	
	@GetMapping("changeAdvStatus/{advId}/{status}")
	public ResponseEntity<?> changeAdvertisementStatus(@PathVariable Long advId,@PathVariable AdvertisementStatus status)
	{
		
	   Advertisement adv=this.advertisementService.changeAdvertisementStatus(advId, status);
	   
	   return new ResponseEntity<>(adv,HttpStatus.OK);
	}
	
	@GetMapping("deleteAdvertisement/{id}/{comment}")
	public ResponseEntity<Long> deleteAdvertisement(@PathVariable Long id,@PathVariable String comment)
	{
		Advertisement adv=this.advertisementService.findById(id);
		adv.setStatus(AdvertisementStatus.DELETED);
		this.advertisementService.save(adv);
		Long userId= adv.getAuthorId();
		Notification notif=new Notification();
		notif.setResponseStatus(NotificationResponseStatus.UNREADED);
		notif.setStatus(NotificationStatus.UNREADED);
		notif.setAddresseeId(userId);
		notif.setDate(LocalDateTime.now());
		notif.setType(NotificationType.DELETE_ADVERTISEMENT);
		String message="Объявление удалено администратором,причина - "+comment;
		notif.setMessage(message);
		try
		{
			this.elasticService.save(adv);
		}
		catch(Exception ex) {}
		this.notifService.save(notif);
		return new ResponseEntity<>(adv.getAuthorId(),HttpStatus.OK);
	
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
		this.tagService.deletebyAdvId(adv.getAdvertisementId());
		
		advertisementService.save(adv);
		AmazonModels amazon=new AmazonModels();
		amazon.allFiles=model.getAllFiles();
		for(Tag tag:model.getTags())
		{
			tag.setAdvertisement(adv);
			tagService.save(tag);	
		}
		
		Collection<Attachment> docs=adv.getAttachments();
		int m=0;
		int n=0;
		int l=0;
		if(docs==null)
		{
			m=0;
			n=model.getAllFiles().size();
		}
		else
		{
			m=docs.size();
			n=m+model.getAllFiles().size();
		}
		for(int i=m;i<n;i++)
		{
			Attachment attachment=new Attachment();
			//String newKey="adv"+this.getAdvertisementId()+"_"+keys.get(i).getName();
			attachment.setKey("adv"+adv.getAdvertisementId()+"document_"+model.getAllFiles().get(l).getName());
			attachment.setAdvertisement(adv);
			//attachment(model.getAllFiles().get(l).getName());
			this.attachmentService.save(attachment);
			amazon.allFiles.get(l).setKey(attachment.getKey());
			l++;
		}
		
		HttpEntity<AmazonModels> requestEntity =new HttpEntity<>(amazon);
		RestTemplate restTemplate = new RestTemplate();
		String host=microservices.getHost();
		String port=microservices.getAmazonPort();
		try 
		{
		   ResponseEntity<Object> res=restTemplate.exchange("http://"+host+":"+port+"/uploadAdvertisementFiles",HttpMethod.POST,requestEntity,Object.class);
		}
		catch(Exception ex)
		{
			
		
		}
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
		Subject subject=this.subjectService.getByName(adv.getSection()).get(0);
		Advertisement advertisement=ModelUtils.AdvertisementModelToEntity(adv,subject.getTranslateName());
		advertisement.setStatus(AdvertisementStatus.ACTIVE);
		
		if(adv.getAuthorRole().equals("ROLE_STUDENT"))
			advertisement.setType(AdvertisementType.ORDER);
		else
			advertisement.setType(AdvertisementType.FREELANCE);
		advertisement=advertisementService.save(advertisement);
		
		String[] keys=advertisement.getAttachmentKeys(adv.getAllFiles());
		advertisement=advertisementService.save(advertisement);
		if(adv.getCoverImage().getContent()!=null)
		{
			advertisement.setCoverImageKey("advcoverImage_"+advertisement.getAdvertisementId());
		}
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
		try {
		this.elasticService.save(advertisement);
		}
		catch(Exception ex) {}
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
	    String host=microservices.getHost();
	    String port=microservices.getAmazonPort();
		try 
		{
		   ResponseEntity<Object> res=restTemplate.exchange("http://"+host+":"+port+"/uploadAdvertisementFiles",HttpMethod.POST,requestEntity,Object.class);
		}
		catch(Exception ex)
		{
			
		
		}			
			 
		return new ResponseEntity<>(true,HttpStatus.OK);
		
	}
	
		
}
