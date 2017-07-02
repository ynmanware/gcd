package com.ynm.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ynm.exception.ParameterPersistException;
import com.ynm.exception.ParameterQueueException;
import com.ynm.messaging.MessagingProducerHelper;
import com.ynm.model.Parameters;
import com.ynm.repository.GCDRepository;

@Service
public class GCDRestServiceImpl implements GCDRestService {

	@Autowired
	@Qualifier("MessagingProducerHelper")
	private MessagingProducerHelper messagingHelper;

	@Autowired
	GCDRepository gcdRepository;

	@Override
	public String processParameters(Parameters params, String key) {
		// persist
		if (key == null) {
			key = UUID.randomUUID().toString().replaceAll("\\s+", "");
		}
		try {
			gcdRepository.persistParameters(params, key);
		} catch (Exception e) {
			throw new ParameterPersistException(
					"Error occurred while persisting message");
		}
		
		pushToQueue(params, key);

		return key;
	}

	@Override
	public List<Parameters> getAllParameters(String key) {
		return gcdRepository.getAllParameters(key);
	}

	private void pushToQueue(Parameters params, String key) {
		try {
			messagingHelper.queueParameters(params, key);
		} catch (IOException e) {
			throw new ParameterQueueException(
					"Error occurred while queueing message");
		}
	}
}
