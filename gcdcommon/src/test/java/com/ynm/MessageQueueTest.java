package com.ynm;

import java.io.IOException;

import javax.jms.JMSException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ynm.messaging.MessageQueueHandler;
import com.ynm.model.Parameters;

/**
 * @author Yogesh.Manware
 *
 */
public class MessageQueueTest {

	static int i = 10;

	 private static Logger logger = LoggerFactory.getLogger(MessageQueueTest.class);
	 
	
	@Autowired
	@Test
	public void ProduceMessages() throws JMSException, IOException {
		
		MessageQueueHandler mq = new MessageQueueHandler();

		i = i + 5;

		Parameters params = new Parameters();
		params.setParam1(i);
		params.setParam2(i + 10);

		mq.queueParameters(params, "TEST_QUEUE");
		// consumer
		/*		
*/}

	@Test
	public void TestWithTwoMessages() throws JMSException, IOException {
		ProduceMessages();
		ProduceMessages();

		consumeMessages();
		consumeMessages();

	}

	@Test
	public void consumeMessages() throws JMSException {
		MessageQueueHandler mq = new MessageQueueHandler();

		Parameters params = mq.fetch("TEST_QUEUE");
		System.out.println("PARAM1 : " + params.getParam1());
	}
}
