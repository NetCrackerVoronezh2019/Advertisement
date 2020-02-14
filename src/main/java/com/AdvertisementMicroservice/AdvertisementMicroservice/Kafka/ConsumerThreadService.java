package com.AdvertisementMicroservice.AdvertisementMicroservice.Kafka;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Entitys.Subject;
import com.AdvertisementMicroservice.AdvertisementMicroservice.Services.SubjectService;



@Service
public class ConsumerThreadService {

	@Autowired
	private Microservices micro;
	
	@Autowired 
	private SubjectService subjectService;
	private KafkaConsumer<String,PortModel> consumer;
	private KafkaConsumer<String,SubjectServiceX> consumer2;
	@Value("${kafka.porttopicname}")
	private String portTopicName;
	@Value("${kafka.porttopicgroup}")
	private String portTopicGroup;

	
	@PostConstruct
	public void init()
	{
		String bootstrapServers1="192.168.99.100:9092";
    	Properties properties1=new Properties();
    	properties1.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers1);
    	properties1.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
    	properties1.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,PortModelDeserializer.class.getName());
    	properties1.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
    	properties1.setProperty(ConsumerConfig.GROUP_ID_CONFIG, this.portTopicGroup);
    	this.consumer=new KafkaConsumer<String,PortModel>(properties1);
    	consumer.subscribe(Arrays.asList(this.portTopicName));
    	

    	Properties properties2=new Properties();
    	properties2.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers1);
    	properties2.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
    	properties2.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,SubjectsDeserializer.class.getName());
    	properties2.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
    	properties2.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "gk5r1dhjdSub");
    	this.consumer2=new KafkaConsumer<String,SubjectServiceX>(properties2);
    	consumer2.subscribe(Arrays.asList("subjects_topic"));
	}
	public Runnable getRunnable()
	{
		return new Runnable() {
			
            public void run() {
            	try {	
        			while(true)
        	    	{
        	    		ConsumerRecords<String,PortModel> records=consumer.poll(Duration.ofMillis(100));	
        	    		for(ConsumerRecord<String,PortModel> recordx:records)
        	    		{
        	    		  
        	    			  if(recordx.value().microServiceName==MicroservicesEnum.ADVERTISMENT)
          	    				micro.setAdvertismentmicroserviceport(recordx.value().port);
          	    			else {
          	    				if(recordx.value().microServiceName==MicroservicesEnum.MAIN)
          	    					micro.setMainmicroserviceport(recordx.value().port);
          	    			}
        	    		  
        	    		}
        	    	}
        		}
        		
        		catch(WakeupException e)
        		{
        			System.out.println(e.getMessage()+" ------WakeupException");
        		}
        		finally
        		{
        			consumer.close();
        			
        		}
            }
        };
	}
	
	public Runnable getRunnable2()
	{
		return new Runnable() {

            public void run() {
            	try {	
            		while(true)
                	{
                		ConsumerRecords<String,SubjectServiceX> records=consumer2.poll(Duration.ofMillis(100));
                		for(ConsumerRecord<String,SubjectServiceX> recordx:records)
                		{
                			subjectService.addNewSubjects(recordx.value().subjects);
                		}
                	}
        		}
        		
        		catch(Exception e)
        		{
        			System.out.println(e.getMessage());
        		}
        		finally
        		{
        			consumer2.close();
        			
        		}
            }
        };
	}
}
