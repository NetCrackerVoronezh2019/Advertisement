package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.OrderDocument;

@Repository
@Transactional
public interface OrderDocumentRepository extends JpaRepository<OrderDocument,Long> {

}
