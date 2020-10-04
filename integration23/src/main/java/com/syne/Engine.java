package com.syne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.Assert;

@SpringBootApplication
@Configuration
@ImportResource("ajay.xml")

public class Engine implements ApplicationRunner{
	
	@Autowired
	private PrinterGateway gateway;
	
	public static void main(String... args)
	{
		SpringApplication.run(Engine.class, args); 
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Person person[] = {new Person("Ajay", "Dhiman"), new Person("Ekta", "Dhiman")};
		for(int x = 0 ;x < person.length; x ++)
		{
			Message<?> message = MessageBuilder.withPayload(person[x])
												.setHeader("privateKey", "12345")
												.build(); 
			gateway.print(message);	
			
		}		
	}
}
