package com.ynm.service;

import java.util.List;

import com.ynm.model.Parameters;

public interface ParameterService {

	void processParameters(Parameters params, String key);

	List<Parameters> getAllParameters(String string);

}
