package com.AdvertisementMicroservice.AdvertisementMicroservice.searchRepository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;



public interface AdvertisementElasticRepository extends ElasticsearchRepository<Advertisement,Long> {

}
