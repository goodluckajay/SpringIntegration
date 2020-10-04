package com.syne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
@Configuration
@ImportResource("integration-def.xml")
public class Engine implements ApplicationRunner{

	public static void main(String... args)
	{
		SpringApplication.run(Engine.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {	
		// First Way with default Headers
		Message<String> message1 = new GenericMessage("Hello Spring Integration.");
		PrintService ps1 = new PrintService();
		ps1.print(message1);
		
		// Second Way with custom Headers
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Name", "Ajay Dhiman");
		map.put("Technology", "Java/J2ee");
		Message<String> message2 = new GenericMessage("Hello Spring Integration.", map);
		PrintService ps2 = new PrintService();
		ps2.print(message2);
		
		// Third Way with custom Headers
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("Name", "Ajay Dhiman");
		map1.put("Technology", "Java/J2ee");
		MessageHeaders headers = new MessageHeaders(map);
		Message<String> message3 = new GenericMessage("Hello Spring Integration.", headers);
		PrintService ps3 = new PrintService();
		ps3.print(message3);
		
		// Fourth Way with custom Headers
		Message<String> message4 = MessageBuilder.withPayload("Hello Spring Integration.")
												.setHeader("Name", "Ajay Dhiman")
												.setHeader("Technology", "Java/J2ee")
												.build();
		PrintService ps4 = new PrintService();
		ps4.print(message4);
	}

}
