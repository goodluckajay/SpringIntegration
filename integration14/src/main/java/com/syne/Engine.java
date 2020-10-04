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


// Note: End point - Router example
@SpringBootApplication
@Configuration
@ImportResource("integration-def.xml")
public class Engine implements ApplicationRunner{
	
	@Autowired
	private PrinterGateway gateway;
	
	public static void main(String... args)
	{
		SpringApplication.run(Engine.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for(int x = 0 ;x < 10; x ++)
		{
			//Message<String> message = MessageBuilder.withPayload("Spring Integration:" + x).build();
			Message<?> message = MessageBuilder.withPayload(x).build(); // comment this and un-comment above line
			gateway.print(message);			
		}		
	}
}
