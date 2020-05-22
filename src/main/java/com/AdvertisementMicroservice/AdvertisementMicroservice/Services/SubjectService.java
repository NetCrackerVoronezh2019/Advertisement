package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Subject;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys.SubjectRepository;


@Service
@Transactional
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	public void addNewSubjects(List<String> _subjects) {
		List<Subject> _allSubjects=subjectRepository.findAll();
	    for(String subject:_subjects)
	    {
	    	if(_allSubjects.stream().noneMatch(s->s.getTranslateName().equals(subject)))
	    	{
	    		Subject _subject=new Subject();
	    		_subject.setTranslateName(subject);
	    		subjectRepository.save(_subject);
	    	}
	    }
	}
	

	public List<Subject> getAllSubjects()
	{
		return subjectRepository.findAll();
	}
	
	public List<Subject> getByName(String _name)
	{
		return subjectRepository.findByTranslateName(_name);
	}
	
	public Subject save(Subject subject)
	{
		return this.subjectRepository.save(subject);
	}
	

}
