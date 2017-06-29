package com.ynm;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import com.ynm.messaging.MessagingConsumerHelper;
import com.ynm.model.Parameters;

@Service
public class GCDServiceImpl implements GCDservice {

	@Autowired
	@Qualifier("MessagingConsumerHelper")
	private MessagingConsumerHelper messagingConsumerHelper;

	@Override
	public int gcd(String apiKey) throws ShutdownSignalException,
			ConsumerCancelledException, ClassNotFoundException, IOException,
			InterruptedException {
		Parameters params = messagingConsumerHelper.fetch(apiKey);
		// calculate GCD

		return gcd(params.getParam1(), params.getParam2());
	}

	/**
	 * @param value1
	 * @param value2
	 * @return
	 */
	private int gcd(int value1, int value2) {
		while (value2 != 0) {
			int temp = value1;
			value1 = value2;
			value2 = temp % value2;
		}

		return Math.abs(value1);
	}

	@Override
	public List<Integer> gcdList(String apiKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int gcdSum(String apiKey) {
		// TODO Auto-generated method stub
		return 0;
	}

}
