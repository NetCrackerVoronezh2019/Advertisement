package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Subject;

@Repository
@Transactional
public interface SubjectRepository extends JpaRepository<Subject,Long>{

	public List<Subject> findByTranslateName(String name);
}
	