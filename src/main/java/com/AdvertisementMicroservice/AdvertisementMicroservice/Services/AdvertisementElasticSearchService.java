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
	
	
	public Advertisement save(Advertisement adv)
	{
		return this.elasticRep.save(adv);
	}
	
	
	public Iterable<Advertisement> findAll()
	{
		return this.elasticRep.findAll();
	}
	
	public void deleteAll()
	{
		this.elasticRep.deleteAll();
	}
	
	public List<Advertisement> filter(AdvFilters filters)
	{
		QueryBuilder query;
		List<String> sections=new ArrayList<>();
		sections=filters.getSubjects().stream().
									  filter(e->e.isChecked()==true).
									  map(e->e.getName().toLowerCase())
								.collect(Collectors.toList());
		
		  List<String> tags=filters.getTags()
 				   .stream()
 				    .map(t->t.getName().toLowerCase())
 				    .collect(Collectors.toList());
	  
	  if(filters.getTags().size()!=0)
	  {
		  String text=".*"+filters.getSearchRow().toLowerCase()+".*";
		  query =QueryBuilders
	    			.boolQuery()
	    			.must(QueryBuilders.regexpQuery("advertisementName", text))
	    		.must(QueryBuilders.rangeQuery("budget").from(filters.getMinPrice()).to(filters.getMaxPrice()))
	    			.must(QueryBuilders.termsQuery("section",sections))
	    			.must(QueryBuilders.termsQuery("tags.name",tags));
	    	
	  }
	  
	  else
	  {
		  String text=".*"+filters.getSearchRow().toLowerCase()+".*";
		  System.out.println(text);
		  query =QueryBuilders
	    			.boolQuery()
	    			.must(QueryBuilders.regexpQuery("advertisementName", text))
	    		.must(QueryBuilders.rangeQuery("budget").from(filters.getMinPrice()).to(filters.getMaxPrice()))
	    			.must(QueryBuilders.termsQuery("section",sections));
	    		
	  }
		

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();
        return this.elasticRep.search(build).toList();
	}
}
