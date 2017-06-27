package com.ynm.service;

import java.io.IOException;
import java.util.List;

import com.ynm.exception.ParameterPersistException;
import com.ynm.exception.ParameterQueueException;
import com.ynm.messaging.MessagingProducerHelper;
import com.ynm.messaging.MessagingProducerHelperImpl;
import com.ynm.model.Parameters;
import com.ynm.repository.ParametersRepository;
import com.ynm.repository.ParametersRepositoryStub;

public class ParameterServiceImpl implements ParameterService {

	private MessagingProducerHelper messagingHelper = new MessagingProducerHelperImpl();
	ParametersRepository parameterRepository = new ParametersRepositoryStub();

	@Override
	public void processParameters(Parameters params, String key) {
		// publish to Q
		try {
			messagingHelper.queueParameters(params, key);
		} catch (IOException e) {
			throw new ParameterQueueException(
					"Error occurred while queueing message");
		}
		// persist
		try {
			parameterRepository.persistParameters(params);
		} catch (Exception e) {
			throw new ParameterPersistException(
					"Error occurred while persisting message");
		}
	}

	@Override
	public List<Parameters> getAllParameters(String key) {
		return parameterRepository.getAllParameters(key);
	}
}
