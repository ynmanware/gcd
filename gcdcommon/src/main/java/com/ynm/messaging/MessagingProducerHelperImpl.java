package com.ynm.messaging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.ynm.model.Parameters;

@Service("MessagingProducerHelper")
public class MessagingProducerHelperImpl implements MessagingProducerHelper {

	private final static String CLOUDAMQP_URL = "amqps://zzhqxuzx:8e1bGcemvk4B4SZlP3uHDMNDVi2bKYm3@sidewinder.rmq.cloudamqp.com/zzhqxuzx";

	private ConnectionFactory factory = null;

	public MessagingProducerHelperImpl() {
		String uri = System.getenv("CLOUDAMQP_URL");
		if (uri == null)
			uri = CLOUDAMQP_URL;

		factory = new ConnectionFactory();
		try {
			factory.setUri(uri);
		} catch (KeyManagementException | NoSuchAlgorithmException
				| URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException,
			KeyManagementException, NoSuchAlgorithmException,
			URISyntaxException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setRequestedHeartbeat(30);
		factory.setConnectionTimeout(30000);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
	}

	@Override
	public void queueParameters(Parameters params, String key)
			throws IOException {
		// Recommended settings
		factory.setRequestedHeartbeat(30);
		factory.setConnectionTimeout(30000);

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		boolean durable = false; // durable - RabbitMQ will never lose the
									// queue
									// if a crash occurs
		boolean exclusive = false; // exclusive - if queue only will be used
									// by
									// one connection
		boolean autoDelete = false; // autodelete - queue is deleted when
									// last
									// consumer unsubscribes

		channel.queueDeclare(key, durable, exclusive, autoDelete, null);

		String exchangeName = "";
		channel.basicPublish(exchangeName, key, null, serialize(params));
	}

	/**
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static byte[] serialize(Parameters params) throws IOException {
		try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
			try (ObjectOutputStream o = new ObjectOutputStream(b)) {
				o.writeObject(params);
			}
			return b.toByteArray();
		}
	}
}
