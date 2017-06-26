package com.ynm.messaging;

import com.ynm.model.Parameters;

/**
 * @author Yogesh.Manware
 *
 */
public interface MessagingProducerHelper {

	void queueParameters(Parameters params, String key);
}
