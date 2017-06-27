package com.ynm.messaging;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import com.ynm.model.Parameters;

/**
 * @author Yogesh.Manware
 *
 */
public class MessagingConsumerHelperImpl implements MessagingConsumerHelper {

	private final static String CLOUDAMQP_URL = "amqp://zzhqxuzx:8e1bGcemvk4B4SZlP3uHDMNDVi2bKYm3@sidewinder.rmq.cloudamqp.com/zzhqxuzx";

	private ConnectionFactory factory = null;

	public MessagingConsumerHelperImpl() throws KeyManagementException,
			NoSuchAlgorithmException, URISyntaxException {
		String uri = System.getenv("CLOUDAMQP_URL");
		if (uri == null)
			uri = CLOUDAMQP_URL;

		factory = new ConnectionFactory();
		factory.setUri(uri);
	}

	public static void main(String[] args) throws KeyManagementException,
			NoSuchAlgorithmException, URISyntaxException, ShutdownSignalException, ConsumerCancelledException, ClassNotFoundException, IOException, InterruptedException {
		MessagingConsumerHelperImpl ms = new MessagingConsumerHelperImpl();
		Parameters params = ms.fetch("TODO");

		System.out.println(params.getParam2());
	}

	public Parameters fetch(String key) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException, ClassNotFoundException {
		Parameters params = null;

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		String queue = key; // queue name

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queue, true, consumer);
		QueueingConsumer.Delivery delivery = consumer.nextDelivery();
		params = deserialize(delivery.getBody());

		channel.close();
		connection.close();
		
		return params;
	}

	public static Parameters deserialize(byte[] bytes) throws IOException,
			ClassNotFoundException {
		try (ByteArrayInputStream b = new ByteArrayInputStream(bytes)) {
			try (ObjectInputStream o = new ObjectInputStream(b)) {
				return (Parameters) o.readObject();
			}
		}
	}
}
