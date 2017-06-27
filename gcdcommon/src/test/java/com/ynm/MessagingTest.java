package com.ynm;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import com.ynm.messaging.MessagingConsumerHelperImpl;
import com.ynm.messaging.MessagingProducerHelper;
import com.ynm.messaging.MessagingProducerHelperImpl;
import com.ynm.model.Parameters;

/**
 * @author Yogesh.Manware
 *
 */
public class MessagingTest {

	private final static String CLOUDAMQP_URL = "amqp://zzhqxuzx:8e1bGcemvk4B4SZlP3uHDMNDVi2bKYm3@sidewinder.rmq.cloudamqp.com/zzhqxuzx";

	private static ConnectionFactory factory = null;
	private static Connection connection;
	private static Channel channel;

	@BeforeClass
	public static void setup() throws IOException, KeyManagementException,
			NoSuchAlgorithmException, URISyntaxException {
		String uri = System.getenv("CLOUDAMQP_URL");
		if (uri == null)
			uri = CLOUDAMQP_URL;

		factory = new ConnectionFactory();
		factory.setUri(uri);

		connection = factory.newConnection();
		channel = connection.createChannel();
	}

	@Test
	public void testMessaging() throws IOException, KeyManagementException,
			NoSuchAlgorithmException, URISyntaxException,
			ShutdownSignalException, ConsumerCancelledException,
			ClassNotFoundException, InterruptedException {

		MessagingProducerHelper mps = new MessagingProducerHelperImpl();
		Parameters params = new Parameters();
		params.setParam1(1);
		params.setParam2(3);
		mps.queueParameters(params, "TODO");

		MessagingConsumerHelperImpl mcs = new MessagingConsumerHelperImpl();
		Parameters paramsResult = mcs.fetch("TODO");
		assertNotNull(paramsResult);

	}

	@AfterClass
	public static void teardown() throws IOException {
		channel.close();
		connection.close();
	}
}
