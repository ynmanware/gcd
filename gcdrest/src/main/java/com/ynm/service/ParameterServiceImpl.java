package com.ynm.service;

import java.util.List;

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
		messagingHelper.queueParameters(params, key);
		// persist
		parameterRepository.persistParameters(params);
	}

	@Override
	public List<Parameters> getAllParameters(String key) {
		return parameterRepository.getAllParameters(key);
	}
}
