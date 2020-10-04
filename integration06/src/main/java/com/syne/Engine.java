package com.syne;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

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
		
		List<Future<Message<String>>> list = new ArrayList<Future<Message<String>>>();

		for(int i = 0 ; i < 10 ; i++)
		{
			Message<String> message = MessageBuilder.withPayload("printing message pay load for : " + i)
													.setHeader("MessageNumber", i)
													.build();
			System.out.println("sending message : " + i);
			list.add(gateway.print(message));			
		}
		for(Future<Message<String>> future : list)
		{
			System.out.println(future.get().getPayload());
		}
	}

}
