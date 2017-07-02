package com.ynm.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynm.UserController;
import com.ynm.exception.ParameterPersistException;
import com.ynm.exception.ParameterQueueException;
import com.ynm.messaging.MessageQueueHandler;
import com.ynm.model.Parameters;
import com.ynm.repository.GCDRepository;

@Service
public class GCDRestService {

	@Autowired
	private MessageQueueHandler messageQueueHandler;

	@Autowired
	GCDRepository gcdRepository;

	@Autowired
	private UserController userController;

	public String processParameters(Parameters params, String key) {
		// persist
		if (key == null) {
			key = UUID.randomUUID().toString().replaceAll("\\s+", "");
		}
		
		userController.waitAndGo(key);
		
		try {
			gcdRepository.persistParameters(params, key);
		} catch (Exception e) {
			throw new ParameterPersistException(
					"Error occurred while persisting message");
		}

		pushToQueue(params, key);

		return key;
	}

	public List<Parameters> getAllParameters(String key) {
		userController.waitAndGo(key);
		return gcdRepository.getAllParameters(key);
	}

	private void pushToQueue(Parameters params, String key) {
		try {
			messageQueueHandler.queueParameters(params, key);
		} catch (IOException | JMSException e) {
			throw new ParameterQueueException(
					"Error occurred while queueing message");
		}
	}
}
