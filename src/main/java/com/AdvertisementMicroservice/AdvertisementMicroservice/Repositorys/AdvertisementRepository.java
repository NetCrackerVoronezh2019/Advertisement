package com.AdvertisementMicroservice.AdvertisementMicroservice.Repositorys;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;

@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement,Long>{

}
