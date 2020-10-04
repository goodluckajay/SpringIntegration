package com.syne;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;


public class CustomRouter extends AbstractMessageRouter {
	
	@Autowired
	@Qualifier("intChannel")
	private MessageChannel intChannel;
	
	@Autowired
	@Qualifier("stringChannel")
	private MessageChannel stringChannel;

	@Override
	protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {
		
		Collection<MessageChannel> list = new ArrayList<MessageChannel>();
		
		if(message.getPayload().getClass().equals(Integer.class))
		{
			list.add(intChannel);
		}
		else if(message.getPayload().getClass().equals(String.class))
		{
			list.add(stringChannel);
		}
		
		return list;
	}

}
