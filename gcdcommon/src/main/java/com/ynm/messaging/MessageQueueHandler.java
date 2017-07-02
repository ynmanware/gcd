package com.ynm.messaging;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.stereotype.Service;

import com.ynm.model.Parameters;

@Service
public class MessageQueueHandler {

	private static final String MQ_URL = "tcp://localhost:8008";

	private static BrokerService broker = null;

	static {
		try {
			broker = BrokerFactory.createBroker(new URI("broker:(MQ_URL)"
					.replace("MQ_URL", MQ_URL)));
			broker.start();
		} catch (Exception e) {

		}
	}

	private Connection connection = null;

	public MessageQueueHandler() {
		connection = null;
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				MQ_URL);

		((ActiveMQConnectionFactory) connectionFactory)
				.setTrustedPackages(new ArrayList(Arrays.asList("com.ynm.model"
						.split(","))));

		try {
			connection = connectionFactory.createConnection();
			connection.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void queueParameters(Parameters params, String key)
			throws JMSException, IOException {
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(key);
		Message msg = session.createObjectMessage(params);
		MessageProducer producer = session.createProducer(queue);
		producer.send(msg);
		session.close();
	}

	public Parameters fetch(String key) throws JMSException {
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue(key);
		MessageConsumer consumer = session.createConsumer(queue);
		ObjectMessage df = (ObjectMessage) consumer.receive(20);
		if (null == df) {
			return null;
		}
		Parameters params = (Parameters) df.getObject();
		session.close();
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