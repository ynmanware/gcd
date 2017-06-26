package com.ynm.repository;

import java.util.List;

import com.ynm.exception.ParameterPersistException;
import com.ynm.model.Parameters;

public interface ParametersRepository {

	void persistParameters(Parameters params)
			throws ParameterPersistException;

	List<Parameters> getAllParameters(String key);
}
