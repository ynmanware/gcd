package com.ynm.repository;

import java.util.List;

import com.ynm.model.Parameters;

public interface ParametersRepository {

	void persistParameters(Parameters params) throws Exception;

	List<Parameters> getAllParameters(String key);
}