package com.ynm;

import java.io.IOException;
import java.util.List;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public interface GCDservice {

	int gcd(String apiKey) throws ShutdownSignalException,
			ConsumerCancelledException, ClassNotFoundException, IOException,
			InterruptedException;

	List<Integer> gcdList(String apiKey);

	int gcdSum(String apiKey);
}
