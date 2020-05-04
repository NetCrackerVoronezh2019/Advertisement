package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Tag;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.TagRepository;

@Service
public class TagService {

	
	@Autowired
	private TagRepository tagRepository;
	
	
	public Tag save(Tag tag)
	{
		return this.tagRepository.save(tag);
	}
	
	public List<Tag> findAllByAdvId(Long id)
	{
		return this.tagRepository.findAllByAdvId(id);
	}
}
