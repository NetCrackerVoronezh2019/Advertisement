package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Subject;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	public void addNewSubjects(List<Subject> _subjects) {
		System.out.println("Service");
		try
		{
			subjectRepository.saveAll(_subjects);
		}
		catch(Exception ex)
		{
			System.out.println("ex");
			System.out.println(ex.getMessage());
		}
		
	}
}
