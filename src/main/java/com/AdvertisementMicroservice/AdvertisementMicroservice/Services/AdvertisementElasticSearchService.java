package com.AdvertisementMicroservice.AdvertisementMicroservice.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.*;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Advertisement;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Models.AdvFilters;
import com.AdvertisementMicroservice.AdvertisementMicroservice.searchRepository.AdvertisementElasticRepository;

@Service
public class AdvertisementElasticSearchService {
	
	
	@Autowired
	private AdvertisementElasticRepository elasticRep;
	
	
	public List<Advertisement> filter(AdvFilters filters)
	{
		List<String> sections=new ArrayList<>();
		sections=filters.getSubjects().stream().
									  filter(e->e.isChecked()==true).
									  map(e->e.getName().toLowerCase())
								.collect(Collectors.toList());
		
		String text=".*"+filters.getSearchRow().toLowerCase()+".*";
    	QueryBuilder query3 =QueryBuilders
    			.boolQuery()
    			.must(QueryBuilders.regexpQuery("advertisementName", text))
    		.must(QueryBuilders.rangeQuery("budget").from(filters.getMinPrice()).to(filters.getMaxPrice()))
    			.must(QueryBuilders.termsQuery("section",sections));
    			//.must(QueryBuilders.terms)
    	

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query3)
                
                .build();
        return this.elasticRep.search(build).toList();
	}
}
