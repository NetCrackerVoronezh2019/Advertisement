package com.AdvertisementMicroservice.AdvertisementMicroservice.Kafka;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Subject;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.SubjectService;




@Service
public class ConsumerThreadService {
	
	@Autowired 
	private SubjectService subjectService;
	@Autowired
	private Microservices micro;
	
	private KafkaConsumer<String,String> microserviceInfoConsumer;
	private KafkaConsumer<String,String> subjectConsumer;
	
	@Value("${kafka.microserviceInfoTopic}")
	private String microserviceInfoTopic;
	
	@Value("${kafka.microservcieInfoGroup}")
	private String microserviceInfoGroup;
	
	@Value("${kafka.subjectsTopic}")
	private String subjectsTopic;
	
	@Value("${kafka.subjectsTopicGroup}")
	private String subjectsTopicGroup;

	
	@PostConstruct
	public void init()
	{
		try {
		String bootstrapServers1="192.168.99.101:9092";
    	Properties properties1=new Properties();
    	properties1.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers1);
    	properties1.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
    	properties1.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
    	properties1.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
    	properties1.setProperty(ConsumerConfig.GROUP_ID_CONFIG, this.microserviceInfoTopic);
    	this.microserviceInfoConsumer=new KafkaConsumer<String,String>(properties1);
    	this.microserviceInfoConsumer.subscribe(Arrays.asList(this.microserviceInfoGroup));
    	

    	Properties properties2=new Properties();
    	properties2.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers1);
    	properties2.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
    	properties2.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
    	properties2.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
    	properties2.setProperty(ConsumerConfig.GROUP_ID_CONFIG, this.subjectsTopicGroup);
    	this.subjectConsumer=new KafkaConsumer<String,String>(properties2);
    	this.subjectConsumer.subscribe(Arrays.asList(this.subjectsTopic));
		}
		catch(Exception ex) {}
	}
	public Runnable microserviceInfoRunnable()
	{
		return new Runnable() {
			
            public void run() {
            	try {	
        			while(true)
        	    	{
        				RestTemplate template=new RestTemplate();
        	    		ConsumerRecords<String,String> records=microserviceInfoConsumer.poll(Duration.ofMillis(100));	
        	    		ResponseEntity<List<MicroserviceInfo>> res=template.exchange("http://192.168.99.101:7082/getAllInfo",HttpMethod.GET,null,new ParameterizedTypeReference<List<MicroserviceInfo>>(){});
            			micro.setMicroservicesInfo(res.getBody());
        	    	}
        		}
        		
        		catch(WakeupException e)
        		{
        			System.out.println(e.getMessage()+" ------WakeupException");
        		}
        		finally
        		{
        			System.out.println("UNSUBSCRIBE");
        			microserviceInfoConsumer.unsubscribe();
        			microserviceInfoConsumer.close();
        			
        		}
            }
        };
	}
	
	public Runnable subjectRunnable()
	{
		return new Runnable() {

            public void run() {
            	try {	
            		while(true)
                	{
                		ConsumerRecords<String,String> records=subjectConsumer.poll(Duration.ofMillis(100));
                		if(records.count()>0)
                		{
                			RestTemplate template=new RestTemplate();
                			ResponseEntity<List<Subject>> res=template.exchange("http://192.168.99.101:7082/getAllSubjects",HttpMethod.GET,null,new ParameterizedTypeReference<List<Subject>>(){});
                			subjectService.addNewSubjects(res.getBody());
                		}                		
                	}
        		}
        		
        		catch(Exception e)
        		{
        			System.out.println(e.getMessage());
        		}
        		finally
        		{
        			subjectConsumer.unsubscribe();
        			subjectConsumer.close();
        			
        		}
            }
        };
	}
}
