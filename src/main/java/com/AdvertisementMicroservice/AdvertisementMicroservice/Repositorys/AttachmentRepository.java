package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Attachment;



@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
	
	@Query(value = "Select * from attachments where advertisementid=?1", 
			  nativeQuery = true)
	public List<Attachment> findAllByAdvertisementId(Long id);
	
}
