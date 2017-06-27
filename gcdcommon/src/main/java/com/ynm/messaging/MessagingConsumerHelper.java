package com.ynm.messaging;

import java.io.IOException;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
import com.ynm.model.Parameters;

/**
 * @author Yogesh.Manware
 *
 */
public interface MessagingConsumerHelper {
	Parameters fetch(String key) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException, ClassNotFoundException;
}
