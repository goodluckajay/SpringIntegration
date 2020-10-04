package com.syne;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;

public class CustomSplitter extends AbstractMessageSplitter{

	@Override
	protected Object splitMessage(Message<?> message) {
		return new ArrayList<String>(Arrays.asList(message.getPayload().toString().split(" ")));
	}
}
