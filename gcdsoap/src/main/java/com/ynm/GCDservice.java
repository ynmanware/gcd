package com.ynm;

import java.io.IOException;
import java.util.List;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import com.ynm.repository.domain.GCD;

public interface GCDservice {

	int gcd(String apiKey) throws ShutdownSignalException,
			ConsumerCancelledException, ClassNotFoundException, IOException,
			InterruptedException;

	List<GCD> gcdList(String apiKey);

	int gcdSum(String apiKey);
}
