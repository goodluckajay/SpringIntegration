package com.syne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@SpringBootApplication
@Configuration
@ImportResource("integration-def.xml")
public class Engine implements ApplicationRunner{

	@Autowired
	@Qualifier("inputChannel")
	private DirectChannel inputChannel;
	
	@Autowired
	@Qualifier("outputChannel")
	private DirectChannel outputChannel;
	
	public static void main(String... args)
	{
		SpringApplication.run(Engine.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {	
		
		// Note: outputChannel.subscibe() must be call before inputChannel.send()
		outputChannel.subscribe(new MessageHandler(){

			@Override
			public void handleMessage(Message<?> mess) throws MessagingException {
				System.out.println(mess.getPayload());		
			}
			
		});
		
		Message<String> message = MessageBuilder.withPayload("Hello Spring Integration.")
												.setHeader("Name", "Ajay Dhiman")
												.setHeader("Technology", "Java/J2ee")
												.build();
		inputChannel.send(message);
	}

}
