package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.AdvertisementStatus;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.AdvFilters;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.SubjectModel;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.AdvertisementRepository;

@Service
public class AdvertisementService {

	@Autowired
	private AdvertisementRepository advRep;
	
	
	public Boolean advertisemensIsExists(Long id)
	{
		return advRep.existsById(id);
	}
	
	
	public Boolean is(Long userId,Long advId)
	{
		return this.advRep.findById(advId).get().getAuthorId()==userId;
	}
	
	public Advertisement save(Advertisement adv)
	{
		return advRep.save(adv);
	}
	
	public List<Advertisement> filterAdvertisement(AdvFilters filters)
	{
		List<Advertisement> advs=this.findAll();
		
	    List<SubjectModel> unchechkedSubjects=filters.getSubjects().stream().filter(s->s.isChecked()==false)
	    		.collect(Collectors.toList());
	    advs=advs.stream()
	    		.filter(adv->adv.getType()==filters.getType())
	    		.filter(adv->adv.getAdvertisementName().toLowerCase().contains(filters.getSearchRow().toLowerCase()))
	    		.filter(adv->unchechkedSubjects.stream().noneMatch(sub->sub.getName().equals(adv.getSection())))
	    		.filter(adv->adv.getBudget()>filters.getMinPrice() && adv.getBudget()<filters.getMaxPrice())
	    .collect(Collectors.toList());
	    
	    return advs;
	}
	public List<Advertisement> getAdvertisementsByAuthorId(Long id)
	{
		return advRep.findByAuthorId(id);
	}
	

	public List<Advertisement> findAll()
	{
		return advRep.findAll();
	}
	
	public Advertisement findById(Long id)
	{
		return advRep.findById(id).get();
	}
	/*public List<Advertisement> getAdvertisementsByTeacherId(Long id)
	{
		return advRep.findByTeacherId(id);
	}
	
	*/
	
	public List<Advertisement> getAdvByAuthorIdAndStatus(Long id,AdvertisementStatus status)
	{
		List<Advertisement> advs=advRep.findByAuthorId(id);
		return advs.stream().filter(adv->adv.getStatus()==status).collect(Collectors.toList());
		
	}
	
}
