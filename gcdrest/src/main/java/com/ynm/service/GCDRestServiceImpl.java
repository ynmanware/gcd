package com.ynm.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ynm.exception.ParameterPersistException;
import com.ynm.exception.ParameterQueueException;
import com.ynm.messaging.MessagingProducerHelper;
import com.ynm.model.Parameters;
import com.ynm.repository.GCDRepository;
import com.ynm.repository.GCDRepositoryStub;

@Service
public class GCDRestServiceImpl implements GCDRestService {

	@Autowired
	@Qualifier("MessagingProducerHelper")
	private MessagingProducerHelper messagingHelper;

	GCDRepository parameterRepository = new GCDRepositoryStub();

	@Override
	public String processParameters(Parameters params, String key) {
		// persist
		try {
			parameterRepository.persistParameters(params);
		} catch (Exception e) {
			throw new ParameterPersistException(
					"Error occurred while persisting message");
		}

		// publish to Q
		try {
			return messagingHelper.queueParameters(params, key);
		} catch (IOException e) {
			throw new ParameterQueueException(
					"Error occurred while queueing message");
		}
	}

	@Override
	public List<Parameters> getAllParameters(String key) {
		return parameterRepository.getAllParameters(key);
	}
}