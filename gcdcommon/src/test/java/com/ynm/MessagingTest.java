package com.ynm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
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

		channel = connection.createChannel();
	}

	public static void main(String[] args) {
		try {
			String key = "Test1";

			// ProduceMessages(key);
			consumeMessages(key);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public static void ProduceMessages(String key) throws IOException,
			ShutdownSignalException, ConsumerCancelledException,
			InterruptedException, ClassNotFoundException,
			KeyManagementException, NoSuchAlgorithmException,
			URISyntaxException {

		Parameters params = new Parameters();
		params.setParam1(5);
		params.setParam2(3);

		factory = new ConnectionFactory();
		factory.setUri(CLOUDAMQP_URL);

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
		// channel.basicPublish(exchangeName, key, null, serialize(params));

		channel.close();
		connection.close();

		// consumer
		/*		
*/}

	private static void consumeMessages(String key) throws IOException,
			ShutdownSignalException, ConsumerCancelledException,
			InterruptedException, ClassNotFoundException,
			KeyManagementException, NoSuchAlgorithmException,
			URISyntaxException {

		factory = new ConnectionFactory();
		factory.setUri(CLOUDAMQP_URL);
		connection = factory.newConnection();

		Channel channel2 = connection.createChannel();
		channel2.basicQos(1);
		channel.queueDeclare(key, false, false, false, null);
		QueueingConsumer consumer = new QueueingConsumer(channel2);
		channel2.basicConsume(key, true, consumer);
		QueueingConsumer.Delivery delivery = consumer.nextDelivery(10);
		Parameters params = deserialize(delivery.getBody());

		System.out.println(params.getParam1());
		channel2.close();
		connection.close();

	}

	public static byte[] serialize(Parameters params) throws IOException {
		try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
			try (ObjectOutputStream o = new ObjectOutputStream(b)) {
				o.writeObject(params);
			}
			return b.toByteArray();
		}
	}

	public static Parameters deserialize(byte[] bytes) throws IOException,
			ClassNotFoundException {
		try (ByteArrayInputStream b = new ByteArrayInputStream(bytes)) {
			try (ObjectInputStream o = new ObjectInputStream(b)) {
				return (Parameters) o.readObject();
			}
		}
	}

	@Test
	public void testMessaging() throws IOException, KeyManagementException,
			NoSuchAlgorithmException, URISyntaxException,
			ShutdownSignalException, ConsumerCancelledException,
			ClassNotFoundException, InterruptedException {

	}

	@AfterClass
	public static void teardown() throws IOException {
		channel.close();
		connection.close();
	}
}
