package com.ynm.messaging;

import java.io.IOException;

import com.ynm.model.Parameters;

/**
 * @author Yogesh.Manware
 *
 */
public interface MessagingProducerHelper {

	String queueParameters(Parameters params, String key) throws IOException;
}
