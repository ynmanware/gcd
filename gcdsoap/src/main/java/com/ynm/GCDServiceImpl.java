package com.ynm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import com.ynm.messaging.MessagingConsumerHelper;
import com.ynm.model.Parameters;
import com.ynm.repository.GCDRepository;
import com.ynm.repository.domain.GCD;

@Service
public class GCDServiceImpl implements GCDservice {

	@Autowired
	@Qualifier("MessagingConsumerHelper")
	private MessagingConsumerHelper messagingConsumerHelper;

	@Autowired
	GCDRepository gcdRepository;

	@Override
	public int gcd(String apiKey) throws ShutdownSignalException,
			ConsumerCancelledException, ClassNotFoundException, IOException,
			InterruptedException {
		Parameters params = messagingConsumerHelper.fetch(apiKey);
		// calculate GCD
		int result = gcd(params.getParam1(), params.getParam2());
		GCD gcd = new GCD();
		gcd.setApiKey(apiKey);
		gcd.setParam1(params.getParam1());
		gcd.setParam2(params.getParam2());
		gcd.setResult(result);
		gcdRepository.updateGCDResult(gcd);

		return result;
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
	public List<GCD> gcdList(String apiKey) {
		return gcdRepository.getGCDResults(apiKey);
	}

	@Override
	public int gcdSum(String apiKey) {
		// TODO Auto-generated method stub
		return 0;
	}

}
