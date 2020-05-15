package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.OrderDocument;

@Repository
@Transactional
public interface OrderDocumentRepository extends JpaRepository<OrderDocument,Long> {

	@Modifying
	@Query(value = "delete from orderdocuments where documentkey=?1", 
			  nativeQuery = true)
	public void deleteOrderDocument(String key);
}
