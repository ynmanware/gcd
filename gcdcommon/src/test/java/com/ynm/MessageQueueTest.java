package com.ynm;

import java.io.IOException;

import javax.jms.JMSException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ynm.messaging.MessageQueueHandler;
import com.ynm.model.Parameters;

/**
 * @author Yogesh.Manware
 *
 */
public class MessageQueueTest {

	private static String TEST_QUEUE = "TEST_QUEUE";

	private static MessageQueueHandler mq = new MessageQueueHandler();

	static int i = 10;

	private static Logger logger = LoggerFactory
			.getLogger(MessageQueueTest.class);

	@AfterClass
	// clear the q
	public static void clearQueue() throws JMSException {
		logger.info("Clearing queue");

		while (null != mq.fetch(TEST_QUEUE)) {

		}
		i = 10;
	}

	public void ProduceMessages() throws JMSException, IOException {
		Parameters params = new Parameters();
		params.setParam1(i);
		params.setParam2(i + 15);
		mq.queueParameters(params, TEST_QUEUE);
		i = i + 5;
	}

	@Test
	public void TestWithOneMessages() throws JMSException, IOException {
		ProduceMessages();
		
		Parameters param = consumeMessages();
		
		Assert.assertTrue(param.getParam1() == 10);
		Assert.assertTrue(param.getParam2() == 25);
	}

	@Test
	public void TestWithTwoMessages() throws JMSException, IOException, InterruptedException {
		ProduceMessages();
		ProduceMessages();

		Thread.sleep(100);
		Parameters param = consumeMessages();
		Assert.assertTrue(param.getParam1() == 10);
		Assert.assertTrue(param.getParam2() == 25);

		param = consumeMessages();
		Assert.assertTrue(param.getParam1() == 15);
		Assert.assertTrue(param.getParam2() == 30);
	}

	public Parameters consumeMessages() throws JMSException {
		return mq.fetch(TEST_QUEUE);
	}
}
